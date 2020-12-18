package Service;

import Database.GradeDb;
import Model.Grade;

public class GradeService
{
    public static int getGrade(int courseId, int studentId)
    {
        GradeDb db = new GradeDb();
        Grade grade = db.getByStudentId(studentId).stream()
                .filter(g -> g.getCourseId() == courseId)
                .findAny()
                .orElse(null);

        return grade == null ? -1 : grade.getValue();
    }

    public static void setGrade(int courseId, int studentId, int value)
    {
        GradeDb db = new GradeDb();
        Grade grade = db.getByCourseId(courseId).stream()
                .filter(g -> g.getStudentId() == studentId)
                .findAny()
                .orElse(null);

        if (grade == null)
        {
            db.insert(new Grade()
                    .setCourseId(courseId)
                    .setStudentId(studentId)
                    .setValue(value)
            );
        }
        else
        {
            db.update(grade
                    .setValue(value)
            );
        }
    }
}
