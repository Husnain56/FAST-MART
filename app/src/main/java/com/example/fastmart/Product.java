package com.example.fastmart;

import java.io.Serializable;

public class Product implements Serializable {

    private int productId;
    private String name;
    private String category;
    private String description;
    private double price;
    private double originalPrice;
    private int imageResId;
    private boolean isFavourite;
    private boolean onDeal;

    // Constructor
    public Product(int id,String name, String category, String description,
                   double price, double originalPrice, int imageResId, boolean isFavourite, boolean onDeal) {
        this.productId = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.originalPrice = originalPrice;
        this.imageResId = imageResId;
        this.isFavourite = isFavourite;
        this.onDeal = onDeal;
    }

    // Getters

    public int getProductId(){ return this.productId; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public double getOriginalPrice() { return originalPrice; }
    public int getImageResId() { return imageResId; }
    public boolean isFavourite() { return isFavourite; }
    public boolean onDeal(){ return onDeal; }

    // Setters
    public void setProductId(int id) { this.productId = id; }
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }
    public void setOriginalPrice(double originalPrice) { this.originalPrice = originalPrice; }
    public void setImageResId(int imageResId) { this.imageResId = imageResId; }
    public void setFavourite(boolean favourite) { isFavourite = favourite; }

    public void setOnDeal(boolean onDeal) { this.onDeal = onDeal; }

}

