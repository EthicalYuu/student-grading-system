package com.example.StudentGradingSystem;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class NewServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, IOException {
        PrintWriter out = res.getWriter();
        out.print("Hi");
        ServletContext ctx = req.getServletContext();
        String str1 = ctx.getInitParameter("name");
        String str2 = ctx.getInitParameter("name");
        out.println(str1);
        out.println(str2);
    }

}
