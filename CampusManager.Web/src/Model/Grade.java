package Model;

public class Grade
{
    private int Id;
    private int Value;
    private int StudentId;
    private int CourseId;

    public int getId()
    {
        return Id;
    }

    public Grade setId(int id)
    {
        Id = id;
        return this;
    }

    public int getValue()
    {
        return Value;
    }

    public Grade setValue(int value)
    {
        Value = value;
        return this;
    }

    public int getStudentId()
    {
        return StudentId;
    }

    public Grade setStudentId(int studentId)
    {
        StudentId = studentId;
        return this;
    }

    public int getCourseId()
    {
        return CourseId;
    }

    public Grade setCourseId(int courseId)
    {
        CourseId = courseId;
        return this;
    }
}
