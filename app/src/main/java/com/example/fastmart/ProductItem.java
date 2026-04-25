package com.example.fastmart;

public class ProductItem {
    private String productId;
    private String name;
    private String category;
    private double originalPrice;
    private double discountedPrice;
    private String description;
    private String sellerUid;
    private long createdAt;

    public ProductItem() {}

    public ProductItem(String productId, String name, String category, double originalPrice,
                       double discountedPrice, String description, String sellerUid, long createdAt) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
        this.description = description;
        this.sellerUid = sellerUid;
        this.createdAt = createdAt;
    }

    public String getProductId() { return productId; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getOriginalPrice() { return originalPrice; }
    public double getDiscountedPrice() { return discountedPrice; }
    public String getDescription() { return description; }
    public String getSellerUid() { return sellerUid; }
    public long getCreatedAt() { return createdAt; }

    public void setProductId(String productId) { this.productId = productId; }
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setOriginalPrice(double originalPrice) { this.originalPrice = originalPrice; }
    public void setDiscountedPrice(double discountedPrice) { this.discountedPrice = discountedPrice; }
    public void setDescription(String description) { this.description = description; }
    public void setSellerUid(String sellerUid) { this.sellerUid = sellerUid; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }

    public boolean isOnSale() {
        return discountedPrice < originalPrice;
    }
}