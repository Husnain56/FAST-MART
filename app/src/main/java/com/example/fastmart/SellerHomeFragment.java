package com.example.fastmart;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SellerHomeFragment extends Fragment {

    FloatingActionButton btnAddProduct;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_seller_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);
        setListeners();
    }

    private void init(View view){
        btnAddProduct = view.findViewById(R.id.fabAddProduct);
    }
    private void setListeners(){
        btnAddProduct.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), CreateProduct.class);
            startActivity(intent);
        });
    }
}