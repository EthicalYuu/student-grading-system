package spring.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.GradeRepo;
import spring.entity.Grade;
import java.util.List;

@Service
public class GradeService {

    @Autowired
    GradeRepo gradeRepo;


    public List<Grade> getGradesByCourseId(int courseId){
        List<Grade> gradeList = gradeRepo.findGradeByCourseId(courseId);
        return gradeList;
    }

    public List<Grade> getGradesByStudentId(int userId){
        List<Grade> gradeList = gradeRepo.findGradeByUserId(userId);
        return gradeList;
    }

    public boolean isValidGrade(int courseId, int userId){
        List<Grade> gradeList = gradeRepo.findGradeByCourseIdAndUserId(courseId, userId);
        return gradeList.stream().findFirst().isPresent();
    }

    public void modifyGrade(int courseId, int userId, double newScore){
        List<Grade> gradeList = gradeRepo.findGradeByCourseIdAndUserId(courseId, userId);
        Grade grade = gradeList.stream().findFirst().get();
        grade.setScore(newScore);
        gradeRepo.save(grade);
    }

}
