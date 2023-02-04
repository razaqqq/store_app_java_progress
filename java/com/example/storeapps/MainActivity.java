package com.example.storeapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.storeapps.Home.CartFragment;
import com.example.storeapps.Home.HomeFragment;
import com.example.storeapps.Home.LiveFragment;
import com.example.storeapps.Home.NotificationFragment;
import com.example.storeapps.Home.PayFragment;
import com.example.storeapps.Home.PayFragmentActivity;
import com.example.storeapps.Home.TestFragment;
import com.example.storeapps.Home.UserFragment;
import com.example.storeapps.Home.VideoFragment;
import com.example.storeapps.Util.SectionPagerAdapter;

import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {



    // Widgert
    private ViewPager mViewPager;
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout_bottom);
        mViewPager = findViewById(R.id.activity_main_rec_view_center_view_pager);

        setUpViewPager();

    }

    private void setUpViewPager()
    {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new PayFragment());
        adapter.addFragment(new LiveFragment());
        adapter.addFragment(new VideoFragment());
        adapter.addFragment(new NotificationFragment());
        adapter.addFragment(new UserFragment());
        mViewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_home_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_account_balance_wallet_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_videocam_24);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_baseline_video_library_24);
        tabLayout.getTabAt(4).setIcon(R.drawable.ic_baseline_notifications_24);
        tabLayout.getTabAt(5).setIcon(R.drawable.ic_baseline_perm_identity_24);


    }

    private void setUpNavigationView()
    {

    }

}