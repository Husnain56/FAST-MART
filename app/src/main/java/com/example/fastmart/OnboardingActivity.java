package com.example.fastmart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OnboardingActivity extends AppCompatActivity {

    SharedPreferences sPref;
    SharedPreferences.Editor editor;
    Button btnGetStarted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_onboarding);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        setListeners();
    }
    public void init(){
        sPref = getSharedPreferences("onboarding",MODE_PRIVATE);
        editor = sPref.edit();


        btnGetStarted = findViewById(R.id.btnGetStarted);
    }

    public void setListeners(){
        btnGetStarted.setOnClickListener(v->{
            moveToLogin();
        });
    }
    public void moveToLogin(){
        editor.putBoolean("firstTime",false);
        editor.apply();
        Intent intent = new Intent(this, AuthenticationActivity.class);
        startActivity(intent);
        finish();
    }
}