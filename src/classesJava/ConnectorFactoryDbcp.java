package classesJava;


import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static classesJava.UserDaoJdbs.*;


public class ConnectorFactoryDbcp implements ConnectionFactory {

    Connection connection;
    BasicDataSource dataSource;
    @Override
    public Connection newConnectoon() throws SQLException {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS);
        dataSource.setUsername(LOGIN);
        dataSource.setPassword(PASSWORD);
        dataSource.setUrl(JDBC_URL);
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(20);
        dataSource.setMaxOpenPreparedStatements(180);
        connection = dataSource.getConnection();
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        connection.setAutoCommit(false);
        return connection;
    }

    @Override
    public void close() throws SQLException {
        dataSource.close();
        connection.close();
    }

    public static void main(String[] args) throws SQLException {
    }
}
