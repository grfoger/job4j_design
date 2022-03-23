package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Warehouse implements FoodStore {

    private List<Food> list = new ArrayList<>();

    public boolean add(Food food) {
        boolean isValid = accept(food);
        if (isValid) {
            list.add(food);
        }
        return isValid;
    }

    public List<Food> getFoodList() {
        return List.copyOf(list);
    }

    public boolean accept(Food food) {
        boolean isValid = false;
        if (getCondition(food) <= 25) {
            isValid = true;
        }
        return isValid;
    }
}
