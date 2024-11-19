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

@WebServlet("/CreateUserAction")
public class CreateUserAction extends HttpServlet {
    RequestDispatcher dispatcher;
    GradingSystemDao systemDb;

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        try {
            String message = "";
            HttpSession session = req.getSession();

            int userId = Integer.parseInt(req.getParameter("userId"));
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String roleName = req.getParameter("roleName");
            String password = req.getParameter("password");

            systemDb = (GradingSystemDao) session.getAttribute("dbInstance");

            boolean userExists = systemDb.userExists(userId);

            if(userExists){
                message = "User already exists";
            } else {
                systemDb.createUser(userId, firstName, lastName);
                systemDb.createRole(userId, roleName, password);
                message = "User created successfully";
            }

            req.setAttribute("message", message);

            dispatcher = req.getRequestDispatcher("create-user-page.jsp");
            dispatcher.forward(req, res);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

