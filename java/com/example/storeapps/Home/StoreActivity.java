package com.example.storeapps.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.storeapps.Model.Store;
import com.example.storeapps.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class StoreActivity extends AppCompatActivity {

    ImageView backArrow;
    TextView storeName, storeName_2, alamat, email, noHp;

    RelativeLayout yourProductRelLayout;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    FirebaseUser currentUser;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        backArrow = findViewById(R.id.activity_store_back);
        yourProductRelLayout = findViewById(R.id.yourProduct);
        storeName = findViewById(R.id.activity_store_store_name);
        storeName_2 = findViewById(R.id.activity_store_store_name_2);
        alamat = findViewById(R.id.activity_store_alamat);
        email = findViewById(R.id.activity_store_email);
        noHp = findViewById(R.id.activity_store_no_hp);

        setUpFirebase();

        Query query = reference.child(getString(R.string.store_app))
                        .child(getString(R.string.user))
                        .child(currentUser.getUid())
                        .child(getString(R.string.store_information));

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    Store store = new Store();

                    storeName.setText(store.getStoreName());
                    storeName_2.setText(store.getStoreName());
                    alamat.setText(store.getAlamat());
                    email.setText(store.getEmail());
                    noHp.setText(store.getNoHP());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        yourProductRelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StoreActivity.this, YourProductActivity.class);
                startActivity(intent);
            }
        });


    }

    private void setUpFirebase()
    {
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        currentUser = mAuth.getCurrentUser();
        reference = database.getReference();
    }

}