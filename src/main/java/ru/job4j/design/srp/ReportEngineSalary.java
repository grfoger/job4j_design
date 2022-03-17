package ru.job4j.design.srp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class ReportEngineSalary implements Report {

    private Store store;

    public ReportEngineSalary(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;");
        for (Employee employee : store.findBy(filter)) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";");
        }
        return text.toString();
    }
}