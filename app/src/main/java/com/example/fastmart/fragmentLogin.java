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
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class fragmentLogin extends Fragment {

    TextInputEditText etEmail;
    TextInputEditText etPassword;
    Button btnLogin;

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference ref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);
        setListeners();
    }

    public void init(View view) {
        etEmail    = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        btnLogin   = view.findViewById(R.id.btnLogin);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        ref  = FirebaseDatabase.getInstance().getReference("users");
    }

    public void setListeners() {
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
                etPassword.setError("Password is required");
                return;
            } else {
                etPassword.setError(null);
            }

            btnLogin.setEnabled(false);

            auth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener(authResult -> {
                        String uid = authResult.getUser().getUid();

                        ref.child(uid).child("accountType").get()
                                .addOnSuccessListener(snapshot -> {
                                    String accountType = snapshot.getValue(String.class);

                                    Intent intent;
                                    if ("Seller".equals(accountType)) {
                                        intent = new Intent(getActivity(), SellerHomeScreen.class);
                                    } else {
                                        intent = new Intent(getActivity(), MainViewPager.class);
                                    }
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                })
                                .addOnFailureListener(e -> {
                                    btnLogin.setEnabled(true);
                                    Toast.makeText(getActivity(), "Failed to fetch user data: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                });
                    })
                    .addOnFailureListener(e -> {
                        btnLogin.setEnabled(true);
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    });
        });
    }
}