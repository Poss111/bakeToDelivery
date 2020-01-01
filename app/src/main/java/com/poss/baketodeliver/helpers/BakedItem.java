package com.poss.baketodeliver.helpers;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

/**
 * Object to store Baked Item Details
 *
 * @author Daniel Poss
 */
public class BakedItem implements Parcelable {

    private String bakerName;
    private String foodName;
    private Double price;

    public BakedItem(String bakerName, String foodName, Double price) {
        this.bakerName = bakerName;
        this.foodName = foodName;
        this.price = price;
    }

    protected BakedItem(Parcel in) {
        bakerName = in.readString();
        foodName = in.readString();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readDouble();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bakerName);
        dest.writeString(foodName);
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(price);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BakedItem> CREATOR = new Creator<BakedItem>() {
        @Override
        public BakedItem createFromParcel(Parcel in) {
            return new BakedItem(in);
        }

        @Override
        public BakedItem[] newArray(int size) {
            return new BakedItem[size];
        }
    };

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
