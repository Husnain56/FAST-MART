package com.example.fastmart;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentCart extends Fragment implements CartAdapter.OnCartUpdateListener {

    public interface SmsHandler {
        void checkSmsPermission(ArrayList<Product> cartList);
    }
    SmsHandler smsHandler;
    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private TextView tvTotal;
    private ArrayList<Product> cartList;
    Button btnCheckout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_cart, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerViewCart);
        tvTotal = view.findViewById(R.id.tvTotalPrice);
        btnCheckout = view.findViewById(R.id.btnCheckout);

        cartList = CartManager.getInstance().getCartItems();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new CartAdapter(getContext(), cartList, this);
        recyclerView.setAdapter(adapter);
        onUpdateTotal();

        btnCheckout.setOnClickListener(v -> {
            if (cartList == null || cartList.isEmpty()) {
                Toast.makeText(requireContext(), "Your cart is empty!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (smsHandler == null) {
                Toast.makeText(requireContext(), "SMS handler not available!", Toast.LENGTH_SHORT).show();
                return;
            }
            new AlertDialog.Builder(requireContext())
                    .setTitle("Checkout")
                    .setMessage("Are you sure you want to buy " + cartList.size() + " item(s)?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        Toast.makeText(requireContext(), "Sending confirmation to your number", Toast.LENGTH_SHORT).show();
                        smsHandler.checkSmsPermission(cartList);
                    })
                    .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                    .show();
        });



    }
    @Override
    public void onResume() {
        super.onResume();
        cartList.clear();
        cartList.addAll(CartManager.getInstance().getCartItems());
        adapter.notifyDataSetChanged();
        onUpdateTotal();
    }
    @Override
    public void onUpdateTotal() {
        double total = 0.0;

        for (Product product : cartList) {
            int qty = CartManager.getInstance().getQuantity(product.getProductId());
            total += (product.getPrice() * qty);
        }

        tvTotal.setText("$" + String.format("%.2f", total));

        if (cartList.isEmpty()) {
            tvTotal.setText("$0.00");
        }
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SmsHandler) {
            smsHandler = (SmsHandler) context;
        } else {
            throw new RuntimeException(context + " must implement SmsHandler");
        }
    }

}