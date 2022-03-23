package ru.job4j.design.lsp;


import java.util.Date;
import java.util.List;

public interface FoodStore {
    boolean add(Food food);
    List<Food> getFoodList();
    boolean accept(Food food);
    default int getCondition(Food food) {
        long foodLife = food.getExpiryDate().getTime() - food.getCreateDate().getTime();
        long now = new Date().getTime();
        return (int) ((now - food.getCreateDate().getTime()) * 100 / foodLife);
    }
}
