package spring.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spring.CourseRepo;
import spring.entity.Course;
import spring.entity.Grade;
import spring.service.CourseService;
import spring.service.GradeService;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    GradeService gradeService;

    @GetMapping("")
    public ModelAndView viewGrades(HttpSession session){

        ModelAndView modelAndView;

        if(!session.getAttribute("roleName").toString().equalsIgnoreCase("student")){
            modelAndView = new ModelAndView("login-page");
            return modelAndView;
        }

        modelAndView = new ModelAndView("student-page");

        String username = (String) session.getAttribute("username");

        List<Grade> gradeList = gradeService.getGradesByStudentId(Integer.parseInt(username));
        modelAndView.addObject("gradeList", gradeList);

        return modelAndView;
    }

}
