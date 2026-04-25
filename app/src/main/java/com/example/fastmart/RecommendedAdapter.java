package com.example.fastmart;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.RecommendedViewHolder> {

    Context context;
    ArrayList<ProductItem> list;

    public RecommendedAdapter(Context context, ArrayList<ProductItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecommendedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recommended, parent, false);
        return new RecommendedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedViewHolder holder, int position) {
        Log.d("FAV", "ivFavourite: " + holder.ivFavourite);
        ProductItem product = list.get(position);
        FavouriteDB db = new FavouriteDB(context);

        holder.tvName.setText(product.getName());
        holder.tvModel.setText(product.getCategory());

        if (product.isOnSale()) {
            holder.tvPrice.setText(String.format("$%.2f", product.getDiscountedPrice()));
        } else {
            holder.tvPrice.setText(String.format("$%.2f", product.getOriginalPrice()));
        }

        holder.ivFavourite.setImageResource(
                db.isFavourite(product.getProductId())
                        ? R.drawable.ic_favourites_fill
                        : R.drawable.ic_favourites
        );


        holder.ivFavourite.setOnClickListener(v -> {
            if (db.isFavourite(product.getProductId())) {
                db.removeFavourite(product.getProductId());
                holder.ivFavourite.setImageResource(R.drawable.ic_favourites);
            } else {
                db.addFavourite(product);
                holder.ivFavourite.setImageResource(R.drawable.ic_favourites_fill);
            }
        });

        holder.cvRecommended.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetails.class);
            intent.putExtra("productId", product.getProductId());
            intent.putExtra("sellerUid", product.getSellerUid());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class RecommendedViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage, ivFavourite;
        TextView tvName, tvPrice, tvModel;
        CardView cvRecommended;

        public RecommendedViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage       = itemView.findViewById(R.id.ivImage);
            ivFavourite   = itemView.findViewById(R.id.ivFavourite);
            tvName        = itemView.findViewById(R.id.tvName);
            tvPrice       = itemView.findViewById(R.id.tvPrice);
            tvModel       = itemView.findViewById(R.id.tvModel);
            cvRecommended = itemView.findViewById(R.id.cvRecommended);
        }
    }
}