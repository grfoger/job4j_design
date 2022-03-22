package ru.job4j.design.parking;

public class LightCar implements Car {
    int size;

    public LightCar() {
        this.size = 1;
    }

    @Override
    public int getSize() {
        return size;
    }
}
