package ua.org.crazy.sqlcmd;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Random;


/**
 * Created by hamster on 30.07.2017.
 */
public class DatabaseManager {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //info for start
        String database = "sqlcmd";
        String user = "postgres";
        String password = "qwerty";

        //connect to db
        Connection connection = getConnection(database, user, password);


        //insert new row in exist table
        String sql = "INSERT INTO users (name, salary)" + "VALUES ( 'Van11', 2000)";
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();


        //select from exist table
        stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM users WHERE id > 5");
        while (resultSet.next()) {
            System.out.println("id " + resultSet.getString("id") +
                    " name " + resultSet.getString("name") + " salary " + resultSet.getString("salary"));
        }
        resultSet.close();
        stmt.close();

        //delete
        stmt = connection.createStatement();
        stmt.executeUpdate("DELETE  FROM users WHERE id > 10");
        stmt.close();


        //update
        PreparedStatement ps = connection.prepareStatement("UPDATE users SET name = ? WHERE id = ?");
        ps.setString(1, "Robin");
        ps.setInt(2, 10);
        ps.executeUpdate();
        ps.close();

        connection.close();
    }

    private static Connection getConnection(String database, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = null;
        connection = DataSource.getConnection("jdbc:postgresql://localhost:5432/" + database, user, password);
        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
        return connection;
    }
}
