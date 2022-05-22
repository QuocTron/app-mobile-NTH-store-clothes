package com.group7.appsellclothes.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.group7.appsellclothes.fragment.HistoryOrderFragment;
import com.group7.appsellclothes.fragment.NowOrderFragment;
import com.group7.appsellclothes.fragment.OrderDetailFragment;

public class ViewPagerTabLayoutAdapter extends FragmentStatePagerAdapter {

    public ViewPagerTabLayoutAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new NowOrderFragment();
            case 1:
                return new OrderDetailFragment();
            case 2:
                return new HistoryOrderFragment();

            default:
                return new NowOrderFragment();

        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title ="";
        switch (position){
            case 0:
                title ="Now order";
                break;
            case 1:
                title ="Detail order";
                break;
            case 2:
                title ="History order";
                break;
        }
        return title;
    }
}
