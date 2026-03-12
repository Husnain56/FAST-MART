package com.example.fastmart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomePage extends AppCompatActivity {

    LinearLayout deal_card;
    LinearLayout black_card;
    LinearLayout white_card;
    TextView item_price;
    TextView item_name;
    TextView item_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        Intent iPurchase = new Intent(this,ProductDetails.class);
        deal_card.setOnClickListener(v->{

            item_price = findViewById(R.id.mic_price);
            item_name = findViewById(R.id.mic_name);
            item_desc = findViewById(R.id.mic_desc);

            iPurchase.putExtra("price",item_price.getText().toString());
            iPurchase.putExtra("name",item_name.getText().toString());
            iPurchase.putExtra("desc",item_desc.getText().toString());
            iPurchase.putExtra("code","mic");

            startActivity(iPurchase);
        });

        black_card.setOnClickListener(v->{
            item_price = findViewById(R.id.headphone_black_cost);
            item_name = findViewById(R.id.headphone_black_name);
            item_desc = findViewById(R.id.headphone_black_desc);

            iPurchase.putExtra("code","hb");
            iPurchase.putExtra("price",item_price.getText().toString());
            iPurchase.putExtra("name",item_name.getText().toString());
            iPurchase.putExtra("desc",item_desc.getText().toString());

            startActivity(iPurchase);
        });

        white_card.setOnClickListener(v->{
            item_price = findViewById(R.id.headphone_white_cost);
            item_name = findViewById(R.id.headphone_white_name);
            item_desc = findViewById(R.id.headphone_white_desc);

            iPurchase.putExtra("code","hw");
            iPurchase.putExtra("price",item_price.getText().toString());
            iPurchase.putExtra("name",item_name.getText().toString());
            iPurchase.putExtra("desc",item_desc.getText().toString());

            startActivity(iPurchase);
        });
    }
    private void init(){

        deal_card = findViewById(R.id.deal_card);
        black_card = findViewById(R.id.black_card);
        white_card = findViewById(R.id.white_card);

    }
}

