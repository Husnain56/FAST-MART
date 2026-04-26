package com.example.fastmart;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastmart.CartDB;

import java.util.ArrayList;

public class FavouriteProductAdapter extends RecyclerView.Adapter<FavouriteProductAdapter.FavViewHolder> {

    private Context context;
    private final FavouriteDB favouriteDB;
    private ArrayList<ProductItem> list;

    public FavouriteProductAdapter(Context context, ArrayList<ProductItem> list) {
        this.context     = context;
        this.favouriteDB = new FavouriteDB(context);
        this.list        = list;
    }

    public void updateList(ArrayList<ProductItem> newList) {
        this.list = newList;
        notifyDataSetChanged();
    }

    public void refreshList() {
        favouriteDB.Open();
        list = favouriteDB.getAllFavourites();
        favouriteDB.Close();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favourite, parent, false);
        return new FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {
        ProductItem product = list.get(position);
        CartDB db = new CartDB(context);

        holder.tvName.setText(product.getName());
        holder.tvDescription.setText(product.getDescription());

        if (product.isOnSale()) {
            holder.tvPrice.setText(String.format("$%.2f", product.getDiscountedPrice()));
        } else {
            holder.tvPrice.setText(String.format("$%.2f", product.getOriginalPrice()));
        }

        holder.ibMoreOptions.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Remove Favourite")
                    .setMessage("Do you want to remove this product from favourites?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        favouriteDB.Open();
                        favouriteDB.removeFavourite(product.getProductId());
                        favouriteDB.Close();
                        list.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, list.size());
                    })
                    .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                    .show();
        });

        holder.ibAddToCart.setOnClickListener(v -> {
            db.Open();
            db.addToCart(product);
            Toast.makeText(context,"Added to cart", Toast.LENGTH_SHORT).show();
            db.Close();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class FavViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvName, tvPrice, tvDescription;
        ImageButton ibAddToCart, ibMoreOptions;

        public FavViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage       = itemView.findViewById(R.id.ivProductImage);
            tvName        = itemView.findViewById(R.id.tvProductName);
            tvPrice       = itemView.findViewById(R.id.tvProductPrice);
            tvDescription = itemView.findViewById(R.id.tvProductDescription);
            ibAddToCart   = itemView.findViewById(R.id.ibAddToCart);
            ibMoreOptions = itemView.findViewById(R.id.ibMoreOptions);
        }
    }
}