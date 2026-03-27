package com.example.fastmart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.RecommendedViewHolder> {

    Context context;
    ArrayList<Product> list;
    public RecommendedAdapter(Context context,ArrayList<Product> list){
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
        Product product = list.get(position);

        holder.tvName.setText(product.getName());
        holder.tvPrice.setText(String.format("$%.2f", product.getPrice()));
        holder.tvModel.setText(product.getDescription());

        holder.ivImage.setImageResource(product.getImageResId());

        holder.ivFavourite.setImageResource(
                product.isFavourite() ? R.drawable.ic_favourites_fill : R.drawable.ic_favourites
        );

        holder.ivFavourite.setOnClickListener(v -> {
            product.setFavourite(!product.isFavourite());
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class RecommendedViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage, ivFavourite;
        TextView tvName,  tvPrice, tvModel;

        public RecommendedViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImage          = itemView.findViewById(R.id.ivImage);
            ivFavourite      = itemView.findViewById(R.id.ivFavourite);
            tvName           = itemView.findViewById(R.id.tvName);
            tvPrice          = itemView.findViewById(R.id.tvPrice);
            tvModel          = itemView.findViewById(R.id.tvModel);
        }
    }
}
