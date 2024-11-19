package ServerPackage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(3000);

        while (true) {
            Socket s = null;

            try {
                s = ss.accept();
                System.out.println("A new client is connected: " + s);

                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);

                System.out.println("Assigning a new thread for this client");

                Thread thread = new ClientHandler(s, in, out);
                thread.start();

            } catch (Exception e) {
                if (s != null) {
                    s.close();
                }
                e.printStackTrace();
            }
        }
    }
}
