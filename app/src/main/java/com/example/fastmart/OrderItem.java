package com.example.fastmart;

public class OrderItem {
    private String name;
    private double price;
    private String category;
    private int quantity;
    private String imageUrl;

    public OrderItem() {}

    public String getName()          { return name; }
    public double getPrice()         { return price; }
    public String getCategory()      { return category; }
    public int getQuantity()         { return quantity; }
    public String getImageUrl()      { return imageUrl; } // NEW

    public void setName(String name)          { this.name = name; }
    public void setPrice(double price)        { this.price = price; }
    public void setCategory(String category)  { this.category = category; }
    public void setQuantity(int quantity)     { this.quantity = quantity; }
    public void setImageUrl(String imageUrl)  { this.imageUrl = imageUrl; } // NEW
}