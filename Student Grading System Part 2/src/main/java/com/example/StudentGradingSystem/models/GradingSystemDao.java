package com.example.StudentGradingSystem.models;

import com.example.StudentGradingSystem.controllers.SecurePasswordStorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradingSystemDao {

    private static final GradingSystemDao instance = new GradingSystemDao();
    private Connection connection;

    private SecurePasswordStorage securePassword;

    String url = "jdbc:mysql://localhost/yussif";
    String sqlName = "root";
    String sqlPass = "";

    private GradingSystemDao(){
        try {
            securePassword = new SecurePasswordStorage();

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url , sqlName, sqlPass);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static GradingSystemDao getInstance(){
        return instance;
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
        ResultSet roll = findRole.executeQuery("select * from Role where user_Id=" + userId + " limit 1");
        roll.next();
        return roll.getString("role_name");
    }

    public ResultSet findEntry(String userId) throws SQLException {
        Statement findEntry = connection.createStatement();
        ResultSet entry = findEntry.executeQuery("select * from User where user_Id=" + userId + " limit 1");
        return entry;
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

    public void deleteUserById(int userId) throws SQLException {
        Statement deleteUser = connection.createStatement();
        String sqlCommand = "delete from user where user_id = " + userId;
        deleteUser.execute(sqlCommand);
    }

    public void deleteRoleByUserId(int userId) throws SQLException {
        Statement deleteRole = connection.createStatement();
        String sqlCommand = "delete from role where user_id = " + userId;
        deleteRole.execute(sqlCommand);
    }

    public void deleteCourseById(int courseId) throws SQLException {
        Statement deleteCourse = connection.createStatement();
        String sqlCommand = "delete from course where course_id = " + courseId;
        deleteCourse.execute(sqlCommand);
    }

    public String viewGrades(int userId) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        Statement findStudent = connection.createStatement();
        Statement findCourse = connection.createStatement();

        // Execute a statement
        ResultSet studentGrades = findStudent.executeQuery("select * from grade where user_Id = " + userId);

        // Iterate through the result and print the student names
        while (studentGrades.next()){
            ResultSet courseName = findCourse.executeQuery("select * from course where course_Id = " + studentGrades.getString(1));
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

        ResultSet studentGrades = findStudent.executeQuery("select * from grade where user_Id = " + userId);

        while (studentGrades.next()){
            ResultSet courseName = findCourse.executeQuery("select * from course where course_Id = " + studentGrades.getString("course_Id"));
            courseName.next();
            String course = courseName.getString("course_name");
            int score = studentGrades.getInt("score");
            StudentGrade studentGrade = new StudentGrade(course, score);
            studentGradesAL.add(studentGrade);
        }

        return studentGradesAL;
    }

    public String viewCourses(int userId) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        Statement findCourses = connection.createStatement();

        // Execute a statement
        ResultSet givenCourses = findCourses.executeQuery("select * from course where teacher_Id = " + userId);

        while (givenCourses.next()){
            stringBuilder.append("Course ID: " + givenCourses.getString(1) + "\n");
            stringBuilder.append("Course Name: " + givenCourses.getString(2) + "\n" + "\n");
        }

        return stringBuilder.toString();
    }

    public ArrayList<Course> getCourses(int userId) throws SQLException {

        ArrayList<Course> courseList = new ArrayList<>();
        Statement findCourses = connection.createStatement();

        ResultSet givenCourses = findCourses.executeQuery("select * from course where teacher_Id = " + userId);

        while (givenCourses.next()){
            int courseId = givenCourses.getInt("course_id");
            String courseName = givenCourses.getString("course_name");
            Course course = new Course(courseId, courseName);
            courseList.add(course);
        }

        return courseList;
    }

    public boolean isValidCourse(int courseId, int userId) throws SQLException {
        ResultSet isGiven;

        Statement givenCourse = connection.createStatement();

        isGiven = givenCourse.executeQuery("select * from course where course_Id = " + courseId + " and teacher_Id = " + userId);

        return isGiven.next();
    }

    public boolean userExists(int userId) throws SQLException {
        ResultSet userExists;

        Statement findUser = connection.createStatement();

        userExists = findUser.executeQuery("select * from user where user_Id = " + userId);

        return userExists.next();
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

        courseExists = findCourse.executeQuery("select * from course where course_id = " + courseId);

        return courseExists.next();
    }

    public boolean correctPass(int userId, String password) throws SQLException {
        ResultSet userExists;

        Statement correctPass = connection.createStatement();

        userExists = correctPass.executeQuery("select * from role where user_Id = " + userId + " and password = " + password);

        return userExists.next();
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

    public void createCourse(String courseName, int teacherId) throws SQLException {
        Statement createCourse = connection.createStatement();
        String sqlCommand = String.format("insert into course (course_name, teacher_id) values ('%s', '%d')", courseName, teacherId);
        createCourse.execute(sqlCommand);
    }
    public List<CourseGrade> getGradesOfCourse(int courseId) throws SQLException{
        ArrayList<CourseGrade> courseGradeList = new ArrayList<>();

        Statement findGrades = connection.createStatement();

        // Execute a statement
        ResultSet studentGrades = findGrades.executeQuery("select * from grade where course_Id = " + courseId);

        while (studentGrades.next()){
            int userId = studentGrades.getInt("user_id");
            int score = studentGrades.getInt("score");
            CourseGrade courseGrade = new CourseGrade(courseId, userId, score);
            courseGradeList.add(courseGrade);
        }

        return courseGradeList;
    }

    public void addCourse(String courseId){

    }

    public void modifyGrades(int courseId, int studentId, int newScore) throws SQLException {

        Statement findStudent = connection.createStatement();

        String sqlQuery = "update grade set score = " + newScore + " where user_Id = " + studentId + " and course_Id = " + courseId;
        findStudent.executeUpdate(sqlQuery);

    }

}

