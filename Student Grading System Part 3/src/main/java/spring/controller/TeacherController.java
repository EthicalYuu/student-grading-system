package spring.controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spring.entity.Course;
import spring.entity.Grade;
import spring.service.CourseService;
import spring.service.GradeService;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    GradeService gradeService;

    @Autowired
    CourseService courseService;

    @GetMapping("")
    public ModelAndView teacherMainPage(HttpSession session){

        ModelAndView modelAndView;

        if(!session.getAttribute("roleName").toString().equalsIgnoreCase("teacher")){
            modelAndView = new ModelAndView("login-page");
        } else {
            modelAndView = new ModelAndView("teacher-page-main");
        }

        return modelAndView;
    }

    @GetMapping("/modify-page")
    public ModelAndView teacherModifyPage(HttpSession session){

        ModelAndView modelAndView;

        if(!session.getAttribute("roleName").toString().equalsIgnoreCase("teacher")){
            modelAndView = new ModelAndView("login-page");
        } else {
            modelAndView = new ModelAndView("teacher-page-modify");
        }

        return modelAndView;
    }

    @GetMapping("/view-page")
    public ModelAndView viewGrades(HttpSession session){

        ModelAndView modelAndView;

        if(!session.getAttribute("roleName").toString().equalsIgnoreCase("teacher")){
            modelAndView = new ModelAndView("login-page");
            return modelAndView;
        }

        String username = (String) session.getAttribute("username");

        List<Course> courseList = courseService.getTeacherCourseList(Integer.parseInt(username));

        ArrayList<List<Grade>> listOfGradeList = new ArrayList<>();

        for(Course course: courseList){
            List<Grade> gradeList = gradeService.getGradesByCourseId(course.getCourseId());
            listOfGradeList.add(gradeList);
        }

        modelAndView = new ModelAndView("teacher-view-page");

        modelAndView.addObject("listOfGradeList", listOfGradeList);

        return modelAndView;
    }

    @PostMapping("/modify")
    public void modifyGrade(HttpSession session, HttpServletRequest req, HttpServletResponse res){
        String username = (String)session.getAttribute("username");

        int courseId = Integer.parseInt(req.getParameter("courseId"));
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        double newScore = Double.parseDouble(req.getParameter("newScore"));


        boolean isValidCourse = courseService.isValidCourse(courseId, Integer.parseInt(username));
        boolean isValidStudent = gradeService.isValidGrade(courseId, studentId);

        if(isValidCourse && isValidStudent){
            gradeService.modifyGrade(courseId, studentId, newScore);
            System.out.println("Score Modified!");
        }

    }


}
