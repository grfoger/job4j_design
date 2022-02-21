package ru.job4j.io.finder;

import ru.job4j.io.ArgsName;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ValidateFinder {

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

    private static void validateArgs(ValidateFinder arguments) {
        if (arguments.get("d") == null || arguments.get("n") == null || arguments.get("t") == null || arguments.get("o") == null) {
            throw new IllegalArgumentException("Have not needed arguments.");
        }
        if (!new File(arguments.get("d")).exists()) {
            throw new IllegalArgumentException("File or folder is not exist. Please check your path.");
        }
        if (!"mask".equals(arguments.get("t")) && !"name".equals(arguments.get("t")) && !"regex".equals(arguments.get("t"))) {
            throw new IllegalArgumentException("Wrong type of search. Please choose \"mask\", or \"name\", or \"regex\".");
        }
        if (!arguments.get("o").contains(".")) {
            throw new IllegalArgumentException("Please specify the file extension.");
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

    public static ValidateFinder of(String[] args) {
        ValidateFinder validator = new ValidateFinder();
        validator.parse(args);
        validateArgs(validator);
        return validator;
    }
}
