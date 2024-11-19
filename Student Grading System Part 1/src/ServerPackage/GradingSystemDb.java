package ServerPackage;

import java.sql.*;
import java.util.ArrayList;

public class GradingSystemDb {
    private Connection connection;
    private SecurePasswordStorage securePassword;

    String url = "jdbc:mysql://localhost/yussif";
    String sqlName = "root";
    String sqlPass = "";

    public GradingSystemDb(){

        securePassword = new SecurePasswordStorage();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url , sqlName, sqlPass);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public boolean check(int username, String password) {
        try{

            PreparedStatement ps = connection.prepareStatement("select * from role where user_id = " + "'" + username + "'");
            ResultSet rs = ps.executeQuery();
            rs.next();
            String realPassword = rs.getString("password");
            String salt = rs.getString("salt");
            return securePassword.authenticateUser(password, realPassword, salt);

        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public Connection getConnection(){
        return connection;
    }

    public String getRole(int userId) throws SQLException {
        Statement findRole = connection.createStatement();
        ResultSet roll = findRole.executeQuery("select * from role where user_id=" + userId + " limit 1");
        roll.next();
        return roll.getString("role_name");
    }

    public ResultSet findEntry(String userId) throws SQLException {
        Statement findEntry = connection.createStatement();
        ResultSet entry = findEntry.executeQuery("select * from user where user_id=" + userId + " limit 1");
        return entry;
    }


    public boolean courseExists(String courseName) throws SQLException {
        ResultSet courseExists;

        Statement findCourse = connection.createStatement();

        courseExists = findCourse.executeQuery("select * from course where course_name = " + "'" + courseName + "'");

        return courseExists.next();
    }

    public boolean courseExistsById(int courseId) throws SQLException {
        ResultSet courseExists;

        Statement findCourse = connection.createStatement();

        courseExists = findCourse.executeQuery("select * from course where course_id = " + "'" + courseId + "'");

        return courseExists.next();
    }

    public boolean studentExists(int studentId, int courseId) throws SQLException {
        ResultSet courseExists;

        Statement findStudent = connection.createStatement();

        courseExists = findStudent.executeQuery("select * from grade where course_id = " + courseId + " and user_id = " + studentId);

        return courseExists.next();
    }

    public boolean userExists(int userId) throws SQLException {
        ResultSet courseExists;

        Statement findStudent = connection.createStatement();

        courseExists = findStudent.executeQuery("select * from user where user_id = " + "'" + userId + "'");

        return courseExists.next();
    }
    public void createCourse(String courseName, int teacherId) throws SQLException {
        Statement createCourse = connection.createStatement();
        String sqlCommand = String.format("insert into course (course_name, teacher_id) values ('%s', '%d')", courseName, teacherId);
        createCourse.execute(sqlCommand);
    }

    public void createUser(int userId, String firstName, String lastName) throws SQLException {
        Statement createUser = connection.createStatement();
        String sqlCommand = String.format("INSERT INTO user (user_id, first_name, last_name) VALUES ('%d', '%s', '%s')", userId, firstName, lastName);
        createUser.execute(sqlCommand);
    }

    public void createRole(int userId, String roleName, String password) throws Exception {
        Statement createRole = connection.createStatement();
        String salt = securePassword.getNewSalt();
        String encryptedPassword = securePassword.getEncryptedPassword(password, salt);
        String sqlCommand = String.format("insert into role (user_id, role_name, password, salt) values ('%d', '%s', '%s', '%s')", userId, roleName, encryptedPassword, salt);
        createRole.execute(sqlCommand);
    }

    public void deleteCourse(String courseName) throws SQLException {
        Statement deleteCourse = connection.createStatement();
        String sqlCommand = "delete from course where course_name = " + "'" + courseName + "'";
        deleteCourse.execute(sqlCommand);
    }

    public void deleteUser(int userId) throws SQLException {
        Statement deleteUser = connection.createStatement();
        String sqlCommand = "delete from user where user_id = " + "'" + userId + "'";
        deleteUser.execute(sqlCommand);
    }

    public boolean correctPass(int userId, String password) throws SQLException {
        ResultSet userExists;

        Statement correctPass = connection.createStatement();

        userExists = correctPass.executeQuery("select * from role where user_Id = " + "'" + userId + "'" + " and password = " + "'" + password + "'");

        return userExists.next();
    }

    public void deleteRoleByUserId(int userId) throws SQLException {
        Statement deleteRole = connection.createStatement();
        String sqlCommand = "delete from role where user_id = " + userId;
        deleteRole.execute(sqlCommand);
    }

    public String getUserName(String userId) throws SQLException {
        String firstName;
        String lastName;
        ResultSet entry = findEntry(userId);
        entry.next();
        firstName = entry.getString(2);
        lastName = entry.getString(3);
        return firstName + " " + lastName;
    }

    public String viewGrades(int userId) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        Statement findStudent = connection.createStatement();
        Statement findCourse = connection.createStatement();

        // Execute a statement
        ResultSet studentGrades = findStudent.executeQuery("select * from grade where user_id = " + userId);

        // Iterate through the result and print the student names
        while (studentGrades.next()){
            ResultSet courseName = findCourse.executeQuery("select * from course where course_id = " + studentGrades.getString(1));
            courseName.next();
            stringBuilder.append("Course Name: " + courseName.getString("course_name") + "  ");
            stringBuilder.append("Score: " + studentGrades.getString("score") + ",");
        }

        return stringBuilder.toString();
    }

    public ArrayList<StudentGrade> getGradesOfStudent(int userId) throws SQLException {

        ArrayList<StudentGrade> studentGradesAL = new ArrayList<>();

        Statement findStudent = connection.createStatement();
        Statement findCourse = connection.createStatement();

        ResultSet studentGrades = findStudent.executeQuery("select * from grade where user_id = " + userId);

        while (studentGrades.next()){
            ResultSet courseName = findCourse.executeQuery("select * from course where course_id = " + studentGrades.getString(1));
            courseName.next();
            String course = courseName.getString("course_name");
            int score = studentGrades.getInt("score");
            StudentGrade studentGrade = new StudentGrade(course, score);
            studentGradesAL.add(studentGrade);
        }

        return studentGradesAL;
    }

    public String getCourses(int userId) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        Statement findCourses = connection.createStatement();

        // Execute a statement
        ResultSet givenCourses = findCourses.executeQuery("select * from course where teacher_id = " + userId);

        while (givenCourses.next()){
            stringBuilder.append("Course ID: " + givenCourses.getString(1) + "\n");
            stringBuilder.append("Course Name: " + givenCourses.getString(2) + "\n" + "\n");
        }

        return stringBuilder.toString();
    }

    public boolean isValidCourse(int courseId, int userId) throws SQLException {
        ResultSet isGiven;

        Statement givenCourse = connection.createStatement();

        isGiven = givenCourse.executeQuery("select * from course where course_id = " + courseId + " and teacher_id = " + userId);

        return isGiven.next();
    }

    public String getGradesOfCourse(int courseId) throws SQLException{
        StringBuilder stringBuilder = new StringBuilder();
        Statement findGrades = connection.createStatement();

        // Execute a statement
        ResultSet studentGrades = findGrades.executeQuery("select * from grade where course_id = " + courseId);

        while (studentGrades.next()){
            stringBuilder.append("Student ID: " + studentGrades.getString("user_id") + "\n");
            stringBuilder.append("Score: " + studentGrades.getString("score") + "\n" + "\n");
        }

        return stringBuilder.toString();
    }

    public void modifyGrades(int courseId, int teacherId, int studentId, double newScore) throws SQLException {

        if(!isValidCourse(courseId, teacherId)){
            System.out.println("Not a valid course of yours");
            return;
        }

        Statement findStudent = connection.createStatement();
        if(isValidCourse(courseId, teacherId)){
            String sqlQuery = "update grade set score = " + newScore + " where user_id = " + studentId + " and course_id = " + courseId;
            findStudent.executeUpdate(sqlQuery);
        }
    }

}

