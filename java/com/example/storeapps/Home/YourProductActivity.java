package com.example.storeapps.Home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.storeapps.Model.Products;
import com.example.storeapps.Model.Store;
import com.example.storeapps.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class YourProductActivity extends AppCompatActivity {

    ImageView backArrow;
    RecyclerView recViewYourProduct;
    FrameLayout frameLayout;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    FirebaseUser currentUser;
    DatabaseReference reference;

    ExtendedFloatingActionButton uploadButton;


    ArrayList<Products> productsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_product);
        backArrow = findViewById(R.id.activity_your_product_back);
        recViewYourProduct = findViewById(R.id.activity_your_product_rec_view);
        uploadButton = findViewById(R.id.activity_your_product_upload_product);
        frameLayout = findViewById(R.id.acativity_your_product_frame_layout);

        setUpFireabase();




        Toast.makeText(this, currentUser.getUid().toString(), Toast.LENGTH_SHORT).show();

        Query query = reference.child(getString(R.string.store_app))
                        .child(getString(R.string.user))
                        .child(currentUser.getUid())
                        .child(getString(R.string.store))
                        .child(getString(R.string.product))
                        ;

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                   Products products = dataSnapshot.getValue(Products.class);
                   productsArrayList.add(products);
                }



                YourProductRecViewAdapter yourProductRecViewAdapter = new YourProductRecViewAdapter(productsArrayList, YourProductActivity.this);
                recViewYourProduct.setAdapter(yourProductRecViewAdapter);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(YourProductActivity.this, 2);
                recViewYourProduct.setLayoutManager(gridLayoutManager);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddProducts addProducts = new AddProducts();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.from_bottom, R.anim.exit);
                transaction.replace(R.id.acativity_your_product_frame_layout, addProducts).commit();
            }
        });














        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void setUpFireabase()
    {
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        currentUser = mAuth.getCurrentUser();
        reference = database.getReference();
    }

}