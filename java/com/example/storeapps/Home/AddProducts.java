package com.example.storeapps.Home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.storeapps.Model.Products;
import com.example.storeapps.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddProducts extends Fragment {

    ImageView backArrow, imagePreview;
    RelativeLayout clear, save;
    EditText name, descr, subDesc, price;
    RelativeLayout changeImage;

    Products products;
    Uri imageuri;

    // Firebase
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    FirebaseUser currentUser;
    DatabaseReference reference;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_products, container, false);

        backArrow = view.findViewById(R.id.fragment_add_product_back);
        clear = view.findViewById(R.id.fragment_add_product_clear);
        save = view.findViewById(R.id.fragment_add_product_save);
        name = view.findViewById(R.id.fragment_add_product_ed_name);
        descr = view.findViewById(R.id.fragment_add_product_ed_desc);
        subDesc = view.findViewById(R.id.fragment_add_product_sub_desc);
        price = view.findViewById(R.id.fragment_add_product_price);
        changeImage = view.findViewById(R.id.fragment_add_products_change_image);
        imagePreview = view.findViewById(R.id.fragment_add_product_image_preview);

        setUpFirebase();

        Bundle bundle = getArguments();


        try {
            imageuri = Uri.parse(bundle.getString("image_uri"));
        }
        catch (NullPointerException e)
        {
            Toast.makeText(getActivity(), "Image Null", Toast.LENGTH_SHORT).show();
        }

        if (imageuri != null)
        {
            imagePreview.setImageURI(imageuri);
        }
        else
        {
            imagePreview.setImageResource(R.drawable.bikini_05);
        }


        changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (!name.getText().toString().equals(""))
//                {
//                    String productName = name.getText().toString();
//                    String productDescription = descr.getText().toString();
//                    String productSubDescription = subDesc.getText().toString();
//                    Integer productPrice = Integer.parseInt(price.getText().toString());
//
//                    products = new Products(productName,
//                            productDescription,
//                            productSubDescription,
//                            "",
//                            0,
//                            productPrice,
//                            productPrice,
//                            0,
//                            0,
//                            0
//                            );
//
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("product_temporary", products);
//
//                    addParoductChangeImageFragment.setArguments(bundle);
//
//
//
//                }
//                else
//                {
//                    Toast.makeText(getActivity(), "Please Insert Your Product Name", Toast.LENGTH_SHORT).show();
//                }

                AddParoductChangeImageFragment addParoductChangeImageFragment = new AddParoductChangeImageFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.from_bottom, R.anim.exit);
                fragmentTransaction.replace(R.id.fragment_add_product_frame_layout, addParoductChangeImageFragment).commit();

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageuri = null;
                name.setText("");
                descr.setText("");
                subDesc.setText("");
                price.setText("");

                imagePreview.setImageResource(R.drawable.bikini_05);

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!name.getText().toString().equals("")
                        && !descr.getText().toString().equals("")
                        && !subDesc.getText().toString().equals("")
                        && !price.getText().toString().equals("")
                        && imageuri != null
                )
                {

                    new AlertDialog.Builder(getActivity())
                            .setTitle("Upload Your Product")
                            .setMessage("Upload Your " + name.getText().toString())
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    products = new Products(
                                            name.getText().toString(),
                                            descr.getText().toString(),
                                            subDesc.getText().toString(),
                                            imageuri.toString(),
                                            Integer.parseInt(price.getText().toString()),
                                            Integer.parseInt(price.getText().toString()),
                                            0,
                                            0,
                                            0,
                                            0
                                            );

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


                                    storageReference.child(getString(R.string._my_store))
                            .child(getString(R.string._product_image_user))
                            .child(currentUser.getUid())
                            .child(products.getTitle().toLowerCase().toString() + ".")
                            .putFile(imageuri)
                                            .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                                    Toast.makeText(getActivity(), "Succes Uploading Product", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(getActivity(), "Error : " + e.toString(), Toast.LENGTH_SHORT).show();
                                                }
                                            });


                                }
                            })
                            .setNegativeButton("BACK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Please Input Your Credentrilal", Toast.LENGTH_SHORT).show();
                }
            }
        });


        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.setCustomAnimations(R.anim.from_bottom, R.anim.exit);
//
//                transaction.remove(AddProducts.this).commit();

                Intent intent = new Intent(getActivity(), YourProductActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);


            }
        });

        return view;
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