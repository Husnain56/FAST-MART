package com.example.fastmart;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

public class CreateProduct extends AppCompatActivity {

    EditText etProductName, etProductType, etProductPrice, etProductDescription;
    Button btnAddProduct;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase db;
    DatabaseReference productsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        setListeners();
    }

    private void init() {
        etProductName = findViewById(R.id.etProductName);
        etProductType = findViewById(R.id.etProductType);
        etProductPrice = findViewById(R.id.etProductPrice);
        etProductDescription = findViewById(R.id.etProductDescription);
        btnAddProduct = findViewById(R.id.btnAddProduct);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        db = FirebaseDatabase.getInstance();

        String uid = user.getUid();
        productsRef = FirebaseDatabase.getInstance().getReference()
                .child("users")
                .child(uid)
                .child("products");
    }

    private void setListeners() {
        btnAddProduct.setOnClickListener(v -> {
            String name = etProductName.getText().toString().trim();
            String type = etProductType.getText().toString().trim();
            String priceStr = etProductPrice.getText().toString().trim();
            String description = etProductDescription.getText().toString().trim();

            if (TextUtils.isEmpty(name)) {
                etProductName.setError("Product name is required");
                return;
            }
            if (TextUtils.isEmpty(type)) {
                etProductType.setError("Product type is required");
                return;
            }
            if (TextUtils.isEmpty(priceStr)) {
                etProductPrice.setError("Product price is required");
                return;
            }
            if (TextUtils.isEmpty(description)) {
                etProductDescription.setError("Product description is required");
                return;
            }

            DatabaseReference newProductRef = productsRef.push();
            String productId = newProductRef.getKey();

            Map<String, Object> product = new HashMap<>();
            product.put("productId", productId);
            product.put("name", name);
            product.put("type", type);
            product.put("price", Double.parseDouble(priceStr));
            product.put("description", description);
            product.put("createdAt", ServerValue.TIMESTAMP);

            newProductRef.setValue(product)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Product added!", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });
    }
}