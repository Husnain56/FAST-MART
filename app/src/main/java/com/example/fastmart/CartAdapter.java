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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private ArrayList<Product> list;
    private OnCartUpdateListener updateListener;

    public interface OnCartUpdateListener {
        void onUpdateTotal();
    }

    public CartAdapter(Context context, ArrayList<Product> list, OnCartUpdateListener listener) {
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
        Product product = list.get(position);
        int productId = product.getProductId();

        holder.tvTitle.setText(product.getName());
        holder.tvPrice.setText("$" + String.format("%.2f", product.getPrice()));
        holder.ivProduct.setImageResource(product.getImageResId());

        int currentQty = CartManager.getInstance().getQuantity(productId);
        holder.tvQty.setText(String.valueOf(currentQty));

        holder.btnPlus.setOnClickListener(v -> {
            int currentPos = holder.getBindingAdapterPosition();
            if (currentPos != RecyclerView.NO_POSITION) {
                int newQty = CartManager.getInstance().getQuantity(productId) + 1;
                CartManager.getInstance().updateQuantity(context, productId, newQty); // pass context
                notifyItemChanged(currentPos);
                updateListener.onUpdateTotal();
            }
        });

        holder.btnMinus.setOnClickListener(v -> {
            int currentPos = holder.getBindingAdapterPosition();
            if (currentPos != RecyclerView.NO_POSITION) {
                int existingQty = CartManager.getInstance().getQuantity(productId);
                if (existingQty > 1) {
                    int newQty = existingQty - 1;
                    CartManager.getInstance().updateQuantity(context, productId, newQty); // pass context
                    notifyItemChanged(currentPos);
                    updateListener.onUpdateTotal();
                }
            }
        });

        holder.ivOptions.setOnClickListener(v -> {
            int currentPos = holder.getBindingAdapterPosition();
            if (currentPos != RecyclerView.NO_POSITION) {
                CartManager.getInstance().removeFromCart(context, productId); // pass context
                list.remove(currentPos);
                notifyItemRemoved(currentPos);
                notifyItemRangeChanged(currentPos, list.size());
                updateListener.onUpdateTotal();
            }
        });
     //   holder.ivProduct.setImageResource(R.drawable.headphone_beige170);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvSubTitle, tvPrice, tvQty, btnPlus, btnMinus;
        ImageView ivProduct, ivOptions;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSubTitle = itemView.findViewById(R.id.tvSubTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQty = itemView.findViewById(R.id.tvQty);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            ivProduct = itemView.findViewById(R.id.ivProduct);
            ivOptions = itemView.findViewById(R.id.ivOptions);
        }
    }
}