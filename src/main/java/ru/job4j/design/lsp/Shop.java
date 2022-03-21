package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class Shop implements FoodStore {

    private List<Food> list = new ArrayList<>();

    public void add(Food food) {
        list.add(food);
    }

    public List<Food> getFoodList() {
        return list;
    }
}
