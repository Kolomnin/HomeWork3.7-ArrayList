package hw7.exceptions;

public class NonExistentElementException extends IllegalArgumentException {

    public NonExistentElementException(String s) {
        super(s);
    }
}
