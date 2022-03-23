package ru.job4j.design.parking;

public class Truck implements Car {
    private int size;

    public Truck(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
