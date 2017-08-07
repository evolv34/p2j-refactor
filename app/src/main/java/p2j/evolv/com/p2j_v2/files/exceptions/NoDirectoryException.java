package p2j.evolv.com.p2j_v2.files.exceptions;

public class NoDirectoryException extends Exception {

    public NoDirectoryException(String message) {
        super(message);
    }

    public NoDirectoryException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
