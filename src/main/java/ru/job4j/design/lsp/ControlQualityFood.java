package ru.job4j.design.lsp;

import java.util.Date;

public class ControlQualityFood implements ControllQuality {

    FoodStore foodStore;

    public ControlQualityFood(FoodStore foodStore) {
        this.foodStore = foodStore;
    }

    public void setFoodStore(FoodStore foodStore) {
        this.foodStore = foodStore;
    }

    public FoodStore getFoodStore() {
        return foodStore;
    }

    public void checkFood(Food food) {
        validate(food);
        foodStore.add(food);
    }

    private void validate(Food food) {
        long foodLife = food.getExpiryDate().getTime() - food.getCreateDate().getTime();
        long now = new Date().getTime();
        int condition = (int) ((now - food.getCreateDate().getTime()) * 100 / foodLife);
        if (condition <= 25) {
            foodStore = new Warehouse();
        } else if (condition <= 75) {
            foodStore = new Shop();
        } else if (condition <= 100) {
            food.setPrice(food.getPrice() * (1 - (float) food.getDiscount() / 100));
            foodStore = new Shop();
        } else {
            foodStore = new Trash();
        }
    }

}
