package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private Node<E> head;
    private Node<E> tail;
    private int modCount = 0;
    private int size = 0;

    @Override
    public void add(E value) {
        final Node<E> beforeTail = tail;
        final Node<E> addElem = new Node<>(beforeTail, value, null);
        tail = addElem;
        if (beforeTail == null) {
            head = addElem;
        } else {
            beforeTail.next = addElem;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> value = head;
        for (int i = 0; i < index; i++) {
            value = value.next;
        }
        return value.e;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> node = head;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node.next != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                node = node.next;
                return node.prev.e;
            }

        };
    }
}