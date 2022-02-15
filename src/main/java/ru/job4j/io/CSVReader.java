package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        validateArgs(argsName);
        File tempFile = File.createTempFile("temp", ".csv", new File("."));
        try (Scanner in = new Scanner(new BufferedReader(new FileReader(new File(argsName.get("path")))));
            PrintStream out = new PrintStream(new FileOutputStream(tempFile))) {
            StringJoiner joinerHead = new StringJoiner(argsName.get("delimiter"));

            String head = in.nextLine();
            String[] headTitles = head.split(argsName.get("delimiter"));
            List<String> filters = Arrays.asList(argsName.get("filter").split(","));
            for (int i = 0; i < headTitles.length; i++) {
                if (filters.contains(headTitles[i])) {
                    joinerHead.add(headTitles[i]);
                }
            }
            out.println(joinerHead.toString());

            while (in.hasNext()) {
                StringJoiner joiner = new StringJoiner(argsName.get("delimiter"));
                String line = in.nextLine();
                String[] column = line.split(argsName.get("delimiter"));
                for (int i = 0; i < column.length; i++) {
                    if (filters.contains(headTitles[i])) {
                        joiner.add(column[i]);
                    }
                }
                out.println(joiner.toString());
            }

            if ("stdout".equals(argsName.get("out"))) {
                try (Scanner tempScan = new Scanner(tempFile)) {
                    while (tempScan.hasNext()) {
                        System.out.println(tempScan.nextLine());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Files.copy(tempFile.toPath(), Path.of(argsName.get("out")), StandardCopyOption.REPLACE_EXISTING);
            }
            tempFile.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validateArgs(ArgsName arguments) {
        if (arguments.get("path") == null || arguments.get("delimiter") == null || arguments.get("out") == null || arguments.get("filter") == null) {
            throw new IllegalArgumentException("Have not one of needed arguments.");
        }
        if (!new File(arguments.get("path")).exists() && !arguments.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("File is not exist. Please check your path.");
        }
        if (!";".equals(arguments.get("delimiter")) && !",".equals(arguments.get("delimiter"))) {
            throw new IllegalArgumentException("Wrong delimiter format.");
        }
        if (!"stdout".equals(arguments.get("out")) && !arguments.get("out").endsWith(".csv")) {
            throw new IllegalArgumentException("Wrong out format.");
        }
    }

    public static void main(String[] args) {
        try {
            ArgsName argsName = ArgsName.of(args);
            handle(argsName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
