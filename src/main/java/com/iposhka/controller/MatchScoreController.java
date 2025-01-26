package com.iposhka.controller;

import com.iposhka.dto.OngoingMatch;
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidStr = req.getParameter("uuid");
        UUID uuid = UUID.fromString(uuidStr);

        OngoingMatch match = ongoingMatchesService.findMatchByUUID(uuid);

        req.setAttribute("match", match);
        req.getRequestDispatcher(JspHelper.getPath("match-score")).forward(req, resp);
    }
}
