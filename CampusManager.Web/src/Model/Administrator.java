package Model;

public class Administrator
{
    private int Id;
    private String Email;
    private String UserName;
    private String PasswordHash;
    private String SecuritySalt;

    public int getId()
    {
        return Id;
    }

    public Administrator setId(int id)
    {
        Id = id;
        return this;
    }

    public String getEmail()
    {
        return Email;
    }

    public Administrator setEmail(String email)
    {
        Email = email;
        return this;
    }

    public String getUserName()
    {
        return UserName;
    }

    public Administrator setUserName(String userName)
    {
        UserName = userName;
        return this;
    }

    public String getPasswordHash()
    {
        return PasswordHash;
    }

    public Administrator setPasswordHash(String passwordHash)
    {
        PasswordHash = passwordHash;
        return this;
    }

    public String getSecuritySalt()
    {
        return SecuritySalt;
    }

    public Administrator setSecuritySalt(String securitySalt)
    {
        SecuritySalt = securitySalt;
        return this;
    }

}
