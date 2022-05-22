package com.group7.appsellclothes.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.group7.appsellclothes.R;
import com.group7.appsellclothes.adapter.NewProductsAdapter;
import com.group7.appsellclothes.api.ApiService;
import com.group7.appsellclothes.model.Category;
import com.group7.appsellclothes.response.ListCategoryResponse;
import com.group7.appsellclothes.response.ListProductResponse;
import com.group7.appsellclothes.model.Photo;
import com.group7.appsellclothes.response.ApiResponse;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductFollowCategoryFragment extends Fragment {

    private List<Photo> mListPhoto;
    private NewProductsAdapter newProductsAdapter;
    ListProductResponse listProductResponse;
    ListCategoryResponse listCategoryResponse;
    Category category;
    RecyclerView catRecyclerView, newProductRecycleView;
    TextView txt_name_category;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_product_follow_category, container, false);
    

        Bundle bundleCategory = getArguments();
       category = (Category) bundleCategory.get("category");


        catRecyclerView =root.findViewById(R.id.rec_category);
        newProductRecycleView=root.findViewById(R.id.new_product_rec);
        txt_name_category=root.findViewById(R.id.category_name);
        if(category!=null){
            txt_name_category.setText(category.getName());
        }
        newProductRecycleView.setLayoutManager(new GridLayoutManager(getContext(),2));
        ApiService.apiService.getAllProducts("20","1","-createdAt","0").enqueue(new Callback<ListProductResponse>() {
            @Override
            public void onResponse(Call<ListProductResponse> call, Response<ListProductResponse> response) {
                if (response.isSuccessful()) {
                    ListProductResponse products= response.body();
                    listProductResponse = new ListProductResponse();
                    listProductResponse = products;
//                    newProductsAdapter = new NewProductsAdapter(getContext(), listProductResponse,new NewProductsAdapter.IClickItemListener() {
//                        @Override
//                        public void onClickItemProduct(Product product) {
//
//                        }
//                    });
//                    newProductRecycleView.setAdapter(newProductsAdapter);
//                    newProductsAdapter.notifyDataSetChanged();
//                    Log.e("Products", products.getListProduct().get(0).getName()+ "");
                } else {
                    try {
                        Gson gson = new Gson();
                        ApiResponse apiResponse = gson.fromJson(response.errorBody().string(), ApiResponse.class);
                        Log.e("Message", apiResponse.getMessage());
//                        Toast.makeText(HomeFragment.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ListProductResponse> call, Throwable t) {
                Log.e("Lỗi server ", t.toString());
                Toast.makeText(getActivity(), "lỗi", Toast.LENGTH_SHORT).show();
            }
        });

        return root;


    }




}