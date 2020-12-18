package Model;

public class StudentCourse
{
    private int StudentId;
    private int CourseId;

    public int getStudentId()
    {
        return StudentId;
    }

    public StudentCourse setStudentId(int studentId)
    {
        StudentId = studentId;
        return this;
    }

    public int getCourseId()
    {
        return CourseId;
    }

    public StudentCourse setCourseId(int courseId)
    {
        CourseId = courseId;
        return this;
    }
}
