package ru.job4j.io;

import java.io.FileOutputStream;

public class MPTable {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    int value = i * j;
                    String space;
                    if (value >= 10) {
                        space = "";
                    } else {
                        space = " ";
                    }
                    out.write((i + " * " + j + " = " + value + space + " |  ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


