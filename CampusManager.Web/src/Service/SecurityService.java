package Service;

import Database.AdministratorDb;
import Database.StudentDb;
import Database.TeacherDb;
import Model.UserType;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecurityService
{
    private static final SecureRandom Random = new SecureRandom();
    public static String getSalt(UserType type, String email)
    {
        switch (type)
        {
            case Administrator:
            {
                AdministratorDb db = new AdministratorDb();
                return db.getByEmail(email).getSecuritySalt();
            }
            case Teacher:
            {
                TeacherDb db = new TeacherDb();
                return db.getByEmail(email).getSecuritySalt();
            }
            case Student:
            {
                StudentDb db = new StudentDb();
                return db.getByEmail(email).getSecuritySalt();
            }
        }
        return "";
    }

    public static byte[] generateSalt()
    {
        int leftLimit = 48; // 0
        int rightLimit = 122; // z
        int len = 16;

        return Random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(len)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString()
                .getBytes();
    }

    public static boolean checkPassword(UserType type, String email, String password)
    {
        try
        {
            switch (type)
            {
                case Administrator:
                {
                    AdministratorDb db = new AdministratorDb();
                    return db.getByEmail(email).getPasswordHash().equals(hashPassword(password,
                            getSalt(type, email).getBytes()));
                }
                case Teacher:
                {
                    TeacherDb db = new TeacherDb();
                    return db.getByEmail(email).getPasswordHash().equals(hashPassword(password,
                            getSalt(type, email).getBytes()));
                }
                case Student:
                {
                    StudentDb db = new StudentDb();
                    return db.getByEmail(email).getPasswordHash().equals(hashPassword(password,
                            getSalt(type, email).getBytes()));
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static String hashPassword(String password, byte[] salt)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(salt);

            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return "";
    }
}
