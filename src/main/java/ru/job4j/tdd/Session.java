package ru.job4j.tdd;

import java.util.List;

public interface Session {
    public void setBusy(int row, int column);

    public boolean[][] getFreeSet();
}
