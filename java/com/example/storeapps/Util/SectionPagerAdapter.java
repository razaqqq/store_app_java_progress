package com.example.storeapps.Util;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmetList = new ArrayList<>();

    public SectionPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmetList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmetList.size();
    }

    public void addFragment(Fragment fragment)
    {
        mFragmetList.add(fragment);
    }

}
