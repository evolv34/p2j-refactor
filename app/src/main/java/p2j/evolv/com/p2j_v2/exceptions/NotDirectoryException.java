package p2j.evolv.com.p2j_v2.exceptions;

public class NotDirectoryException extends Exception {

    public NotDirectoryException(String message) {
        super(message);
    }

    public NotDirectoryException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
