package ru.job4j.design.srp;

import java.nio.file.Path;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Sandbox {

    public static void main(String[] args) {
        Store store = new MemStore();
        Calendar day = new GregorianCalendar(2022, 2, 17, 0, 0, 0);
        Sort sorter = new SortEmployee();
        Employee worker1 = new Employee("Ivan", day, day, 100);
        Employee worker2 = new Employee("Stepan", day, day, 120);
        Employee worker3 = new Employee("Padavan", day, day, 90);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        store = sorter.sort(store, (x, y) -> (int) y.getSalary() - (int) x.getSalary());
        Output output1 = new OutXml(store.findBy(x -> true));
        Output output2 = new OutJson(store.findBy(x -> true));
        output1.outReport(Path.of("report.xml"));
        output2.outReport(Path.of("report.json"));
    }


}

