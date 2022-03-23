package ru.job4j.design.srp;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import jdk.jfr.Name;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.*;

public class ReportEngineTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void whenGetOutHtml() throws Exception {
        MemStore store = new MemStore();
        Calendar day = Calendar.getInstance();
        day.set(2022, 2, 17);
        Employee worker = new Employee("Ivan", day, day, 100);
        store.add(worker);
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
        String newReport = output.outReport(target.toPath());
        StringBuilder expected = new StringBuilder()
                .append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"").append(System.lineSeparator())
                .append("        \"http://www.w3.org/TR/html4/loose.dtd\">").append(System.lineSeparator())
                .append("<html>").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">").append(System.lineSeparator())
                .append("    <title>Отчёт</title>").append(System.lineSeparator())
                .append("</head>").append(System.lineSeparator())
                .append("    <body>Name; Hired; Fired; Salary;<br />").append(System.lineSeparator())
                .append("    Ivan;2022-03-17;2022-03-17;100.0;</body>").append(System.lineSeparator())
                .append("</html>").append(System.lineSeparator());
        Assert.assertEquals(expected.toString(), newReport);
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

    @Name("исправить")
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
        String newReport = output.outReport(target.toPath());
        StringBuilder expected = new StringBuilder()
                .append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"").append(System.lineSeparator())
                .append("        \"http://www.w3.org/TR/html4/loose.dtd\">").append(System.lineSeparator())
                .append("<html>").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">").append(System.lineSeparator())
                .append("    <title>Отчёт</title>").append(System.lineSeparator())
                .append("</head>").append(System.lineSeparator())
                .append("    <body>Name; Salary;<br />").append(System.lineSeparator())
                .append("    Stepan;120.0;<br />").append(System.lineSeparator())
                .append("    Ivan;100.0;<br />").append(System.lineSeparator())
                .append("    Padavan;90.0;</body>").append(System.lineSeparator())
                .append("</html>").append(System.lineSeparator());
        Assert.assertEquals(expected.toString(), newReport);
    }

    @Test
    public void whenOutXml() throws IOException {
        Store store = new MemStore();
        Calendar day = new GregorianCalendar(2022, 2, 17, 0, 0);
        day.setTimeZone(TimeZone.getTimeZone(ZoneOffset.of("+3")));
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
        String newReport = output.outReport(target.toPath());
        StringBuilder expected = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append("\n")
                .append("<employees>").append("\n")
                .append("    <employees>").append("\n")
                .append("        <fired>2022-03-17T00:00:00+03:00</fired>").append("\n")
                .append("        <hired>2022-03-17T00:00:00+03:00</hired>").append("\n")
                .append("        <name>Stepan</name>").append("\n")
                .append("        <salary>120.0</salary>").append("\n")
                .append("    </employees>").append("\n")
                .append("    <employees>").append("\n")
                .append("        <fired>2022-03-17T00:00:00+03:00</fired>").append("\n")
                .append("        <hired>2022-03-17T00:00:00+03:00</hired>").append("\n")
                .append("        <name>Ivan</name>").append("\n")
                .append("        <salary>100.0</salary>").append("\n")
                .append("    </employees>").append("\n")
                .append("    <employees>").append("\n")
                .append("        <fired>2022-03-17T00:00:00+03:00</fired>").append("\n")
                .append("        <hired>2022-03-17T00:00:00+03:00</hired>").append("\n")
                .append("        <name>Padavan</name>").append("\n")
                .append("        <salary>90.0</salary>").append("\n")
                .append("    </employees>").append("\n")
                .append("</employees>").append("\n");
        Assert.assertEquals(expected.toString(), newReport);
    }

    @Test
    public void whenOutJson() throws IOException {
        Store store = new MemStore();
        Calendar day = new GregorianCalendar(2022, 2, 17, 0, 0);
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
        String newReport = output.outReport(target.toPath());
        StringBuilder expected = new StringBuilder()
                .append("[")
                .append("{\"name\":\"Stepan\",")
                .append("\"hired\":{\"year\":2022,\"month\":2,\"dayOfMonth\":17,\"hourOfDay\":0,\"minute\":0,\"second\":0},")
                .append("\"fired\":{\"year\":2022,\"month\":2,\"dayOfMonth\":17,\"hourOfDay\":0,\"minute\":0,\"second\":0},")
                .append("\"salary\":120.0},")
                .append("{\"name\":\"Ivan\",")
                .append("\"hired\":{\"year\":2022,\"month\":2,\"dayOfMonth\":17,\"hourOfDay\":0,\"minute\":0,\"second\":0},")
                .append("\"fired\":{\"year\":2022,\"month\":2,\"dayOfMonth\":17,\"hourOfDay\":0,\"minute\":0,\"second\":0},")
                .append("\"salary\":100.0},")
                .append("{\"name\":\"Padavan\",")
                .append("\"hired\":{\"year\":2022,\"month\":2,\"dayOfMonth\":17,\"hourOfDay\":0,\"minute\":0,\"second\":0},")
                .append("\"fired\":{\"year\":2022,\"month\":2,\"dayOfMonth\":17,\"hourOfDay\":0,\"minute\":0,\"second\":0},")
                .append("\"salary\":90.0}")
                .append("]");
        Assert.assertEquals(expected.toString(), newReport);
    }

}