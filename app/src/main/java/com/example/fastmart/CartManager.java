package com.example.fastmart;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.ArrayList;
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

    public ArrayList<Product> getCartItems() {
        return new ArrayList<>(productMap.values());
    }

    public void addToCart(Context context, Product product) {
        int id = product.getProductId();
        if (cartMap.containsKey(id)) {
            cartMap.put(id, cartMap.get(id) + 1);
        } else {
            cartMap.put(id, 1);
            productMap.put(id, product);
        }
        saveToPrefs(context);
    }

    public void updateQuantity(Context context, int productId, int newQuantity) {
        if (newQuantity <= 0) {
            removeFromCart(context, productId);
        } else {
            cartMap.put(productId, newQuantity);
            saveToPrefs(context);
        }
    }

    public void removeFromCart(Context context, int productId) {
        cartMap.remove(productId);
        productMap.remove(productId);
        saveToPrefs(context);
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

    public void saveToPrefs(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("cart", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        for (Map.Entry<Integer, Integer> entry : cartMap.entrySet()) {
            editor.putInt("qty_" + entry.getKey(), entry.getValue());
        }
        editor.putString("cart_ids", TextUtils.join(",", cartMap.keySet()));
        editor.apply();
    }

    public void loadFromPrefs(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("cart", Context.MODE_PRIVATE);
        String ids = prefs.getString("cart_ids", "");
        if (ids.isEmpty()) return;

        for (String id : ids.split(",")) {
            if (id.isEmpty()) continue;
            int productId = Integer.parseInt(id.trim());
            int qty = prefs.getInt("qty_" + productId, 1);

            // get directly from ProductList instead of FavouritesManager
            Product p = null;
            for (Product product : ProductList.getProducts()) {
                if (product.getProductId() == productId) {
                    p = product;
                    break;
                }
            }

            if (p != null) {
                productMap.put(productId, p);
                cartMap.put(productId, qty);
            }
        }
    }
}