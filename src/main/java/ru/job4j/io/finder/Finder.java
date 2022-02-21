package ru.job4j.io.finder;

import ru.job4j.io.duplicates.DuplicatesVisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Finder {
    public static void main(String[] args) throws IOException {
        ValidateFinder arguments = ValidateFinder.of(args);
        String root = arguments.get("d");
        Predicate<Path> condition = null;

        if ("mask".equals(arguments.get("t"))) {
            System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzomeone");
        } else if ("name".equals(arguments.get("t"))) {
            String[] namePart;
            if (arguments.get("n").contains(".")) {
                namePart = arguments.get("n").split(".");
            }
            String name = namePart[0];
            condition = x -> x.getFileName().toString().equals(arguments.get("t"));
        } else if ("regex".equals(arguments.get("t"))) {
            System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzomeone");
        }

        SearchFinder searchFinder = new SearchFinder(condition);
        Files.walkFileTree(Path.of(root), searchFinder);
        List<Path> paths = searchFinder.getPaths();

        /**
         ///////////ArgsName arguments = ArgsName.of(args);
         //////////////validateArgs(arguments);
         root = arguments.get("d");
         List<File> fileList = new ArrayList<>();
         Search.search(Path.of(arguments.get("d")), x -> !x.toString().endsWith(arguments.get("e"))).forEach(x -> fileList.add(x.toFile()));
         Zip zip = new Zip();
         zip.packFiles(fileList, new File(arguments.get("o")));
         */

    }
}
