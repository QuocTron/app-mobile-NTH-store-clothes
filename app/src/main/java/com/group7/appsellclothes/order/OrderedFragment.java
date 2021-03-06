package com.group7.appsellclothes.order;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.group7.appsellclothes.R;
import com.group7.appsellclothes.adapter.OrderAdapter;
import com.group7.appsellclothes.api.ApiService;
import com.group7.appsellclothes.model.User;
import com.group7.appsellclothes.response.ApiResponse;
import com.group7.appsellclothes.response.ListOrderResponse;
import com.group7.appsellclothes.utils.UserReaderSqlite;
import com.group7.appsellclothes.utils.Util;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderedFragment extends Fragment {
    RecyclerView recOrder;
    OrderAdapter orderAdapter;
    private User user= null;
    private ListOrderResponse listOrder;
    private UserReaderSqlite userReaderSqlite;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toast.makeText(getActivity(),"Vô rồi á",Toast.LENGTH_SHORT);
        View view =inflater.inflate(R.layout.fragment_all_order, container, false);
        userReaderSqlite= new UserReaderSqlite(getActivity(),"user.db",null,1);
        Util.refreshToken(getActivity());
        user = userReaderSqlite.getUser();

        addControls(view);

        loadOrders();


        return view;
    }

    private void loadOrders() {
        Log.e("User order ",user.toString());
        if(user!=null){
            Log.e("User order ",user.toString());
            callApiGetMyOrder("Bearer " + user.getAccessToken());

        }
    }

    private void addControls(View view) {
        recOrder = view.findViewById(R.id.rec_order);
    }

    public void callApiGetMyOrder(String accessToken){
        String accept = "application/json;versions=1";
        ApiService.apiService.getMyOrder(accept, accessToken,"Processing").enqueue(new Callback<ListOrderResponse>() {
            @Override
            public void onResponse(Call<ListOrderResponse> call, Response<ListOrderResponse> response) {
                if(response.isSuccessful()){
                    listOrder = response.body();
                    Log.e("order đã nhận được",listOrder.getOrders().get(0).getOrderItems().get(0).getName());
                    recOrder.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
                    orderAdapter= new OrderAdapter(getActivity(),listOrder);
                    recOrder.setAdapter(orderAdapter);
                    orderAdapter.notifyDataSetChanged();
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
}