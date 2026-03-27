package com.example.fastmart;

import java.util.ArrayList;

public class Recommended_items {

    public static ArrayList<Product> getRecommendedProducts() {
        ArrayList<Product> items = new ArrayList<>();

        items.add(new Product(
                201,
                "MacBook Air M2",
                "Laptops",
                "8-core CPU, 8GB RAM, 256GB SSD",
                999.99,
                1299.99,
                R.drawable.ic_launcher_foreground,
                false,
                true
        ));

        items.add(new Product(
                202,
                "iPad Pro 11\"",
                "Tablets",
                "M2 chip, Liquid Retina display, 128GB",
                799.99,
                899.99,
                R.drawable.ic_launcher_foreground,
                false,
                true
        ));

        items.add(new Product(
                203,
                "Mechanical Keyboard",
                "Accessories",
                "TKL, RGB backlit, Blue switches",
                59.99,
                89.99,
                R.drawable.ic_launcher_foreground,
                false,
                true
        ));

        items.add(new Product(
                204,
                "GoPro Hero 12",
                "Cameras",
                "5.3K video, HyperSmooth 6.0, waterproof",
                349.99,
                449.99,
                R.drawable.ic_launcher_foreground,
                false,
                true
        ));

        items.add(new Product(
                205,
                "Anker 65W Charger",
                "Chargers",
                "GaN, 3-port, USB-C & USB-A, foldable plug",
                35.99,
                49.99,
                R.drawable.ic_launcher_foreground,
                false,
                true
        ));

        return items;
    }
}