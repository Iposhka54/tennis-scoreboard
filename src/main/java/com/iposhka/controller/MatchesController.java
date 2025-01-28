package com.iposhka.controller;

import com.iposhka.dto.MatchDto;
import com.iposhka.service.FinishedMatchesPersistenceService;
import com.iposhka.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesController extends HttpServlet {
    private final FinishedMatchesPersistenceService matchesService = FinishedMatchesPersistenceService.getInstance();
    private static final int COUNT_MATCHES_ON_PAGE = 5;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        String filter = req.getParameter("filter_by_player_name");

        int page = pageStr == null ? 1 : Integer.parseInt(pageStr);

        List<MatchDto> matches = matchesService.findAll(filter, COUNT_MATCHES_ON_PAGE, page);
        long countMatches = matchesService.count(filter);

        int countPages = (int) Math.ceil((double)countMatches / COUNT_MATCHES_ON_PAGE);

        req.setAttribute("matches", matches);
        req.setAttribute("currentPage", page);
        req.setAttribute("countPages", countPages);

        req.getRequestDispatcher(JspHelper.getPath("matches")).forward(req, resp);
    }
}
