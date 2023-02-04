package com.example.storeapps.Model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.math.BigDecimal;

public class Products implements Serializable {
    private static final String TAG = "Products";

    private String title;
    private String description;
    private String sudDescriptions;
    private String image;
    private int price;
    private int salePrice;
    private float num_rating;
    private int rating;
    private int saledNumber;
    private int serial_number;

    public Products()
    {

    }

    public Products(String title, String description, String subDescriptions, String image, int price, int salePrice, float num_rating, int rating, int saledNumber, int serial_number) {
        this.title = title;
        this.description = description;
        this.sudDescriptions = subDescriptions;
        this.image = image;
        this.price = price;
        this.salePrice = salePrice;
        this.num_rating = num_rating;
        this.rating = rating;
        this.saledNumber = saledNumber;
        this.serial_number = serial_number;
    }

    protected Products(Parcel in) {
        title = in.readString();
        description = in.readString();
        sudDescriptions = in.readString();
        image = in.readString();
        price = in.readInt();
        salePrice = in.readInt();
        num_rating = in.readFloat();
        rating = in.readInt();
        saledNumber = in.readInt();
        serial_number = in.readInt();
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSudDescriptions() {
        return sudDescriptions;
    }

    public void setSudDescriptions(String sudDescriptions) {
        this.sudDescriptions = sudDescriptions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public float getNum_rating() {
        return num_rating;
    }

    public void setNum_rating(float num_rating) {
        this.num_rating = num_rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getSaledNumber() {
        return saledNumber;
    }

    public void setSaledNumber(int saledNumber) {
        this.saledNumber = saledNumber;
    }

    public int getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(int serial_number) {
        this.serial_number = serial_number;
    }




}
