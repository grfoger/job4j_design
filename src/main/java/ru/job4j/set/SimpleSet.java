package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>();

    @Override
    public boolean add(T value) {
        boolean isAdd = !contains(value);
        if (isAdd) {
            set.add(value);
        }
       return isAdd;
    }

    @Override
    public boolean contains(T value) {
        boolean isHave = false;
        for (T e: set) {
            if (Objects.equals(e, value)) {
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