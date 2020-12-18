package Database;

import Model.Course;

import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

public class CourseDb extends DbContext<Course>
{
    @Override
    protected String getTableName()
    {
        return "Courses";
    }

    @Override
    protected String[] getColumnName()
    {
        return new String[] { "CourseName", "Classroom", "CourseTime", "TeacherId" };
    }

    @Override
    protected Course fromResult(ResultSet result)
    {
        Course course = new Course();
        try
        {
            course = course
                    .setId(result.getInt("Id"))
                    .setCourseName(result.getString("CourseName"))
                    .setClassroom(result.getString("Classroom"))
                    .setCourseTime(result.getString("CourseTime"))
                    .setTeacherId(result.getInt("TeacherId"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public boolean insert(Course item)
    {
        return doInsert(new String[] {
                item.getCourseName(),
                item.getClassroom(),
                item.getCourseTime(),
                String.valueOf(item.getTeacherId())
        });
    }

    @Override
    public boolean update(Course item)
    {
        return doUpdate(new String[] {
                item.getCourseName(),
                item.getClassroom(),
                item.getCourseTime(),
                String.valueOf(item.getTeacherId()),
                String.valueOf(item.getId())
        });
    }

    public List<Course> getByTeacherId(int teacherId)
    {
        return getAll().stream()
                .filter(c -> c.getTeacherId() == teacherId)
                .collect(Collectors.toList());
    }
}
