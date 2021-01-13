package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public abstract class DbContext<T>
{
    protected static String DbPath = "C:\\Users\\dctew\\OneDrive\\Develop\\Solutions\\CampusManager\\CampusManager.Web\\CampusManager.db";
    protected static String ConnectString = "jdbc:sqlite:" + DbPath;
    protected static Connection DbConnection;
    protected abstract String getTableName();
    protected abstract String[] getColumnName();
    protected abstract T fromResult(ResultSet result);

    public abstract boolean insert(T item);
    public abstract boolean update(T item);

    private static Connection getDb()
    {
        try
        {
            if (DbConnection == null || DbConnection.isClosed())
            {
                Class.forName("org.sqlite.JDBC");
                DbConnection = DriverManager.getConnection(ConnectString);
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return DbConnection;
    }

    protected boolean doInsert(String[] parameters)
    {
        StringBuilder builder = new StringBuilder("INSERT INTO \"main\".")
                .append(getTableName())
                .append("(");
        for (int i = 0; i < getColumnName().length; i++)
        {
            builder.append(getColumnName()[i]);
            if (i != getColumnName().length - 1)
            {
                builder.append(",");
            }
            else
            {
                builder.append(")VALUES (");
                for (int j = 0; j < getColumnName().length; j++)
                {
                    builder.append("?");
                    if (j != getColumnName().length - 1)
                    {
                        builder.append(",");
                    }
                    else
                    {
                        builder.append(");");
                    }
                }
            }
        }

        return doUpdateStatement(builder.toString(), parameters);
    }

    protected boolean doUpdate(String[] parameters)
    {
        StringBuilder builder = new StringBuilder("UPDATE \"main\".")
                .append(getTableName())
                .append(" SET ");
        for (int i = 0; i < getColumnName().length; i++)
        {
            builder.append(getColumnName()[i]).append("=?");
            if (i != getColumnName().length - 1)
            {
                builder.append(", ");
            }
            else
            {
                builder.append(" WHERE Id=?;");
            }
        }

        return doUpdateStatement(builder.toString(), parameters);
    }

    public boolean delete(int id)
    {
        String sql = "DELETE FROM \"main\".\"" + getTableName() + "\" WHERE \"Id\" IN (?);";

        String[] parameters = { String.valueOf(id) };

        return doUpdateStatement(sql, parameters);
    }

    public T getById(int id)
    {
        String sql = "SELECT * FROM \"main\".\"" + getTableName() + "\" WHERE \"Id\"=?";

        String[] parameters = { String.valueOf(id) };

        ResultSet resultSet = doQueryStatement(sql, parameters);

        T result = null;
        try
        {
            while (resultSet.next())
            {
                result = fromResult(resultSet);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<T> getAll()
    {
        String sql = "SELECT * FROM \"main\"." + getTableName();

        String[] parameters = {};

        ResultSet resultSet = doQueryStatement(sql, parameters);

        ArrayList<T> res = new ArrayList<>();
        try
        {
            while (resultSet.next())
            {
                res.add(fromResult(resultSet));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return res;
    }

    protected boolean doUpdateStatement(String sql, String[] parameters)
    {
        try
        {
            Connection conn = getDb();
            PreparedStatement st = Objects.requireNonNull(conn).prepareStatement(sql);

            for (int i = 0; i < parameters.length; i++)
            {
                st.setString(i + 1, parameters[i]);
            }

            st.executeUpdate();
            st.close();
            conn.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    protected ResultSet doQueryStatement(String sql, String[] parameters)
    {
        try
        {
            Connection conn = getDb();
            PreparedStatement st = Objects.requireNonNull(conn).prepareStatement(sql);

            for (int i = 0; i < parameters.length; i++)
            {
                st.setString(i + 1, parameters[i]);
            }

            return st.executeQuery();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
