package com.example.storeapps.Home.UserFragmentChild;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.storeapps.R;
import com.example.storeapps.login.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class PengaturanAccount extends AppCompatActivity {

    RecyclerView rec_1;
    RecyclerView rec_2;
    RecyclerView rec_3;
    ImageView back_arrow;
    Button log_out;

    // Firebase
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    FirebaseUser currentUser;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan_account);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        rec_1 = findViewById(R.id.rec_view_1);
        rec_2 = findViewById(R.id.rec_view_2);
        rec_3 = findViewById(R.id.rec_view_3);
        back_arrow = findViewById(R.id.backArrow);
        log_out = findViewById(R.id.log_out);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser = mAuth.getCurrentUser();
                if (currentUser == null)
                {
                    startActivity(new Intent(PengaturanAccount.this, Login.class));
                    finish();
                }
                else
                {
                    back_arrow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    });

                    ArrayList<String> textList = new ArrayList<>();
                    textList.add("Account Security");
                    textList.add("My Address");
                    textList.add("Card Bank");

                    BuyAdapter3 buyAdapter3 = new BuyAdapter3(PengaturanAccount.this, textList);
                    rec_1.setAdapter(buyAdapter3);

                    LinearLayoutManager layoutManager = new LinearLayoutManager(PengaturanAccount.this);
                    rec_1.setLayoutManager(layoutManager);

                    ArrayList<String> textList2 = new ArrayList<>();
                    textList2.add("Chat Setting");
                    textList2.add("Notification Setting");
                    textList2.add("Privacy Setting");
                    textList2.add("Blocked User");
                    textList2.add("Languange");

                    BuyAdapter4 buyAdapter4 = new BuyAdapter4(PengaturanAccount.this, textList2);
                    rec_2.setAdapter(buyAdapter4);

                    LinearLayoutManager layoutManager1 = new LinearLayoutManager(PengaturanAccount.this);
                    rec_2.setLayoutManager(layoutManager1);

                    ArrayList<String> textList3 = new ArrayList<>();

                    textList3.add("Help Center");
                    textList3.add("Tips and trick");
                    textList3.add("Community Setting");
                    textList3.add("MyStore Law");
                    textList3.add("Do You Like Our Service ?");
                    textList3.add("Information");
                    textList3.add("Account Deletion");

                    BuyAdapter5 buyAdapter5 = new BuyAdapter5(PengaturanAccount.this, textList3);
                    rec_3.setAdapter(buyAdapter5);

                    LinearLayoutManager layoutManager3 = new LinearLayoutManager(PengaturanAccount.this);
                    rec_3.setLayoutManager(layoutManager3);

                    log_out.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mAuth.signOut();
                        }
                    });

                }
            }
        };



    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
}