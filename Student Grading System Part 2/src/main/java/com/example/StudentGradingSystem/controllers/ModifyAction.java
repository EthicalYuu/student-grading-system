package com.example.StudentGradingSystem.controllers;
import com.example.StudentGradingSystem.models.GradingSystemDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ModifyAction")
public class ModifyAction extends HttpServlet {
    RequestDispatcher dispatcher;
    GradingSystemDao systemDb;

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        try {
            String message = "";
            HttpSession session = req.getSession();
            int username = (int) session.getAttribute("username");
            int courseId = Integer.parseInt(req.getParameter("courseId"));
            int studentId = Integer.parseInt(req.getParameter("userId"));
            int newscore = Integer.parseInt(req.getParameter("newScore"));

            systemDb = (GradingSystemDao) session.getAttribute("dbInstance");

            if(systemDb.isValidCourse(courseId, username)){
                systemDb.modifyGrades(courseId, studentId, newscore);
                message = "Grade modified successfully";
            } else {
                message = "Invalid course";
            }

            req.setAttribute("message", message);

            dispatcher = req.getRequestDispatcher("/ModifyServlet");
            dispatcher.forward(req, res);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

