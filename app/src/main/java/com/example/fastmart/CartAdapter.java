package com.example.fastmart;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private ArrayList<ProductItem> list;
    private OnCartUpdateListener updateListener;

    public interface OnCartUpdateListener {
        void onUpdateTotal();
    }

    public CartAdapter(Context context, ArrayList<ProductItem> list, OnCartUpdateListener listener) {
        this.context = context;
        this.list = list;
        this.updateListener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        ProductItem product = list.get(position);
        CartDB db = new CartDB(context);

        holder.tvName.setText(product.getName());
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
                    .into(holder.ivProduct);
        } else {
            holder.ivProduct.setImageResource(R.drawable.image_place_holder);
        }

        db.Open();
        int currentQty = db.getQuantity(product.getProductId());
        holder.tvQty.setText(String.valueOf(currentQty));
        db.Close();

        holder.btnPlus.setOnClickListener(v -> {
            db.Open();
            db.increaseQuantity(product.getProductId());
            db.Close();
            notifyItemChanged(position);
            updateListener.onUpdateTotal();
        });

        holder.btnMinus.setOnClickListener(v -> {
            db.Open();
            int qty = db.getQuantity(product.getProductId());
            db.decreaseQuantity(product.getProductId());
            db.Close();
            if (qty <= 1) {
                list.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, list.size());
            } else {
                notifyItemChanged(position);
            }
            updateListener.onUpdateTotal();
        });

        holder.ivOptions.setOnClickListener(v -> {
            db.Open();
            db.removeFromCart(product.getProductId());
            db.Close();
            list.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, list.size());
            updateListener.onUpdateTotal();
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,  tvPrice, tvQty, btnPlus, btnMinus;
        ImageView ivProduct, ivOptions;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQty = itemView.findViewById(R.id.tvQty);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            ivProduct = itemView.findViewById(R.id.ivProduct);
            ivOptions = itemView.findViewById(R.id.ivOptions);
        }
    }
}