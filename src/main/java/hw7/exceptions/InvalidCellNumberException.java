package hw7.exceptions;

public class InvalidCellNumberException extends IndexOutOfBoundsException {
    public InvalidCellNumberException() {
    }

    public InvalidCellNumberException(String s) {
        super(s);
    }

    public InvalidCellNumberException(int index) {
        super(index);
    }

    public InvalidCellNumberException(long index) {
        super(index);
    }
}
