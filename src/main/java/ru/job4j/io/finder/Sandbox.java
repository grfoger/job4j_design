package ru.job4j.io.finder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.file.Path;

public class Sandbox {

    public static void main(String[] args) {
        Path path = Path.of("G:\\Архипелаг\\Работа\\Объекты\\141_Толбино\\141_Толбино-КР-02.bak");
        System.out.println(path.getFileName().toString());
    }
}

