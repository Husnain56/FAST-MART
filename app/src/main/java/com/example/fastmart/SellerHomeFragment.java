package com.example.fastmart;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SellerHomeFragment extends Fragment {

    FloatingActionButton fabAddProduct;
    RecyclerView rvRecommended;
    TextView tvName;
    ProductAdapter adapter;
    DatabaseReference productsRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_seller_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);
        setListeners();
        loadProducts();
    }

    private void init(View view) {
        fabAddProduct = view.findViewById(R.id.fabAddProduct);
        rvRecommended = view.findViewById(R.id.rvRecommended);
        tvName        = view.findViewById(R.id.tvName);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase.getInstance().getReference()
                .child("users").child(uid).child("name")
                .get()
                .addOnSuccessListener(snapshot -> {
                    tvName.setText("Hello " + snapshot.getValue(String.class));
                });

        productsRef = FirebaseDatabase.getInstance().getReference()
                .child("users").child(uid).child("products");

        adapter = new ProductAdapter(getContext());
        rvRecommended.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvRecommended.setAdapter(adapter);
    }

    private void setListeners() {
        fabAddProduct.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CreateProduct.class);
            startActivity(intent);
        });
    }

    private void loadProducts() {
        productsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<ProductItem> products = new ArrayList<>();
                for (DataSnapshot child : snapshot.getChildren()) {
                    ProductItem product = child.getValue(ProductItem.class);
                    if (product != null) products.add(product);
                }
                adapter.updateProducts(products);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Failed to load products", Toast.LENGTH_SHORT).show();
            }
        });
    }
}