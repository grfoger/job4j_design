package ru.job4j.design.srp;

import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class ReportEngine implements Report {

    private Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Employee employee : store.findBy(filter)) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(dateFormat.format(employee.getHired().getTime())).append(";")
                    .append(dateFormat.format(employee.getFired().getTime())).append(";")
                    .append(employee.getSalary()).append(";");
        }
        return text.toString();
    }
}