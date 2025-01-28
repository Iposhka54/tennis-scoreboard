package com.iposhka.controller;

import com.iposhka.exception.MatchNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class ExceptionFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        try {
            super.doFilter(req, res, chain);
        }catch (MatchNotFoundException e){
            req.getSession().setAttribute("error", e.getMessage());
            res.sendRedirect(req.getContextPath() + "/new-match");
        }catch (Exception e){
            res.sendRedirect(req.getContextPath());
        }
    }
}
