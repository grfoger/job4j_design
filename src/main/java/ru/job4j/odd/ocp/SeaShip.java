package ru.job4j.odd.ocp;

public class SeaShip extends SpaceShip {
    @Override
    public String move(String point) {
        return "переплыв на винтах совершён в " + point;
    }
}
/**
 * 3)ошибка на уровне наследования: ресширение переопределением не верное так как SeaShip != SpaceShip
 */