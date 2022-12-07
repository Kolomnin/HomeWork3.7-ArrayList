package arraylist;

import hw7.arraylist.StringList;
import hw7.arraylist.StringListInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class StringListTest {
    private StringList stringList;
    private final static String FIRST = "test1";
    private final static String SECOND = "test2";
    private final static String EXCEPTED = "test3";


    @BeforeEach
    void setUp() {
        stringList = new StringList(3);
        stringList.add(FIRST);
        stringList.add(SECOND);
    }

    @Test
    void increase() {
        try {
            Field field = StringList.class.getDeclaredField("capacity");
            field.setAccessible(true);
            Method method = StringList.class.getDeclaredMethod("increase", null);
            method.setAccessible(true);
            method.invoke(stringList);
            assertEquals(5, field.getInt(stringList));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Test
    void addValidString() {
        stringList.add(EXCEPTED);
        assertEquals(EXCEPTED, stringList.get(2));
    }

    @Test
    void addValidStringInBegin() {
        stringList.add(0, EXCEPTED);
        assertEquals(EXCEPTED, stringList.get(0));
        assertEquals(FIRST, stringList.get(1));
        assertEquals(SECOND, stringList.get(2));
        assertEquals(3, stringList.size());
    }

    @Test
    void setValidString() {
        stringList.set(0, EXCEPTED);
        assertEquals(EXCEPTED, stringList.get(0));
    }

    @Test
    void removeItem() {
        stringList.remove(FIRST);
        assertEquals(1, stringList.size());
        assertEquals(SECOND, stringList.get(0));
    }

    @Test
    void removeIndex() {
        stringList.remove(0);
        assertEquals(1, stringList.size());
        assertEquals(SECOND, stringList.get(0));
    }

    @Test
    void containsTrue() {
        assertTrue(stringList.contains(FIRST));
    }

    @Test
    void containsFalse() {
        assertFalse(stringList.contains(EXCEPTED));
    }

    @Test
    void indexOf() {
        assertEquals(0, stringList.indexOf(FIRST));
    }

    @Test
    void indexOfNonExistent() {
        assertEquals(-1, stringList.indexOf(EXCEPTED));
    }

    @Test
    void lastIndexOf() {
        stringList.add(FIRST);
        assertEquals(2, stringList.lastIndexOf(FIRST));
    }

    @Test
    void lastIndexOfNonExistent() {
        assertEquals(-1, stringList.lastIndexOf(EXCEPTED));
    }

    @Test
    void get() {
        assertEquals(FIRST, stringList.get(0));
    }

    @Test
    void testEquals() {
        StringListInterface excepted = new StringList(2);
        excepted.add(FIRST);
        excepted.add(SECOND);
        assertTrue(stringList.equals(excepted));
    }

    @Test
    void testEqualsDifferentSize() {
        StringListInterface excepted = new StringList(3);
        excepted.add(FIRST);
        excepted.add(SECOND);
        excepted.add(EXCEPTED);
        assertFalse(stringList.equals(excepted));
    }

    @Test
    void testEqualsDifferentContent() {
        StringListInterface excepted = new StringList(2);
        excepted.add(FIRST);
        excepted.add(EXCEPTED);
        assertFalse(stringList.equals(excepted));
    }

    @Test
    void size() {
        assertEquals(2, stringList.size());
    }

    @Test
    void isNotEmpty() {
        assertFalse(stringList.isEmpty());
    }

    @Test
    void isEmpty() {
        stringList.clear();
        assertTrue(stringList.isEmpty());
    }

    @Test
    void clear() {
        stringList.clear();
        assertEquals(0, stringList.size());
    }

    @Test
    void toArray() {
        String[] excepted = new String[]{FIRST, SECOND};
        assertArrayEquals(excepted, stringList.toArray());
    }
    @Test
    void toArrayEmpty() {
        String[] excepted = new String[0];
        stringList.clear();
        assertArrayEquals(excepted, stringList.toArray());
    }
}
