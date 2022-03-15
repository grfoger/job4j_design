package ru.job4j.tdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {
    private List<Session> sessions = new ArrayList<>();

    public List<Session> find(Predicate<Session> filter) {
        return sessions;
    }
    public Ticket buy(Account account, int row, int column, Calendar date) {
        return new Ticket3D();
    }

    public void add(Session session) {
        sessions.add(session);
    }
}
