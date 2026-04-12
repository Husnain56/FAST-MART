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

import java.util.ArrayList;

public class FavouriteProductAdapter extends RecyclerView.Adapter<FavouriteProductAdapter.FavViewHolder> {

    private final Context context;
    private final ArrayList<Product> list;

    public FavouriteProductAdapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
    }

    public void refreshList() {
        list.clear();
        list.addAll(ProductList.getFavourites()); // filter isFavourite == true
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
        Product product = list.get(position);

        holder.tvName.setText(product.getName());
        holder.tvPrice.setText(String.format("$%.2f", product.getPrice()));
        holder.tvDescription.setText(product.getDescription());
        holder.ivImage.setImageResource(product.getImageResId());

        // Triple-dot → confirm delete from favourites
        holder.ibMoreOptions.setOnClickListener(v -> {
            int pos = holder.getBindingAdapterPosition();
            if (pos == RecyclerView.NO_ID) return;
            Product current = list.get(pos);

            new AlertDialog.Builder(context)
                    .setTitle("Remove Favourite")
                    .setMessage("Do you want to delete this product from favourites?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        current.setFavourite(false); // flips flag on shared Product object
                        list.remove(pos);
                        notifyItemRemoved(pos);
                        notifyItemRangeChanged(pos, list.size());
                    })
                    .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                    .show();
        });

        // Cart icon → add to cart, stays in favourites
        holder.ibAddToCart.setOnClickListener(v -> {
            CartManager.getInstance().addToCart(context, product);
            Toast.makeText(context, product.getName() + " added to cart!", Toast.LENGTH_SHORT).show();
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