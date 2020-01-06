package ro.mindit.todo.dao;

import ro.mindit.todo.model.Carti;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ro.mindit.todo.util.Constants.*;
import static ro.mindit.todo.util.Constants.jdbcPassword;

public class CartiDao {


    private static Connection jdbcConnection;

    public static List<Carti> findAll() throws SQLException {
        List<Carti> cartiList = new ArrayList<Carti>();

        String sql = "SELECT * FROM carti";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nume = resultSet.getString("name");
            String author = resultSet.getString("author");
            String genre = resultSet.getString("genre");
            Carti carti = new Carti(id, nume, author, genre);
            cartiList.add(carti);

        }

        resultSet.close();
        statement.close();

        disconnect();

        return cartiList;
    }

    public Carti findOne(int id) throws SQLException {
        Carti carti = null;
        String sql = "SELECT * FROM carti WHERE id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String author = resultSet.getString("author");
            String genre = resultSet.getString("genre");

            carti = new Carti(id, name, author, genre);
        }

        resultSet.close();
        statement.close();

        return carti;
    }



    public void deleteCarti(int id) throws SQLException {

        String sql = "DELETE from carti where id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();

        statement.close();
        disconnect();
    }

    public void updateCarti(int id, String name, String author, String genre) throws SQLException {


        String sql = "UPDATE carti set name=?, author=?, genre=? where id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, author);
        statement.setString(3, genre);
        statement.setInt(4,id);

        statement.executeUpdate();

        statement.close();
        disconnect();


    }

    public void addCarti(String name, String author, String genre) throws SQLException {

        String sql = "INSERT INTO carti (name, author, genre) VALUES (?,?,?)";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, author);
        statement.setString(3, genre);

        statement.executeUpdate();

        statement.close();
        disconnect();


    }

    public static void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName(jdbcDriver);
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    public static void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public static void main(String args[]) throws SQLException {


        CartiDao q = new CartiDao();
        //System.out.println(q.findAll());
        //q.addTodo("nume_test1","owner_test1","high1");
        //q.updateTable(2,"num_updt","own_updt","prio_updt");
        //q.deleteFromTable(1);


    }
}
