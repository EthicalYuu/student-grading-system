package spring.controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.entity.Role;
import spring.service.RoleService;

@Controller
public class LoginController {

    @Autowired
    RoleService roleService;

    @GetMapping("/")
    public String showLoginPage(){
        return "login-page";
    }

    @PostMapping("/validate")
    public String getRolePage(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        if(!roleService.validateEntry(username, password)){
            return "login-page";
        }

        Role userRole = roleService.getRole(username);

        if(userRole == null){
            return "login-page";
        }

        HttpSession session = req.getSession();

        session.setAttribute("username", username);

        String roleName = userRole.getRoleName();

        System.out.println(roleName);

        session.setAttribute("roleName", roleName);
        if(roleName.equalsIgnoreCase("admin")){
            return "redirect:/admin";
        }

        if (roleName.equalsIgnoreCase("teacher")){
            return "redirect:/teacher";

        }

        if (roleName.equalsIgnoreCase("student")){
            return "redirect:/student";
        }

            return "error-page";

    }

}
