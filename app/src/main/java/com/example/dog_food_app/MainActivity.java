// MainActivity.java
package com.example.dog_food_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;

public class MainActivity extends Activity {

    private static final int SPLASH_DISPLAY_LENGTH = 2000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Correct method call
        setContentView(R.layout.activity_main); // Ensure this layout is for your splash screen

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the login page activity
                Intent intent = new Intent(MainActivity.this, LoginPage.class);
                startActivity(intent);
                finish(); // Close the splash activity
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
