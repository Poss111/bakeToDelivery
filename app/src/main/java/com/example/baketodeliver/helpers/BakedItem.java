package com.example.baketodeliver.helpers;

import java.util.Objects;

/**
 * Object to store Baked Item Details
 *
 * @author Daniel Poss
 */
public class BakedItem {

    private String bakerName;
    private String foodName;
    private Double price;

    public BakedItem(String bakerName, String foodName, Double price) {
        this.bakerName = bakerName;
        this.foodName = foodName;
        this.price = price;
    }

    public String getBakerName() {
        return bakerName;
    }

    public void setBakerName(String bakerName) {
        this.bakerName = bakerName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BakedItem bakedItem = (BakedItem) o;
        return Objects.equals(bakerName, bakedItem.bakerName) &&
                Objects.equals(foodName, bakedItem.foodName) &&
                Objects.equals(price, bakedItem.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bakerName, foodName, price);
    }

    @Override
    public String toString() {
        return "BakedItem{" +
                "bakerName='" + bakerName + '\'' +
                ", foodName='" + foodName + '\'' +
                ", price=" + price +
                '}';
    }

}
