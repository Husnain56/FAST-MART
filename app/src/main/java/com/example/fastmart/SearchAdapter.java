package com.example.fastmart;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SearchAdapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> list;
    SharedPreferences sharedPreferences;
    String searchHashKey;

    public SearchAdapter(@NonNull Context context, @NonNull ArrayList<String> objects,
                         SharedPreferences sharedPreferences, String searchHashKey) {
        super(context, 0, objects);
        this.context           = context;
        this.list              = objects;
        this.sharedPreferences = sharedPreferences;
        this.searchHashKey     = searchHashKey;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_recent_search, parent, false);
        }

        String searchTerm = list.get(position);

        TextView tvSearchTerm = convertView.findViewById(R.id.tvSearchTerm);
        tvSearchTerm.setText(searchTerm);

        ImageView ivRemove = convertView.findViewById(R.id.ivRemove);
        ivRemove.setOnClickListener(v -> {
            list.remove(searchTerm);
            Set<String> updated = new HashSet<>(list);
            sharedPreferences.edit().putStringSet(searchHashKey, updated).apply();
            notifyDataSetChanged();
        });

        return convertView;
    }
}