package com.group7.appsellclothes.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.group7.appsellclothes.R;
import com.group7.appsellclothes.activities.detailActivities.ProductDetailActivity;
import com.group7.appsellclothes.response.ListProductResponse;
import com.group7.appsellclothes.model.Product;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ShowAllAdapter extends RecyclerView.Adapter<ShowAllAdapter.ViewHolder> {
    private Context context;
    private ListProductResponse listProductResponse;
    public ShowAllAdapter(Context context, ListProductResponse listProductResponse) {
        this.context = context;
        this.listProductResponse = listProductResponse;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_all_item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = listProductResponse.getProducts().get(position);
        Glide.with(context).load(product.getImages().get(0).getImg()).into(holder.imageView);
        holder.name.setText(product.getName());
        holder.discount.setText("-" + product.getDiscount() + "%");
        NumberFormat formatter = new DecimalFormat("#,###");
        String formatterPriceProduct = formatter.format(product.getPrice() - (product.getPrice() * product.getDiscount() / 100));
        holder.price.setText(formatterPriceProduct + "đ");
        holder.likeCount.setText(product.getLikeCount()+"");
        holder.quantitySold.setText(product.getQuantitySold()+"");
        if (product.getDiscount() != 0) {
            String formatterCurrentPriceProduct = formatter.format(product.getPrice());
            holder.currentPrice.setText(formatterCurrentPriceProduct + "đ");
        }
        else {
            holder.currentPrice.setText("");
        }
        Float rate = product.getRate(); /// 3.2 * 10
        holder.ratingBar.setRating(rate);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("product_id", product.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listProductResponse.getProducts() != null) {
            return listProductResponse.getProducts().size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        RatingBar ratingBar;
        TextView name, currentPrice, price, discount, likeCount, quantitySold;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.show_all_product_img);
            name = itemView.findViewById(R.id.show_all_product_name);
            price = itemView.findViewById(R.id.show_all_product_price);
            discount = itemView.findViewById(R.id.show_all_product_discount);
            currentPrice = itemView.findViewById(R.id.show_all_product_current_price);
            likeCount = itemView.findViewById(R.id.show_all_product_like_count);
            quantitySold = itemView.findViewById(R.id.show_all_product_sold);
            ratingBar = itemView.findViewById(R.id.show_all_product_rating_bar);
        }
    }
}
