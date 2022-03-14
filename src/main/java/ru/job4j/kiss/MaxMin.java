package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {

    private <T> T compare(List<T> value, Comparator<T> comparator, Predicate<Integer> predicate) {
        T getValue = null;
        for (T type : value) {
            if (getValue == null || predicate.test(comparator.compare(type, getValue))) {
                getValue = type;
            }
        }
        return getValue;
    }


    public <T> T max(List<T> value, Comparator<T> comparator) {
        return compare(value, comparator, x -> x > 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return compare(value, comparator, x -> x < 0);
    }
}

