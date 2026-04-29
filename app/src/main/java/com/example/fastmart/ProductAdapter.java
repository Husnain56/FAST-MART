package com.example.fastmart;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    Context context;
    List<ProductItem> products;

    public ProductAdapter(Context context) {
        this.context  = context;
        this.products = new ArrayList<>();
    }

    public void updateProducts(List<ProductItem> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_recommended, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductItem product = products.get(position);
        FavouriteDB db = new FavouriteDB(context);

        holder.tvName.setText(product.getName());
        holder.tvModel.setText(product.getCategory());

        if (product.isOnSale()) {
            holder.tvPrice.setText(String.format("$%.2f", product.getDiscountedPrice()));
        } else {
            holder.tvPrice.setText(String.format("$%.2f", product.getOriginalPrice()));
        }

        String imageUrl = product.getImageUrl();

        if (!TextUtils.isEmpty(imageUrl)) {
            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.image_place_holder)
                    .error(R.drawable.image_place_holder)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(holder.ivProductImage);
        } else {
            holder.ivProductImage.setImageResource(R.drawable.image_place_holder);
        }

        db.Open();
        holder.ivFavourite.setImageResource(
                db.isFavourite(product.getProductId())
                        ? R.drawable.ic_favourites_fill
                        : R.drawable.ic_favourites);
        db.Close();

        holder.ivFavourite.setOnClickListener(v -> {
            db.Open();
            if (db.isFavourite(product.getProductId())) {
                db.removeFavourite(product.getProductId());
                holder.ivFavourite.setImageResource(R.drawable.ic_favourites);
            } else {
                db.addFavourite(product);
                holder.ivFavourite.setImageResource(R.drawable.ic_favourites_fill);
            }
            db.Close();
        });

        holder.cvRecommended.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetails.class);
            intent.putExtra("productId",  product.getProductId());
            intent.putExtra("sellerUid",  product.getSellerUid());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView  tvName, tvPrice, tvModel;
        CardView  cvRecommended;
        ImageView ivFavourite;
        ImageView ivProductImage;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName        = itemView.findViewById(R.id.tvName);
            tvPrice       = itemView.findViewById(R.id.tvPrice);
            tvModel       = itemView.findViewById(R.id.tvModel);
            cvRecommended = itemView.findViewById(R.id.cvRecommended);
            ivFavourite   = itemView.findViewById(R.id.ivFavourite);
            ivProductImage = itemView.findViewById(R.id.ivProductImage); // product photo
        }
    }
}