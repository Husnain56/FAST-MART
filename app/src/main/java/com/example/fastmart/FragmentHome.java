package com.example.fastmart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentHome extends Fragment {

    RecyclerView rvDeals, rvRecommended;
    DealsAdapter dealsAdapter;
    RecommendedAdapter recommendedAdapter;

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

        // Both use the cached shared list — same Product object references
        dealsAdapter       = new DealsAdapter(getContext(), ProductList.getDeals());
        recommendedAdapter = new RecommendedAdapter(getContext(), ProductList.getRecommended());

        rvDeals.setAdapter(dealsAdapter);
        rvRecommended.setAdapter(recommendedAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Refresh heart icons if user removed a favourite from the Favourites tab
        dealsAdapter.notifyDataSetChanged();
        recommendedAdapter.notifyDataSetChanged();
    }
}