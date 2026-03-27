package com.example.fastmart;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainViewPager extends AppCompatActivity {

    ViewPager2 mainViewPager;
    ViewPagerAdapter adapter;
    TabLayout bottomTabLayout;
    TabLayoutMediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_view_pager);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }
    public void init() {
        mainViewPager = findViewById(R.id.MainViewPager);
        adapter = new ViewPagerAdapter(this);
        mainViewPager.setAdapter(adapter);
        bottomTabLayout = findViewById(R.id.bottomTabLayout);
        mainViewPager.setOffscreenPageLimit(1);
        mainViewPager.setUserInputEnabled(false);

        mediator = new TabLayoutMediator(bottomTabLayout, mainViewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Home");
                    tab.setIcon(R.drawable.ic_home);
                    break;
                case 1:
                    tab.setText("Browse");
                    tab.setIcon(R.drawable.ic_browse);
                    break;
                case 2:
                    tab.setText("Favourites");
                    tab.setIcon(R.drawable.ic_favourites);
                    break;
                case 3:
                    tab.setText("Cart");
                    tab.setIcon(R.drawable.ic_cart);
                    break;
                case 4:
                    tab.setText("Profile");
                    tab.setIcon(R.drawable.ic_profile);
                    break;
            }
        });

        mediator.attach();
    }
}