package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        int i = row;
        int j = column - 1;
        while (i < data.length) {
            if (data[i].length == j) {
                i++;
            }
            if (data[i].length == 0) {
                i++;
                j = 0;
                continue;
            }
            return true;
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        while (row < data.length) {
            if (data[row].length == column) {
                row++;
                column = 0;
            }
            if (data[row].length == 0) {
                row++;
                column = 0;
                continue;
            }
            return data[row][column++];
        }
        return null;
    }
}