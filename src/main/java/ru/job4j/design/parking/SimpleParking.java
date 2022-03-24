package ru.job4j.design.parking;

public class SimpleParking implements Parking {

    private int truckPlace;
    private int carPlace;

    private Car[] trucks;
    private Car[] lightCars;

    public SimpleParking(int truckPlace, int carPlace) {
        this.truckPlace = truckPlace;
        this.carPlace = carPlace;
        trucks = new Car[truckPlace];
        lightCars = new Car[carPlace];
    }

    @Override
    public boolean put(Car car) {
        boolean isPut = false;
        if (car.getSize() == 1 && carPlace > 0) {
           lightCars[carPlace - 1] = car;
            carPlace--;
            isPut = true;
        } else if (car.getSize() > 1 && truckPlace > 0) {
           trucks[truckPlace - 1] = car;
           truckPlace--;
           isPut = true;
        } else if (car.getSize() > 1 && truckPlace == 0 && carPlace >= car.getSize()) {
            lightCars[carPlace - 1] = car;
            carPlace -= car.getSize();
            isPut = true;
        }
        return isPut;
    }

}
