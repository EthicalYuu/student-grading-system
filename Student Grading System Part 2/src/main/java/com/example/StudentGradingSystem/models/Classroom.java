package com.example.StudentGradingSystem.models;

public class Classroom {
    private int minGrade;
    private int maxGrade;


    public Classroom(int minGrade, int maxGrade) {
        this.minGrade = minGrade;
        this.maxGrade = maxGrade;
    }

    public int getMinGrade() {
        return minGrade;
    }

    public void setMinGrade(int minGrade) {
        this.minGrade = minGrade;
    }

    public int getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(int maxGrade) {
        this.maxGrade = maxGrade;
    }
}
