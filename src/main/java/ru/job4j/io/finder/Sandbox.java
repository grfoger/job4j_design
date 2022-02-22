package ru.job4j.io.finder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.file.Path;

public class Sandbox {

    public static void main(String[] args) {
        String one = "h*sbhfd?";
        one = one.replace("*", "111");
        one = one.replace("?", "222");
        System.out.println(one);
    }
}

