package ru.job4j.collection.list;

import ru.job4j.it.MatrixIt;

public class Sandbox {
    public static void main(String[] args) {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.println(list.get(0));
        System.out.println(list.get(4));
        for (int i:list) {
            System.out.println(i);
        }

    }
}
