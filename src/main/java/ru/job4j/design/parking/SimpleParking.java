package ru.job4j.design.parking;

public class SimpleParking implements Parking {

    private Car[] trucks;

    private Car[] lightCars;

    public SimpleParking(int truckPlace, int carPlace) {
        trucks = new Car[truckPlace];
        lightCars = new Car[carPlace];
    }

    @Override
    public boolean put(Car car) {
        return false;
    }
}
