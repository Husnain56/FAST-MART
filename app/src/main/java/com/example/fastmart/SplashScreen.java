package com.example.fastmart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {

    ImageView truck;
    Animation moveTruck;
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        SetAnimations();
        StartAnimations();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(CreateIntent());
                finish();
            }
        },4000);
    }

    private void init(){
        truck = findViewById(R.id.delivery_truck);
        sPref = getSharedPreferences("onboarding",MODE_PRIVATE);
    }
    private Intent CreateIntent(){
        if(sPref.getBoolean("firstTime",true)) {
            return new Intent(this, OnboardingActivity.class);
        }
        else{
            return new Intent(this, AuthenticationActivity.class);
        }
    }
    private void SetAnimations(){
        moveTruck = AnimationUtils.loadAnimation(this,R.anim.move_truck);
    }
    private void StartAnimations(){
        truck.setAnimation(moveTruck);
    }
}