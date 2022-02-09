package ru.job4j.question;

import ru.job4j.collection.ForwardLinked;
import ru.job4j.collection.list.SimpleLinkedList;
import ru.job4j.it.MatrixIt;
import ru.job4j.map.Map;
import ru.job4j.map.SimpleMap;

import java.util.*;

public class Sandbox {

    public static void main(String[] args) {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);

        Set<User> current = Set.of(u1, new User(2, "BB"), new User(3, "C"));
        System.out.println(Analize.diff(previous, current).getAdded() + " added");
        System.out.println(Analize.diff(previous, current).getChanged() + " changed");
        System.out.println(Analize.diff(previous, current).getDeleted() + " deleted");
    }
}

