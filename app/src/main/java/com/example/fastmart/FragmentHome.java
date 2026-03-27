package com.example.fastmart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.ranging.oob.TransportHandle;
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        return view;
    }

    public void init(View view){
        rvDeals = view.findViewById(R.id.rvDeals);
        rvRecommended = view.findViewById(R.id.rvRecommended);

        rvDeals.setHasFixedSize(true);
        rvRecommended.setHasFixedSize(true);

        rvDeals.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvRecommended.setLayoutManager(new GridLayoutManager(getContext(), 2));

        dealsAdapter = new DealsAdapter(getContext(), Deal_items.getDealProducts());
        rvDeals.setAdapter(dealsAdapter);

        recommendedAdapter = new RecommendedAdapter(getContext(), Recommended_items.getRecommendedProducts());
        rvRecommended.setAdapter(recommendedAdapter);


    }
}