package ru.job4j.io.finder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Finder {
    public static void main(String[] args) throws IOException {
        ValidateFinder arguments = ValidateFinder.of(args);
        Path root = Path.of(arguments.get("d"));
        Predicate<Path> condition = null;

        if ("mask".equals(arguments.get("t"))) {
            String[] forMask = arguments.get("n").split("\\*");
            String findMask = null;
            for (String mask: forMask) {
                if (mask.length() != 0) {
                    findMask = mask;
                }
            }
            String finalFindMask = findMask;
            condition = x -> x.getFileName().toString().contains(finalFindMask);
        } else if ("name".equals(arguments.get("t"))) {
            condition = x -> x.getFileName().toString().equals(arguments.get("n"));
        } else if ("regex".equals(arguments.get("t"))) {
            condition = x -> x.getFileName().toString().matches(arguments.get("n"));
        }

        SearchFinder searchFinder = new SearchFinder(condition);
        Files.walkFileTree(root, searchFinder);
        List<Path> paths = searchFinder.getPaths();
        try (PrintWriter out = new PrintWriter(new FileWriter(arguments.get("o")));) {
            for (Path path: paths) {
                out.println(path.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
