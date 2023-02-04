package com.example.storeapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storeapps.Home.ActivityChatPageBottomFragment;
import com.example.storeapps.Home.ChatPageProductPage;
import com.example.storeapps.Home.RecViewProductAdapter;
import com.example.storeapps.Model.Products;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ProducPage extends AppCompatActivity {

    ImageView backArrow, chatPage, cartPage;
    ImageView imageProducts;
    TextView descText, priceText, ratingText, selledText;
    RatingBar ratingBar;
    RelativeLayout buyNowRel;
    FrameLayout frameLayout;
    RecyclerView recyclerView;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    FirebaseUser currentUser;
    DatabaseReference reference;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produc_page);

        setUpFirebase();

        Products products = (Products) getIntent().getSerializableExtra("products");
        imageProducts = findViewById(R.id.imageProduct);
        descText = findViewById(R.id.activity_product_description);
        priceText = findViewById(R.id.product_page_price_tag);
        ratingText = findViewById(R.id.activity_product_page_rating_text);
        selledText = findViewById(R.id.selledText);
        ratingBar = findViewById(R.id.activity_produc_rating_bar);
        chatPage = findViewById(R.id.activity_product_page_chat);
        cartPage = findViewById(R.id.cart);
        buyNowRel = findViewById(R.id.activity_product_page_buy_now);
        frameLayout = findViewById(R.id.activity_chat_page_product_page_frame_layout);
        recyclerView = findViewById(R.id.activity_product_rec_view);

        ratingBar.setRating(products.getNum_rating());

        ratingText.setText(String.valueOf(products.getNum_rating()));

        descText.setText(products.getDescription());
        priceText.setText(String.valueOf(products.getPrice()));
        selledText.setText(String.valueOf(products.getSaledNumber()) + " Selled");



        backArrow = findViewById(R.id.backArrow);

        storageReference = FirebaseStorage.getInstance().getReference()
                .child(getString(R.string._my_store))
                .child(getString(R.string._product_image_user))
                .child(currentUser.getUid())
                .child(products.getTitle() + ".");

        try {
            final File localFile = File.createTempFile(products.getTitle() + ".", "jpg");
            storageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            imageProducts.setImageBitmap(bitmap);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            imageProducts.setImageResource(R.drawable.bikini_01);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }




        ArrayList<Products> productsArrayList = new ArrayList<>();

        reference.child(getString(R.string.store_app))
                .child(getString(R.string.product)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            Products products = dataSnapshot.getValue(Products.class);
                            productsArrayList.add(products);
                        }
                        RecViewProductAdapter productAdapter = new RecViewProductAdapter(ProducPage.this, productsArrayList);

                        recyclerView.setAdapter(productAdapter);
                        GridLayoutManager layoutManager1 = new GridLayoutManager(ProducPage.this, 2);

                        recyclerView.setLayoutManager(layoutManager1);
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

        chatPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProducPage.this, ChatPageProductPage.class);
                startActivity(intent);
            }
        });

        cartPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("products", products);

                ActivityChatPageBottomFragment activityChatPageBottomFragment = new ActivityChatPageBottomFragment();
                activityChatPageBottomFragment.setArguments(bundle);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.from_bottom, R.anim.exit);

                transaction.replace(R.id.activity_chat_page_product_page_frame_layout, activityChatPageBottomFragment).commit();
            }
        });

        buyNowRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("products", products);

                ActivityChatPageBottomFragment activityChatPageBottomFragment = new ActivityChatPageBottomFragment();
                activityChatPageBottomFragment.setArguments(bundle);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.from_bottom, R.anim.exit);
                transaction.replace(R.id.activity_chat_page_product_page_frame_layout, activityChatPageBottomFragment).commit();
            }
        });

    }

    private void setUpFirebase() {
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        currentUser = mAuth.getCurrentUser();
        reference = database.getReference();

    }

}