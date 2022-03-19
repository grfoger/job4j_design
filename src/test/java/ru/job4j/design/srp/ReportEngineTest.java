package ru.job4j.design.srp;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ReportEngineTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();


    @Test
    public void whenGetOutHtml() throws Exception {
        MemStore store = new MemStore();
        Calendar day = Calendar.getInstance();
        day.set(2022, 2, 17);
        Employee worker = new Employee("Ivan", day , day, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder report = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(dateFormat.format(worker.getHired().getTime())).append(";")
                .append(dateFormat.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";");

        File target = temporaryFolder.newFile("report.html");
        Output output = new OutHtml(report.toString());
        output.outReport(target.toPath());
        File expected = new File("src/test/java/ru/job4j/design/srp/expected.html");
        Assert.assertEquals(Files.readString(expected.toPath()), Files.readString(target.toPath()));
    }

    @Test
    public void getNewSalary() {
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Salary salaryNew = new WhiteSalary();
        assertEquals(110, Math.round(salaryNew.getSalaryCurrentType(worker)));

    }

    @Test
    public void sortBySalary() {
        Sort sorter = new SortEmployee();
        Calendar now = Calendar.getInstance();
        MemStore store = new MemStore();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Stepan", now, now, 120);
        Employee worker3 = new Employee("Padavan", now, now, 90);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        store = (MemStore) sorter.sort(store, (x, y) -> (int) y.getSalary() - (int) x.getSalary());
        List<Employee> list = store.findBy(x -> true);
        assertEquals(list, Arrays.asList(worker2, worker1, worker3));
    }

    @Test
    public void whenNoWorkPeriod() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngineSalary(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";");
        System.out.println(expect);
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(dateFormat.format(worker.getHired().getTime())).append(";")
                .append(dateFormat.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenNewGenerated() throws IOException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Sort sorter = new SortEmployee();
        File target = temporaryFolder.newFile("report.html");

        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Stepan", now, now, 120);
        Employee worker3 = new Employee("Padavan", now, now, 90);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        store = (MemStore) sorter.sort(store, (x, y) -> (int) y.getSalary() - (int) x.getSalary());
        Report engine = new ReportEngineSalary(store);
        String report = engine.generate(x -> true);
        Output output = new OutHtml(report);
        output.outReport(target.toPath());
        File expected = new File("src/test/java/ru/job4j/design/srp/expectedNew.html");
        Assert.assertEquals(Files.readString(expected.toPath()), Files.readString(target.toPath()));
    }

    @Test
    public void whenOutXml() throws IOException {
        Store store = new MemStore();
        Calendar day = new GregorianCalendar(2022, 2, 17, 0 , 0);
        Sort sorter = new SortEmployee();
        File target = temporaryFolder.newFile("report.xml");
        Employee worker1 = new Employee("Ivan", day, day, 100);
        Employee worker2 = new Employee("Stepan", day, day, 120);
        Employee worker3 = new Employee("Padavan", day, day, 90);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        store = sorter.sort(store, (x, y) -> (int) y.getSalary() - (int) x.getSalary());
        Output output = new OutXml(store.findBy(x -> true));
        output.outReport(target.toPath());
        File expected = new File("src/test/java/ru/job4j/design/srp/expectedNew.xml");
        Assert.assertEquals(Files.readString(expected.toPath()), Files.readString(target.toPath()));
    }

    @Test
    public void whenOutJson() throws IOException {
        Store store = new MemStore();
        Calendar day = new GregorianCalendar(2022, 2, 17, 0 , 0);
        Sort sorter = new SortEmployee();
        File target = temporaryFolder.newFile("report.xml");
        Employee worker1 = new Employee("Ivan", day, day, 100);
        Employee worker2 = new Employee("Stepan", day, day, 120);
        Employee worker3 = new Employee("Padavan", day, day, 90);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        store = sorter.sort(store, (x, y) -> (int) y.getSalary() - (int) x.getSalary());
        Output output = new OutJson(store.findBy(x -> true));
        output.outReport(target.toPath());
        File expected = new File("src/test/java/ru/job4j/design/srp/expectedNew.json");
        Assert.assertEquals(Files.readString(expected.toPath()), Files.readString(target.toPath()));
    }

}