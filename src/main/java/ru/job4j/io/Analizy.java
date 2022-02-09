package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> log = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String start = null;
            String end = null;

            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] parts = line.split(" ");
                if (start == null && ("400".equals(parts[0]) || "500".equals(parts[0]))) {
                    start = parts[1];
                }
                if (start != null && end == null && (!"400".equals(parts[0]) && !"500".equals(parts[0]))) {
                    end = parts[1];
                    log.add(start + ";" + end + ";");
                    start = null;
                    end = null;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            log.forEach(out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/server.log", "./data/unavailable.csv");
    }
}