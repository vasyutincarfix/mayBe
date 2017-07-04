package classesJava;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static classesJava.UserDaoJdbs.SELECT_ALL_USERS;

public class UserDaoJdbsC3po implements UserDao {
    static ComboPooledDataSource cpds;
    static Connection connection;
    static PreparedStatement preparedStatement;
    static ResultSet result;

    @Override
    public List<User> selectAll() throws DBSystemException {
        List<User> listUsers = new ArrayList<>();
        try {
            result = getPreparedStatement(SELECT_ALL_USERS).executeQuery();
            while (result.next()) {
                User us = new User();
                us.setId(result.getInt("id"));
                us.setLogin(result.getString("login"));
                us.setEmail(result.getString("email"));
                us.setUsercol(result.getString("usercol"));
                listUsers.add(us);
            }
            result.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUsers;
    }

    @Override
    public void deleteById(int id) throws DBSystemException {
        String sql = "DELETE FROM Tables.users WHERE id=?;";
        PreparedStatement ps;
        try {
            ps = getPreparedStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.commit();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {e.printStackTrace();}
    }

    @Override
    public void insert(User user) throws DBSystemException {
        try {
            getPreparedStatement("").executeUpdate("INSERT INTO `Tables`.`users` (`id`, `login`, `email`, `usercol`) " +
                    "VALUES ('" + user.getId() + "'," +
                    " '" + user.getLogin() + "', " +
                    "'" + user.getEmail() + "', " +
                    "'" +user.getUsercol() + "');");
            connection.commit();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {e.printStackTrace();}

    }

    public static Connection getConnection() throws SQLException {
        cpds = new ComboPooledDataSource();
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/testBase");
        cpds.setUser("root");
        cpds.setPassword("root");
        cpds.setInitialPoolSize(5);
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(100);
        connection = cpds.getConnection();
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        connection.setAutoCommit(false);
        return connection;
    }

    public static PreparedStatement getPreparedStatement(String sql) {
        try {
            preparedStatement = getConnection().prepareStatement(sql);
        } catch (SQLException sqlExc) {sqlExc.printStackTrace();}
        return preparedStatement;
    }

    public static void main(String[] args) throws PropertyVetoException, SQLException {
        preparedStatement = getPreparedStatement(SELECT_ALL_USERS);
//        cpds = new ComboPooledDataSource();
//     //   cpds.setDriverClass("com.mysql.jdbc.Driver");
//        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/testBase");
//        cpds.setUser("root");
//        cpds.setPassword("root");
//        cpds.setInitialPoolSize(5);
//        cpds.setMinPoolSize(5);
//        cpds.setAcquireIncrement(5);
//        cpds.setMaxPoolSize(20);
//        cpds.setMaxStatements(100);
//        Connection connection = cpds.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Tables.users;");
//        ResultSet resultSet = preparedStatement.executeQuery();
//        List<User> listUsers = new ArrayList<>();
//        while (resultSet.next()) {
//            User us = new User();
//            us.setId(resultSet.getInt("id"));
//            us.setLogin(resultSet.getString("login"));
//            us.setEmail(resultSet.getString("email"));
//            us.setUsercol(resultSet.getString("usercol"));
//            listUsers.add(us);
//            System.out.println(us);
//        }
    }
}
