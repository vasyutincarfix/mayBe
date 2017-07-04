package classesJava;

import java.util.LinkedList;
import java.util.List;

public class ConnectionFactoryFactory {
    public static enum FactoryType {RAW, C3P0, DBCP};
    private static FactoryType currentType = FactoryType.RAW;
    private static List<ConnectionFactory> allFactories = new LinkedList<>();
    public static synchronized void setType (FactoryType type) {
        currentType = type;
    }
    public static synchronized ConnectionFactory newconnectionFactory() {
        ConnectionFactory result;
        switch (currentType) {
            case RAW:
                result = new ConnectionFactoryJdbc();
                break;
            case C3P0:
                result = new ConnectionFactoryC3po();
                break;
            case DBCP:
                result = new ConnectorFactoryDbcp();
                break;
                default:
                    throw new RuntimeException("Never here!!!!");
        }
        return result;
    }
}
