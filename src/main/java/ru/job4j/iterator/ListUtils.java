package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    /**
     Реализовать недостающие методы в классе ListUtils:
- addAfter() вставляет после индекса;
- addBefore() вставляет до индекса;
- removeIf() удаляет все элементы, которые удовлетворяют предикату. Запрещено использовать метод List.removeIf;
- replaceIf() заменяет все элементы, которые удовлетворяют предикату;
- removeAll() удаляет из списка те элементы, которые есть в elements. Запрещено использовать метод List.removeAll().
- Запрещено использовать методы List.removeIf(),  List.removeAll()
     */

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            iterator.next();
            if (iterator.previousIndex() == index) {
                iterator.add(value);
                break;
            }
        }
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {

    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {

    }

    public static <T> void removeAll(List<T> list, List<T> elements) {

    }

}