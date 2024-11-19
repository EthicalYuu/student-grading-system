package ClientPackage;

import ServerPackage.Admin;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class AdminPage extends Page{

    @Override
    public void viewPage() {
        System.out.println("Here's what you can do?");
        System.out.println("Create a course <CREATECOURSE>");
        System.out.println("Delete a course <DELETECOURSE>");
        System.out.println("Create a user <CREATEUSER>");
        System.out.println("Delete a user <DELETEUSER>");
    }

}
