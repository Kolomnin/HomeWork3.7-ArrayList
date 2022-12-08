package arraylist;

import hw7.arraylist.IntegerList;
import hw7.arraylist.IntegerListInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class IntegerListTest {
    private IntegerList integerList;
    private final static Integer FIRST = 12;
    private final static Integer SECOND = 20;
    private final static Integer EXCEPTED = 90;


    @BeforeEach
    void setUp() {
        integerList = new IntegerList(3);
        integerList.add(FIRST);
        integerList.add(SECOND);
    }

    @Test
    void increase() {
        try {
            Field field = IntegerList.class.getDeclaredField("capacity");
            field.setAccessible(true);
            Method method = IntegerList.class.getDeclaredMethod("grow");
            method.setAccessible(true);
            method.invoke(integerList);
            assertEquals(5, field.getInt(integerList));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Test
    void addValidString() {
        integerList.add(EXCEPTED);
        assertEquals(EXCEPTED, integerList.get(2));
    }

    @Test
    void addValidStringInBegin() {
        integerList.add(0, EXCEPTED);
        assertEquals(EXCEPTED, integerList.get(0));
        assertEquals(FIRST, integerList.get(1));
        assertEquals(SECOND, integerList.get(2));
        assertEquals(3, integerList.size());
    }

    @Test
    void setValidString() {
        integerList.set(0, EXCEPTED);
        assertEquals(EXCEPTED, integerList.get(0));
    }

    @Test
    void removeItem() {
        integerList.remove(FIRST);
        assertEquals(1, integerList.size());
        assertEquals(SECOND, integerList.get(0));
    }

    @Test
    void removeIndex() {
        integerList.remove(0);
        assertEquals(1, integerList.size());
        assertEquals(SECOND, integerList.get(0));
    }

    @Test
    void containsTrue() {
        assertTrue(integerList.contains(FIRST));
    }

    @Test
    void containsFalse() {
        assertFalse(integerList.contains(EXCEPTED));
    }

    @Test
    void indexOf() {
        assertEquals(0, integerList.indexOf(FIRST));
    }

    @Test
    void indexOfNonExistent() {
        assertEquals(-1, integerList.indexOf(EXCEPTED));
    }

    @Test
    void lastIndexOf() {
        integerList.add(FIRST);
        assertEquals(2, integerList.lastIndexOf(FIRST));
    }

    @Test
    void lastIndexOfNonExistent() {
        assertEquals(-1, integerList.lastIndexOf(EXCEPTED));
    }

    @Test
    void get() {
        assertEquals(FIRST, integerList.get(0));
    }

    @Test
    void testEquals() {
        IntegerListInterface excepted = new IntegerList(2);
        excepted.add(FIRST);
        excepted.add(SECOND);
        assertTrue(integerList.equals(excepted));
    }

    @Test
    void testEqualsDifferentSize() {
        IntegerListInterface excepted = new IntegerList(3);
        excepted.add(FIRST);
        excepted.add(SECOND);
        excepted.add(EXCEPTED);
        assertFalse(integerList.equals(excepted));
    }

    @Test
    void testEqualsDifferentContent() {
        IntegerListInterface excepted = new IntegerList(2);
        excepted.add(FIRST);
        excepted.add(EXCEPTED);
        assertFalse(integerList.equals(excepted));
    }

    @Test
    void size() {
        assertEquals(2, integerList.size());
    }

    @Test
    void isNotEmpty() {
        assertFalse(integerList.isEmpty());
    }

    @Test
    void isEmpty() {
        integerList.clear();
        assertTrue(integerList.isEmpty());
    }

    @Test
    void clear() {
        integerList.clear();
        assertEquals(0, integerList.size());
    }

    @Test
    void toArray() {
        Integer[] excepted = new Integer[]{FIRST, SECOND};
        assertArrayEquals(excepted, integerList.toArray());
    }

    @Test
    void toArrayEmpty() {
        Integer[] excepted = new Integer[0];
        integerList.clear();
        assertArrayEquals(excepted, integerList.toArray());
    }

    @Test
    void sort() {
        Integer[] excepted = new Integer[]{FIRST, SECOND, EXCEPTED};
        integerList.add(0, EXCEPTED);
        Integer[] arr = integerList.toArray();
        try {
            Method method = IntegerList.class.getDeclaredMethod("quickSort", Integer[].class, int.class, int.class);
            method.setAccessible(true);
            method.invoke(integerList, arr, 0, arr.length - 1);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        assertArrayEquals(excepted, arr);
    }

    @Test
    void binarySearch() {
        try {
            Method method = IntegerList.class.getDeclaredMethod("binarySearch", int.class);
            method.setAccessible(true);
            Assertions.assertTrue((Boolean) method.invoke(integerList, 20));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Test
    void binarySearchFalse() {
        try {
            Method method = IntegerList.class.getDeclaredMethod("binarySearch", int.class);
            method.setAccessible(true);
            Assertions.assertFalse((Boolean) method.invoke(integerList, 14));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
