package com.example.storeapps.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.storeapps.Model.Store;
import com.example.storeapps.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MulaiPendaftaran extends AppCompatActivity {

    ImageView backArrow;
    Button next;
    EditText edStorename, edAlamat, edEmail, edNoHp;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    FirebaseUser currentUser;
    DatabaseReference reference;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    Context context;
    Store store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulai_pendaftaran);

        context = MulaiPendaftaran.this;

        backArrow = findViewById(R.id.activity_mulai_pendaftaran_back_arrow);
        next = findViewById(R.id.activity_mulai_pendaftaran_next);
        edStorename = findViewById(R.id.activity_mulai_pendaftaran_ed_text_store_name);
        edAlamat = findViewById(R.id.activity_mulai_pendaftaran_alamat);
        edEmail = findViewById(R.id.activity_mulai_pendaftaran_email);
        edNoHp = findViewById(R.id.activity_mulai_pendaftaran_no_hp);

        setUpFirebase();


        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Back Dialog")
                        .setMessage("Are You Sure Want To Leave This page, all data will not be saved")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setIcon(R.drawable.bikini_01)
                        .show();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!edStorename.getText().toString().equals("") && !edAlamat.getText().toString().equals("")
                    && !edEmail.getText().toString().equals("") && !edNoHp.getText().toString().equals("")
                )
                {
                    store = new Store();

                    store.setStoreName(edStorename.getText().toString());
                    store.setAlamat(edAlamat.getText().toString());
                    store.setEmail(edEmail.getText().toString());
                    store.setNoHP(edNoHp.getText().toString());
                    store.setHasCreatedStore(true);

                    if (store != null)
                    {
                        reference.child(getString(R.string.store_app))
                                .child(getString(R.string.user))
                                .child(currentUser.getUid())
                                .child(getString(R.string.store_information))
                                .setValue(store);
                        Intent intent = new Intent(MulaiPendaftaran.this, UploadYourProduct.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(context, "Store Is Null Fuck You", Toast.LENGTH_SHORT).show();
                    }




                }
                else
                {
                    Toast.makeText(context, "Please Input Your Credential", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        new AlertDialog.Builder(context)
                .setTitle("Back Dialog")
                .setMessage("Are You Sure Want To Leave This page, all data will not be saved")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setIcon(R.drawable.bikini_01)
                .show();

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