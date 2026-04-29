package com.example.fastmart;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProductDetails extends AppCompatActivity {

    ImageView item_image, btn_back;
    TextView item_price, item_name, item_desc, item_details;
    Button btn_buy;
    String currentUserUid;
    CartDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        String productId = getIntent().getStringExtra("productId");
        String sellerUid = getIntent().getStringExtra("sellerUid");
        boolean isSeller = sellerUid.equals(currentUserUid);

        DatabaseReference ref = isSeller
                ? FirebaseDatabase.getInstance().getReference()
                .child("users").child(sellerUid).child("products").child(productId)
                : FirebaseDatabase.getInstance().getReference()
                .child("products").child(productId);

        ref.get()
                .addOnSuccessListener(snapshot -> {
                    ProductItem product = snapshot.getValue(ProductItem.class);
                    if (product == null) return;

                    // ── Text fields ──────────────────────────────────────
                    item_name.setText(product.getName());
                    item_desc.setText(product.getDescription());
                    item_details.setText(product.getCategory());

                    if (product.isOnSale()) {
                        item_price.setText(String.format("$%.2f", product.getDiscountedPrice()));
                    } else {
                        item_price.setText(String.format("$%.2f", product.getOriginalPrice()));
                    }

                    // ── Product image ────────────────────────────────────
                    String imageUrl = product.getImageUrl();
                    if (!TextUtils.isEmpty(imageUrl)) {
                        Glide.with(this)
                                .load(imageUrl)
                                .placeholder(R.drawable.image_place_holder)
                                .error(R.drawable.image_place_holder)
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .centerCrop()
                                .into(item_image);
                        item_image.setVisibility(View.VISIBLE);
                    } else {
                        // No image uploaded for this product
                        item_image.setImageResource(R.drawable.image_place_holder);
                        item_image.setVisibility(View.VISIBLE);
                    }

                    // ── Seller vs buyer view ─────────────────────────────
                    if (isSeller) {
                        btn_buy.setVisibility(View.GONE);
                    } else {
                        btn_buy.setOnClickListener(v -> {
                            db.Open();
                            db.addToCart(product);
                            Toast.makeText(this,
                                    product.getName() + " added to cart!",
                                    Toast.LENGTH_SHORT).show();
                            db.Close();
                        });
                    }
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Failed to load product", Toast.LENGTH_SHORT).show());

        btn_back.setOnClickListener(v -> finish());
    }

    private void init() {
        item_image   = findViewById(R.id.product_image);
        item_price   = findViewById(R.id.lbl_price);
        item_name    = findViewById(R.id.lbl_name);
        item_desc    = findViewById(R.id.lbl_desc);
        item_details = findViewById(R.id.lbl_details);
        btn_buy      = findViewById(R.id.btn_buy);
        btn_back     = findViewById(R.id.btnback);
        db           = new CartDB(this);
    }
}