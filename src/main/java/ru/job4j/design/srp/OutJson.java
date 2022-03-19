package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;

public class OutJson implements Output {
    private List<Employee> list;

    public OutJson(List<Employee> list) {
        this.list = list;
    }

    public void outReport(Path outPath) {
        final Gson gson = new GsonBuilder().create();
        String newReport = gson.toJson(list);
        try (PrintWriter out = new PrintWriter(new FileWriter(outPath.toFile()))) {
            out.write(newReport);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
