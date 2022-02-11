package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> mapOfFiles = new HashMap<>();

    public Map<FileProperty, List<Path>> getMapOfFiles() {
        return mapOfFiles;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty currentFile = new FileProperty(file.toFile().length(), file.toFile().getName());

        if (!mapOfFiles.containsKey(currentFile)) {
            mapOfFiles.put(currentFile, new ArrayList<>());
        }
        mapOfFiles.get(currentFile).add(file);

        return super.visitFile(file, attrs);
    }
}