package classesJava;

public class ConnectionFactoryFactory {

    public static enum FactoryType {JDBS, C3P0, DBCP};

    private static FactoryType currentType = FactoryType.JDBS;
//    private static List<ConnectionFactory> allFactories = new LinkedList<>();
    public static synchronized void setType (FactoryType type) {
        currentType = type;
    }
    public static synchronized ConnectionFactory newconnectionFactory() {
        ConnectionFactory result;
        switch (currentType) {
            case JDBS:
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
