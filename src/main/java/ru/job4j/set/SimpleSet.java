package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>();

    @Override
    public boolean add(T value) {
        boolean isAdd = false;
        if (!contains(value)) {
            set.add(value);
            isAdd = true;
        }
       return isAdd;
    }

    @Override
    public boolean contains(T value) {
        boolean isHave = false;
        for (T e: set) {
            if (e == value || e.equals(value)) {
                isHave = true;
                break;
            }
        }
        return isHave;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}