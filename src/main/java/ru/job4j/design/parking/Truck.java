package ru.job4j.design.parking;

public class Truck implements Car {
    int size;

    public Truck(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
