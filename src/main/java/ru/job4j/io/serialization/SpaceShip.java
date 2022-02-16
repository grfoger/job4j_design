package ru.job4j.io.serialization;

public class SpaceShip {

    private static int id = 1;
    private final String shipType;
    private final float fuelCapacity;
    private final int shipLength;

    public SpaceShip(String shipType, float fuelCapacity, int shipLength) {
        this.shipType = shipType;
        this.fuelCapacity = fuelCapacity;
        this.shipLength = shipLength;
        id++;
    }

    @Override
    public String toString() {
        return "SpaceShip{"
                + "shipType='" + shipType + '\''
                + ", fuelCapacity=" + fuelCapacity
                + ", shipLength=" + shipLength
                + '}';
    }
}
