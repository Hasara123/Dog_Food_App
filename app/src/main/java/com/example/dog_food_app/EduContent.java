package com.example.dog_food_app;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EduContent extends AppCompatActivity {
    TextView article_title,article_author,article_read_time,article_title1,article_author1,article_read_time1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edu_content);
        article_author =findViewById(R.id.article_author);
        article_title = findViewById(R.id.article_title);
        article_read_time = findViewById(R.id.article_read_time);
        article_title1 =findViewById(R.id.article_title1);
        article_author1 = findViewById(R.id.article_author1);
        article_read_time1 = findViewById(R.id.article_read_time1);
    }
}