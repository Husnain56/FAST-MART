package com.example.fastmart;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class fragmentLogin extends Fragment {

    TextInputEditText etEmail;
    TextInputEditText etPassword;
    Button btnLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        init(view);
        setListeners();
        return view;
    }

    public void init(View view){
        etEmail     = view.findViewById(R.id.etEmail);
        etPassword  = view.findViewById(R.id.etPassword);
        btnLogin    = view.findViewById(R.id.btnLogin);
    }
    public void setListeners(){
        btnLogin.setOnClickListener(v -> {
            String email    = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty()) {
                etEmail.setError("Email is required");
                return;
            } else {
                etEmail.setError(null);
            }

            if (password.isEmpty()) {
                Toast.makeText(getActivity(), "Password is required", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(getActivity(), HomePage.class);
            startActivity(intent);
            requireActivity().finish();
        });
    }
}