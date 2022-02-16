package ru.job4j.io.serialization;

import java.util.Arrays;

public class SpaceGasStation {
    private boolean isBusy;
    private int availableFuel;
    private SpaceShip refuelType;
    private String[] operations;

    public SpaceGasStation(boolean isBusy, int availableFuel, SpaceShip refuelType, String[] operations) {
        this.isBusy = isBusy;
        this.availableFuel = availableFuel;
        this.refuelType = refuelType;
        this.operations = operations;
    }

    @Override
    public String toString() {
        return "SpaceGasStation{"
                + "isBusy=" + isBusy
                + ", availableFuel=" + availableFuel
                + ", refuelType=" + refuelType
                + ", operations=" + Arrays.toString(operations)
                + '}';
    }
}
