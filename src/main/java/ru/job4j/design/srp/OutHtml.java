package ru.job4j.design.srp;

import java.io.*;
import java.nio.file.Path;

public class OutHtml implements Output {
    private String report;

    public OutHtml(String report) {
        this.report = report;
    }

    @Override
    public void outReport(Path outPath) {
        File file = new File("src/main/java/ru/job4j/design/srp/template.html");
        File target = outPath.toFile();
        try (BufferedReader in = new BufferedReader(new FileReader(file));
             PrintWriter out = new PrintWriter(new FileWriter(target))) {
            target.createNewFile();
            while (in.ready()) {
                String line = in.readLine();
                if (line.contains("$title")) {
                    line = line.replace("$title", "Отчёт");
                }
                if (line.contains("$body")) {
                    line = line.replace("$body", report);
                }
                if (line.contains(System.lineSeparator())) {
                    line = line.replace(System.lineSeparator(), "<br />" + System.lineSeparator() + "    ");
                }
                out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
