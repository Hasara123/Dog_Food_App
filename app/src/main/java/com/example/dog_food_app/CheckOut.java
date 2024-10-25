package com.example.dog_food_app;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class CheckOut extends AppCompatActivity {

    private EditText etQuantity1, etQuantity2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialization code
    }

    public void decreaseQuantity(View view) {
        EditText quantityEditText = (EditText) ((View) view.getParent()).findViewById(R.id.etQuantity1);
        int quantity = Integer.parseInt(quantityEditText.getText().toString());
        if (quantity > 1) {
            quantity--;
            quantityEditText.setText(String.valueOf(quantity));
        }
    }

    public void increaseQuantity(View view) {
        EditText quantityEditText = (EditText) ((View) view.getParent()).findViewById(R.id.etQuantity1);
        int quantity = Integer.parseInt(quantityEditText.getText().toString());
        quantity++;
        quantityEditText.setText(String.valueOf(quantity));
    }

}