package com.flab;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class IntContainerTest {
    private IntContainer container;

    @Before
    public void setUp() {
        container = new IntContainer();
    }

    @Test
    public void testContainerCreation() {
        assertEquals(0, container.size());
        assertTrue(container.isEmpty());
        assertTrue(container.getCapacity() >= 10);
        
        IntContainer container2 = new IntContainer(20);
        assertTrue(container2.getCapacity() >= 20);
    }

    @Test
    public void testAddSingleElement() {
        container.add(10);
        assertEquals(1, container.size());
        assertEquals(10, container.get(0));
    }

    @Test
    public void testAddMultipleElements() {
        container.add(10);
        container.add(20);
        container.add(30);
        container.add(40);
        
        assertEquals(4, container.size());
        assertEquals(10, container.get(0));
        assertEquals(20, container.get(1));
        assertEquals(30, container.get(2));
        assertEquals(40, container.get(3));
    }

    @Test
    public void testGetElements() {
        int[] values = {5, 15, 25, 35, 45};
        for (int v : values) {
            container.add(v);
        }
        
        for (int i = 0; i < values.length; i++) {
            assertEquals(values[i], container.get(i));
        }
        
        assertEquals(5, container.get(0));
        assertEquals(45, container.get(4));
    }

    @Test
    public void testRemoveFromMiddle() {
        container.add(10);
        container.add(20);
        container.add(30);
        container.add(40);
        container.add(50);
        
        int removed = container.remove(2);
        assertEquals(30, removed);
        assertEquals(4, container.size());
        assertEquals(40, container.get(2));
    }

    @Test
    public void testRemoveFromBeginning() {
        container.add(10);
        container.add(20);
        container.add(30);
        
        int removed = container.remove(0);
        assertEquals(10, removed);
        assertEquals(20, container.get(0));
    }

    @Test
    public void testRemoveFromEnd() {
        container.add(10);
        container.add(20);
        container.add(30);
        
        int removed = container.remove(2);
        assertEquals(30, removed);
        assertEquals(2, container.size());
    }

    @Test
    public void testClearContainer() {
        container.add(1);
        container.add(2);
        container.add(3);
        
        assertEquals(3, container.size());
        
        container.clear();
        assertEquals(0, container.size());
        assertTrue(container.isEmpty());
        
        container.add(100);
        assertEquals(1, container.size());
        assertEquals(100, container.get(0));
    }

    @Test
    public void testContainerInfo() {
        assertTrue(container.isEmpty());
        assertEquals(0, container.size());
        
        for (int i = 0; i < 5; i++) {
            container.add(i);
        }
        
        assertFalse(container.isEmpty());
        assertEquals(5, container.size());
        assertTrue(container.getCapacity() >= 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetNegativeIndex() {
        container.add(10);
        container.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOutOfBoundsIndex() {
        container.add(10);
        container.get(100);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveOutOfBoundsIndex() {
        container.add(10);
        container.remove(100);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveNegativeIndex() {
        container.add(10);
        container.remove(-1);
    }

    @Test
    public void testCapacityExpand() {
        IntContainer smallContainer = new IntContainer(5);
        int initialCapacity = smallContainer.getCapacity();
        
        for (int i = 0; i < 10; i++) {
            smallContainer.add(i);
        }
        
        int newCapacity = smallContainer.getCapacity();
        assertTrue(newCapacity > initialCapacity);
        assertEquals(10, smallContainer.size());
        
        for (int i = 0; i < 10; i++) {
            assertEquals(i, smallContainer.get(i));
        }
    }

    @Test
    public void testCapacityShrink() {
        for (int i = 0; i < 15; i++) {
            container.add(i * 10);
        }
        
        int expandedCapacity = container.getCapacity();
        
        while (container.size() > 3) {
            container.remove(0);
        }
        
        int shrunkCapacity = container.getCapacity();
        assertTrue(shrunkCapacity < expandedCapacity);
        assertEquals(3, container.size());
        
        assertEquals(120, container.get(0));
        assertEquals(130, container.get(1));
        assertEquals(140, container.get(2));
    }

    @Test
    public void testToString() {
        container.add(1);
        container.add(2);
        container.add(3);
        
        String expected = "[1, 2, 3]";
        assertEquals(expected, container.toString());
    }

    @Test
    public void testToStringEmpty() {
        assertEquals("[]", container.toString());
    }
}
