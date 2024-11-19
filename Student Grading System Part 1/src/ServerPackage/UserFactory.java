package ServerPackage;

import ServerPackage.Admin;
import ServerPackage.User;

import java.sql.Connection;

public class UserFactory {
    public static User getUserObj(String role){
        System.out.println(role.toLowerCase());
        if(role.toLowerCase().equals("admin")){
            return new Admin();
        } else if(role.toLowerCase().equals("teacher")){
            return new Teacher();
        } else if(role.toLowerCase().equals("student")){
            return new Student();
        }
        return null;
    }
}
