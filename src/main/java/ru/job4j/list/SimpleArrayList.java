package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    boolean isLastElementNull = false;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    public static void main(String[] args) {
        SimpleArrayList<Integer> list = new SimpleArrayList<>(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.remove(2);
        for (int i: list) {
            System.out.println(i);
        }
        System.out.println(list.isLastElementNull);
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            container = incArray();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = get(index);
        container[index] = newValue;
        modCount++;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T oldValue = get(index);
        int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(container, index + 1, container, index, newSize - index);
        }
        container[--size] = null;
        isLastElementNull = container[size] == null;
        modCount++;
        return oldValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    private T[] incArray() {
        return Arrays.copyOf(container, container.length * 2 + 1);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int point = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }

        };
    }
}