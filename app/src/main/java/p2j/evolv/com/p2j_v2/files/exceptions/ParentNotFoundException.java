package p2j.evolv.com.p2j_v2.files.exceptions;

public class ParentNotFoundException extends Exception {

    public ParentNotFoundException(String message) {
        super(message);
    }

    public ParentNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
