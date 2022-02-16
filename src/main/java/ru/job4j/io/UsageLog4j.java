package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte a = 1;
        int i = 1;
        char c = 'c';
        double d = 1.0;
        float f = 2.0f;
        short s = 1;
        long l = 1;
        boolean b = true;

        LOG.info("byte:{},int:{},char:{},double:{},float:{},short:{},long:{},boolean:{}", a, i, c, d, f, s, l, b);
    }
}