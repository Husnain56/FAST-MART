package com.example.fastmart;

import java.util.ArrayList;

public class Deal_items {

    public static ArrayList<Product> getDealProducts() {
        ArrayList<Product> deals = new ArrayList<>();

        deals.add(new Product(
                "RØDE PodMic",
                "Microphones",
                "Dynamic microphone, Speaker microphone",
                108.20,
                199.99,
                R.drawable.ic_launcher_foreground,
                false,
                true
        ));

        deals.add(new Product(
                "Sony WH-1000XM4",
                "Headphones",
                "Industry leading noise cancellation",
                249.99,
                349.99,
                R.drawable.ic_launcher_foreground,
                false,
                true
        ));

        deals.add(new Product(
                "Samsung 27\" Monitor",
                "Monitors",
                "4K UHD, 60Hz, IPS Panel",
                299.99,
                449.99,
                R.drawable.ic_launcher_foreground,
                false,
                true
        ));

        deals.add(new Product(
                "Logitech MX Master 3",
                "Accessories",
                "Advanced wireless mouse for Mac & PC",
                79.99,
                99.99,
                R.drawable.ic_launcher_foreground,
                false,
                true
        ));

        deals.add(new Product(
                "Apple AirPods Pro",
                "Earbuds",
                "Active noise cancellation, MagSafe charging",
                189.99,
                249.99,
                R.drawable.ic_launcher_foreground,
                false,
                true
        ));

        return deals;
    }
}