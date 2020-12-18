package Service;

import Database.AdministratorDb;
import Database.StudentDb;
import Database.TeacherDb;
import Model.Administrator;
import Model.Student;
import Model.Teacher;
import Model.UserType;

public class AccountService
{
    public static void registerAdmin(Administrator admin)
    {
        AdministratorDb db = new AdministratorDb();

        System.out.println(db.insert(admin));
    }

    public static void registerTeacher(Teacher teacher)
    {
        TeacherDb db = new TeacherDb();

        db.insert(teacher);
    }

    public static void registerStudent(Student student)
    {
        StudentDb db = new StudentDb();

        db.insert(student);
    }

    public static void updateProfile(Object profile)
    {
        if (profile instanceof Administrator)
        {
            AdministratorDb db = new AdministratorDb();
            db.update((Administrator) profile);
        }
        else if (profile instanceof Teacher)
        {
            TeacherDb db = new TeacherDb();
            db.update((Teacher) profile);
        }
        else if (profile instanceof Student)
        {
            StudentDb db = new StudentDb();
            db.update((Student) profile);
        }
    }

    public static Object[] getProfileOf(UserType type)
    {
        switch (type)
        {
            case Administrator:
            {
                AdministratorDb db = new AdministratorDb();
                return db.getAll().toArray();
            }
            case Teacher:
            {
                TeacherDb db = new TeacherDb();
                return db.getAll().toArray();
            }
            case Student:
            {
                StudentDb db = new StudentDb();
                return db.getAll().toArray();
            }
        }
        return null;
    }

    public static Object getProfileByEmail(UserType type, String email)
    {
        try
        {
            switch (type)
            {
                case Administrator:
                {
                    AdministratorDb db = new AdministratorDb();
                    return db.getByEmail(email);
                }
                case Teacher:
                {
                    TeacherDb db = new TeacherDb();
                    return db.getByEmail(email);
                }
                case Student:
                {
                    StudentDb db = new StudentDb();
                    return db.getByEmail(email);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getProfileById(UserType type, int id)
    {
        try
        {
            switch (type)
            {
                case Administrator:
                {
                    AdministratorDb db = new AdministratorDb();
                    return db.getById(id);
                }
                case Teacher:
                {
                    TeacherDb db = new TeacherDb();
                    return db.getById(id);
                }
                case Student:
                {
                    StudentDb db = new StudentDb();
                    return db.getById(id);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
