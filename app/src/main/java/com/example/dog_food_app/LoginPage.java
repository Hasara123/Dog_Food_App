package com.example.dog_food_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {

    private Button btnContinue;
    private EditText editTextEmail;
    private TextView txtSignUp;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Ensure this layout file exists and is correct

        // Initialize UI components
        btnContinue = findViewById(R.id.btnContinue);
        editTextEmail = findViewById(R.id.editTextEmail);
        txtSignUp = findViewById(R.id.txtSignUp);
        databaseHelper = new DatabaseHelper(this);

        // Set onClick listener for the "Continue" button
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the email entered by the user
                String email = editTextEmail.getText().toString().trim();

                // Validate the email field
                if (email.isEmpty()) {
                    Toast.makeText(LoginPage.this, "Please enter an email", Toast.LENGTH_SHORT).show();
                } else {
                    // Check if the email exists in the database
                    if (databaseHelper.checkUser(email)) {
                        // Show "Login successful" message
                        Toast.makeText(LoginPage.this, "Login successful", Toast.LENGTH_SHORT).show();

                        // Navigate to the HomePage activity
                        Intent intent = new Intent(LoginPage.this, HomePage.class);
                        startActivity(intent);
                    } else {
                        // If email is not found, show a toast message
                        Toast.makeText(LoginPage.this, "Email not found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Set onClick listener for the "Sign Up" text
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Registration activity
                Intent intent = new Intent(LoginPage.this, activity_registration.class);
                startActivity(intent);
            }
        });
    }
}
