package app.dao;

import app.model.User;
import app.util.Constants;

import java.sql.*;

public class UserDao {
    private static Connection jdbcConnection;

    public boolean checkLogin(String username, String password) throws SQLException{

        connect();
        String sql = "SELECT * FROM users WHERE password = ? && username= ?";
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, password);
        statement.setString(2, username);

        ResultSet result = statement.executeQuery();

        boolean authenticate = false;

        if (result.next()) {
            authenticate = true;
        }

        disconnect();

        return authenticate;
    }
    public static void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName(Constants.jdbcDriver);
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(Constants.jdbcURL, Constants.jdbcUsername, Constants.jdbcPassword);
        }
    }

    public static void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
}
