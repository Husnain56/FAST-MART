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

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    ImageView truck;
    Animation moveTruck;
    SharedPreferences onboardingPref;

    FirebaseAuth auth;
    FirebaseUser user;

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
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        truck = findViewById(R.id.delivery_truck);
        onboardingPref = getSharedPreferences("onboarding",MODE_PRIVATE);
    }
    private Intent createIntent() {

        if (user!=null){
            return new Intent(this, MainViewPager.class);
        }
        else if (!onboardingPref.getBoolean("onboarded", false)) {
            return new Intent(this, OnboardingActivity.class);
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