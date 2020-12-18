package Database;

import Model.Teacher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherDbTest
{
    @Test
    void insert()
    {
        TeacherDb db = new TeacherDb();

        assertTrue(db.insert(new Teacher()
            .setEmail("1@3")
            .setUserName("Haha")
            .setPasswordHash("!23")
            .setSecuritySalt("1233")
            .setPhone("!23456789")
        ));
    }
}