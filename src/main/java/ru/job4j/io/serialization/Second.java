package ru.job4j.io.serialization;

import org.json.JSONObject;

public class Second {
    private First f;

    public First getFirst() {
        return f;
    }

    public void setFirst(First f) {
        this.f = f;
    }

    public static void main(String[] args) {
        First f = new First();
        Second s = new Second();
        f.setSecond(s);
        s.setFirst(f);

        System.out.println(new JSONObject(s));
    }
}