package classesJava;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionFactoryC3po implements ConnectionFactory {
      ComboPooledDataSource cpds;
      Connection connection;
    @Override
    public Connection newConnectoon() throws SQLException, ClassNotFoundException {
        cpds = new ComboPooledDataSource();
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/testBase?useSSL=false");
        cpds.setUser("root");
        cpds.setPassword("root");
        cpds.setInitialPoolSize(5);
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(100);
        connection = (Connection) cpds.getConnection();
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        connection.setAutoCommit(false);
        return connection;
    }

    @Override
    public void close() throws SQLException {
        cpds.close();
        connection.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

    }
}
