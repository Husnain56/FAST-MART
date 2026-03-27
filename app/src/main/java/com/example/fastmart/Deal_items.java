package com.example.fastmart;

import java.util.ArrayList;

public class Deal_items {

    public static ArrayList<Product> getDealProducts() {
        ArrayList<Product> deals = new ArrayList<>();

        deals.add(new Product(
                101,
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
                102,
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
                103,
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
                104,
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
                105,
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