package ServerPackage;
import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

class ClientHandler extends Thread {


    private GradingSystemDb systemDb;
    final BufferedReader in;
    final PrintWriter out;
    final Socket socket;

    public ClientHandler(Socket socket, BufferedReader in, PrintWriter out) {
        this.systemDb = new GradingSystemDb();
        this.socket = socket;
        this.in = in;
        this.out = out;
    }

    public void logOut() throws IOException {
        System.out.println("Client " + this.socket + " logging out...");
        System.out.println("Closing this connection.");
        this.socket.close();
        System.out.println("Connection closed");
        in.close();
        out.close();
        System.exit(0);
    }

    @Override
    public void run() {
        try {

            out.println("Enter Your User ID");
            int userId = Integer.parseInt(in.readLine());        // Gets user ID

            out.println("Enter your password");
            String password = in.readLine();        // Gets user password

            if(systemDb.check(userId, password)){
                out.println("You are logged in as a " + systemDb.getRole(userId));
            } else {
                out.println("There seems to be an issue with your username or password.");
                logOut();
            }

            User user = UserFactory.getUserObj(systemDb.getRole(userId));

            RequestHandler reqHandler = new RequestHandler(userId, in, out);

            out.println(systemDb.getRole(userId));  // Determining Page

            while (true) {
                String recReq = in.readLine();

                System.out.println(recReq);
                if(user.getPermissions().contains(Permission.valueOf(recReq))){
                    reqHandler.handleRequest(recReq);
                    continue;
                } else {
                    out.println("Permission not granted");
                }

                if (recReq.equalsIgnoreCase("log out")) {
                    logOut();
                }
            }


        } catch(NumberFormatException e){
            System.out.println("Invalid input type");
        }catch (IOException | SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
