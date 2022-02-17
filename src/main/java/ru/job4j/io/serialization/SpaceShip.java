package ru.job4j.io.serialization;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "spaceShip")
public class SpaceShip {

    private static int id = 1;
    @XmlAttribute
    private String shipType;
    @XmlAttribute
    private float fuelCapacity;
    @XmlAttribute
    private int shipLength;

    public SpaceShip() {

    }

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
