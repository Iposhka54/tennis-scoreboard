package com.iposhka.controller;

import com.iposhka.dto.MatchDto;
import com.iposhka.dto.OngoingMatch;
import com.iposhka.service.FinishedMatchesPersistenceService;
import com.iposhka.service.MatchScoreCalculationService;
import com.iposhka.service.OngoingMatchesService;
import com.iposhka.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {
    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();
    private final MatchScoreCalculationService matchScoreCalculationService = MatchScoreCalculationService.getInstance();
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = FinishedMatchesPersistenceService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidStr = req.getParameter("uuid");
        UUID uuid = UUID.fromString(uuidStr);

        OngoingMatch match = ongoingMatchesService.findMatchByUUID(uuid);
        ongoingMatchesService.put(match);

        req.setAttribute("match", match);
        req.getRequestDispatcher(JspHelper.getPath("match-score")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidStr = req.getParameter("uuid");
        String winnerIdStr = req.getParameter("winnerId");
        UUID uuid = UUID.fromString(uuidStr);
        Integer winnerId = Integer.valueOf(winnerIdStr);

        OngoingMatch match = ongoingMatchesService.findMatchByUUID(uuid);
        matchScoreCalculationService.calculateScore(match, winnerId);

        if(matchScoreCalculationService.isMatchOver(match)){
            MatchDto finishedMatch = finishedMatchesPersistenceService.save(match);
            //redirect on matches
        }
        else{
            ongoingMatchesService.put(match);
            resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + uuidStr);
        }
    }
}