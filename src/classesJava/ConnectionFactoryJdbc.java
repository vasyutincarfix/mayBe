package classesJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static classesJava.UserDaoJdbs.*;

public class ConnectionFactoryJdbc implements ConnectionFactory {
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
