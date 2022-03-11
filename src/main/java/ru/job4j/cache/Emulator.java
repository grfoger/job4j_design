package ru.job4j.cache;

public class Emulator {

    private String cacheDir;
    private AbstractCache<String, String> cache;

    public void setCacheDir(String cacheDir) {
        this.cacheDir = cacheDir;
        cache = new DirFileCache(cacheDir);
    }

    public void loadData(String path) {
        cache.put(path, cache.get(path));
    }

    public String getData(String path) {
       return cache.get(path);
    }

    public static void main(String[] args) {
        Emulator user = new Emulator();
        user.setCacheDir("cacheDir");
        user.loadData("Names.txt");
        user.loadData("Names.txt");
        user.loadData("Prof.txt");
        user.loadData("Prof.txt");
        System.out.println(user.getData("Names.txt"));
        System.out.println(user.getData("Prof.txt"));
    }
}
