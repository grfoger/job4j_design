package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.Optional;

import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenMultiOnePutAndGet() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("#1", 1);
        map.put("#1", 1);
        map.put("#1", 1);
        int value = map.get("#1");
        assertEquals(1, value);
    }

    @Test
    public void whenIterate() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("#1", 1);
        map.put("#2", 2);
        map.put("#3", 3);
        Iterator<String> iterator = map.iterator();
        int size = 0;
        while (iterator.hasNext()) {
            iterator.next();
            size++;
        }
        assertEquals(3, size);
    }

    @Test
    public void whenPutOverCapacity() {
        Map<String, Integer> map = new SimpleMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("#" + i, i);
        }
        for (int i = 0; i < 10; i++) {
            if (map.get("#" + i) != null) {
                int value = map.get("#" + i);
                assertEquals(value, i);
            }
        }
    }

    @Test
    public void whenPutInBusy() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("#1", 1);
        assertFalse(map.put("#1", 42));
        int value = map.get("#1");
        assertEquals(1, value);
    }

    @Test
    public void whenGetNullValue() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("#1", null);
        assertNull(map.get("#1"));
    }

    @Test
    public void whenIterateAndRemove() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("#1", 1);
        map.put("#2", 2);
        map.put("#3", 3);
        assertTrue(map.remove("#2"));
        Iterator<String> iterator = map.iterator();
        int size = 0;
        while (iterator.hasNext()) {
            iterator.next();
            size++;
        }
        assertEquals(2, size);
    }

    @Test
    public void whenPutGetRemove() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("#1", 1);
        assertTrue(map.remove("#1"));
        assertNull(map.get("#1"));
    }

    @Test
    public void whenPutFalse() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("#1", 1);
        assertFalse(map.put("#1", 1));
    }

    @Test
    public void whenPutNull() {
        Map<String, Integer> map = new SimpleMap<>();
        assertFalse(map.put(null, 1));
    }
}
