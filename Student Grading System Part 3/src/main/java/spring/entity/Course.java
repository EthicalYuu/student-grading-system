package spring.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "course")
public class Course {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "course_id")
    private int courseId;
    @Basic
    @Column(name = "course_name")
    private String courseName;
    @Basic
    @Column(name = "teacher_id")
    private int teacherId;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

}
