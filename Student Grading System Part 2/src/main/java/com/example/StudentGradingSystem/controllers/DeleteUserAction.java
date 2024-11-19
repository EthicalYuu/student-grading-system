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

@WebServlet("/DeleteUserAction")
public class DeleteUserAction extends HttpServlet {
    RequestDispatcher dispatcher;
    GradingSystemDao systemDb;

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        try {
            String message = "";
            HttpSession session = req.getSession();

            int userId = Integer.parseInt(req.getParameter("userId"));
            String password = req.getParameter("password");

            systemDb = (GradingSystemDao) session.getAttribute("dbInstance");

            boolean userExists = systemDb.userExists(userId);
            boolean correctPass = systemDb.correctPass(userId, password);

            if(!userExists){
                message = "User does not exist";
            } else if (!correctPass){
                message = "Incorrect Password";
            } else {
                systemDb.deleteRoleByUserId(userId);
                systemDb.deleteUserById(userId);
                message = "User deleted successfully";
            }

            req.setAttribute("message", message);

            dispatcher = req.getRequestDispatcher("delete-user-page.jsp");
            dispatcher.forward(req, res);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

