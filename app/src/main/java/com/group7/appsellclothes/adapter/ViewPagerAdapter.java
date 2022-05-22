package com.group7.appsellclothes.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.group7.appsellclothes.fragment.HomeFragment;
import com.group7.appsellclothes.fragment.OrderFragment;
import com.group7.appsellclothes.fragment.ProductFragment;
import com.group7.appsellclothes.fragment.ProfileFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new ProductFragment();
            case 2:
                return new OrderFragment();
            case 3:
                return new ProfileFragment();
            case 0:
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}

