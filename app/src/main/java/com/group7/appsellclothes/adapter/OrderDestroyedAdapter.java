package com.group7.appsellclothes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group7.appsellclothes.R;
import com.group7.appsellclothes.model.Cart;
import com.group7.appsellclothes.model.Order;
import com.group7.appsellclothes.response.ListOrderResponse;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class OrderDestroyedAdapter extends RecyclerView.Adapter<OrderDestroyedAdapter.ViewHolder> {
    Context context;
    ListOrderResponse listOrder;
    MyItemOrderAdapter myItemOrderAdapter;
    Cart cart;

    public OrderDestroyedAdapter(Context context, ListOrderResponse listOrder) {
        this.context = context;
        this.listOrder = listOrder;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderDestroyedAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_destroyed,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = listOrder.getOrders().get(position);
        holder.recItemOrder.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        myItemOrderAdapter = new MyItemOrderAdapter(context,order);
        holder.recItemOrder.setAdapter(myItemOrderAdapter);
        myItemOrderAdapter.notifyDataSetChanged();
        holder.txtNameOrder.setText(order.getUser());
        holder.txtQuantityOrder.setText(order.getOrderItems().size()+"");
        holder.txtStatusOrder.setText(order.getOrderStatus());
        NumberFormat formatter = new DecimalFormat("#,###");
        String formatTotalPriceOrder = formatter.format(order.getTotalPrice());
        holder.txtTotalPriceOrder.setText(formatTotalPriceOrder +"??");
        holder.txtDestroyed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"h???y ????n"+order.toString(),Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public int getItemCount() {

        if(listOrder.getOrders()!=null&&listOrder!=null){
            return listOrder.getOrders().size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recItemOrder;
        TextView txtNameOrder, txtQuantityOrder ,txtTotalPriceOrder,txtStatusOrder,txtDestroyed;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recItemOrder=itemView.findViewById(R.id.rec_item_order);
            txtNameOrder=itemView.findViewById(R.id.txt_name_order);
            txtQuantityOrder=itemView.findViewById(R.id.txt_quantity_product_order);
            txtTotalPriceOrder=itemView.findViewById(R.id.txt_total_price_order);
            txtStatusOrder=itemView.findViewById(R.id.txt_status_order);
            txtDestroyed=itemView.findViewById(R.id.txt_destroyed);
        }
    }
}
