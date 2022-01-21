package ru.job4j.collection.list;

public class Node<E> {

    E e;
    Node<E> next;
    Node<E> prev;

    public Node(Node<E> prev, E e, Node<E> next) {
        this.e = e;
        this.next = next;
        this.prev = prev;
    }

}
