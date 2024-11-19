package com.example.StudentGradingSystem.controllers;
import com.example.StudentGradingSystem.models.GradingSystemDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {
    RequestDispatcher dispatcher;
    GradingSystemDao systemDb;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        try {

            dispatcher = req.getRequestDispatcher("create-user-page.jsp");
            dispatcher.forward(req, res);

        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

    }
}
