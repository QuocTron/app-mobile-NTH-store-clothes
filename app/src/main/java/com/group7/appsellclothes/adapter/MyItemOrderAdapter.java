package com.group7.appsellclothes.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.group7.appsellclothes.R;
import com.group7.appsellclothes.activities.ReviewProductActivity;
import com.group7.appsellclothes.activities.detailActivities.ProductDetailActivity;
import com.group7.appsellclothes.model.Order;
import com.group7.appsellclothes.model.OrderItem;
import com.group7.appsellclothes.response.OrderResponse;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class MyItemOrderAdapter extends RecyclerView.Adapter<MyItemOrderAdapter.ViewHolder> {

    private Context context;
    private Order order;

    public MyItemOrderAdapter(Context context, Order order) {
        this.context = context;
        this.order = order;
    }

    @NonNull
    @Override
    public MyItemOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyItemOrderAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_order, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyItemOrderAdapter.ViewHolder holder, int position) {
        OrderItem orderItem = order.getOrderItems().get(position);

        Glide.with(context).load(orderItem.getImage()).into(holder.imageViewItemOrder);
        holder.nameItemOrder.setText(orderItem.getName());
        holder.quantityItemOrder.setText(orderItem.getQuantity()+"");
        NumberFormat formatter = new DecimalFormat("#,###");
        String formatTotalPriceOrder = formatter.format(orderItem.getPrice()-(orderItem.getPrice()*orderItem.getDiscount()/100));
        holder.priceItemOrder.setText(formatTotalPriceOrder+"??");
        if(order.getOrderStatus().equals("Delivered")){// ???? giao
            holder.txtReviewProduct.setVisibility(View.VISIBLE);
            holder.txtReviewProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ReviewProductActivity.class);
                    intent.putExtra("order_item", orderItem);
                    context.startActivity(intent);
                    Toast.makeText(context,"????nh gi?? ??i b??",Toast.LENGTH_SHORT);
                    Log.e("????nh gi??","??i");
                }
            });
        }else {
            holder.txtReviewProduct.setVisibility(View.GONE);
        }
//        CartItem cartItems = cart.getCartItems().get(position);
//        Glide.with(context).load(cartItems.getProduct().getImages().get(0).getImg()).into(holder.imageViewItemCartPayment);
//        holder.nameItemCartPayment.setText(cartItems.getProduct().getName());
//        holder.colorItemCartPayment.setText(cartItems.getColor());
//        holder.sizeItemCartPayment.setText(cartItems.getSize());
//        holder.quantityItemCartPayment.setText(cartItems.getQuantity() + "");
//        NumberFormat formatter = new DecimalFormat("#,###");
//        String formatterPriceProduct = formatter.format(cartItems.getProduct().getPrice() - (cartItems.getProduct().getPrice() * cartItems.getProduct().getDiscount() / 100));
//        holder.priceItemCartPayment.setText(formatterPriceProduct + "??");
    }

    @Override
    public int getItemCount() {
        if ( order.getOrderItems() != null) {
            return  order.getOrderItems().size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewItemOrder;
        private TextView nameItemOrder, priceItemOrder, quantityItemOrder,txtReviewProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewItemOrder = itemView.findViewById(R.id.image_order_item);
            nameItemOrder = itemView.findViewById(R.id.txt_order_item_name);
            priceItemOrder = itemView.findViewById(R.id.txt_price_item_order);
            quantityItemOrder = itemView.findViewById(R.id.txt_quantity_item_order);
            txtReviewProduct=itemView.findViewById(R.id.txt_review_product);
        }
    }
}