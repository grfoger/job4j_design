package ru.job4j.design.srp;



import java.util.Comparator;
import java.util.List;

public interface Sort {
    Store sort(Store store, Comparator<Employee> compare);
}
