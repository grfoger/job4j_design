package ru.job4j.design.lsp;



import java.util.ArrayList;
import java.util.List;

public class ControlQualityFood implements ControlQuality {

    private List<FoodStore> stores = new ArrayList<>();

    public ControlQualityFood() {
        stores.add(new Shop());
        stores.add(new Warehouse());
        stores.add(new Trash());
    }

    public void checkFood(Food food) {
        for (FoodStore fs : stores) {
            if (fs.accept(food)) {
                fs.add(food);
                break;
            }
        }
    }

    public List<FoodStore> getStores() {
        return List.copyOf(stores);
    }

    public boolean resort() {
        boolean isResort = false;
        List<Food> foods = new ArrayList<>();
        for (FoodStore fs:stores) {
            isResort = foods.addAll(fs.getFoodList());
        }
        stores.clear();
        stores.add(new Shop());
        stores.add(new Warehouse());
        stores.add(new Trash());
        foods.forEach(this::checkFood);
        return isResort;
    }
}
