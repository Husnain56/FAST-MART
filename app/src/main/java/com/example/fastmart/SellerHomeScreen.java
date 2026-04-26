package com.example.fastmart;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SellerHomeScreen extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seller_home_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        loadFragment(new SellerHomeFragment(), "Home");
        setListeners();

        View headerView = navigationView.getHeaderView(0);
        TextView tvNavName = headerView.findViewById(R.id.tvNavName);
        TextView tvNavEmail = headerView.findViewById(R.id.tvNavEmail);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        tvNavEmail.setText(email);

        FirebaseDatabase.getInstance().getReference()
                .child("users").child(uid).child("name")
                .get()
                .addOnSuccessListener(snapshot -> {
                    tvNavName.setText(snapshot.getValue(String.class));
                });
    }

    private void init() {
        drawerLayout   = findViewById(R.id.main);
        navigationView = findViewById(R.id.navigationView);
        toolbar        = findViewById(R.id.toolbar);

    }

    private void setListeners() {
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                loadFragment(new SellerHomeFragment(), "Home");
            } else if (id == R.id.nav_order_history) {
                loadFragment(new OrderHistoryFragment(), "Order History");
            } else if (id == R.id.nav_account_settings) {
                loadFragment(new FragmentProfile(), "Account");
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void loadFragment(Fragment fragment, String title) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.sellerFragmentContainer, fragment);
        transaction.commit();

        navigationView.setCheckedItem(
                title.equals("Home") ? R.id.nav_home
                        : title.equals("Order History") ? R.id.nav_order_history
                        : R.id.nav_account_settings
        );
    }

}