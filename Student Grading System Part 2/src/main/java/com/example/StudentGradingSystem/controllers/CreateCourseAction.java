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

@WebServlet("/CreateCourseAction")
public class CreateCourseAction extends HttpServlet {
    RequestDispatcher dispatcher;
    GradingSystemDao systemDb;

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        try {
            String message = "";
            HttpSession session = req.getSession();

            String courseName = req.getParameter("courseName");
            int teacherId = Integer.parseInt(req.getParameter("teacherId"));

            systemDb = (GradingSystemDao) session.getAttribute("dbInstance");

            boolean courseExist = systemDb.courseExists(courseName);

            if(courseExist){
                message = "Course with the same name already exists";
            } else {
                systemDb.createCourse(courseName, teacherId);
                message = "Course created successfully";
            }

            req.setAttribute("message", message);

            dispatcher = req.getRequestDispatcher("create-course-page.jsp");
            dispatcher.forward(req, res);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

