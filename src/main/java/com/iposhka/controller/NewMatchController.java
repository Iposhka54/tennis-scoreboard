package com.iposhka.controller;

import com.iposhka.dto.PlayerDto;
import com.iposhka.exception.InvalidDataException;
import com.iposhka.service.OngoingMatchesService;
import com.iposhka.service.PlayerService;
import com.iposhka.util.JspHelper;
import com.iposhka.util.NameFormatter;
import com.iposhka.util.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchController extends HttpServlet {
    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();
    private final PlayerService playerService = PlayerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("new-match")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getSession().removeAttribute("error");

            String firstPlayer = req.getParameter("firstPlayer");
            String secondPlayer = req.getParameter("secondPlayer");

            Validator.checkNames(firstPlayer, secondPlayer);

            String player1 = NameFormatter.formatName(firstPlayer);
            String player2 = NameFormatter.formatName(secondPlayer);

            PlayerDto playerFirst = playerService.findOrCreateByName(player1);
            PlayerDto playerSecond = playerService.findOrCreateByName(player2);

            UUID id = ongoingMatchesService.createMatch(playerFirst, playerSecond);

            resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + id);
        }catch(InvalidDataException e){
            req.getSession().setAttribute("error", e.getMessage());
            req.getRequestDispatcher(JspHelper.getPath("new-match")).forward(req, resp);
        }
    }
}
