package com.example.fastmart;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

public class CreateProduct extends AppCompatActivity {

    EditText etProductName, etProductType, etProductOriginalPrice,
            etProductDiscountedPrice, etProductDescription;
    Button btnAddProduct;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase db;
    ImageView ivProductImage;
    Uri imageUri;
    ActivityResultLauncher<Intent> imagePickerLauncher;

    private static final String CLOUD_NAME    = "deu5xuwqa";
    private static final String UPLOAD_PRESET = "fast_mart";

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

        initCloudinary();
        init();
        setListeners();
    }

    private void initCloudinary() {
        Map<String, Object> config = new HashMap<>();
        config.put("cloud_name", CLOUD_NAME);
        try {
            MediaManager.init(this, config);
        } catch (IllegalStateException e) {
            // Already initialised (e.g. app restarted in background)
        }
    }

    private void init() {
        etProductName          = findViewById(R.id.etProductName);
        etProductType          = findViewById(R.id.etProductType);
        etProductOriginalPrice = findViewById(R.id.etProductOriginalPrice);
        etProductDiscountedPrice = findViewById(R.id.etProductDiscountedPrice);
        etProductDescription   = findViewById(R.id.etProductDescription);
        btnAddProduct          = findViewById(R.id.btnAddProduct);
        ivProductImage         = findViewById(R.id.ivProductImage);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        db   = FirebaseDatabase.getInstance();

        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        imageUri = result.getData().getData();
                        ivProductImage.setImageURI(imageUri);
                    }
                });
    }

    private void setListeners() {

        ivProductImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            imagePickerLauncher.launch(intent);
        });

        btnAddProduct.setOnClickListener(v -> {

            String name             = etProductName.getText().toString().trim();
            String type             = etProductType.getText().toString().trim();
            String originalPriceStr = etProductOriginalPrice.getText().toString().trim();
            String discountedPriceStr = etProductDiscountedPrice.getText().toString().trim();
            String description      = etProductDescription.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(type)
                    || TextUtils.isEmpty(originalPriceStr) || TextUtils.isEmpty(description)) {
                Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
                return;
            }

            double originalPrice   = Double.parseDouble(originalPriceStr);
            double discountedPrice = TextUtils.isEmpty(discountedPriceStr)
                    ? originalPrice
                    : Double.parseDouble(discountedPriceStr);

            String uid       = user.getUid();
            String productId = db.getReference().child("products").push().getKey();

            Map<String, Object> product = new HashMap<>();
            product.put("productId",        productId);
            product.put("name",             name);
            product.put("category",         type);
            product.put("originalPrice",    originalPrice);
            product.put("discountedPrice",  discountedPrice);
            product.put("description",      description);
            product.put("sellerUid",        uid);
            product.put("createdAt",        ServerValue.TIMESTAMP);
            product.put("imageUrl",         "");

            if (imageUri != null) {
                uploadToCloudinary(product, uid, productId);
            } else {
                saveProductToFirebase(product, uid, productId);
            }
        });
    }

    private void uploadToCloudinary(Map<String, Object> product,
                                    String uid, String productId) {

        Toast.makeText(this, "Uploading image…", Toast.LENGTH_SHORT).show();

        MediaManager.get()
                .upload(imageUri)
                .unsigned(UPLOAD_PRESET)
                .option("folder", "products")
                .callback(new UploadCallback() {

                    @Override public void onStart(String requestId) { }

                    @Override public void onProgress(String requestId,
                                                     long bytes, long totalBytes) { }

                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        String imageUrl = (String) resultData.get("secure_url");
                        product.put("imageUrl", imageUrl);
                        saveProductToFirebase(product, uid, productId);
                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {
                        runOnUiThread(() ->
                                Toast.makeText(CreateProduct.this,
                                        "Image upload failed: " + error.getDescription(),
                                        Toast.LENGTH_LONG).show());
                    }

                    @Override public void onReschedule(String requestId, ErrorInfo error) { }
                })
                .dispatch();
    }

    private void saveProductToFirebase(Map<String, Object> product,
                                       String uid, String productId) {
        Map<String, Object> updates = new HashMap<>();
        updates.put("products/" + productId,                 product);
        updates.put("users/" + uid + "/products/" + productId, product);

        db.getReference()
                .updateChildren(updates)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Product added!", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this,
                                "Firebase error: " + e.getMessage(),
                                Toast.LENGTH_LONG).show());
    }
}