package Model;

public class Student
{
    private int Id;
    private String Email;
    private String UserName;
    private String PasswordHash;
    private String SecuritySalt;
    private String Phone;
    private String AvatarUrl;

    public int getId()
    {
        return Id;
    }

    public Student setId(int id)
    {
        Id = id;
        return this;
    }

    public String getEmail()
    {
        return Email;
    }

    public Student setEmail(String email)
    {
        Email = email;
        return this;
    }

    public String getUserName()
    {
        return UserName;
    }

    public Student setUserName(String userName)
    {
        UserName = userName;
        return this;
    }

    public String getPasswordHash()
    {
        return PasswordHash;
    }

    public Student setPasswordHash(String passwordHash)
    {
        PasswordHash = passwordHash;
        return this;
    }

    public String getSecuritySalt()
    {
        return SecuritySalt;
    }

    public Student setSecuritySalt(String securitySalt)
    {
        SecuritySalt = securitySalt;
        return this;
    }

    public String getPhone()
    {
        return Phone;
    }

    public Student setPhone(String phone)
    {
        Phone = phone;
        return this;
    }

    public String getAvatarUrl()
    {
        return AvatarUrl;
    }

    public Student setAvatarUrl(String avatarUrl)
    {
        AvatarUrl = avatarUrl;
        return this;
    }
}
