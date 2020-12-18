package Database;

import Model.Administrator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdministratorDbTest
{
    private final Administrator admin = new Administrator()
            .setEmail("3n@campus.com")
            .setPasswordHash("SomePassword")
            .setSecuritySalt("Salt")
            .setUserName("Admin Of Campus");

    private final AdministratorDb db = new AdministratorDb();

    @Test
    void insert()
    {
        assertTrue(db.insert(admin));
    }

    @Test
    void update()
    {
        String to = "123@456";

        assertTrue(db.update(db.getById(1).setEmail(to)));
        assertEquals(
                to,
                db.getById(1).getEmail()
        );
    }

    @Test
    void deleteById()
    {
        assertTrue(db.delete(2));
    }

    @Test
    void getById()
    {
        assertDoesNotThrow(() ->
                System.out.println(db.getById(1).getEmail()));
    }

    @Test
    void getAll()
    {
        assertDoesNotThrow(() ->
                System.out.println(db.getAll().get(1).getUserName()));
    }
}
