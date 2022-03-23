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
        stores.forEach(s -> {
            if (s.accept(food)) {
                s.add(food);
            }
        });
    }

    public List<FoodStore> getStores() {
        return List.copyOf(stores);
    }
}
