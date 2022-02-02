package ru.job4j.map;

import ru.job4j.collection.ForwardLinked;
import ru.job4j.collection.list.SimpleLinkedList;
import ru.job4j.it.MatrixIt;

import java.util.*;

public class Sandbox {

    public static void main(String[] args) {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("один", 1);
        map.put("два", 2);
        map.put("три", 3);
        Iterator<String> iterator = map.iterator();
        int size = 0;
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

