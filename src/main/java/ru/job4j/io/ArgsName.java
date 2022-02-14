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
        int countChars = 0;
        for (int i = 0; i < arg.length(); i++) {
            if (arg.charAt(i) == '=') {
                countChars++;
            }
        }
        if (countChars == 1 && arg.endsWith("=")) {
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
        ArgsName zip = ArgsName.of(args);
        System.out.println(zip.get("d"));
        System.out.println(zip.get("e"));
        System.out.println(zip.get("o"));
    }
}