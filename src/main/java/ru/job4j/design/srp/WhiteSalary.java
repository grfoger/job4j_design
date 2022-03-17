package ru.job4j.design.srp;

public class WhiteSalary implements Salary {
    @Override
    public double getSalaryCurrentType(Employee em) {
        return em.getSalary() * 1.1;
    }
}
