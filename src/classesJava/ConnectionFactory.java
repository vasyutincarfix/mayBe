package classesJava;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionFactory {
    public Connection newConnectoon() throws SQLException;
    public void close () throws SQLException;
}
