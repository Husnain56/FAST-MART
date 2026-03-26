package com.example.fastmart;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class fragmentSignup extends Fragment {

    TextInputEditText etEmail;
    TextInputEditText etPassword;
    TextInputEditText etVerifyPassword;
    Button btnSignUp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        init(view);
        setListeners();
        return view;
    }

    public void init(View view){
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        etVerifyPassword = view.findViewById(R.id.etVerifyPassword);
        btnSignUp = view.findViewById(R.id.btnSignUp);
    }
    public void setListeners() {
        btnSignUp.setOnClickListener(v -> {
            String email    = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirm  = etVerifyPassword.getText().toString().trim();

            if (email.isEmpty()) {
                etEmail.setError("Email is required");
                return;
            } else { etEmail.setError(null); }

            if (password.isEmpty()) {
                Toast.makeText(getActivity(), "Password is required", Toast.LENGTH_SHORT).show();
                return;
            }

            if (confirm.isEmpty()) {
                Toast.makeText(getActivity(), "Please confirm your password", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirm)) {
                Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            requireActivity().finish();
        });
    }
}