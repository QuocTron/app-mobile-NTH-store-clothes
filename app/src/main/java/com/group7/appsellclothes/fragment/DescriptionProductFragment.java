package com.group7.appsellclothes.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.group7.appsellclothes.R;
import com.group7.appsellclothes.activities.detailActivities.ProductDetailActivity;
import com.group7.appsellclothes.model.Product;

public class DescriptionProductFragment extends Fragment {
    ProductDetailActivity productDetailActivity;
    private Product product;

    public DescriptionProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        productDetailActivity = (ProductDetailActivity) getActivity();
        product = productDetailActivity.getProduct();
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_description_product, container, false);
        TextView desProduct = root.findViewById(R.id.txt_description_product_detail);
        desProduct.setText(product.getDesProduct());
        return root;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }
}