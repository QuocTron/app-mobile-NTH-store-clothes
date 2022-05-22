package com.group7.appsellclothes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.group7.appsellclothes.R;
import com.group7.appsellclothes.activities.authActivities.LoginActivity;
import com.group7.appsellclothes.api.ApiService;
import com.group7.appsellclothes.model.ShipmentDetail;
import com.group7.appsellclothes.model.User;
import com.group7.appsellclothes.response.ApiResponse;
import com.group7.appsellclothes.response.UserResponse;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NumberAddressActivity extends AppCompatActivity {
//    TextView txtNamePersonal,txtRemoveAddress,txtEditAddress,
//            txtNumberPhone,txtAddAddress,txtAddress,txtTitle;
//    User user = null;
//    ImageView imgBackPersonal;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_number_address_activity);
//        addControls();
//        addEvents();
//        user = LoginActivity.user;
//        if(user!=null){
//            callApiMyAccount(user.getAccessToken());
//        }
//
//    }
//
//    private void addEvents() {
//        imgBackPersonal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(getFragmentManager()!=null){
//                    onBackPressed();
//                }
//            }
//        });
//        txtEditAddress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        txtAddAddress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(NumberAddressActivity.this,ShipmentDetailActivity.class));
//            }
//        });
//    }
//
//    private void render() {
//        if(user.getUsername()!=null){
//            txtNamePersonal.setText(user.getUsername());
//        }
//        Log.e("user adress",user.toString());
//        ShipmentDetail addressShipmentDetail = findDefaultShipmentDetail(user);
//        if(addressShipmentDetail!=null){
//            txtAddress.setText(addressShipmentDetail.getAddress());
//          if(addressShipmentDetail.getPhone()!=null){
//              txtNumberPhone.setText(addressShipmentDetail.getPhone());
//          }
//        }
//        txtTitle.setText("Số địa chỉ");
//    }
//    private ShipmentDetail findDefaultShipmentDetail(User user) {
//        List<ShipmentDetail> shipmentDetails = user.getShipmentDetails();
//        Log.e("user địa chỉ",user.toString());
//        if(shipmentDetails==null) return null;
//        if(shipmentDetails.size()==0) return shipmentDetails.get(0);
//        for (int i = 0; i < shipmentDetails.size(); i++) {
//            if(shipmentDetails.get(i).isDefault()==true) {
//                return shipmentDetails.get(i);
//            }
//        }
//        return shipmentDetails.get(0);
//    }
//    private void addControls() {
//        txtNamePersonal=findViewById(R.id.txt_name_user_personal);
//        txtRemoveAddress=findViewById(R.id.txt_remove_address);
//        txtEditAddress=findViewById(R.id.txt_edit_address);
//        txtNumberPhone=findViewById(R.id.txt_number_phone_personal);
//        txtAddress=findViewById(R.id.txt_address_personal);
//        txtAddAddress=findViewById(R.id.txt_add_address);
//        txtTitle=findViewById(R.id.title_see_all);
//        imgBackPersonal = findViewById(R.id.img_back_detail);
//    }
//    private void callApiMyAccount(String token){
//        String tokenUser = "Bearer "+token;
//        String accept ="application/json;versions=1";
//        ApiService.apiService.getMyAccount(accept,tokenUser).enqueue(new Callback<UserResponse>() {
//            @Override
//            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
//                if(response.isSuccessful()){
//                    UserResponse userResponse = response.body();
//                    user = userResponse.getUser();
//                    render();
//                    Log.e("user",user.toString());
//                }else {
//                    try {
//                        Gson gson = new Gson();
//                        ApiResponse apiError = gson.fromJson(response.errorBody().string(), ApiResponse.class);
//                        Toast.makeText(NumberAddressActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserResponse> call, Throwable t) {
//                Log.e("Lỗi server ", t.toString());
//                Toast.makeText(NumberAddressActivity.this, "Lỗi server", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
}