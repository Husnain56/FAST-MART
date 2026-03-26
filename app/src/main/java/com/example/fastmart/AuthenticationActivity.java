package com.example.fastmart;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class AuthenticationActivity extends AppCompatActivity {

    TextView tabLogin;
    TextView tabSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_authentication);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        setListeners();
    }
    private void init() {
        tabLogin  = findViewById(R.id.tabLogin);
        tabSignUp = findViewById(R.id.tabSignUp);
        loadFragment(new fragmentLogin());
    }
    private void setListeners() {
        tabLogin.setOnClickListener(v -> {
            setActiveTab(tabLogin, tabSignUp);
            loadFragment(new fragmentLogin());
        });

        tabSignUp.setOnClickListener(v -> {
            setActiveTab(tabSignUp, tabLogin);
            loadFragment(new fragmentSignup());
        });
    }
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    private void setActiveTab(TextView active, TextView inactive) {
        active.setBackgroundResource(R.drawable.bg_tab_selected);
        active.setTextColor(0xFF1A1A1A);
        active.setTypeface(null, android.graphics.Typeface.BOLD);

        inactive.setBackgroundResource(android.R.color.transparent);
        inactive.setTextColor(0xFF888888);
        inactive.setTypeface(null, android.graphics.Typeface.NORMAL);
    }
}