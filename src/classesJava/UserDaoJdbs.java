package classesJava;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbs implements UserDao {
    public static ConnectionFactory factory = ConnectionFactoryFactory.newconnectionFactory();
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/testBase?useSSL=false";
    public static final String LOGIN =  "root";
    public static final String PASSWORD = "root";
    public static final String SELECT_ALL_USERS = "SELECT * FROM Tables.users;";
    public static final String INSERT = "INSERT INTO `Tables`.`users` (`id`, `login`, `email`, `usercol`) VALUES ('55', 'dfg', 'skldf', '');";
    static Connection connection;
    static Statement statement;
    static PreparedStatement preparedStatement;
    static ResultSet result;

    //connect
    public static Connection getConnection() throws ClassNotFoundException {
        try {
            connection = factory.newConnectoon();
        } catch (SQLException sqlExc) {sqlExc.printStackTrace();}
        return connection;
    }

  @Override
    public List<User> selectAll() throws ClassNotFoundException {
        ResultSet resultSet;
        List<User> listUsers = new ArrayList<>();
        try {
            resultSet = getResultSet(SELECT_ALL_USERS);
            while (resultSet.next()) {
                User us = new User();
                us.setId(result.getInt("id"));
                us.setLogin(result.getString("login"));
                us.setEmail(result.getString("email"));
                us.setUsercol(result.getString("usercol"));
                listUsers.add(us);
            }
            resultSet.close();
            statement.close();
            connection.close();
            factory.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUsers;
    }

    @Override
    public void deleteById(int id) throws ClassNotFoundException {
        try {
            getStatement().executeUpdate("DELETE FROM Tables.users WHERE id=" + String.valueOf(id));
            connection.commit();
            statement.close();
            connection.close();
            factory.close();
        } catch (SQLException e) {e.printStackTrace();}
    }

//    public void deleteByIdPreparedStatemend(int id) {
////        String sql = "DELETE FROM Tables.users WHERE id=?;";
////        PreparedStatement ps;
////        try {
//////            ps = getPreparedStatement(sql);
////            ps.setInt(1, id);
////            ps.executeUpdate();
////            connection.commit();
////            preparedStatement.close();
////            connection.close();
//        } catch (SQLException e) {e.printStackTrace();}
//    }
//Проверка логина на повторение
    private static boolean existWithLogin0(Connection connection, String login) throws SQLException, NotUniqueUserLoginException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT login FROM Tables.users where login=\"" + login + "\"");
        return resultSet.next();
    }
// Проверка почты на повторение
    private static boolean existWithEmail0(Connection connection, String email) throws SQLException, NotUniqueUserEmaiException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT email FROM Tables.users where email=\"" + email + "\"");
        return resultSet.next();
    }

    @Override
    public void insert(User user) throws ClassNotFoundException, NotUniqueUserLoginException, NotUniqueUserEmaiException {
            getConnection();
        try {
            if(existWithLogin0(connection, user.login)) {
                throw new NotUniqueUserLoginException("LoginError");
            }
            if(existWithEmail0(connection, user.email)) {
                throw new NotUniqueUserEmaiException("MailError");
            }
            preparedStatement = connection.prepareStatement("INSERT INTO Tables.users (id, login, email, usercol) VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, user.id);
            preparedStatement.setString(2, user.login);
            preparedStatement.setString(3, user.email);
            preparedStatement.setString(4, user.usercol);
            preparedStatement.executeUpdate();

         String string = "INSER INTO users (id, login, email, usercol) VALUES (?, ?, ?, ?);";

//            getStatement().executeUpdate("INSERT INTO `Tables`.`users` (`id`, `login`, `email`, `usercol`) " +
//                    "VALUES ('" + user.getId() + "'," +
//                    " '" + user.getLogin() + "', " +
//                    "'" + user.getEmail() + "', " +
//                    "'" +user.getUsercol() + "');");
            connection.commit();
            preparedStatement.close();
            connection.close();
            factory.close();
        } catch (SQLException e) {e.printStackTrace();}
    }

    //statament
    public static Statement getStatement() throws ClassNotFoundException {
        try {
            statement = getConnection().createStatement();
        } catch (SQLException sqlExc) {sqlExc.printStackTrace();}
        return statement;
    }
    //prepareStatemend
//    public static PreparedStatement getPreparedStatement(String sql) {
//        try {
//            preparedStatement = (PreparedStatement) getConnection().prepareStatement(sql);
//        } catch (SQLException sqlExc) {sqlExc.printStackTrace();}
//        return preparedStatement;
//    }
    //result из mysql
    public static ResultSet getResultSet(String str) throws ClassNotFoundException {
        try {
            result = getStatement().executeQuery(str);
        } catch (SQLException sqlExc) {sqlExc.printStackTrace();}
        return result;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, NotUniqueUserLoginException {
       connection = getConnection();
        User user = new User();
        user.setId(7);user.setLogin("ff");user.setEmail("fff");user.setUsercol("coll");
        System.out.println(existWithLogin0(connection,user.login));

    }

}
