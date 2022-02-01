package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.Optional;

import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenMultiPutAndGet() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("один", 1);
        map.put("один", 1);
        map.put("один", 1);
        int value = map.get("один");
        assertEquals(value, 1);
    }

    @Test
    public void whenIterate() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("один", 1);
        map.put("два", 2);
        map.put("три", 3);
        Iterator<String> iterator = map.iterator();
        int size = 0;
        while (iterator.hasNext()){
            iterator.next();
            size++;
        }
        assertEquals(size, 3);
    }

}
