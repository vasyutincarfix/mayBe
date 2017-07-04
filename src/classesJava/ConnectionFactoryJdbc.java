package classesJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryJdbc implements ConnectionFactory {
    static Connection connection;
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/testBase?useSSL=false";
    public static final String LOGIN =  "root";
    public static final String PASSWORD = "root";

    @Override
    public Connection newConnectoon() throws SQLException {
        connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(JDBC_URL, LOGIN, PASSWORD);
        connection.setTransactionIsolation(com.mysql.jdbc.Connection.TRANSACTION_SERIALIZABLE);
        connection.setAutoCommit(false);
        return connection;
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
