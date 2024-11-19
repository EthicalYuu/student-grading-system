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
import spring.service.CourseService;
import spring.service.GradeService;
import spring.service.RoleService;
import spring.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    GradeService gradeService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    CourseService courseService;

    @GetMapping("")
    public ModelAndView adminMainPage(HttpSession session){

        ModelAndView modelAndView;

        if(!session.getAttribute("roleName").toString().equalsIgnoreCase("admin")){
            modelAndView = new ModelAndView("login-page");
        } else {
            modelAndView = new ModelAndView("admin-page-main");
        }

        return modelAndView;
    }

    @GetMapping("/create-user")
    public ModelAndView adminCreateUserPage(HttpSession session){

        ModelAndView modelAndView;

        if(!session.getAttribute("roleName").toString().equalsIgnoreCase("admin")){
            modelAndView = new ModelAndView("login-page");
        } else {
            modelAndView = new ModelAndView("create-user");
        }

        return modelAndView;
    }

    @PostMapping("/createUser")
    public ModelAndView createUser(HttpSession session, HttpServletRequest req, HttpServletResponse res) throws Exception {

        String message = "";
        ModelAndView modelAndView;

        if(!session.getAttribute("roleName").toString().equalsIgnoreCase("admin")){
            modelAndView = new ModelAndView("login-page");
            return modelAndView;
        }

        String username = req.getParameter("username");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String roleName = req.getParameter("roleName");
        String password = req.getParameter("password");

        boolean userExists = roleService.userExists(username);

        if(userExists){
            message = "User already exists";
        } else {
            userService.createNewUser(username, firstName, lastName);
            roleService.createNewRole(username, roleName, password);
            message = "User created successfully";
        }

        modelAndView = new ModelAndView("create-user");

        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @GetMapping("/delete-user")
    public ModelAndView adminDeleteUserPage(HttpSession session){

        ModelAndView modelAndView;

        if(!session.getAttribute("roleName").toString().equalsIgnoreCase("admin")){
            modelAndView = new ModelAndView("login-page");
        } else {
            modelAndView = new ModelAndView("delete-user");
        }

        return modelAndView;
    }

    @PostMapping("/deleteUser")
    public ModelAndView deleteUser(HttpSession session, HttpServletRequest req, HttpServletResponse res){

        String message = "";
        ModelAndView modelAndView;

        if(!session.getAttribute("roleName").toString().equalsIgnoreCase("admin")){
            modelAndView = new ModelAndView("login-page");
            return modelAndView;
        }

        String username = req.getParameter("username");

        boolean userExists = roleService.userExists(username);

        if(!userExists){
            message = "User does not exist";
        } else {
            roleService.deleteByUserId(username);
            userService.deleteUserById(username);
        }

        modelAndView = new ModelAndView("delete-user");

        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @GetMapping("/create-course")
    public ModelAndView adminCreateCoursePage(HttpSession session){

        ModelAndView modelAndView;

        if(!session.getAttribute("roleName").toString().equalsIgnoreCase("admin")){
            modelAndView = new ModelAndView("login-page");
        } else {
            modelAndView = new ModelAndView("create-course");
        }

        return modelAndView;
    }

    @PostMapping("/createCourse")
    public ModelAndView createCourse(HttpSession session, HttpServletRequest req, HttpServletResponse res){

        String message = "";
        ModelAndView modelAndView;

        if(!session.getAttribute("roleName").toString().equalsIgnoreCase("admin")){
            modelAndView = new ModelAndView("login-page");
            return modelAndView;
        }

        String username = req.getParameter("username");
        String teacherId = req.getParameter("teacherId");
        String courseName = req.getParameter("courseName");

        boolean isTeacher = roleService.isRoleName(teacherId, "teacher");
        boolean courseExists = courseService.courseExistsByName(courseName);

        modelAndView = new ModelAndView("create-course");

        if(!isTeacher){
            message = "Teacher does not exist";
        } else if (courseExists) {
            message = "Course already exists";
        } else {
            courseService.createNewCourse(courseName, teacherId);
            message = "Course successfully created";
        }

        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @GetMapping("/delete-course")
    public ModelAndView adminDeleteCoursePage(HttpSession session){

        ModelAndView modelAndView;

        if(!session.getAttribute("roleName").toString().equalsIgnoreCase("admin")){
            modelAndView = new ModelAndView("login-page");
        } else {
            modelAndView = new ModelAndView("delete-course");

        }

        return modelAndView;
    }

    @PostMapping("/deleteCourse")
    public ModelAndView deleteCourse(HttpSession session, HttpServletRequest req, HttpServletResponse res){

        String message = "";
        ModelAndView modelAndView;

        if(!session.getAttribute("roleName").toString().equalsIgnoreCase("admin")){
            modelAndView = new ModelAndView("login-page");
            return modelAndView;
        }


        String courseId = req.getParameter("courseId");

        boolean courseExists = courseService.courseExistsById(Integer.parseInt(courseId));

        if(courseExists){
            courseService.deleteCourseById(courseId);
            message = "Course deleted successfully";
        } else {
            message = "Invalid Course";
        }

        modelAndView = new ModelAndView("delete-course");

        modelAndView.addObject("message", message);

        return modelAndView;
    }

}
