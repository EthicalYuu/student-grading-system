package ClientPackage;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class PageNavigator {
    public static void navigate(String role, BufferedReader in, PrintWriter out){
        Page page = null;

        switch(role.toLowerCase()) {
            case "admin":
                page = new AdminPage();
                break;
            case "teacher":
                page = new TeacherPage();
                break;
            case "student":
                page = new StudentPage();
                break;
        }

        page.viewPage();
        page.enterOption(in, out);
    }
}


