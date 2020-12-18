package Database;

import Model.StudentCourse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentCourseDbTest
{

    @Test
    void insert()
    {
        StudentCourseDb db = new StudentCourseDb();

        assertTrue(db.insert(new StudentCourse()
                .setCourseId(1)
                .setStudentId(1)
        ));
    }

    @Test
    void getByStudentId()
    {
        StudentCourseDb db = new StudentCourseDb();

        assertNotNull(db.getByStudentId(1).get(0));
    }

    @Test
    void getByCourseId()
    {
        StudentCourseDb db = new StudentCourseDb();

        assertNotNull(db.getByCourseId(1).get(0));
    }
}