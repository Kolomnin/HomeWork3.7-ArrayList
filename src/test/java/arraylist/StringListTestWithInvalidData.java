package arraylist;

import hw7.arraylist.StringList;
import hw7.exceptions.EmptyArgumentException;
import hw7.exceptions.InvalidCellNumberException;
import hw7.exceptions.NonExistentElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringListTestWithInvalidData {
    private StringList stringList;
    private final static String FIRST = "ppp";
    private final static String CASUAL = "ggg";
    private final static String EMPTY = "";
    private final static String BLANK = "         ";


    @BeforeEach
    void setUp() {
        stringList = new StringList(3);
        stringList.add(FIRST);
    }


    @Test
    void addBlankString() {
        assertThrows(EmptyArgumentException.class, () -> stringList.add(BLANK));
    }

    @Test
    void addEmptyString() {
        assertThrows(EmptyArgumentException.class, () -> stringList.add(EMPTY));
    }

    @Test
    void addNullString() {
        assertThrows(EmptyArgumentException.class, () -> stringList.add(null));
    }

    @Test
    void addWithBigCellNumber() {
        assertThrows(InvalidCellNumberException.class, () -> stringList.add(14, CASUAL));
    }

    @Test
    void addWithNegativeCellNumber() {
        assertThrows(InvalidCellNumberException.class, () -> stringList.add(-14, CASUAL));
    }

    @Test
    void setWithBigCellNumber() {
        assertThrows(InvalidCellNumberException.class, () -> stringList.set(14, CASUAL));
    }

    @Test
    void setWithNegativeCellNumber() {
        assertThrows(InvalidCellNumberException.class, () -> stringList.set(-14, CASUAL));
    }

    @Test
    void setBlankString() {
        assertThrows(EmptyArgumentException.class, () -> stringList.set(0, BLANK));
    }

    @Test
    void setEmptyString() {
        assertThrows(EmptyArgumentException.class, () -> stringList.set(0, EMPTY));
    }

    @Test
    void setNullString() {
        assertThrows(EmptyArgumentException.class, () -> stringList.set(0, null));
    }

    @Test
    void removeNonExistentItem() {
        assertThrows(NonExistentElementException.class, () -> stringList.remove(CASUAL));
    }

    @Test
    void removeNonExistentIndex() {
        assertThrows(InvalidCellNumberException.class, () -> stringList.remove(9));
    }

    @Test
    void removeNegativeIndex() {
        assertThrows(InvalidCellNumberException.class, () -> stringList.remove(-9));
    }

    @Test
    void getWithBigCellNumber() {
        assertThrows(InvalidCellNumberException.class, () -> stringList.set(14, CASUAL));
    }

    @Test
    void getWithNegativeCellNumber() {
        assertThrows(InvalidCellNumberException.class, () -> stringList.set(-1, CASUAL));
    }

    @Test
    void testEqualsNull() {
        assertThrows(EmptyArgumentException.class, () -> stringList.equals(null));
    }
}
