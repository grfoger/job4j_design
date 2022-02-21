package ru.job4j.io.finder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.file.Path;

public class Sandbox {

    public static void main(String[] args) {
        String[] parts = "*.txt".split("\\*");
        for (String part: parts) {
            System.out.println(part);
        }
    }
}

