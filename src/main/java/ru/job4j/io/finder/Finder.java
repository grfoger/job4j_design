package ru.job4j.io.finder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {
    public static void main(String[] args) {
        ValidateFinder arguments = ValidateFinder.of(args);
        print(arguments);
    }

    private static void print(ValidateFinder arguments) {
        try (PrintWriter out = new PrintWriter(new FileWriter(arguments.get("o")))) {
            for (Path path: resultOfSearch(arguments)) {
                out.println(path.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Path> resultOfSearch(ValidateFinder arguments) throws IOException {
        Path root = Path.of(arguments.get("d"));

        SearchFinder searchFinder = new SearchFinder(typeOfSearch(arguments.get("t"), arguments.get("n")));
        Files.walkFileTree(root, searchFinder);
        return searchFinder.getPaths();
    }

    private static Predicate<Path> typeOfSearch(String argKey, String argValue) {
        Predicate<Path> condition = null;
        if ("mask".equals(argKey)) {
            argValue = argValue.replace("*", ".*");
            argValue = argValue.replace("?", ".");
            Pattern pattern = Pattern.compile(argValue);
            condition = x -> {
                Matcher matcher = pattern.matcher(x.getFileName().toString());
                return matcher.find();
            };
        } else if ("name".equals(argKey)) {
            String finalArgValue = argValue;
            condition = x -> x.getFileName().toString().equals(finalArgValue);
        } else if ("regex".equals(argKey)) {
            Pattern pattern = Pattern.compile(argValue);
            condition = x -> {
                Matcher matcher = pattern.matcher(x.getFileName().toString());
                return matcher.find();
            };
        }
        return condition;
    }
}
