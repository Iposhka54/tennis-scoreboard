package com.iposhka.controller;

import com.iposhka.exception.InvalidDataException;
import com.iposhka.util.JspHelper;
import com.iposhka.util.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/new-match")
public class NewMatchController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("new-match")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.removeAttribute("error");
            String firstPlayer = req.getParameter("firstPlayer");
            String secondPlayer = req.getParameter("secondPlayer");
            Validator.checkNames(firstPlayer, secondPlayer);
        }catch(InvalidDataException e){
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher(JspHelper.getPath("new-match")).forward(req, resp);
        }
    }
}
