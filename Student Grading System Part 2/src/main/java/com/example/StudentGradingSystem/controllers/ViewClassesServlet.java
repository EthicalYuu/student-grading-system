package com.example.StudentGradingSystem.controllers;

import com.example.StudentGradingSystem.models.Classroom;
import com.example.StudentGradingSystem.models.Course;
import com.example.StudentGradingSystem.models.CourseGrade;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet("/ViewClassesServlet")

public class ViewClassesServlet extends HttpServlet {

    RequestDispatcher dispatcher;

    GradingSystemDao systemDb;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {

        try {
            HttpSession session = req.getSession();
            int username = (int) session.getAttribute("username");

            systemDb = (GradingSystemDao) session.getAttribute("dbInstance");

            List<Course> courseList= null;

            courseList = systemDb.getCourses(username);

//            req.setAttribute("courseList", courseList);

            ArrayList<List<CourseGrade>> gradeListList = new ArrayList<>();
            ArrayList<Classroom> classrooms = new ArrayList<>();
            List<Integer> grades = new ArrayList<>();
            for(Course course: courseList){
                List<CourseGrade> gradeList = systemDb.getGradesOfCourse(course.getCourseId());
                for(CourseGrade courseGrade: gradeList){
                    grades.add(courseGrade.getScore());
                }
                Classroom classroom = new Classroom(
                        Collections.min(grades),
                        Collections.max(grades)
                );
                classrooms.add(classroom);
                gradeListList.add(gradeList);
            }



            req.setAttribute("gradeListList", gradeListList);
            req.setAttribute("classrooms", classrooms);


            dispatcher = req.getRequestDispatcher("teacher-view-page.jsp");
            dispatcher.forward(req, res);

        } catch (SQLException | ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
