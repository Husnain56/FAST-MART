package com.example.fastmart;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class fragmentSignup extends Fragment {

    TextInputEditText etName, etEmail, etPhone, etDob,
            etAddress, etCountry, etPassword, etVerifyPassword;
    RadioGroup rgGender, rgAccountType;
    Button btnSignUp;

    SharedPreferences sPref;
    FirebaseAuth auth;
    DatabaseReference dbRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        init(view);
        setListeners();
        return view;
    }

    public void init(View view) {
        auth             = FirebaseAuth.getInstance();
        dbRef            = FirebaseDatabase.getInstance().getReference("users");

        etName           = view.findViewById(R.id.etName);
        etEmail          = view.findViewById(R.id.etEmail);
        etPhone          = view.findViewById(R.id.etPhone);
        etDob            = view.findViewById(R.id.etDob);
        etAddress        = view.findViewById(R.id.etAddress);
        etCountry        = view.findViewById(R.id.etCountry);
        etPassword       = view.findViewById(R.id.etPassword);
        etVerifyPassword = view.findViewById(R.id.etVerifyPassword);
        rgGender         = view.findViewById(R.id.rgGender);
        rgAccountType    = view.findViewById(R.id.rgAccountType);
        btnSignUp        = view.findViewById(R.id.btnSignUp);

        sPref = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
    }

    public void setListeners() {

        etDob.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year  = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day   = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePicker = new DatePickerDialog(requireContext(),
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        String dob = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        etDob.setText(dob);
                    }, year, month, day);

            datePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePicker.show();
        });

        btnSignUp.setOnClickListener(v -> {
            String name     = Objects.requireNonNull(etName.getText()).toString().trim();
            String email    = Objects.requireNonNull(etEmail.getText()).toString().trim();
            String phone    = Objects.requireNonNull(etPhone.getText()).toString().trim();
            String dob      = Objects.requireNonNull(etDob.getText()).toString().trim();
            String address  = Objects.requireNonNull(etAddress.getText()).toString().trim();
            String country  = Objects.requireNonNull(etCountry.getText()).toString().trim();
            String password = Objects.requireNonNull(etPassword.getText()).toString().trim();
            String confirm  = Objects.requireNonNull(etVerifyPassword.getText()).toString().trim();

            if (name.isEmpty()) {
                etName.setError("Name is required");
                return;
            }
            if (email.isEmpty()) {
                etEmail.setError("Email is required");
                return;
            }
            if (phone.isEmpty()) {
                etPhone.setError("Phone is required");
                return;
            }
            if (dob.isEmpty()) {
                etDob.setError("Date of birth is required");
                return;
            }
            if (address.isEmpty()) {
                etAddress.setError("Address is required");
                return;
            }
            if (country.isEmpty()) {
                etCountry.setError("Country is required");
                return;
            }
            if (rgGender.getCheckedRadioButtonId() == -1) {
                Toast.makeText(getActivity(), "Please select your gender", Toast.LENGTH_SHORT).show();
                return;
            }
            if (rgAccountType.getCheckedRadioButtonId() == -1) {
                Toast.makeText(getActivity(), "Please select account type", Toast.LENGTH_SHORT).show();
                return;
            }
            if (password.isEmpty()) {
                etPassword.setError("Password is required");
                return;
            }
            if (password.length() < 6) {
                etPassword.setError("Password must be at least 6 characters");
                return;
            }
            if (!password.equals(confirm)) {
                etVerifyPassword.setError("Passwords do not match");
                return;
            }


            String gender = rgGender.getCheckedRadioButtonId() == R.id.rbMale ? "Male"
                    : rgGender.getCheckedRadioButtonId() == R.id.rbFemale ? "Female"
                    : "Other";

            String accountType = rgAccountType.getCheckedRadioButtonId() == R.id.rbBuyer
                    ? "Buyer" : "Seller";

            btnSignUp.setEnabled(false);

            auth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener(authResult -> {
                        String uid = Objects.requireNonNull(authResult.getUser()).getUid();

                        Map<String, String> userMap = new HashMap<>();
                        userMap.put("name",        name);
                        userMap.put("email",       email);
                        userMap.put("phone",       phone);
                        userMap.put("dob",         dob);
                        userMap.put("address",     address);
                        userMap.put("country",     country);
                        userMap.put("gender",      gender);
                        userMap.put("accountType", accountType);

                        dbRef.child(uid).setValue(userMap)
                                .addOnSuccessListener(unused -> {

                                    btnSignUp.setEnabled(true);
                                    Toast.makeText(getActivity(), "Account created! Please log in.", Toast.LENGTH_SHORT).show();

                                    ((AuthenticationActivity) requireActivity()).switchToLogin();
                                })
                                .addOnFailureListener(e -> {
                                    Objects.requireNonNull(authResult.getUser()).delete();
                                    btnSignUp.setEnabled(true);
                                    Toast.makeText(getActivity(), "Failed to save user data: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                });
                    })
                    .addOnFailureListener(e -> {
                        btnSignUp.setEnabled(true);
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    });
        });
    }
}