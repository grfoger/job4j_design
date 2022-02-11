package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Please, check the input of the source data. Usage java -jar dir.jar ROOT_FOLDER FILE_EXTENSION.");
        }
        File file = new File(args[0]);
        if (!file.exists() || !file.isDirectory()) {
            throw new IllegalArgumentException("First argument is not exist or not folder. Please check your path.");
        }
        String extension = args[1];
        if (extension.startsWith("/.")) {
            throw new IllegalArgumentException("Second argument is not extension. Please check.");
        }
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static class SearchFiles implements FileVisitor<Path> {
        private List<Path> paths = new ArrayList();
        private Predicate<Path> condition;

        public SearchFiles(Predicate<Path> condition) {
            this.condition = condition;
        }

        public List<Path> getPaths() {
            return paths;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (condition.test(file)) {
                paths.add(file.toAbsolutePath());
            }
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            return CONTINUE;
        }
    }
}