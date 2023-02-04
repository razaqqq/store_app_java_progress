package com.example.storeapps.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.storeapps.Home.UserFragmentChild.BuyFragment;
import com.example.storeapps.Home.UserFragmentChild.PengaturanAccount;
import com.example.storeapps.MainActivity;
import com.example.storeapps.Model.Products;
import com.example.storeapps.Model.Store;
import com.example.storeapps.R;
import com.example.storeapps.Util.SectionPagerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UploadYourProduct extends AppCompatActivity {

    Button back, uploadProduct;
    EditText edProductName, edProductDescription, edProductSubDescription, edProductPrice;
    RelativeLayout changeImage;
    ImageView uploadProductsss;


    private ViewPager mViewPager;
    TabLayout tabLayout;

    // Firebase
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    FirebaseUser currentUser;
    DatabaseReference reference;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    Uri productImage;

    Store store = new Store();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_your_product);



        back = findViewById(R.id.activity_upload_your_product_back);
        uploadProduct = findViewById(R.id.btn_activity_uploady_your_product);
        edProductName = findViewById(R.id.activity_upload_your_product_product_name);
        edProductDescription = findViewById(R.id.activity_upload_your_product_product_description);
        edProductSubDescription = findViewById(R.id.activity_upload_your_product_sub_description);
        edProductPrice = findViewById(R.id.activity_upload_your_product_price);
        changeImage = findViewById(R.id.upload_your_product_product_image);
        uploadProductsss = findViewById(R.id.activity_upload_your_product_image);

        setUpFirebase();

        store = (Store) getIntent().getSerializableExtra("storeObject");



        try {
            reference.child(getString(R.string.store_app))
                    .child(getString(R.string.user))
                    .child(currentUser.getUid())
                    .child(getString(R.string.store))
                    .child("store_information")
                    .setValue(store);
        }catch (NullPointerException e)
        {
            Toast.makeText(UploadYourProduct.this, "Store is Null", Toast.LENGTH_SHORT).show();
        }



        productImage = getIntent().getParcelableExtra("upload_image");

        if (productImage == null)
        {
            uploadProductsss.setImageResource(R.drawable.bikini_01);
        }
        else
        {
            uploadProductsss.setImageURI(productImage);
        }

        changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UploadYourProduct.this, ChangeImageProduct.class);
                startActivity(intent);
            }
        });

        uploadProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (
                        !edProductName.getText().toString().equals("") &&
                                !edProductDescription.getText().toString().equals("") &&
                                !edProductSubDescription.getText().toString().equals("") &&
                                !edProductPrice.getText().toString().equals("")
                )
                {

                    String productName = edProductName.getText().toString();
                    String productDescriptions = edProductDescription.getText().toString();
                    String productSubDescriptions = edProductSubDescription.getText().toString();
                    String productPrice = edProductPrice.getText().toString();

                    Products products = new Products(productName,
                            productDescriptions,
                            productSubDescriptions,
                            productImage.toString(),
                            Integer.valueOf(productPrice),
                            Integer.valueOf(productPrice),
                            0, 0,
                            0,
                            123456);

                    new AlertDialog.Builder(UploadYourProduct.this)
                            .setTitle("Upload Product")
                            .setMessage("Are You Sure Want To Upload Your Product")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    new AlertDialog.Builder(UploadYourProduct.this)
                                            .setTitle("Your Product Has Been Uploaded")
                                            .setPositiveButton("Home", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {

                                                    Toast.makeText(UploadYourProduct.this, productImage.toString(), Toast.LENGTH_SHORT).show();

                                                    reference.child(getString(R.string.store_app))
                                                            .child(getString(R.string.user))
                                                            .child(currentUser.getUid())
                                                            .child(getString(R.string.store))
                                                            .child(getString(R.string.product))
                                                            .push()
                                                            .setValue(products)
                                                    ;

                                                    reference.child(getString(R.string.store_app))
                                                            .child(getString(R.string.product))
                                                            .push()
                                                            .setValue(products);



                                                    if (productImage == null)
                                                    {
                                                        Toast.makeText(UploadYourProduct.this, "Product Image is Null", Toast.LENGTH_SHORT).show();
                                                    }
                                                    else
                                                    {
//                                                        storageReference.child(getString(R.string._my_store))
//                                                                .child(getString(R.string._product_image_user))
//                                                                .child(currentUser.getUid())
//                                                                .child(System.currentTimeMillis() + ".")
//                                                                .putFile(productImage);
                                                        storageReference.child(getString(R.string._my_store))
                                                                .child(getString(R.string._product_image_user))
                                                                .child(currentUser.getUid())
                                                                .child(products.getTitle().toLowerCase().toString() + ".")
                                                                .putFile(productImage)
                                                                .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                                                        Toast.makeText(getApplicationContext(), "Succes Uploading Product", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                })
                                                                .addOnFailureListener(new OnFailureListener() {
                                                                    @Override
                                                                    public void onFailure(@NonNull Exception e) {
                                                                        Toast.makeText(getApplicationContext(), "Error : " + e.toString(), Toast.LENGTH_SHORT).show();
                                                                    }
                                                                });

                                                        Intent intent = new Intent(UploadYourProduct.this, MainActivity.class);
                                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                        startActivity(intent);

                                                    }





//                                                UserFragment userFragment = new UserFragment();
//
//                                                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                                                fragmentTransaction.replace(R.id.activity_upload_your_product, userFragment).commit();
//                                                Toast.makeText(UploadYourProduct.this, "Your Product Has Been Uploaded", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .show();



//                                setUpViewPager();

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .show();
                }
                else
                {
                    Toast.makeText(UploadYourProduct.this, "Please Input Your Credential", Toast.LENGTH_SHORT).show();
                }


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void setUpFirebase() {
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();
        currentUser = mAuth.getCurrentUser();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
    }


}