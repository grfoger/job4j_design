package ru.job4j.design.srp;

import ru.job4j.collection.list.List;

import java.nio.file.Path;

public class OutJson implements Output {
    private List<Employee> list;

    public OutJson(List<Employee> list) {
        this.list = list;
    }

    public void outReport(Path outPath) {
        Employee em = list.get(0);
    }
}
