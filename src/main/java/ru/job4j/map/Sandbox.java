package ru.job4j.map;

import ru.job4j.collection.ForwardLinked;
import ru.job4j.collection.list.SimpleLinkedList;
import ru.job4j.it.MatrixIt;

import java.util.*;

public class Sandbox {
    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar();
        User user1 = new User("Глоин", 1, calendar);
        User user2 = new User("Глоин", 1, calendar);
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        for (Map.Entry<User, Object> entry: map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " value: " + entry.getValue());
        }
    }
}

