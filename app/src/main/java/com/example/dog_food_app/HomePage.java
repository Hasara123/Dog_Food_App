package com.example.dog_food_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DogFoodAdapter adapter;
    private List<DogFoodItem> dogFoodItems;
    private String selectedBrand = "Brand";
    private String selectedType = "Type";
    private String selectedAge = "Age"; // 0 represents "All"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Sample data
        dogFoodItems = new ArrayList<>();
        dogFoodItems.add(new DogFoodItem("Pedigree Chopped Ground Dinner", "Wet Dog Food", R.drawable.beneful, "Pedigree", "wet", "Adult",2000));
        dogFoodItems.add(new DogFoodItem("Blue Buffalo Wilderness Trail Toppers", "Wet Dog Food", R.drawable.dogfood1, "Blue Buffalo", "wet", "Puppy",1000));
        dogFoodItems.add(new DogFoodItem("Purina ONE SmartBlend", "Wet Dog Food", R.drawable.dogfood2, "Purina", "wet", "Senior",1500));
        dogFoodItems.add(new DogFoodItem("Blue Buffalo Wilderness Trail Toppers", "Dry Dog Food", R.drawable.dogfood3, "Purina", "Dry", "Adult",400));
        dogFoodItems.add(new DogFoodItem("Blue Buffalo Wilderness Trail Toppers", "Dry Dog Food", R.drawable.dogfood4, "Purina", "Dry", "Adult",560));

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DogFoodAdapter(this, dogFoodItems); // Pass context and list
        recyclerView.setAdapter(adapter);

        setupSpinners();
    }

    private void setupSpinners() {
        // Setup brand spinner
        Spinner brandSpinner = findViewById(R.id.brand_spinner);
        ArrayAdapter<CharSequence> brandSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.brands_array, android.R.layout.simple_spinner_item);
        brandSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        brandSpinner.setAdapter(brandSpinnerAdapter);
        brandSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedBrand = parentView.getItemAtPosition(position).toString();
                filterItems();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selectedBrand = "Brand";
                filterItems();
            }
        });

        // Setup type spinner
        Spinner typeSpinner = findViewById(R.id.type_spinner);
        ArrayAdapter<CharSequence> typeSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.types_array, android.R.layout.simple_spinner_item);
        typeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeSpinnerAdapter);
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedType = parentView.getItemAtPosition(position).toString();
                filterItems();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selectedType = "Type";
                filterItems();
            }
        });

        // Setup age spinner
        Spinner ageSpinner = findViewById(R.id.age_spinner);
        ArrayAdapter<CharSequence> ageSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.ages_array, android.R.layout.simple_spinner_item);
        ageSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ageSpinner.setAdapter(ageSpinnerAdapter);
        ageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedAge = parentView.getItemAtPosition(position).toString();
                filterItems();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selectedAge = "Age"; // "All"
                filterItems();
            }
        });
    }

    private void filterItems() {
        List<DogFoodItem> filteredList = new ArrayList<>();
        for (DogFoodItem item : dogFoodItems) {
            boolean matchesBrand = selectedBrand.equals("Brand") || item.getBrand().equals(selectedBrand);
            boolean matchesType = selectedType.equals("Type") || item.getType().equals(selectedType);
            boolean matchesAge = selectedAge.equals("Age") || item.getAge().equals(selectedAge);

            if (matchesBrand && matchesType && matchesAge) {
                filteredList.add(item);
            }
        }
        adapter.updateList(filteredList);
    }
}
