package spring;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepo extends JpaRepository<Course, Integer> {
    List<Course> findCourseByTeacherId(int teacherId);
    List<Course> findCourseByCourseIdAndTeacherId(int courseId, int teacherId);

    Optional<Course> findCourseByCourseName(String courseName);

    Optional<Course> findCourseByCourseId(int courseId);


    void deleteByCourseId(int courseId);

}
