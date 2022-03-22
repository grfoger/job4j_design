package ru.job4j.design.lsp;


import java.util.List;

public interface FoodStore {
    boolean add(Food food);
    List<Food> getFoodList();
}
