package Model;

public class Course
{
    private int Id;
    private String CourseName;
    private String Classroom;
    private String CourseTime;
    private int TeacherId;

    public int getId()
    {
        return Id;
    }

    public Course setId(int id)
    {
        Id = id;
        return this;
    }

    public String getCourseName()
    {
        return CourseName;
    }

    public Course setCourseName(String courseName)
    {
        CourseName = courseName;
        return this;
    }

    public String getClassroom()
    {
        return Classroom;
    }

    public Course setClassroom(String classroom)
    {
        Classroom = classroom;
        return this;
    }

    public String getCourseTime()
    {
        return CourseTime;
    }

    public Course setCourseTime(String courseTime)
    {
        CourseTime = courseTime;
        return this;
    }

    public int getTeacherId()
    {
        return TeacherId;
    }

    public Course setTeacherId(int teacherId)
    {
        TeacherId = teacherId;
        return this;
    }
}
