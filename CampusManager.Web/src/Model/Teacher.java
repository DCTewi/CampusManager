package Model;

public class Teacher
{
    private int Id;
    private String UserName;
    private String Email;
    private String PasswordHash;
    private String SecuritySalt;
    private String Phone;
    private String AvatarUrl;

    public int getId()
    {
        return Id;
    }

    public Teacher setId(int id)
    {
        Id = id;
        return this;
    }

    public String getUserName()
    {
        return UserName;
    }

    public Teacher setUserName(String userName)
    {
        UserName = userName;
        return this;
    }

    public String getEmail()
    {
        return Email;
    }

    public Teacher setEmail(String email)
    {
        Email = email;
        return this;
    }

    public String getPasswordHash()
    {
        return PasswordHash;
    }

    public Teacher setPasswordHash(String passwordHash)
    {
        PasswordHash = passwordHash;
        return this;
    }

    public String getSecuritySalt()
    {
        return SecuritySalt;
    }

    public Teacher setSecuritySalt(String securitySalt)
    {
        SecuritySalt = securitySalt;
        return this;
    }

    public String getPhone()
    {
        return Phone;
    }

    public Teacher setPhone(String phone)
    {
        Phone = phone;
        return this;
    }

    public String getAvatarUrl()
    {
        return AvatarUrl;
    }

    public Teacher setAvatarUrl(String avatarUrl)
    {
        AvatarUrl = avatarUrl;
        return this;
    }
}
