package lists;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CustomListTest {
    private List<Integer> list;
    @Before
    public void setUp() {
        list = new CustomList<Integer>();
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(0, list.size());
    }

    @Test
    public void testIsEmpty() throws Exception {
        assertTrue(list.isEmpty());
        list.add(new Integer(1));
        assertFalse(list.isEmpty());
    }

    @Test
    public void testContains() throws Exception {
        list.add(new Integer(1));
        assertFalse(list.contains(new Integer(-400)));
        assertTrue(list.contains(new Integer(1)));
    }
    //todo
    @Test
    public void testIterator() throws Exception {
    }
    //todo
    @Test
    public void testToArray() throws Exception {
        list.add(new Integer(1));
        list.add(new Integer(2));
        assertArrayEquals(new Object[] {new Integer(1), new Integer(2)}, list.toArray());
    }

    @Test
    public void testToArray1() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {
        list.add(new Integer(1));
        assertEquals(1, list.size());
        list.add(new Integer(1));
        list.add(new Integer(1));
        list.add(new Integer(1));
        list.add(new Integer(1));
        list.add(new Integer(1));
        list.add(new Integer(1));
        list.add(new Integer(1));
        list.add(new Integer(1));
        list.add(new Integer(1));
        list.add(new Integer(1));
        list.add(new Integer(1));
        assertEquals(12, list.size());
    }

    @Test
    public void testRemove() throws Exception {
        list.add(new Integer(1));
        list.add(new Integer(2));
        list.remove(new Integer(1));
        assertEquals(-1, list.indexOf(new Integer(1)));
        assertEquals(1, list.size());
    }

    @Test
    public void testContainsAll() throws Exception {

    }

    //todo
    @Test
    public void testAddAll() throws Exception {

    }

    @Test
    public void testAddAll1() throws Exception {

    }

    @Test
    public void testRemoveAll() throws Exception {

    }

    @Test
    public void testRetainAll() throws Exception {

    }

    @Test
    public void testClear() throws Exception {

    }
    //todo
    @Test
    public void testGet() throws Exception {

    }
    //todo
    @Test
    public void testSet() throws Exception {

    }

    @Test
    public void testAdd1() throws Exception {

    }

    @Test
    public void testRemove1() throws Exception {

    }

    @Test
    public void testIndexOf() throws Exception {

    }

    @Test
    public void testLastIndexOf() throws Exception {
        list.add(new Integer(1));
        assertEquals(0, list.lastIndexOf(new Integer(1)));
        list.add(new Integer(1));
        assertEquals(1, list.lastIndexOf(new Integer(1)));
    }

    @Test
    public void testListIterator() throws Exception {

    }

    @Test
    public void testListIterator1() throws Exception {

    }

    @Test
    public void testSubList() throws Exception {

    }
}