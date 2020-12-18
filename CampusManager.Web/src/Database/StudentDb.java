package Database;

import Model.Student;

import java.sql.ResultSet;
import java.util.Optional;

public class StudentDb extends DbContext<Student>
{
    @Override
    protected String getTableName()
    {
        return "Students";
    }

    @Override
    protected String[] getColumnName()
    {
        return new String[] { "Email", "UserName", "PasswordHash", "SecuritySalt", "Phone", "AvatarUrl" };
    }

    @Override
    protected Student fromResult(ResultSet result)
    {
        Student student = new Student();
        try
        {
            student = student
                    .setId(result.getInt("Id"))
                    .setEmail(result.getString("Email"))
                    .setUserName(result.getString("UserName"))
                    .setPasswordHash(result.getString("PasswordHash"))
                    .setSecuritySalt(result.getString("SecuritySalt"))
                    .setPhone(result.getString("Phone"))
                    .setAvatarUrl(result.getString("AvatarUrl"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public boolean insert(Student item)
    {
        return doInsert(new String[] {
                item.getEmail(),
                item.getUserName(),
                item.getPasswordHash(),
                item.getSecuritySalt(),
                item.getPhone(),
                item.getAvatarUrl(),
        });
    }

    @Override
    public boolean update(Student item)
    {
        return doUpdate(new String[] {
                item.getEmail(),
                item.getUserName(),
                item.getPasswordHash(),
                item.getSecuritySalt(),
                item.getPhone(),
                item.getAvatarUrl(),
                String.valueOf(item.getId()),
        });
    }

    public Student getByEmail(String email)
    {
        return getAll().stream()
                .filter(s -> s.getEmail().equals(email))
                .findAny()
                .orElse(null);
    }
}
