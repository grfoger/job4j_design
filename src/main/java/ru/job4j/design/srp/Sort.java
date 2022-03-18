package ru.job4j.design.srp;

import java.util.Comparator;

public interface Sort {
    Store sort(Store store, Comparator<Employee> compare);
}
