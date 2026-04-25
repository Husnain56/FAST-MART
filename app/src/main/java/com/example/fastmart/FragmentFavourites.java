package com.example.fastmart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentFavourites extends Fragment {

    private RecyclerView rvFavourites;
    private FavouriteProductAdapter adapter;
    private TextView tvEmpty;
    private FavouriteDB db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favourites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);
    }

    public void init(View view) {
        db           = new FavouriteDB(requireContext());
        rvFavourites = view.findViewById(R.id.rvFavourites);
        tvEmpty      = view.findViewById(R.id.tvEmpty);

        ArrayList<ProductItem> favs = db.getAllFavourites();
        adapter = new FavouriteProductAdapter(requireContext(), favs);
        rvFavourites.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvFavourites.setAdapter(adapter);

        updateEmptyState(favs);
    }

    @Override
    public void onResume() {
        super.onResume();
        ArrayList<ProductItem> favs = db.getAllFavourites();
        adapter.updateList(favs);
        updateEmptyState(favs);
    }

    private void updateEmptyState(List<ProductItem> favs) {
        boolean isEmpty = favs.isEmpty();
        tvEmpty.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        rvFavourites.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
    }
}