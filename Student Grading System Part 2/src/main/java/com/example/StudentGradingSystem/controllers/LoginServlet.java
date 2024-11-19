package com.example.StudentGradingSystem.controllers;
import com.example.StudentGradingSystem.models.GradingSystemDao;
import com.example.StudentGradingSystem.models.StudentGrade;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private GradingSystemDao systemDb;


    public LoginServlet(){
        this.systemDb = GradingSystemDao.getInstance();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int userId = Integer.parseInt(req.getParameter("username"));
        String password = req.getParameter("password");

        if(systemDb.check(userId, password)){

            HttpSession session = req.getSession();
            session.setAttribute("dbInstance", systemDb);
            session.setAttribute("username", userId);

        } else {
            res.sendRedirect("login.jsp");
        }

        try {

            RequestDispatcher dispatcher = null;

            String role = systemDb.getRole(userId);
            req.setAttribute("role", role);

            if (role.equalsIgnoreCase("student")) {
                List<StudentGrade> gradeList = systemDb.getGradesOfStudent(userId);
                req.setAttribute("gradeList", gradeList);
                dispatcher = req.getRequestDispatcher("student-page.jsp");
            } else if (role.equalsIgnoreCase("teacher")){
                dispatcher = req.getRequestDispatcher("teacher-page-main.jsp");
            } else if (role.equalsIgnoreCase("admin")) {
                dispatcher = req.getRequestDispatcher("admin-page.jsp");
            }

            dispatcher.forward(req, res);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

