package ru.job4j.design.parking;

import org.junit.Ignore;
import org.junit.Test;


public class ParkingTest {

    @Test
    public void whenRightPark() {
        Parking park = new SimpleParking(5, 10);
        for (int i = 0; i < 5; i++) {
            park.put(new Truck(3));
        }
        for (int i = 0; i < 10; i++) {
            park.put(new LightCar());
        }
    }


    @Test
    public void whenMoreTrucksAndItsRight() {
        Parking park = new SimpleParking(5, 10);
        for (int i = 0; i < 7; i++) {
            park.put(new Truck(3));
        }
        for (int i = 0; i < 4; i++) {
            park.put(new LightCar());
        }
    }

    @Ignore
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenMoreTrucksAndItsWrong() {
        Parking park = new SimpleParking(5, 10);
        for (int i = 0; i < 7; i++) {
            park.put(new Truck(3));
        }
        for (int i = 0; i < 5; i++) {
            park.put(new LightCar());
        }
    }

    @Ignore
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenMoreLightCarsAndItsWrong() {
        Parking park = new SimpleParking(5, 10);
        for (int i = 0; i < 3; i++) {
            park.put(new Truck(3));
        }
        for (int i = 0; i < 11; i++) {
            park.put(new LightCar());
        }
    }

    @Ignore
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenOnlyLightCarsAndItsWrong() {
        Parking park = new SimpleParking(5, 10);
        for (int i = 0; i < 11; i++) {
            park.put(new LightCar());
        }
    }

    @Ignore
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenOnlyTrucksAndItsWrong() {
        Parking park = new SimpleParking(5, 10);
        for (int i = 0; i < 9; i++) {
            park.put(new Truck(3));
        }
    }
}
