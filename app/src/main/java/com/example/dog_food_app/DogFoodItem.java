package com.example.dog_food_app;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DogFoodItem {
    private String name;
    private String description;
    private int imageResId;
    private String brand;
    private String type;
    private String age;
    private double price;

    public DogFoodItem(String name, String description, int imageResId, String brand, String type, String age,double price) {
        this.name = name;
        this.description = description;
        this.imageResId = imageResId;
        this.brand = brand;
        this.type = type;
        this.age = age;
        this.price=price;
    }

    // Getters for the fields
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getImageResId() { return imageResId; }
    public String getBrand() { return brand; }
    public String getType() { return type; }
    public String getAge() { return age; }
    public double getPrice(){return price;}
}
