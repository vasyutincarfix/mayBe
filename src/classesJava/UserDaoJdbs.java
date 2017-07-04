package classesJava;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbs implements UserDao {
//    private final  ConnectionFactory factory;
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/testBase?useSSL=false";
    public static final String LOGIN =  "root";
    public static final String PASSWORD = "root";
    public static final String SELECT_ALL_USERS = "SELECT * FROM Tables.users;";
    public static final String INSERT = "INSERT INTO `Tables`.`users` (`id`, `login`, `email`, `usercol`) VALUES ('55', 'dfg', 'skldf', '');";


    static Connection connection;
    static Statement statement;
    static PreparedStatement preparedStatement;
    static ResultSet result;


    public UserDaoJdbs() throws SQLException {
//        factory = ConnectionFactoryFactory.setType(ConnectionFactoryFactory.FactoryType.C3P0);
    }
  @Override
    public List<User> selectAll() {
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
            result.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUsers;
    }

    @Override
    public void deleteById(int id) {
        try {
            getStatement().executeUpdate("DELETE FROM Tables.users WHERE id=" + String.valueOf(id));
            connection.commit();
            statement.close();
            connection.close();
        } catch (SQLException e) {e.printStackTrace();}
    }

    public void deleteByIdPreparedStatemend(int id) {
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
    public void insert(User user) {

        try {
            getStatement().executeUpdate("INSERT INTO `Tables`.`users` (`id`, `login`, `email`, `usercol`) " +
                    "VALUES ('" + user.getId() + "'," +
                    " '" + user.getLogin() + "', " +
                    "'" + user.getEmail() + "', " +
                    "'" +user.getUsercol() + "');");
            connection.commit();
            statement.close();
            connection.close();
        } catch (SQLException e) {e.printStackTrace();}
    }
    //connect
    public static Connection getConnection() {
        try {
            connection = (Connection) DriverManager.getConnection(JDBC_URL, LOGIN, PASSWORD);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
        } catch (SQLException sqlExc) {sqlExc.printStackTrace();}
        return connection;
    }
    //statament
    public static Statement getStatement() {
        try {
            statement = getConnection().createStatement();
        } catch (SQLException sqlExc) {sqlExc.printStackTrace();}
        return statement;
    }
    //prepareStatemend
    public static PreparedStatement getPreparedStatement(String sql) {
        try {
            preparedStatement = (PreparedStatement) getConnection().prepareStatement(sql);
        } catch (SQLException sqlExc) {sqlExc.printStackTrace();}
        return preparedStatement;
    }
    //result из mysql
    public static ResultSet getResultSet(String str)  {
        try {
            result = getStatement().executeQuery(str);
        } catch (SQLException sqlExc) {sqlExc.printStackTrace();}
        return result;
    }

    public static void main(String[] args) throws SQLException {
//        User user = new User();
//        user.setId(7); user.setLogin("asd");user.setEmail("djh@com"); user.setUsercol(null);
//        new UserDaoJdbs().insert(user);
        System.out.println(new UserDaoJdbs().selectAll().size());
//        Statement st;
//        try {
//            connection = (Connection) DriverManager.getConnection(JDBC_URL, LOGIN, PASSWORD);
//            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
//            connection.setAutoCommit(false);
//            statement = connection.createStatement();
//            statement.executeUpdate(INSERT);
//            connection.commit();
//
////            result.close();
//            statement.close();
//            connection.close();
////            System.out.println(a);
//            System.out.println("ok");
//        } catch (SQLException sql) {
//            sql.printStackTrace();
//        }
////
////
//
   }


}
