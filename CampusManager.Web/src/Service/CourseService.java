package Service;

import Database.CourseDb;
import Database.StudentCourseDb;
import Database.StudentDb;
import Model.Course;
import Model.Student;
import Model.StudentCourse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CourseService
{
    public static ArrayList<Course> getAllCourses()
    {
        CourseDb db = new CourseDb();
        return db.getAll();
    }

    public static void updateCourse(Course course)
    {
        CourseDb db = new CourseDb();
        db.update(course);
    }

    public static void insertCourse(Course course)
    {
        CourseDb db = new CourseDb();
        db.insert(course);
    }

    public static List<Course> filter(Predicate<? super Course> cond)
    {
        return getAllCourses().stream()
                .filter(cond)
                .collect(Collectors.toList());
    }

    public static List<Course> getCourseOf(int studentId)
    {
        StudentCourseDb scDb = new StudentCourseDb();
        CourseDb cDb = new CourseDb();
        List<StudentCourse> scs = scDb.getByStudentId(studentId);
        List<Course> result = new ArrayList<>();

        for (StudentCourse sc : scs)
        {
            result.add(cDb.getById(sc.getCourseId()));
        }
        return result;
    }

    public static List<Student> getStudentsOf(int courseId)
    {
        StudentCourseDb scDb = new StudentCourseDb();
        StudentDb stuDb = new StudentDb();
        List<StudentCourse> scs = scDb.getByCourseId(courseId);
        List<Student> result = new ArrayList<>();

        for (StudentCourse sc : scs)
        {
            result.add(stuDb.getById(sc.getStudentId()));
        }
        return result;
    }

    public static void addStudentTo(int studentId, int courseId)
    {
        StudentCourseDb db = new StudentCourseDb();
        db.insert(new StudentCourse()
                .setCourseId(courseId)
                .setStudentId(studentId)
        );
    }
}
