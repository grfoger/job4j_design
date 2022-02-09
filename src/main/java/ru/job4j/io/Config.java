package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            in.lines().forEach(x -> {
                if (x.contains("=") && !x.startsWith("#")) {
                    String[] mapElement = x.split("=");
                    if (mapElement.length == 1) {
                        throw new IllegalArgumentException("Please be so kind, if it's not oo much trouble, check your properties. Key or Value is not exist!");
                    }
                    if (mapElement.length == 2) {
                        values.put(mapElement[0], mapElement[1]);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config conf = new Config("app.properties");
        conf.load();
        System.out.println(conf.values.toString());
    }

}