package com.group7.appsellclothes.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.group7.appsellclothes.R;
import com.group7.appsellclothes.activities.MyCartActivity;
import com.group7.appsellclothes.adapter.ViewPagerTabLayoutAdapter;
import com.group7.appsellclothes.api.ApiService;
import com.group7.appsellclothes.model.Order;
import com.group7.appsellclothes.model.User;
import com.group7.appsellclothes.order.OrderViewPagerAdapter;
import com.group7.appsellclothes.response.ApiResponse;
import com.group7.appsellclothes.response.ListOrderResponse;
import com.group7.appsellclothes.utils.UserReaderSqlite;
import com.group7.appsellclothes.utils.Util;
import com.group7.appsellclothes.widget.CustomViewPager;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderFragment extends Fragment {
    private TabLayout tabLayout;
    private CustomViewPager viewPager;
    private View view;
    private User user= null;
    private Order order =null;
    private ListOrderResponse listOrder;
    private UserReaderSqlite userReaderSqlite;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_order, container, false);

        userReaderSqlite= new UserReaderSqlite(getActivity(),"user.db",null,1);
        Util.refreshToken(getActivity());
        user = userReaderSqlite.getUser();
        addControls(view);

        loadOrders();
        OrderViewPagerAdapter orderViewPagerAdapter= new OrderViewPagerAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(orderViewPagerAdapter);

        viewPager.setPagingEnable(false);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    private void loadOrders() {
        if(user!=null){
            callApiGetMyOrder("Bearer " + user.getAccessToken());
        }
    }

    private void addControls(View view) {
        tabLayout = view.findViewById(R.id.tab_layout_order);
        viewPager = view.findViewById(R.id.view_pager_order);
    }
    public void callApiGetMyOrder(String accessToken){
        String accept = "application/json;versions=1";
        ApiService.apiService.getMyOrder(accept, accessToken,"").enqueue(new Callback<ListOrderResponse>() {
            @Override
            public void onResponse(Call<ListOrderResponse> call, Response<ListOrderResponse> response) {
                if(response.isSuccessful()){
                    listOrder = response.body();
                    Log.e("order đã nhận được",listOrder.getOrders().get(0).getOrderItems().get(0).getName());
                }else {
                    Log.e("order","Không có");
                    Gson gson = new Gson();
                    try {
                        ApiResponse apiError = gson.fromJson(response.errorBody().string(), ApiResponse.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ListOrderResponse> call, Throwable t) {
                Log.e("order","Sai rồi");
               Toast.makeText(getActivity(),"Lỗi server !",Toast.LENGTH_SHORT);
            }
        });

    }
//    private void setToast(Activity activity, String msg ) {
//        Toast toast = new Toast(activity);
//        LayoutInflater inflater = getLayoutInflater();
//        View view = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.layout_toast));
//        TextView message = view.findViewById(R.id.message_toast);
//        message.setText(msg);
//        toast.setView(view);
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        toast.setDuration(Toast.LENGTH_SHORT);
//        toast.show();
//    }
}