package com.example.fastmart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentFavourites extends Fragment {

    private RecyclerView rvFavourites;
    private FavouriteProductAdapter adapter;
    private TextView tvEmpty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);

        rvFavourites = view.findViewById(R.id.rvFavourites);
        tvEmpty      = view.findViewById(R.id.tvEmpty);


        ArrayList<Product> favs = ProductList.getFavourites();
        adapter = new FavouriteProductAdapter(requireContext(), favs);
        rvFavourites.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvFavourites.setAdapter(adapter);

        updateEmptyState();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.refreshList();
        updateEmptyState();
    }

    private void updateEmptyState() {
        boolean isEmpty = ProductList.getFavourites().isEmpty();
        tvEmpty.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        rvFavourites.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
    }
}