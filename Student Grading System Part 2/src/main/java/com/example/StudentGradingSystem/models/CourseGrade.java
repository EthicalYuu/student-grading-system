package com.example.StudentGradingSystem.models;

public class CourseGrade {
    private int userId;
    private int score;

    private int courseId;

    public CourseGrade(int courseId, int userId, int score) {
        this.courseId = courseId;
        this.userId = userId;
        this.score = score;
    }

    public int getUserId() {
        return userId;
    }

    public int getScore() {
        return score;
    }

    public int getCourseId() {
        return courseId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
