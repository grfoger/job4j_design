package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        validateArgs(argsName);
        try (Scanner in = new Scanner(new BufferedReader(new FileReader(new File(argsName.get("path")))));
        PrintWriter out = new PrintWriter(new FileWriter(argsName.get("out")))) {
            List<String> list = new ArrayList<>();
            StringJoiner joiner = new StringJoiner(argsName.get("delimiter"));
            while (in.hasNext()) {
                in.useDelimiter(argsName.get("delimiter"));
                String line = in.nextLine();
                out.println(" ");
                out.write(" ");
            }
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
}
