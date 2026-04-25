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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SplashScreen extends AppCompatActivity {

    ImageView truck;
    Animation moveTruck;
    SharedPreferences onboardingPref;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference ref;

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
                if (user != null) {
                    fetchAccountTypeAndNavigate();
                } else if (!onboardingPref.getBoolean("onboarded", false)) {
                    startActivity(new Intent(SplashScreen.this, OnboardingActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashScreen.this, AuthenticationActivity.class));
                    finish();
                }
            }
        }, 4000);
    }

    private void init() {
        auth             = FirebaseAuth.getInstance();
        user             = auth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        ref              = firebaseDatabase.getReference("users");
        truck            = findViewById(R.id.delivery_truck);
        onboardingPref   = getSharedPreferences("onboarding", MODE_PRIVATE);
    }

    private void fetchAccountTypeAndNavigate() {
        String uid = user.getUid();

        ref.child(uid).child("accountType").get()
                .addOnSuccessListener(snapshot -> {
                    String accountType = snapshot.getValue(String.class);
                    Intent intent;
                    if ("Seller".equals(accountType)) {
                        intent = new Intent(SplashScreen.this, SellerHomeScreen.class);
                    } else {
                        intent = new Intent(SplashScreen.this, MainViewPager.class);
                    }
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Intent intent = new Intent(SplashScreen.this, MainViewPager.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                });
    }

    private void SetAnimations() {
        moveTruck = AnimationUtils.loadAnimation(this, R.anim.move_truck);
    }

    private void StartAnimations() {
        truck.setAnimation(moveTruck);
    }
}