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
        item_price.setText(intent.getStringExtra("price"));
        item_name.setText(intent.getStringExtra("name"));
        item_desc.setText(intent.getStringExtra("desc"));
        String code = intent.getStringExtra("code");

        if(code.equals("mic")){
            item_image.setImageResource(R.drawable.mic350);
            item_desc.setText(R.string.mic_desc);
        }
        else if(code.equals("hw")){
            item_image.setImageResource(R.drawable.headphone_beige350);
            item_desc.setText(R.string.headphone_desc);
        }
        else if(code.equals("hb")){
            item_image.setImageResource(R.drawable.headphone_black350);
            item_desc.setText(R.string.headphone_desc);
        }

        btn_buy.setOnClickListener(v->{
            new AlertDialog.Builder(this).setTitle("Buy Now")
                    .setMessage("Are you sure you want to buy "+item_name.getText()+" ?")
                    .setPositiveButton("Yes", (dialog,which)->{
                        Toast.makeText(this,"Sending confirmation message to your number",Toast.LENGTH_SHORT).show();
                        checkMessagePermissions();
                    })
                    .setNegativeButton("Cancel",(dialog,which)->{
                        dialog.dismiss();
                    }).show();
        });
        btn_back.setOnClickListener(v->{
            finish();
        });
    }

    private void checkMessagePermissions() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED)
        {
            sendMessage();
        }
        else
        {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.SEND_SMS},
                    SMS_REQUEST_CODE);
        }
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