package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.StringJoiner;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String text = null;
            try (BufferedReader in = new BufferedReader(new FileReader(cachingDir + "\\" + key))) {
                StringJoiner join = new StringJoiner(System.lineSeparator());
                while (in.ready()) {
                    join.add(in.readLine());
                }
                text = join.toString();
                put(key, text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return text;
    }
}