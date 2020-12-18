package Database;

import Model.Course;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseDbTest
{

    @Test
    void insert()
    {
        CourseDb db = new CourseDb();

        assertTrue(db.insert(new Course()
            .setTeacherId(1)
            .setCourseName("Math")
        ));
    }
}