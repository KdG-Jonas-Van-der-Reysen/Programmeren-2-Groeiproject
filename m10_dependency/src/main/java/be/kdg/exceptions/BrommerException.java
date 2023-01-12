package be.kdg.exceptions;

public class BrommerException extends RuntimeException {
    public BrommerException() {
    }

    public BrommerException(String message) {
        super(message);
    }

    public BrommerException(String message, Throwable cause) {
        super(message, cause);
    }

    public BrommerException(Throwable cause) {
        super(cause);
    }

    public BrommerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
