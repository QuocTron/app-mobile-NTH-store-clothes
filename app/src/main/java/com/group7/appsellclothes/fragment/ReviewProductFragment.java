package com.group7.appsellclothes.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.group7.appsellclothes.R;
import com.group7.appsellclothes.activities.detailActivities.ProductDetailActivity;
import com.group7.appsellclothes.adapter.ReviewProductAdapter;
import com.group7.appsellclothes.api.ApiService;
import com.group7.appsellclothes.response.ListReviewResponse;
import com.group7.appsellclothes.model.Product;
import com.group7.appsellclothes.response.ApiResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewProductFragment extends Fragment {
    RecyclerView recyclerViewReview;
    ReviewProductAdapter reviewProductAdapter ;
    ListReviewResponse listReviewResponse;
    Product product;
    RatingBar ratingBar;
    TextView rating, quantityReviewProduct, reviewNull;
    LinearLayout layoutReview;
    ProductDetailActivity productDetailActivity;
    public ReviewProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        productDetailActivity = (ProductDetailActivity) getActivity();
        product = productDetailActivity.getProduct();
        View view =inflater.inflate(R.layout.fragment_review_product, container, false);
        ratingBar = view.findViewById(R.id.detail_product_review_rating_bar);
        ratingBar.setRating(product.getRate());
        rating = view.findViewById(R.id.rating_product_review);
        rating.setText(product.getRate()+"/5");
        recyclerViewReview = view.findViewById(R.id.rec_review);
        recyclerViewReview.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));;
        callApiGetAllReviewByProduct(product.getId());
        quantityReviewProduct = view.findViewById(R.id.quantity_review_product);
        reviewNull =view.findViewById(R.id.review_null);
        layoutReview = view.findViewById(R.id.layout_review);
        return view;
    }

    private void callApiGetAllReviewByProduct(String productID) {
        ApiService.apiService.getAllReviewByProduct(productID).enqueue(new Callback<ListReviewResponse>() {
            @Override
            public void onResponse(Call<ListReviewResponse> call, Response<ListReviewResponse> response) {
                if (response.isSuccessful()) {
                    listReviewResponse = response.body();
                    if(listReviewResponse.getReviews().size()==0) {
                        reviewNull.setVisibility(View.VISIBLE);
                        layoutReview.setVisibility(View.GONE);
                    }else {
                        quantityReviewProduct.setText("("+ listReviewResponse.getReviews().size()+" ????nh gi??)");
                        reviewProductAdapter = new ReviewProductAdapter(getContext(), listReviewResponse);
                        recyclerViewReview.setAdapter(reviewProductAdapter);
                        reviewProductAdapter.notifyDataSetChanged();
                    }
                } else {
                    try {
                        Gson gson = new Gson();
                        ApiResponse apiError = gson.fromJson(response.errorBody().string(), ApiResponse.class);
                        Log.e("Message", apiError.getMessage());
                        setToast(getActivity(), apiError.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ListReviewResponse> call, Throwable t) {
                setToast(getActivity(), "L???i server !");
            }
        });
    }
    private void setToast(Activity activity, String msg) {
        Toast toast = new Toast(activity);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_toast, (ViewGroup) activity.findViewById(R.id.layout_toast));
        TextView message = view.findViewById(R.id.message_toast);
        message.setText(msg);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}