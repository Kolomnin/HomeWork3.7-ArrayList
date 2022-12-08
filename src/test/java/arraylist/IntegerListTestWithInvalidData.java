package arraylist;

import hw7.arraylist.IntegerList;
import hw7.exceptions.EmptyArgumentException;
import hw7.exceptions.InvalidCellNumberException;
import hw7.exceptions.NonExistentElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntegerListTestWithInvalidData {
    private IntegerList integerList;
    private final static Integer FIRST = 10;
    private final static Integer CASUAL = 72;

    @BeforeEach
    void setUp() {
        integerList = new IntegerList(3);
        integerList.add(FIRST);
    }

    @Test
    void validateItem() {
        try {
            Method method = IntegerList.class.getDeclaredMethod("validateItem", Integer.class);
            method.setAccessible(true);
            method.invoke(integerList, (Object) null);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            assertEquals(EmptyArgumentException.class, e.getCause().getClass());
        }
    }

    @Test
    void validateIndexTooBig() {
        try {
            Method method = IntegerList.class.getDeclaredMethod("validateIndex", int.class);
            method.setAccessible(true);
            method.invoke(integerList, 16);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            assertEquals(InvalidCellNumberException.class, e.getCause().getClass());
        }
    }

    @Test
    void validateIndexNegative() {
        try {
            Method method = IntegerList.class.getDeclaredMethod("validateIndex", int.class);
            method.setAccessible(true);
            method.invoke(integerList, -16);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            assertEquals(InvalidCellNumberException.class, e.getCause().getClass());
        }
    }

    @Test
    void removeNonExistentItem() {
        assertThrows(NonExistentElementException.class, () -> integerList.remove(CASUAL));
    }

    @Test
    void testEqualsNull() {
        assertThrows(EmptyArgumentException.class, () -> integerList.equals(null));
    }
}
