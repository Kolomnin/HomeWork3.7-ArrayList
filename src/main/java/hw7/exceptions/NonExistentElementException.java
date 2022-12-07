package hw7.exceptions;

public class NonExistentElementException extends IllegalArgumentException {
    public NonExistentElementException() {
    }

    public NonExistentElementException(String s) {
        super(s);
    }

    public NonExistentElementException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistentElementException(Throwable cause) {
        super(cause);
    }
}
