package com.group7.appsellclothes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.group7.appsellclothes.R;
import com.group7.appsellclothes.model.Category;
import com.group7.appsellclothes.response.ListCategoryResponse;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Context context;
    private ListCategoryResponse list;

    public CategoryAdapter(Context context, ListCategoryResponse list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list, parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        Category category = list.getCategories().get(position);
        Glide.with(context).load(category.getImageCate()).into(holder.catImage);
        holder.catName.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return list.getCategories().size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView catImage;
        TextView catName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catImage= itemView.findViewById(R.id.cat_img);
            catName = itemView.findViewById(R.id.cat_name);
        }
    }
}
