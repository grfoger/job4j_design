package ru.job4j.design.srp;

import java.util.Comparator;
import java.util.List;


public class SortEmployee implements Sort {
    @Override
    public Store sort(Store store, Comparator<Employee> compare) {
        MemStore newStore = new MemStore();
        store.findBy(x -> true).stream().sorted(compare).forEach(newStore::add);
        return newStore;
    }
}
