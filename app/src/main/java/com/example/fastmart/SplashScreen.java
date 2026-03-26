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
    SharedPreferences onboardingPref;
    SharedPreferences userPref;

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
                startActivity(createIntent());
                finish();
            }
        },4000);
    }

    private void init(){
        truck = findViewById(R.id.delivery_truck);
        onboardingPref = getSharedPreferences("onboarding",MODE_PRIVATE);
        userPref = getSharedPreferences("user_prefs", MODE_PRIVATE);
    }
    private Intent createIntent() {
        if (onboardingPref.getBoolean("firstTime", true)) {
            return new Intent(this, OnboardingActivity.class);
        }
        if (userPref.getBoolean("loggedIn", false)) {
            return new Intent(this, HomePage.class);
        }
        return new Intent(this, AuthenticationActivity.class);
    }
    private void SetAnimations(){
        moveTruck = AnimationUtils.loadAnimation(this,R.anim.move_truck);
    }
    private void StartAnimations(){
        truck.setAnimation(moveTruck);
    }
}