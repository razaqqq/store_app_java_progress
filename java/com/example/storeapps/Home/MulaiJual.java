package com.example.storeapps.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.storeapps.R;

public class MulaiJual extends AppCompatActivity {

    ImageView backArrow;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulai_jual);

        backArrow = findViewById(R.id.activity_mulai_jual_back_arrow);
        button = findViewById(R.id.activity_mulai_jual_button);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MulaiPendaftaran.class);
                startActivity(intent);
            }
        });

    }
}