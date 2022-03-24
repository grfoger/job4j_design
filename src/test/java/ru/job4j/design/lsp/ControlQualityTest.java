package ru.job4j.design.lsp;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ControlQualityTest {

    @Test
    public void whenToWarehouse() {
        Food milk = new Food("молоко",
                java.sql.Date.valueOf(LocalDate.now().plusDays(10)),
                java.sql.Date.valueOf(LocalDate.now().minusDays(1)),
                100.0f, 10);
        ControlQualityFood cq = new ControlQualityFood();
        cq.checkFood(milk);
        Assert.assertEquals(cq.getStores().get(1).getFoodList(), Arrays.asList(milk));
    }

    @Test
    public void whenToShop() {
        Food milk = new Food("молоко",
                java.sql.Date.valueOf(LocalDate.now().plusDays(5)),
                java.sql.Date.valueOf(LocalDate.now().minusDays(5)),
                100.0f, 10);
        ControlQualityFood cq = new ControlQualityFood();
        cq.checkFood(milk);
        Assert.assertEquals(cq.getStores().get(0).getFoodList(), Arrays.asList(milk));
        Assert.assertEquals(100.0f, milk.getPrice(), 0.01);
    }

    @Test
    public void whenToShopWithDiscount() {
        Food milk = new Food("молоко",
                java.sql.Date.valueOf(LocalDate.now().plusDays(1)),
                java.sql.Date.valueOf(LocalDate.now().minusDays(10)),
                100.0f, 10);
        ControlQualityFood cq = new ControlQualityFood();
        cq.checkFood(milk);
        Assert.assertEquals(cq.getStores().get(0).getFoodList(), Arrays.asList(milk));
        Assert.assertEquals(90.0f, milk.getPrice(), 0.01);
    }

    @Test
    public void whenToTrash() {
        Food milk = new Food("молоко",
                java.sql.Date.valueOf(LocalDate.now().minusDays(1)),
                java.sql.Date.valueOf(LocalDate.now().minusDays(10)),
                100.0f, 10);
        ControlQualityFood cq = new ControlQualityFood();
        cq.checkFood(milk);
        Assert.assertEquals(cq.getStores().get(2).getFoodList(), Arrays.asList(milk));
    }

    @Test
    public void whenCheckDifferentProducts() {
        Food milk = new Food("молоко",
                java.sql.Date.valueOf(LocalDate.now().minusDays(1)),
                java.sql.Date.valueOf(LocalDate.now().minusDays(10)),
                100.0f, 10);
        Food bread = new Food("хлеб",
                java.sql.Date.valueOf(LocalDate.now().plusDays(1)),
                java.sql.Date.valueOf(LocalDate.now().minusDays(10)),
                100.0f, 10);
        Food eggs = new Food("яйца",
                java.sql.Date.valueOf(LocalDate.now().plusDays(10)),
                java.sql.Date.valueOf(LocalDate.now().minusDays(10)),
                100.0f, 10);
        Food butter = new Food("масло",
                java.sql.Date.valueOf(LocalDate.now().plusDays(10)),
                java.sql.Date.valueOf(LocalDate.now().minusDays(1)),
                100.0f, 10);
        ControlQualityFood cq = new ControlQualityFood();
        cq.checkFood(milk);
        cq.checkFood(bread);
        cq.checkFood(eggs);
        cq.checkFood(butter);
        Assert.assertEquals(cq.getStores().get(0).getFoodList(), Arrays.asList(bread, eggs));
        Assert.assertEquals(cq.getStores().get(1).getFoodList(), Arrays.asList(butter));
        Assert.assertEquals(cq.getStores().get(2).getFoodList(), Arrays.asList(milk));
    }

    @Test
    public void whenResort() {
        Food milk = new Food("молоко",
                java.sql.Date.valueOf(LocalDate.now().minusDays(1)),
                java.sql.Date.valueOf(LocalDate.now().minusDays(10)),
                100.0f, 10);
        Food bread = new Food("хлеб",
                java.sql.Date.valueOf(LocalDate.now().plusDays(1)),
                java.sql.Date.valueOf(LocalDate.now().minusDays(10)),
                100.0f, 10);
        Food eggs = new Food("яйца",
                java.sql.Date.valueOf(LocalDate.now().plusDays(10)),
                java.sql.Date.valueOf(LocalDate.now().minusDays(10)),
                100.0f, 10);
        Food butter = new Food("масло",
                java.sql.Date.valueOf(LocalDate.now().plusDays(10)),
                java.sql.Date.valueOf(LocalDate.now().minusDays(1)),
                100.0f, 10);
        ControlQualityFood cq = new ControlQualityFood();
        cq.checkFood(milk);
        cq.checkFood(bread);
        cq.checkFood(eggs);
        cq.checkFood(butter);
        assertTrue(cq.resort());
        Assert.assertEquals(cq.getStores().get(0).getFoodList(), Arrays.asList(bread, eggs));
        Assert.assertEquals(cq.getStores().get(1).getFoodList(), Arrays.asList(butter));
        Assert.assertEquals(cq.getStores().get(2).getFoodList(), Arrays.asList(milk));
    }
}
