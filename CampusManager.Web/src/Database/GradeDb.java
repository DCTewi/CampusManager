package Database;

import Model.Grade;

import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

public class GradeDb extends DbContext<Grade>
{
    @Override
    protected String getTableName()
    {
        return "Grades";
    }

    @Override
    protected String[] getColumnName()
    {
        return new String[] { "Value", "StudentId", "CourseId" };
    }

    @Override
    protected Grade fromResult(ResultSet result)
    {
        Grade grade = new Grade();
        try
        {
            grade = grade
                    .setId(result.getInt("Id"))
                    .setValue(result.getInt("Value"))
                    .setStudentId(result.getInt("StudentId"))
                    .setCourseId(result.getInt("CourseId"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return grade;
    }

    @Override
    public boolean insert(Grade item)
    {
        return doInsert(new String[] {
                String.valueOf(item.getValue()),
                String.valueOf(item.getStudentId()),
                String.valueOf(item.getCourseId())
        });
    }

    @Override
    public boolean update(Grade item)
    {
        return doUpdate(new String[] {
                String.valueOf(item.getValue()),
                String.valueOf(item.getStudentId()),
                String.valueOf(item.getCourseId()),
                String.valueOf(item.getId())
        });
    }

    public List<Grade> getByStudentId(int studentId)
    {
        return getAll().stream()
                .filter(g -> g.getStudentId() == studentId)
                .collect(Collectors.toList());
    }

    public List<Grade> getByCourseId(int courseId)
    {
        return getAll().stream()
                .filter(g -> g.getCourseId() == courseId)
                .collect(Collectors.toList());
    }
}
