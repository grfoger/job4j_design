package ru.job4j.map;

import ru.job4j.collection.ForwardLinked;
import ru.job4j.collection.list.SimpleLinkedList;
import ru.job4j.it.MatrixIt;

import java.util.*;

public class Sandbox {

    public static void main(String[] args) {
       Map<String, Integer> map = new SimpleMap<>();
       map.put("один", 1);
       map.put("один", 1);
       map.put("один", 1);

        System.out.println(map.get("один"));
        int i = 3;
        int j = 8;
        float k = (float) i / j;
        System.out.println(k);
    }
}

