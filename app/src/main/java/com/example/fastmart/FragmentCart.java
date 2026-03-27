package com.example.fastmart;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class FragmentCart extends Fragment implements CartAdapter.OnCartUpdateListener {

    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private TextView tvTotal;
    private ArrayList<Product> cartList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewCart);
        tvTotal = view.findViewById(R.id.tvTotalPrice);

        cartList = CartManager.getInstance().getCartItems();

        // Setup RecyclerView with LinearLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize Adapter with the custom listener
        adapter = new CartAdapter(getContext(), cartList, this);
        recyclerView.setAdapter(adapter);

        // Calculate initial total
        onUpdateTotal();

        return view;
    }

    @Override
    public void onUpdateTotal() {
        double total = 0.0;

        // Loop through the current list of products
        for (Product product : cartList) {
            // Fetch quantity directly from the CartManager Map since Product has no quantity field
            int qty = CartManager.getInstance().getQuantity(product.getProductId());

            // Multiply price by the quantity found in the manager
            total += (product.getPrice() * qty);
        }

        // Update the TextView with formatted currency
        tvTotal.setText("$" + String.format("%.2f", total));

        // Optional: Handle empty state UI
        if (cartList.isEmpty()) {
            // You could show a "Cart is Empty" layout here if needed
            tvTotal.setText("$0.00");
        }
    }
}