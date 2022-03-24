package ru.job4j.design.parking;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;


public class ParkingTest {


    @Test
    public void whenRightParkFirstLight() {
        Parking park = new SimpleParking(2, 5);
        for (int i = 0; i < 5; i++) {
            Assert.assertTrue(park.put(new LightCar()));
        }
        Assert.assertTrue(park.put(new Truck(2)));
        Assert.assertTrue(park.put(new Truck(30)));
        Assert.assertFalse(park.put(new LightCar()));
        Assert.assertFalse(park.put(new Truck(2)));
    }


    @Test
    public void whenRightParkFirstTruck() {
        Parking park = new SimpleParking(2, 5);
        Assert.assertTrue(park.put(new Truck(2)));
        Assert.assertTrue(park.put(new Truck(30)));
        for (int i = 0; i < 5; i++) {
            Assert.assertTrue(park.put(new LightCar()));
        }
        Assert.assertFalse(park.put(new LightCar()));
        Assert.assertFalse(park.put(new Truck(2)));
    }


    @Test
    public void whenMoreTrucksAndItsRight() {
        Parking park = new SimpleParking(1, 5);
        Assert.assertTrue(park.put(new Truck(5)));
        Assert.assertTrue(park.put(new Truck(2)));
        Assert.assertTrue(park.put(new Truck(2)));
        Assert.assertTrue(park.put(new LightCar()));
        Assert.assertFalse(park.put(new LightCar()));
        Assert.assertFalse(park.put(new Truck(2)));
    }


    @Test
    public void whenOnlyTrucks() {
        Parking park = new SimpleParking(1, 5);
        Assert.assertTrue(park.put(new Truck(5)));
        Assert.assertTrue(park.put(new Truck(2)));
        Assert.assertTrue(park.put(new Truck(3)));
        Assert.assertFalse(park.put(new Truck(2)));
        Assert.assertFalse(park.put(new LightCar()));
    }


    @Test
    public void whenTrucksPark() {
        Parking park = new SimpleParking(4, 0);
        Assert.assertTrue(park.put(new Truck(5)));
        Assert.assertTrue(park.put(new Truck(2)));
        Assert.assertFalse(park.put(new LightCar()));
        Assert.assertTrue(park.put(new Truck(3)));
        Assert.assertTrue(park.put(new Truck(2)));
        Assert.assertFalse(park.put(new Truck(2)));
        Assert.assertFalse(park.put(new LightCar()));
    }


    @Test
    public void whenLightCarsPark() {
        Parking park = new SimpleParking(0, 5);
        Assert.assertTrue(park.put(new Truck(2)));
        Assert.assertTrue(park.put(new Truck(2)));
        Assert.assertTrue(park.put(new LightCar()));
        Assert.assertFalse(park.put(new LightCar()));
        Assert.assertFalse(park.put(new Truck(2)));
    }

    @Test
    public void whenOnlyLightCars() {
        Parking park = new SimpleParking(0, 4);
        Assert.assertTrue(park.put(new LightCar()));
        Assert.assertTrue(park.put(new LightCar()));
        Assert.assertTrue(park.put(new LightCar()));
        Assert.assertTrue(park.put(new LightCar()));
        Assert.assertFalse(park.put(new LightCar()));
        Assert.assertFalse(park.put(new Truck(2)));
    }
}
