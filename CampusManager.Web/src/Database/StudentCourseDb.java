package Database;

import Model.StudentCourse;

import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

public class StudentCourseDb extends DbContext<StudentCourse>
{
    @Override
    protected String getTableName()
    {
        return "StudentCourse";
    }

    @Override
    protected String[] getColumnName()
    {
        return new String[] { "StudentId", "CourseId" } ;
    }

    @Override
    protected StudentCourse fromResult(ResultSet result)
    {
        StudentCourse sc = new StudentCourse();
        try
        {
            sc = sc.setStudentId(result.getInt("StudentId"))
                    .setCourseId(result.getInt("CourseId"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return sc;
    }

    @Override
    public boolean insert(StudentCourse item)
    {
        return doInsert(new String[] {
                String.valueOf(item.getStudentId()),
                String.valueOf(item.getCourseId()),
        });
    }

    @Override
    public boolean update(StudentCourse item)
    {
        // You can't update relation set
        return false;
    }

    @Override
    public StudentCourse getById(int id)
    {
        // No integer PK
        return null;
    }

    public List<StudentCourse> getByStudentId(int studentId)
    {
        return getAll().stream()
                .filter(sc -> sc.getStudentId() == studentId)
                .collect(Collectors.toList());
    }

    public List<StudentCourse> getByCourseId(int courseId)
    {
        return getAll().stream()
                .filter(sc -> sc.getCourseId() == courseId)
                .collect(Collectors.toList());
    }
}
