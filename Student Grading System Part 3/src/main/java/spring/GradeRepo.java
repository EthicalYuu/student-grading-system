package spring;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.entity.Course;
import spring.entity.Grade;

import java.util.List;

public interface GradeRepo extends JpaRepository<Grade, Integer> {
    List<Grade> findGradeByCourseId(int courseId);

    List<Grade> findGradeByUserId(int userId);

    List<Grade> findGradeByCourseIdAndUserId(int courseId, int userId);

}
