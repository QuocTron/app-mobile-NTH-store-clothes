package com.group7.appsellclothes.order;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class OrderViewPagerAdapter extends FragmentStatePagerAdapter {

    public OrderViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new OrderedFragment();
            case 2:
                return new DeliveryFragment();
            case 3:
                return new DeliveredFragment();
            case 4:
                return new DestroyedFragment();
            case 0:
            default:
                return new AllOrderFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 1:
                return "Đã đặt";
            case 2:
                return "Đang giao";

            case 3:
                return "Đã giao";
            case 4:
                return "Đã hủy";
            case 0:
            default:
                return "Tất cả đơn hàng";
        }
    }
}
