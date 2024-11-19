package ClientPackage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client
{

    public static void main(String[] args)
    {

        Scanner scanner = new Scanner(System.in);

        try {

            Socket s = new Socket("localhost", 3000);

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);

            System.out.println(in.readLine());          // Prompts user to enter his user ID
            String username = scanner.nextLine();
            out.println(username);

            System.out.println(in.readLine());          // Prompts user to enter his password
            String password = scanner.nextLine();
            out.println(password);

            System.out.println(in.readLine());          // You are logged in as...

            String role = in.readLine();


            PageNavigator.navigate(role, in, out);


            scanner.close();
            in.close();
            out.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
