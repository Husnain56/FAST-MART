package com.example.fastmart;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderViewHolder> {

    Context context;
    ArrayList<Order> orders;

    public OrderHistoryAdapter(Context context, ArrayList<Order> orders) {
        this.context = context;
        this.orders  = orders;
    }

    public void updateOrders(ArrayList<Order> newOrders) {
        this.orders = newOrders;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);

        holder.tvOrderId.setText("#" + order.getOrderId().substring(0, 8).toUpperCase());

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        holder.tvOrderDate.setText(sdf.format(new Date(order.getOrderedAt())));

        String status = order.getStatus().toUpperCase();
        holder.tvStatus.setText(status);
        switch (status) {
            case "DELIVERED":
                holder.tvStatus.setTextColor(context.getResources().getColor(android.R.color.holo_green_dark));
                break;
            case "CANCELLED":
                holder.tvStatus.setTextColor(context.getResources().getColor(android.R.color.holo_red_dark));
                break;
            default:
                holder.tvStatus.setTextColor(context.getResources().getColor(android.R.color.holo_orange_dark));
                break;
        }

        holder.tvTotal.setText(String.format("$%.2f", order.getTotalPrice()));

        holder.llItems.removeAllViews();
        if (order.getItems() != null) {
            for (Map.Entry<String, OrderItem> entry : order.getItems().entrySet()) {
                OrderItem item = entry.getValue();
                View row = LayoutInflater.from(context).inflate(R.layout.item_order_product, holder.llItems, false);

                TextView tvName   = row.findViewById(R.id.tvProductName);
                TextView tvQty    = row.findViewById(R.id.tvProductQty);
                TextView tvPrice  = row.findViewById(R.id.tvProductPrice);
                ImageView ivImage = row.findViewById(R.id.ivProductImage);

                tvName.setText(item.getName());
                tvQty.setText("Qty: " + item.getQuantity());
                tvPrice.setText(String.format("$%.2f", item.getPrice()));

                if (!TextUtils.isEmpty(item.getImageUrl())) {
                    Glide.with(context)
                            .load(item.getImageUrl())
                            .placeholder(R.drawable.image_place_holder)
                            .error(R.drawable.image_place_holder)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .centerCrop()
                            .into(ivImage);
                } else {
                    ivImage.setImageResource(R.drawable.image_place_holder);
                }

                holder.llItems.addView(row);
            }
        }
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderId, tvOrderDate, tvStatus, tvTotal;
        LinearLayout llItems;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderId   = itemView.findViewById(R.id.tvOrderId);
            tvOrderDate = itemView.findViewById(R.id.tvOrderDate);
            tvStatus    = itemView.findViewById(R.id.tvStatus);
            tvTotal     = itemView.findViewById(R.id.tvTotal);
            llItems     = itemView.findViewById(R.id.llItems);
        }
    }
}