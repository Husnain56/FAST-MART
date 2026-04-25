package com.example.fastmart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {

    RecyclerView rvDeals, rvRecommended;
    DealsAdapter dealsAdapter;
    ProductAdapter productAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        return view;
    }

    public void init(View view) {
        rvDeals       = view.findViewById(R.id.rvDeals);
        rvRecommended = view.findViewById(R.id.rvRecommended);

        rvDeals.setHasFixedSize(true);
        rvRecommended.setHasFixedSize(true);

        rvDeals.setLayoutManager(new LinearLayoutManager(
                getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvRecommended.setLayoutManager(new GridLayoutManager(getContext(), 2));

        dealsAdapter   = new DealsAdapter(getContext());
        productAdapter = new ProductAdapter(getContext());

        rvDeals.setAdapter(dealsAdapter);
        rvRecommended.setAdapter(productAdapter);

        loadProducts();
    }

    private void loadProducts() {
        FirebaseDatabase.getInstance().getReference()
                .child("products")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<ProductItem> allProducts = new ArrayList<>();
                        ArrayList<ProductItem> deals = new ArrayList<>();

                        for (DataSnapshot child : snapshot.getChildren()) {
                            ProductItem product = child.getValue(ProductItem.class);
                            if (product == null) continue;
                            allProducts.add(product);
                            if (product.isOnSale()) deals.add(product);
                        }

                        productAdapter.updateProducts(allProducts);
                        dealsAdapter.updateDeals(deals);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), "Failed to load products", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    @Override
    public void onResume() {
        super.onResume();
        if (productAdapter != null) productAdapter.notifyDataSetChanged();
        if (dealsAdapter != null) dealsAdapter.notifyDataSetChanged();
    }
}