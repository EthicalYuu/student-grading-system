package com.example.StudentGradingSystem.controllers;
import com.example.StudentGradingSystem.models.GradingSystemDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    RequestDispatcher dispatcher;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        try {

            dispatcher = req.getRequestDispatcher("delete-user-page.jsp");
            dispatcher.forward(req, res);

        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

    }
}