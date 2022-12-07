package hw7.exceptions;

public class EmptyArgumentException extends IllegalArgumentException {
    public EmptyArgumentException() {
    }

    public EmptyArgumentException(String s) {
        super(s);
    }

    public EmptyArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyArgumentException(Throwable cause) {
        super(cause);
    }
}
