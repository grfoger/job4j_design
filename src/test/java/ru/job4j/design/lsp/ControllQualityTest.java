package ru.job4j.design.lsp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tdd.*;

import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ControllQualityTest {


    @Test
    public void whenToWarehouse() {
        Food milk = new Food("молоко",
                java.sql.Date.valueOf(LocalDate.now().plusDays(10)),
                java.sql.Date.valueOf(LocalDate.now().minusDays(1)),
                100.0f, 10 );
        ControllQuality cq = new ControlQualityFood(null);
        cq.checkFood(milk);
        FoodStore foodStore = cq.getFoodStore();
        Assert.assertEquals(Warehouse.class, foodStore.getClass());
        Assert.assertEquals(foodStore.getFoodList(), Arrays.asList(milk));
    }

    @Test
    public void whenToShop() {
        Food milk = new Food("молоко",
                java.sql.Date.valueOf(LocalDate.now().plusDays(5)),
                java.sql.Date.valueOf(LocalDate.now().minusDays(5)),
                100.0f, 10 );
        ControllQuality cq = new ControlQualityFood(null);
        cq.checkFood(milk);
        FoodStore foodStore = cq.getFoodStore();
        Assert.assertEquals(Shop.class, foodStore.getClass());
        Assert.assertEquals(foodStore.getFoodList(), Arrays.asList(milk));
        Assert.assertEquals(100.0f, milk.getPrice(), 0.01);
    }

    @Test
    public void whenToShopWithDiscount() {
        Food milk = new Food("молоко",
                java.sql.Date.valueOf(LocalDate.now().plusDays(1)),
                java.sql.Date.valueOf(LocalDate.now().minusDays(10)),
                100.0f, 10 );
        ControllQuality cq = new ControlQualityFood(null);
        cq.checkFood(milk);
        FoodStore foodStore = cq.getFoodStore();
        Assert.assertEquals(Shop.class, foodStore.getClass());
        Assert.assertEquals(foodStore.getFoodList(), Arrays.asList(milk));
        Assert.assertEquals(90.0f, milk.getPrice(), 0.01);
    }

    @Test
    public void whenToTrash() {
        Food milk = new Food("молоко",
                java.sql.Date.valueOf(LocalDate.now().minusDays(1)),
                java.sql.Date.valueOf(LocalDate.now().minusDays(10)),
                100.0f, 10 );
        ControllQuality cq = new ControlQualityFood(null);
        cq.checkFood(milk);
        FoodStore foodStore = cq.getFoodStore();
        Assert.assertEquals(Trash.class, foodStore.getClass());
        Assert.assertEquals(foodStore.getFoodList(), Arrays.asList(milk));
    }
}
