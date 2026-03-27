package com.example.fastmart;

import java.util.LinkedHashMap;
import java.util.Map;

public class CartManager {

    private static CartManager instance;
    // Maps productId → quantity
    private final LinkedHashMap<Integer, Integer> cartMap = new LinkedHashMap<>();
    private final LinkedHashMap<Integer, Product> productMap = new LinkedHashMap<>();

    private CartManager() {}

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addToCart(Product product) {
        int id = product.getProductId();
        if (cartMap.containsKey(id)) {
            cartMap.put(id, cartMap.get(id) + 1); // increment if already in cart
        } else {
            cartMap.put(id, 1); // start with quantity 1
            productMap.put(id, product);
        }
    }

    public int getQuantity(int productId) {
        return cartMap.getOrDefault(productId, 0);
    }

    public Map<Integer, Integer> getCartMap() {
        return cartMap;
    }

    public Map<Integer, Product> getProductMap() {
        return productMap;
    }
}