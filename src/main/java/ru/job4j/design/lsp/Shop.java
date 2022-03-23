package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Shop implements FoodStore {

    private List<Food> list = new ArrayList<>();

    public boolean add(Food food) {
        boolean isValid = accept(food);
        if (isValid) {
            if (getCondition(food) > 75) {
                food.setPrice(food.getPrice() * (1 - (float) food.getDiscount() / 100));
            }
            list.add(food);
        }
        return isValid;
    }

    public List<Food> getFoodList() {
        return List.copyOf(list);
    }

    public boolean accept(Food food) {
        boolean isValid = false;
        if (getCondition(food) <= 100 && getCondition(food) > 25) {
            isValid = true;
        }
        return isValid;
    }
}
