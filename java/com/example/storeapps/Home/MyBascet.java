package com.example.storeapps.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.storeapps.Model.Products;
import com.example.storeapps.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MyBascet extends AppCompatActivity {

    ImageView backArrow;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bascet);

        viewPager = findViewById(R.id.activity_my_bascet_view_pager);
        tabLayout = findViewById(R.id.activity_my_bascet_tab_layout);

        backArrow = findViewById(R.id.activity_my_bascet_backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setUpViewPager();


    }



    private void setUpViewPager() {
        MyBascetAdapter viewpagerAdapter = new MyBascetAdapter(getSupportFragmentManager());

        viewpagerAdapter.addFragment(new SemuaFragment());
        viewpagerAdapter.addFragment(new BeliLagiFragment());

        viewPager.setAdapter(viewpagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        int semua = 20;

        tabLayout.getTabAt(0).setText("Semua " + "(" + semua + ")" );
        tabLayout.getTabAt(1).setText("BeliLagi");

    }
}