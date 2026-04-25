package com.example.fastmart;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.DealsViewHolder> {

    Context context;
    List<ProductItem> list;

    public DealsAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void updateDeals(List<ProductItem> deals) {
        this.list = deals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_deal, parent, false);
        return new DealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DealsViewHolder holder, int position) {
        ProductItem product = list.get(position);

        holder.tvCategory.setText(product.getCategory());
        holder.tvName.setText(product.getName());
        holder.tvDescription.setText(product.getDescription());
        holder.tvPrice.setText(String.format("$%.2f", product.getDiscountedPrice()));
        holder.tvOriginalPrice.setText(String.format("$%.2f", product.getOriginalPrice()));

        holder.cvDeal.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetails.class);
            intent.putExtra("productId", product.getProductId());
            intent.putExtra("sellerUid", product.getSellerUid());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return Math.min(list.size(), 3);
    }

    public static class DealsViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;
        TextView tvCategory, tvName, tvDescription, tvPrice, tvOriginalPrice;
        CardView cvDeal;

        public DealsViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImage         = itemView.findViewById(R.id.iv_deal_image);
            tvCategory      = itemView.findViewById(R.id.tv_deal_category);
            tvName          = itemView.findViewById(R.id.tv_deal_name);
            tvDescription   = itemView.findViewById(R.id.tv_deal_description);
            tvPrice         = itemView.findViewById(R.id.tv_deal_price);
            tvOriginalPrice = itemView.findViewById(R.id.tv_deal_original_price);
            cvDeal          = itemView.findViewById(R.id.cvDeal);
        }
    }
}