package ru.job4j.design.parking;

public class LightCar implements Car {
    public static final int LIGHTCARSIZE = 1;

    public LightCar() {
    }

    @Override
    public int getSize() {
        return LIGHTCARSIZE;
    }
}
