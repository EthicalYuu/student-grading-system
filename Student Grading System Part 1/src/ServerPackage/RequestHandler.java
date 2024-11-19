package ServerPackage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class RequestHandler {
    private GradingSystemDb systemDB;
    private BufferedReader in;
    private PrintWriter out;

    private int loggedInUser;
    public RequestHandler(int loggedInUser, BufferedReader in, PrintWriter out){
        this.systemDB = new GradingSystemDb();
        this.loggedInUser = loggedInUser;
        this.in = in;
        this.out = out;
    }

    public void handleRequest(String reqCode) throws Exception {
        switch (reqCode){

            case "VIEWGRADE": {
                String grades = systemDB.viewGrades(loggedInUser);
                out.println(grades);
                break;
            }


            case "CREATECOURSE": {
                out.println("Enter a course name to create");
                out.println("\n");
                String courseName = in.readLine();
                boolean courseExists = systemDB.courseExists(courseName);
                if(!courseExists){
                    systemDB.createCourse(courseName, loggedInUser);
                    out.println("Course created successfully");
                    out.println("\n");
                } else {
                    out.println("Course name already exists");
                    out.print("\n");
                }
                break;
            }


            case "DELETECOURSE": {
                out.println("Enter a course name to delete");
                out.println("\n");
                String courseName = in.readLine();
                boolean courseExists = systemDB.courseExists(courseName);
                if(courseExists){
                    systemDB.deleteCourse(courseName);
                    out.println("Course deleted successfully");
                    out.println("\n");
                } else {
                    out.println("Course doesn't exist");
                    out.print("\n");
                }
            }

            case "MODIFYGRADE": {
                int courseId;
                int studentId;
                double newScore;

                out.println("Enter a course ID");
                out.println("\n");
                courseId = Integer.parseInt(in.readLine());
                boolean courseExists = systemDB.courseExistsById(courseId);
                if(!courseExists){
                    out.println("No such course");
                    out.println("\n");
                    break;
                }
                out.println("Enter a student ID");
                out.println("\n");
                studentId = Integer.parseInt(in.readLine());
                boolean studentExists = systemDB.studentExists(studentId, courseId);
                System.out.println(studentId);
                if (!studentExists){
                    out.println("student doesn't exist");
                    out.print("\n");
                    break;
                }
                out.println("Enter a new score");
                out.println("\n");
                newScore = Double.parseDouble(in.readLine());
                systemDB.modifyGrades(courseId, loggedInUser, studentId, newScore);
                out.println("Grade modified successfully");
                out.println("\n");
            }

            case "CREATEUSER": {

                int userId;
                String firstName;
                String lastName;
                String roleName;
                String password;

                out.println("Enter a user ID");
                out.println("\n");
                userId = Integer.parseInt(in.readLine());
                boolean userExists = systemDB.userExists(userId);
                if(userExists){
                    out.println("A user with the same ID already exists");
                    out.println("\n");
                    break;
                }
                out.println("Enter first name");
                out.println("\n");
                firstName = in.readLine();
                out.println("Enter last name");
                out.println("\n");
                lastName = in.readLine();
                out.println("Enter role name (admin/teacher/student)");
                out.println("\n");
                roleName = in.readLine();
                out.println("Enter password");
                out.println("\n");
                password = in.readLine();
                systemDB.createUser(userId, firstName, lastName);
                systemDB.createRole(userId, roleName, password);
                out.println("User has been added successfully");
                out.println("\n");
            }

            case "DELETEUSER": {

                int userId;
                String password;

                out.println("Enter a user ID");
                out.println("\n");
                userId = Integer.parseInt(in.readLine());
                boolean userExists = systemDB.userExists(userId);
                if(!userExists){
                    out.println("User does not exist");
                    out.println("\n");
                    break;
                }
                out.println("Enter password");
                out.println("\n");
                password = in.readLine();
                boolean correctPassword = systemDB.correctPass(userId, password);
                if(!correctPassword){
                    out.println("Wrong password");
                    out.println("\n");
                    break;
                }
                systemDB.deleteRoleByUserId(userId);
                systemDB.deleteUser(userId);
                out.println("User has been deleted successfully");
                out.println("\n");
            }

        }
    }
}

