package classesJava;

public class DBSystemException extends Exception {
    public DBSystemException (String  message) {
        super(message);
    }
    public DBSystemException(String message, Throwable cause) {
        super(message,cause);
    }
}
