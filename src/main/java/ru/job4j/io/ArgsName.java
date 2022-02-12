package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Have not such key");
        }
        return values.get(key);
    }

    private void validateArg(String arg) {
        if (!arg.startsWith("-") || arg.startsWith("=") || arg.startsWith("-=")) {
                throw new IllegalArgumentException("Wrong key of argument.");
        }
        if (!arg.contains("=")) {
            throw new IllegalArgumentException("Wrong argument.");
        }
        String[] countChars = arg.split("=");
        if (countChars.length == 1 && arg.endsWith("=")) {
            throw new IllegalArgumentException("Wrong value of argument.");
        }
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Have not arguments.");
        }

        for (int i = 0; i < args.length; i++) {
            validateArg(args[i]);
            String[] pair = args[i].split("=", 2);
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