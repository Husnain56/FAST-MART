package com.example.fastmart;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProductDetails extends AppCompatActivity {

    private final int SMS_REQUEST_CODE = 1;
    ImageView item_image;
    TextView item_price;
    TextView item_name;
    TextView item_desc;
    TextView item_details;


    ImageView btn_back;
    Button btn_buy;

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
        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra("product");
        item_image.setImageResource(product.getImageResId());
        item_price.setText(String.format("$%.2f", product.getPrice()));
        item_name.setText(product.getName());
        item_desc.setText(product.getDescription());

        btn_buy.setOnClickListener(v->{
            CartManager.getInstance().addToCart(product);
            Toast.makeText(this, product.getName() + " added to cart!", Toast.LENGTH_SHORT).show();
        });

        btn_back.setOnClickListener(v->{
            finish();
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == SMS_REQUEST_CODE)
        {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                sendMessage();
            }
            else
            {
                Toast.makeText(this, "You have to give permission to send message", Toast.LENGTH_SHORT).show();
            }
        }

    }
    private void sendMessage(){
        SmsManager smsManager = SmsManager.getDefault();
        String msg = "Product "+ item_name.getText()+" bought";
        smsManager.sendTextMessage("+923263874962", null, msg, null, null);
    }
    private void init(){
        item_image = findViewById(R.id.product_image);
        item_price = findViewById(R.id.lbl_price);
        item_name = findViewById(R.id.lbl_name);
        item_desc = findViewById(R.id.lbl_desc);
        item_details = findViewById(R.id.lbl_details);
        btn_buy = findViewById(R.id.btn_buy);
        btn_back = findViewById(R.id.btnback);
    }

}