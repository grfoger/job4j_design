package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private static String root = null;

    public void packFiles(List<File> sources, File target) {

        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath().substring(root.length())));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void validateArgs(ArgsName arguments) {
        if (arguments.get("d") == null || arguments.get("o") == null || arguments.get("e") == null) {
            throw new IllegalArgumentException("Have not needed arguments.");
        }
        if (!new File(arguments.get("d")).exists()) {
            throw new IllegalArgumentException("File or folder is not exist. Please check your path.");
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName arguments = ArgsName.of(args);
        validateArgs(arguments);
        root = arguments.get("d");
        List<File> fileList = new ArrayList<>();
        Search.search(Path.of(arguments.get("d")), x -> !x.toString().endsWith(arguments.get("e"))).forEach(x -> fileList.add(x.toFile()));

        Zip zip = new Zip();
        zip.packFiles(fileList, new File(arguments.get("o")));

    }
}