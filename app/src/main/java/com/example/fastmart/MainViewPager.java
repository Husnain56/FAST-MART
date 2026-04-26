package com.example.fastmart;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainViewPager extends AppCompatActivity implements FragmentCart.SmsHandler {

    ViewPager2 mainViewPager;
    ViewPagerAdapter adapter;
    TabLayout bottomTabLayout;
    TabLayoutMediator mediator;
    ArrayList<ProductItem> cart;
    private final int SMS_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_view_pager);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    public void init() {
        mainViewPager = findViewById(R.id.MainViewPager);
        adapter = new ViewPagerAdapter(this);
        mainViewPager.setAdapter(adapter);
        bottomTabLayout = findViewById(R.id.bottomTabLayout);
        mainViewPager.setOffscreenPageLimit(1);
        mainViewPager.setUserInputEnabled(false);

        mediator = new TabLayoutMediator(bottomTabLayout, mainViewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Home");
                    tab.setIcon(R.drawable.ic_home);
                    break;
                case 1:
                    tab.setText("Browse");
                    tab.setIcon(R.drawable.ic_browse);
                    break;
                case 2:
                    tab.setText("Favourites");
                    tab.setIcon(R.drawable.ic_favourites);
                    break;
                case 3:
                    tab.setText("Cart");
                    tab.setIcon(R.drawable.ic_cart);
                    break;
                case 4:
                    tab.setText("Profile");
                    tab.setIcon(R.drawable.ic_profile);
                    break;
            }
        });

        mediator.attach();
    }

    private void checkMessagePermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED) {
            sendMessage();
        } else {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.SEND_SMS},
                    SMS_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendMessage();
            } else {
                Toast.makeText(this, "You have to give permission to send message", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendMessage() {
        StringBuilder msg = new StringBuilder("Products bought:\n");
        for (ProductItem p : cart) {
            double price = p.isOnSale() ? p.getDiscountedPrice() : p.getOriginalPrice();
            msg.append("- ").append(p.getName())
                    .append(" ($").append(String.format("%.2f", price)).append(")\n");
        }
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("+923263874962", null, msg.toString(), null, null);
        storeOrders();
    }

    private void storeOrders() {
        String buyerUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference().child("orders");
        CartDB cartDB = new CartDB(this);
        cartDB.Open();

        Map<String, List<ProductItem>> itemsBySeller = new HashMap<>();
        for (ProductItem item : cart) {
            String sellerUid = item.getSellerUid();
            if (!itemsBySeller.containsKey(sellerUid)) {
                itemsBySeller.put(sellerUid, new ArrayList<>());
            }
            itemsBySeller.get(sellerUid).add(item);
        }

        for (Map.Entry<String, List<ProductItem>> entry : itemsBySeller.entrySet()) {
            String sellerUid = entry.getKey();
            List<ProductItem> sellerItems = entry.getValue();

            Map<String, Object> itemsMap = new HashMap<>();
            double total = 0;

            for (ProductItem item : sellerItems) {
                double price = item.isOnSale() ? item.getDiscountedPrice() : item.getOriginalPrice();
                int quantity = cartDB.getQuantity(item.getProductId());
                total += price * quantity;

                Map<String, Object> itemData = new HashMap<>();
                itemData.put("name",     item.getName());
                itemData.put("price",    price);
                itemData.put("category", item.getCategory());
                itemData.put("quantity", quantity);
                itemsMap.put(item.getProductId(), itemData);
            }

            Map<String, Object> order = new HashMap<>();
            order.put("buyerUid",   buyerUid);
            order.put("items",      itemsMap);
            order.put("totalPrice", total);
            order.put("orderedAt",  ServerValue.TIMESTAMP);
            order.put("status",     "pending");

            ordersRef.child(sellerUid).push().setValue(order);
        }

        cartDB.Close();
    }

    @Override
    public void checkSmsPermission(ArrayList<ProductItem> cartList) {
        this.cart = cartList;
        checkMessagePermissions();
    }
}