package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class Shop implements FoodStore {

    private List<Food> list = new ArrayList<>();

    public boolean add(Food food) {
        list.add(food);
        return true;
    }

    public List<Food> getFoodList() {
        return List.copyOf(list);
    }
}
