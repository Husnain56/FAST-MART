package com.example.fastmart;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;

public class FavouritesManager {

    private static FavouritesManager instance;

    // The one shared list — all fragments reference this same instance
    private final ArrayList<Product> allProducts;

    private FavouritesManager() {
        allProducts = ProductList.getProducts();
    }

    public static FavouritesManager getInstance() {
        if (instance == null) {
            instance = new FavouritesManager();
        }
        return instance;
    }

    public ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public ArrayList<Product> getFavourites() {
        ArrayList<Product> favs = new ArrayList<>();
        for (Product p : allProducts) {
            if (p.isFavourite()) favs.add(p);
        }
        return favs;
    }

    public Product findById(int productId) {
        for (Product p : allProducts) {
            if (p.getProductId() == productId) return p;
        }
        return null;
    }

    public void saveToPrefs(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("favList", Context.MODE_PRIVATE);
        prefs.edit().putString("favourites", "").apply();
    }
}