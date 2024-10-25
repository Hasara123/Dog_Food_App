package com.example.dog_food_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DogFoodAdapter extends RecyclerView.Adapter<DogFoodAdapter.ViewHolder> {
    private Context context; // Add Context
    private List<DogFoodItem> dogFoodList;

    public DogFoodAdapter(Context context, List<DogFoodItem> dogFoodList) {
        this.context = context; // Initialize Context
        this.dogFoodList = dogFoodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.dog_food_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DogFoodItem item = dogFoodList.get(position);
        holder.imageView.setImageResource(item.getImageResId());
        holder.nameTextView.setText(item.getName());
        holder.descriptionTextView.setText(item.getDescription());

        holder.addButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, CartItem.class);
            intent.putExtra("ITEM_NAME", item.getName());
            intent.putExtra("ITEM_TYPE", item.getType());
            intent.putExtra("ITEM_BRAND", item.getBrand());
            intent.putExtra("ITEM_AGE", item.getAge());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dogFoodList.size();
    }

    public void updateList(List<DogFoodItem> newList) {
        dogFoodList = newList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView;
        TextView descriptionTextView;
        Button addButton;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            nameTextView = itemView.findViewById(R.id.text_view_name);
            descriptionTextView = itemView.findViewById(R.id.text_view_description);
            addButton = itemView.findViewById(R.id.add_button);
        }
    }
}
