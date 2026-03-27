package com.example.fastmart;

import java.util.ArrayList;

public class ProductList {

    public static ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();

        // ── Headphones & Earbuds ───────────────────────────────────────────────
        products.add(new Product(1,  "Sony WH-1000XM4 Black",   "Headphones", "Model: WH-1000XM4, Black",               249.99, 349.99, R.drawable.ic_launcher_foreground, false, true));
        products.add(new Product(2,  "Sony WH-1000XM4 Beige",   "Headphones", "Model: WH-1000XM4, Beige",               249.99, 349.99, R.drawable.ic_launcher_foreground, false, false));
        products.add(new Product(3,  "Apple AirPods Pro",        "Earbuds",    "Active noise cancellation, MagSafe",     189.99, 249.99, R.drawable.ic_launcher_foreground, false, true));
        products.add(new Product(4,  "Samsung Galaxy Buds 2",    "Earbuds",    "Active noise cancellation, IPX7",         89.99, 149.99, R.drawable.ic_launcher_foreground, false, false));

        // ── Microphones ────────────────────────────────────────────────────────
        products.add(new Product(5,  "RØDE PodMic",              "Microphones","Dynamic microphone, Speaker microphone", 108.20, 199.99, R.drawable.ic_launcher_foreground, false, true));
        products.add(new Product(6,  "Blue Yeti USB Mic",        "Microphones","Plug & play, 4 pickup patterns",          99.99, 129.99, R.drawable.ic_launcher_foreground, false, false));

        // ── Monitors ──────────────────────────────────────────────────────────
        products.add(new Product(7,  "Samsung 27\" Monitor",     "Monitors",   "4K UHD, 60Hz, IPS Panel",               299.99, 449.99, R.drawable.ic_launcher_foreground, false, true));
        products.add(new Product(8,  "LG UltraWide 34\"",        "Monitors",   "21:9, 144Hz, 1ms response time",        379.99, 499.99, R.drawable.ic_launcher_foreground, false, false));

        // ── Laptops & Tablets ─────────────────────────────────────────────────
        products.add(new Product(9,  "MacBook Pro 14\"",         "Laptops",    "M3 chip, 16GB RAM, 512GB SSD",         1599.99,1999.99, R.drawable.ic_launcher_foreground, false, true));
        products.add(new Product(10, "Dell XPS 15",              "Laptops",    "Intel i7, 16GB RAM, 512GB SSD",         999.99,1299.99, R.drawable.ic_launcher_foreground, false, false));
        products.add(new Product(11, "iPad Air",                 "Tablets",    "M1 chip, 10.9-inch Liquid Retina",      749.99, 899.99, R.drawable.ic_launcher_foreground, false, false));

        // ── Phones ────────────────────────────────────────────────────────────
        products.add(new Product(12, "Samsung Galaxy S24",       "Phones",     "6.2-inch, 128GB, Snapdragon 8",         799.99, 999.99, R.drawable.ic_launcher_foreground, false, true));
        products.add(new Product(13, "iPhone 15",                "Phones",     "6.1-inch Super Retina XDR, 128GB",      899.99,1099.99, R.drawable.ic_launcher_foreground, false, false));

        // ── Accessories ───────────────────────────────────────────────────────
        products.add(new Product(14, "Logitech MX Master 3",     "Accessories","Advanced wireless mouse for Mac & PC",   79.99,  99.99, R.drawable.ic_launcher_foreground, false, false));
        products.add(new Product(15, "Mechanical Keyboard",      "Accessories","TKL RGB, Brown switches",                69.99,  89.99, R.drawable.ic_launcher_foreground, false, false));
        products.add(new Product(16, "USB-C Hub 7-in-1",         "Accessories","HDMI, USB 3.0, SD card reader",          29.99,  49.99, R.drawable.ic_launcher_foreground, false, false));
        products.add(new Product(17, "Webcam 4K",                "Accessories","4K 30fps, built-in mic, autofocus",      99.99, 149.99, R.drawable.ic_launcher_foreground, false, false));

        // ── Storage ───────────────────────────────────────────────────────────
        products.add(new Product(18, "Portable SSD 1TB",         "Storage",    "USB 3.2, 1050MB/s read speed",           89.99, 119.99, R.drawable.ic_launcher_foreground, false, true));

        // ── Clothing ──────────────────────────────────────────────────────────
        products.add(new Product(19, "Jeans",                    "Clothing",   "Slim fit denim jeans",                   39.99,  59.99, R.drawable.ic_launcher_foreground, false, false));
        products.add(new Product(20, "Casual Clothes",           "Clothing",   "Everyday casual wear set",               49.99,  69.99, R.drawable.ic_launcher_foreground, false, false));
        products.add(new Product(21, "Hoodie",                   "Clothing",   "Pullover fleece hoodie",                 34.99,  54.99, R.drawable.ic_launcher_foreground, false, false));
        products.add(new Product(22, "V-Neck T-Shirt",           "Clothing",   "Cotton slim-fit v-neck",                 19.99,  29.99, R.drawable.ic_launcher_foreground, false, false));
        products.add(new Product(23, "Winter Clothes",           "Clothing",   "Warm winter jacket & scarf set",         79.99, 109.99, R.drawable.ic_launcher_foreground, false, false));

        // ── Footwear ──────────────────────────────────────────────────────────
        products.add(new Product(24, "Nike Shoes Black",         "Footwear",   "Air Max running shoes, black",           89.99, 119.99, R.drawable.ic_launcher_foreground, false, true));

        // ── Home ──────────────────────────────────────────────────────────────
        products.add(new Product(25, "Desk Lamp LED",            "Home",       "Touch dimmer, USB charging port",        24.99,  39.99, R.drawable.ic_launcher_foreground, false, false));

        return products;
    }

    public static ArrayList<Product> getDeals() {
        ArrayList<Product> deals = new ArrayList<>();
        for (Product p : getProducts()) {
            if (p.onDeal()) deals.add(p);
        }
        return deals;
    }

    public static ArrayList<Product> getRecommended() {
        ArrayList<Product> recommended = new ArrayList<>();
        for (Product p : getProducts()) {
            if (!p.onDeal()) recommended.add(p);
        }
        return recommended;
    }
}