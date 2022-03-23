package ru.job4j.design.parking;

public class SimpleParking implements Parking {

    private int truckPlace;
    private int carPlace;

    private Car[] trucks = new Car[truckPlace];
    private Car[] lightCars = new Car[carPlace];

    public SimpleParking(int truckPlace, int carPlace) {
        this.truckPlace = truckPlace;
        this.carPlace = carPlace;
    }

    @Override
    public boolean put(Car car) {
        return false;
    }
}
