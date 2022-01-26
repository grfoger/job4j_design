package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>();

    @Override
    public boolean add(T value) {
        boolean isHave = false;
        while (iterator().hasNext()) {
            if (!iterator().next().equals(value)) {
                isHave = false;
            } else {
                set.add(value);
                isHave = true;
            }
        }
        return isHave;
    }

    @Override
    public boolean contains(T value) {
        boolean isHave = false;
        while (iterator().hasNext()) {
            if (!iterator().next().equals(value)) {
                isHave = false;
            } else {
                isHave = true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}