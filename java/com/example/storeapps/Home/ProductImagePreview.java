package com.example.storeapps.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.storeapps.Model.Products;
import com.example.storeapps.R;

public class ProductImagePreview extends AppCompatActivity {

    ImageView back, imagePreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_image_preview);



        back = findViewById(R.id.activity_product_image_back);
        imagePreview = findViewById(R.id.activity_product_image_preview_image);

        Intent intent = getIntent();
        Products products = (Products) intent.getSerializableExtra("products");

        if (products != null)
        {
            imagePreview.setImageURI(Uri.parse(products.getImage()));
        }
        else
        {
            Toast.makeText(this, "Products Is Empty", Toast.LENGTH_SHORT).show();
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}