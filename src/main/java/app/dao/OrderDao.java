package app.dao;

import app.model.Order;
import app.util.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private static Connection jdbcConnection;


    public static List<Order> findAll() throws SQLException {
        List<Order> orderList = new ArrayList<Order>();

        String sql = "SELECT o.id,c.username, b.name, o.borrowDate FROM orders o,clienti c, carti b WHERE o.personID = c.id && o.bookID = b.id";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("o.id");
            String bookTitle = resultSet.getString("b.name");
            String username = resultSet.getString("c.username");
            String borrowDate = resultSet.getString("o.borrowDate");

            Order order = new Order(id,username,bookTitle, borrowDate);
            orderList.add(order);

        }

        resultSet.close();
        statement.close();

        disconnect();

        return orderList;
    }


    public static List<Order> findAll(String username) throws SQLException {
        List<Order> orderList = new ArrayList<Order>();

        String sql = "SELECT o.id, b.name, o.borrowDate FROM orders o,clienti c, carti b WHERE o.personID =  (SELECT id from clienti where username = ?) && o.bookID = b.ID group by id";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();


        while (resultSet.next()) {
            int id = resultSet.getInt("o.id");
            String bookTitle = resultSet.getString("b.name");
            String borrowDate = resultSet.getString("o.borrowDate");

            Order order = new Order(id,"",bookTitle, borrowDate);
            orderList.add(order);

        }

        resultSet.close();
        statement.close();

        disconnect();

        return orderList;
    }


    public Order findOne(int id) throws SQLException {
        Order order = null;
        String sql = "SELECT c.username, b.name, o.borrowDate FROM orders o,clienti c, carti b WHERE o.id = ? && o.personID = c.id && o.bookID = b.id";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String bookTitle = resultSet.getString("b.name");
            String username = resultSet.getString("c.username");
            String borrowDate = resultSet.getString("o.borrowDate");

            order = new Order(id, username,bookTitle , borrowDate);
        }

        resultSet.close();
        statement.close();

        return order;
    }

    public void deleteOrder(int id) throws SQLException {

        String sql = "DELETE from Orders where id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();

        statement.close();
        disconnect();
    }

    public void addOrder(String userName, String bookTitle, String borrowDate) throws SQLException {


        String sql = "INSERT INTO orders (PersonID ,BookID, borrowDate) SELECT c.id, b.id, ? FROM clienti c ,carti b WHERE c.username = ? && b.name = ?;";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, borrowDate);
        statement.setString(2, userName);
        statement.setString(3, bookTitle);


        statement.executeUpdate();

        statement.close();
        disconnect();


    }

    public void updateOrder(int id, String username, String bookTitle, String borrowDate) throws SQLException {


        String sql = "UPDATE orders set PersonID=( SELECT c.id FROM clienti c WHERE c.username = ?), BookID =(SELECT b.id from carti b where b.name = ? ),borrowDate = ? WHERE id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, bookTitle);
        statement.setString(3, borrowDate);
        statement.setInt(4,id);





        statement.executeUpdate();

        statement.close();
        disconnect();


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
