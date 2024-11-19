package spring.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "grade")
public class Grade {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "grade_id")
    private int gradeId;

    @Basic
    @Column(name = "course_id")
    private int courseId;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "score")

    private double score;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }


    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }
}
