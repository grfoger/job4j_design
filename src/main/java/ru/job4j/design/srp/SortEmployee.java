package ru.job4j.design.srp;

import java.util.Comparator;


public class SortEmployee implements Sort {
    @Override
    public Store sort(Store store, Comparator<Employee> compare) {
        Store newStore = new MemStore();
        store.findBy(x -> true).stream().sorted(compare).forEach(newStore::add);
        return newStore;
    }
}
