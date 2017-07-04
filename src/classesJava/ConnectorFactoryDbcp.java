package classesJava;


import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ConnectorFactoryDbcp implements ConnectionFactory {
    static Connection connection;
    static  BasicDataSource dataSource;
    static Statement statement;
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/testBase";
    public static final String LOGIN =  "root";
    public static final String PASSWORD = "root";
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    public static final String SELECT_ALL_USERS = "SELECT * FROM Tables.users;";

    @Override
    public Connection newConnectoon() throws SQLException {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS);
        dataSource.setUsername(LOGIN);
        dataSource.setPassword(PASSWORD);
        dataSource.setUrl(JDBC_URL);
//        dataSource.setMinIdle(5);
//        dataSource.setMaxIdle(20);
//        dataSource.setMaxOpenPreparedStatements(180);

        connection = dataSource.getConnection();
        return connection;
    }

    @Override
    public void close() throws SQLException {
        dataSource.close();
        connection.close();
    }

    public static void main(String[] args) throws SQLException {
       ConnectorFactoryDbcp connectorFactoryDbcp =  new ConnectorFactoryDbcp();
       connectorFactoryDbcp.newConnectoon();
       statement = connection.createStatement();
        ResultSet result = statement.executeQuery(SELECT_ALL_USERS);
        List<User> listUsers = new ArrayList<>();
        while (result.next()) {
            User us = new User();
            us.setId(result.getInt("id"));
            us.setLogin(result.getString("login"));
            us.setEmail(result.getString("email"));
            us.setUsercol(result.getString("usercol"));
            listUsers.add(us);
            System.out.println(us);
        }
    }
}
