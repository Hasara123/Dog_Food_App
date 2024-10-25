package com.example.dog_food_app;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_registration extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword, editTextEmail;
    private Button btnRegister;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextEmail = findViewById(R.id.editTextEmail);
        btnRegister = findViewById(R.id.btnRegister);
        databaseHelper = new DatabaseHelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                    Toast.makeText(activity_registration.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        Toast.makeText(activity_registration.this, "Invalid email format", Toast.LENGTH_SHORT).show();
                    } else if (password.length() < 6) {
                        Toast.makeText(activity_registration.this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
                    } else if (!password.matches(".*[A-Z].*")) {
                        Toast.makeText(activity_registration.this, "Password must contain at least one uppercase letter", Toast.LENGTH_SHORT).show();
                    } else if (!password.matches(".*[a-z].*")) {
                        Toast.makeText(activity_registration.this, "Password must contain at least one lowercase letter", Toast.LENGTH_SHORT).show();
                    } else if (!password.matches(".*[0-9].*")) {
                        Toast.makeText(activity_registration.this, "Password must contain at least one digit", Toast.LENGTH_SHORT).show();
                    } else if (databaseHelper.checkUser(email)) {
                        Toast.makeText(activity_registration.this, "Email already registered", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean result = databaseHelper.addUser(username, password, email);
                        if (result) {
                            Toast.makeText(activity_registration.this, "Registration successful", Toast.LENGTH_SHORT).show();
                            finish(); // Close this activity and return to the previous one
                        } else {
                            Toast.makeText(activity_registration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}
