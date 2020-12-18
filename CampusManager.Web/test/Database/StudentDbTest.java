package Database;

import Model.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentDbTest
{
    @Test
    void insert()
    {
        StudentDb db = new StudentDb();

        assertTrue(db.insert(new Student()
                .setEmail("1@3")
                .setUserName("Haha")
                .setPasswordHash("!23")
                .setSecuritySalt("1233")
                .setPhone("!23456789")
        ));
    }
}