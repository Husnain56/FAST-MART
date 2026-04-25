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
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentProfile extends Fragment {

    FirebaseAuth auth;
    Button btnLogout;
    FirebaseUser user;
    FirebaseDatabase db;
    EditText etName;
    EditText etAddress;
    EditText etCountry;
    EditText etDob;
    EditText etGender;
    EditText etPhone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            init(view);
            setListeners();
            fetchRecords();
    }

    public void init(View view){
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        user = auth.getCurrentUser();
        btnLogout = view.findViewById(R.id.btnLogout);
        etName = view.findViewById(R.id.etName);
        etAddress = view.findViewById(R.id.etAddress);
        etCountry = view.findViewById(R.id.etCountry);
        etDob = view.findViewById(R.id.etDob);
        etGender = view.findViewById(R.id.etGender);
        etPhone = view.findViewById(R.id.etPhone);
    }
    public void fetchRecords(){
        String uid = user.getUid();

        db.getReference("users").child(uid).get()
                .addOnSuccessListener(snapshot -> {
                    if (snapshot.exists()){
                        String name    = snapshot.child("name").getValue(String.class);
                        String address = snapshot.child("address").getValue(String.class);
                        String country = snapshot.child("country").getValue(String.class);
                        String dob     = snapshot.child("dob").getValue(String.class);
                        String gender  = snapshot.child("gender").getValue(String.class);
                        String phone   = snapshot.child("phone").getValue(String.class);
                        etName.setText(name);
                        etAddress.setText(address);
                        etCountry.setText(country);
                        etDob.setText(dob);
                        etGender.setText(gender);
                        etPhone.setText(phone);
                    }
                })
                .addOnFailureListener(e->{
                    Toast.makeText(getContext(), "Failed to load profile: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    public void setListeners(){
        btnLogout.setOnClickListener(v->{
            auth.signOut();
            startActivity(new Intent(getActivity(), AuthenticationActivity.class));
            getActivity().finish();
        });
    }
}