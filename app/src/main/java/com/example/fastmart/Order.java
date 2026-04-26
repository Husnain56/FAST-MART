package com.example.fastmart;

import java.util.Map;

public class Order {
    private String orderId;
    private String buyerUid;
    private Map<String, OrderItem> items;
    private double totalPrice;
    private long orderedAt;
    private String status;

    public Order() {}

    public String getOrderId() { return orderId; }
    public String getBuyerUid() { return buyerUid; }
    public Map<String, OrderItem> getItems() { return items; }
    public double getTotalPrice() { return totalPrice; }
    public long getOrderedAt() { return orderedAt; }
    public String getStatus() { return status; }

    public void setOrderId(String orderId) { this.orderId = orderId; }
    public void setBuyerUid(String buyerUid) { this.buyerUid = buyerUid; }
    public void setItems(Map<String, OrderItem> items) { this.items = items; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public void setOrderedAt(long orderedAt) { this.orderedAt = orderedAt; }
    public void setStatus(String status) { this.status = status; }
}
