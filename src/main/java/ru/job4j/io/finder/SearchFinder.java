package ru.job4j.io.finder;

import ru.job4j.io.duplicates.FileProperty;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class SearchFinder extends SimpleFileVisitor<Path> {



    private List<Path> paths = new ArrayList();
    private Predicate<Path> condition;

    public SearchFinder(Predicate<Path> condition) {
        this.condition = condition;
    }

    public List<Path> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (condition.test(file.getFileName())) {
            paths.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
