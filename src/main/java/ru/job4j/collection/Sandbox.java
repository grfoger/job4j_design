package ru.job4j.collection;

import ru.job4j.collection.list.SimpleLinkedList;
import ru.job4j.it.MatrixIt;

public class Sandbox {
    public static void main(String[] args) {
        ForwardLinked<Integer> list = new ForwardLinked<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        for (int i:list
             ) {
            System.out.println(i);
        }
        System.out.println(list.revert());
        for (int i:list
        ) {
            System.out.println(i);
        }




    }
}
