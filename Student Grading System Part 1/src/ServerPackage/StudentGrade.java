package ServerPackage;

public class StudentGrade {
    private String courseName;
    private int score;

    public StudentGrade(String courseName, int score) {
        this.courseName = courseName;
        this.score = score;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getScore() {
        return score;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
