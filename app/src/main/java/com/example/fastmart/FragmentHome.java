package com.example.fastmart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentHome extends Fragment {

    RecyclerView rvDeals, rvRecommended;
    DealsAdapter dealsAdapter;


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
        dealsAdapter = new DealsAdapter(getContext(), Deal_items.getDealProducts());
        rvDeals.setAdapter(dealsAdapter);

        rvDeals.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

    }
}