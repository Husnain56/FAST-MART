package com.example.fastmart;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FragmentBrowse extends Fragment {

    public static final String SEARCH_HASH_KEY = "search_history";
    private static final String PREFS_NAME      = "fastmart_prefs";

    ListView lvRecentSearches;
    ArrayList<String> list;
    SearchAdapter adapter;
    TextView tvClearAll;
    EditText etSearch;
    ImageView ivBack;

    SharedPreferences sharedPreferences;

    ArrayList<Product> allProducts = ProductList.getProducts();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browse, container, false);
        init(view);
        return view;
    }

    public void init(View view) {
        sharedPreferences = requireActivity()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        lvRecentSearches = view.findViewById(R.id.lvRecentSearches);
        tvClearAll       = view.findViewById(R.id.tvClearAll);
        etSearch         = view.findViewById(R.id.etSearch);
        ivBack           = view.findViewById(R.id.ivBack);

        list    = loadSearchHistory();
        adapter = new SearchAdapter(getContext(), list, sharedPreferences, SEARCH_HASH_KEY);
        lvRecentSearches.setAdapter(adapter);


        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String query = etSearch.getText().toString().trim();
                if (!query.isEmpty()) {
                    saveSearchTerm(query);
                    searchProduct(query);
                }
                return true;
            }
            return false;
        });


        tvClearAll.setOnClickListener(v -> {
            list.clear();
            sharedPreferences.edit().remove(SEARCH_HASH_KEY).apply();
            adapter.notifyDataSetChanged();
        });

        ivBack.setOnClickListener(v -> hideKeyboard());
    }

    private void searchProduct(String query) {
        boolean found = false;
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(query.toLowerCase())) {
                found = true;
                break;
            }
        }

        new AlertDialog.Builder(requireContext())
                .setTitle(found ? "Product Found" : "Not Found")
                .setMessage(found
                        ? "Product Found."
                        : "No product matched \"" + query + "\".")
                .setPositiveButton("OK", null)
                .show();
    }

    private void saveSearchTerm(String term) {
        if (!list.contains(term)) {
            list.add(0, term);
            adapter.notifyDataSetChanged();
        }
        Set<String> historySet = new HashSet<>(list);
        sharedPreferences.edit().putStringSet(SEARCH_HASH_KEY, historySet).apply();
    }

    private ArrayList<String> loadSearchHistory() {
        Set<String> historySet = sharedPreferences
                .getStringSet(SEARCH_HASH_KEY, new HashSet<>());
        return new ArrayList<>(historySet);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        View focused = requireActivity().getCurrentFocus();
        if (focused != null) {
            imm.hideSoftInputFromWindow(focused.getWindowToken(), 0);
            focused.clearFocus();
        }
    }
}