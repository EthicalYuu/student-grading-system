package spring.service;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.CourseRepo;
import spring.entity.Course;
import spring.entity.User;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    CourseRepo courseRepo;

    public List<Course> getTeacherCourseList(int teacherId){
        List<Course> courseList = courseRepo.findCourseByTeacherId(teacherId);
        return courseList;
    }

    public boolean isValidCourse(int courseId, int teacherId){
        List<Course> course = courseRepo.findCourseByCourseIdAndTeacherId(courseId, teacherId);
        return course.stream().findFirst().isPresent();
    }

    public boolean courseExistsByName(String courseName){
        Optional<Course> courses = courseRepo.findCourseByCourseName(courseName);
        return courses.isPresent();
    }

    public boolean courseExistsById(int courseId){
        Optional<Course> courses = courseRepo.findCourseByCourseId(courseId);
        return courses.isPresent();
    }

    public void createNewCourse(String courseName, String teacherId){
        Course course = new Course();
        course.setCourseName(courseName);
        course.setTeacherId(Integer.parseInt(teacherId));
        courseRepo.save(course);
    }

    @Transactional
    public void deleteCourseById(String courseId){
        courseRepo.deleteByCourseId(Integer.parseInt(courseId));
    }
}
