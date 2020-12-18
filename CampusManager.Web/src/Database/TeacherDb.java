package Database;

import Model.Teacher;

import java.sql.ResultSet;

public class TeacherDb extends DbContext<Teacher>
{
    @Override
    protected String getTableName()
    {
        return "Teachers";
    }

    @Override
    protected String[] getColumnName()
    {
        return new String[] { "Email", "UserName", "PasswordHash", "SecuritySalt", "Phone", "AvatarUrl" };
    }

    @Override
    protected Teacher fromResult(ResultSet result)
    {
        Teacher teacher = new Teacher();
        try
        {
            teacher = teacher
                    .setId(result.getInt("Id"))
                    .setEmail(result.getString("Email"))
                    .setUserName(result.getString("UserName"))
                    .setPhone(result.getString("Phone"))
                    .setAvatarUrl(result.getString("AvatarUrl"))
                    .setPasswordHash(result.getString("PasswordHash"))
                    .setSecuritySalt(result.getString("SecuritySalt"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return teacher;
    }

    @Override
    public boolean insert(Teacher item)
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
    public boolean update(Teacher item)
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

    public Teacher getByEmail(String email)
    {
        return getAll().stream()
                .filter(t -> t.getEmail().equals(email))
                .findAny()
                .orElse(null);
    }
}
