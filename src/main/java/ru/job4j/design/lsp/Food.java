package ru.job4j.design.lsp;

import java.util.Date;

public class Food {

    private String name;
    private Date expiryDate;
    private Date createDate;
    private float price;
    private int discount;

    public Food(String name, Date expiryDate, Date createDate, float price, int discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Food food = (Food) o;

        if (Float.compare(food.price, price) != 0) {
            return false;
        }
        if (discount != food.discount) {
            return false;
        }
        if (name != null ? !name.equals(food.name) : food.name != null) {
            return false;
        }
        if (expiryDate != null ? !expiryDate.equals(food.expiryDate) : food.expiryDate != null) {
            return false;
        }
        return createDate != null ? createDate.equals(food.createDate) : food.createDate == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (expiryDate != null ? expiryDate.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + discount;
        return result;
    }
}
