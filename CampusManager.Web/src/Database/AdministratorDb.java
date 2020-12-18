package Database;

import Model.Administrator;

import java.sql.ResultSet;
import java.util.Optional;

public class AdministratorDb extends DbContext<Administrator>
{
    @Override
    protected String getTableName()
    {
        return "Administrators";
    }

    @Override
    protected String[] getColumnName()
    {
        return new String[] { "Email", "UserName", "PasswordHash", "SecuritySalt" };
    }

    @Override
    protected Administrator fromResult(ResultSet result)
    {
        Administrator admin = new Administrator();
        try
        {
            admin = admin
                    .setId(result.getInt("Id"))
                    .setEmail(result.getString("Email"))
                    .setUserName(result.getString("UserName"))
                    .setPasswordHash(result.getString("PasswordHash"))
                    .setSecuritySalt(result.getString("SecuritySalt"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public boolean insert(Administrator item)
    {
        return doInsert(new String[] {
                item.getEmail(),
                item.getUserName(),
                item.getPasswordHash(),
                item.getSecuritySalt(),
        });
    }

    @Override
    public boolean update(Administrator item)
    {
        return doUpdate(new String[] {
                item.getEmail(),
                item.getUserName(),
                item.getPasswordHash(),
                item.getSecuritySalt(),
                String.valueOf(item.getId())
        });
    }

    public Administrator getByEmail(String email)
    {
        return getAll().stream()
                .filter(a -> a.getEmail().equals(email))
                .findAny()
                .orElse(null);
    }
}
