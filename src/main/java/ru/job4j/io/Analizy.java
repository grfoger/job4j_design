package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String start = null;
                        for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] parts = line.split(" ");
                if (start == null && ("400".equals(parts[0]) || "500".equals(parts[0]))) {
                    start = parts[1];
                }
                if (start != null && (!"400".equals(parts[0]) && !"500".equals(parts[0]))) {
                    out.println(start + ";" + parts[1] + ";");
                    start = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/server.log", "./data/unavailable.csv");
    }
}