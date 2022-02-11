package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Have not arguments.");
        }

        for (int i = 0; i < args.length; i++) {
            String[] pair = args[i].split("=", 2);
            if (pair[0] == null || pair[0].length() == 0 || !pair[0].startsWith("-")) {
                throw new IllegalArgumentException("Wrong key of argument.");
            }
            if (pair[1] == null || pair[1].length() == 0) {
                throw new IllegalArgumentException("Wrong value of argument.");
            }
            values.put(pair[0].substring(1), pair[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}