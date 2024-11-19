package ClientPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public abstract class Page {
Scanner scanner = new Scanner(System.in);

abstract void viewPage();

public void enterOption(BufferedReader in, PrintWriter out){

        String userEntry;

        while(true){
            userEntry = scanner.nextLine();
            out.println(userEntry);
            try {
                String line;
                while (!(line = in.readLine()).isEmpty()) {
                        System.out.println(line);
                }
                in.readLine();  // Handles newline sent by the server
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
