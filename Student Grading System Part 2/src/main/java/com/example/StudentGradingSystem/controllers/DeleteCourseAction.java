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

@WebServlet("/DeleteCourseAction")
public class DeleteCourseAction extends HttpServlet {
    RequestDispatcher dispatcher;
    GradingSystemDao systemDb;

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        try {
            String message = "";
            HttpSession session = req.getSession();

            int courseId = Integer.parseInt(req.getParameter("courseId"));

            systemDb = (GradingSystemDao) session.getAttribute("dbInstance");

            boolean courseExists = systemDb.courseExistsById(courseId);

            if(!courseExists){
                message = "No such course";
            } else {
                systemDb.deleteCourseById(courseId);
                message = "Course deleted successfully";
            }

            req.setAttribute("message", message);

            dispatcher = req.getRequestDispatcher("delete-course-page.jsp");
            dispatcher.forward(req, res);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        res.sendRedirect("login.jsp");
    }
}

