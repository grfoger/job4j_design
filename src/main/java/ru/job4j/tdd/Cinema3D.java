package ru.job4j.tdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {

    public List<Session> find(Predicate<Session> filter) {
        return Collections.emptyList();
    }
    public Ticket buy(Account account, int row, int column, Calendar date) {
        return null;
    }

    public void add(Session session) {
    }
}
