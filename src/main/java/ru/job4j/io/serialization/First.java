package ru.job4j.io.serialization;

import org.json.JSONPropertyIgnore;

public class First {
    private Second s;

    @JSONPropertyIgnore
    public Second getSecond() {
        return s;
    }

    public void setSecond(Second s) {
        this.s = s;
    }

    public String getHello() {
        return "Hello";
    }

}