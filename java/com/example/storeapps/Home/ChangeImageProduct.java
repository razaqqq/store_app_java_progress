package com.example.storeapps.Home;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.lights.LightState;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.storeapps.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.core.view.Change;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class ChangeImageProduct extends AppCompatActivity {

    private int STORAGE_PERMISSION_CODE = 1;
    private int CAMERA_PERMISSION_CODE = 2;
    private int CAMERA_REQUEST = 99;

    Button camera, storage, back, save;
    ActivityResultLauncher<String> mTakePhoto;
    ActivityResultLauncher<String> mCameraPermission;
    ImageView productImage;

    FirebaseStorage firebaseStoreage;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    StorageReference storageReference;

    Bitmap uploadImageBitmap;
    Uri uploadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_image_product);
        camera = findViewById(R.id.activity_change_image_product_camera);
        storage = findViewById(R.id.activity_change_image_product_storage);
        back = findViewById(R.id.activity_change_image_product_back);
        save = findViewById(R.id.activity_change_image_product_save);
        productImage = findViewById(R.id.activity_change_image_product_product_image);

        initFirebase();

        mTakePhoto = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        Toast.makeText(ChangeImageProduct.this, result.toString() + "Storage", Toast.LENGTH_SHORT).show();
                        productImage.setImageURI(result);
                        uploadImage = result;
                    }
                }
        );

        // TODO : I Will Doit Later

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChangeImageProduct.this, "Camera Clicked", Toast.LENGTH_SHORT).show();
                checkPermissionCamera();
            }
        });

        storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChangeImageProduct.this, "Storage Clicked", Toast.LENGTH_SHORT).show();

                checkPermission();

//                if (ContextCompat.checkSelfPermission(
//                        ChangeImageProduct.this,
//                        Manifest.permission.READ_EXTERNAL_STORAGE
//                ) == PackageManager.PERMISSION_GRANTED)
//                {
//                    mTakePhoto.launch("image/*");
//                }
//                else
//                {
//                    Toast.makeText(ChangeImageProduct.this, "Permission Not Granted", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                storageReference.child(getString(R.string._my_store))
//                                .child("_product_user_image")
//                                .child(currentUser.getUid())
//                                .putFile(uploadImage)
//                                .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                                        Toast.makeText(ChangeImageProduct.this, "Succesfull Uploade the Image", Toast.LENGTH_SHORT).show();
//                                    }
//                                })
//                                .addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        Toast.makeText(ChangeImageProduct.this, " Failed  : " + e.toString(), Toast.LENGTH_SHORT).show();
//                                    }
//                                });




                if (uploadImageBitmap == null)
                {
                    Intent intent = new Intent(ChangeImageProduct.this, UploadYourProduct.class);
                    intent.putExtra("upload_image", uploadImage);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else
                {
                    uploadImage = getImageUri(ChangeImageProduct.this, uploadImageBitmap);
                    Intent intent = new Intent(ChangeImageProduct.this, UploadYourProduct.class);
                    intent.putExtra("upload_image", uploadImage);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }


            }
        });


    }



    private void checkPermissionCamera() {
        List<String> permissionArray = new ArrayList<>();
        permissionArray.add(Manifest.permission.CAMERA);

        if (ContextCompat.checkSelfPermission(ChangeImageProduct.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
        {
            //TODO : i Will Todo It Later
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
        else
        {
            requestCameraPermission();
        }

    }

    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(ChangeImageProduct.this, Manifest.permission.CAMERA))
        {
            new AlertDialog.Builder(ChangeImageProduct.this)
                    .setTitle("Permission Needed")
                    .setMessage("This Permission is Needed to Upload Your Image From Your Camera")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(ChangeImageProduct.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    CAMERA_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create()
                    .show();
        }
        else
        {
            ActivityCompat.requestPermissions(ChangeImageProduct.this,
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_CODE);
        }
    }

    private void initFirebase() {
        mAuth = FirebaseAuth.getInstance();
        firebaseStoreage = FirebaseStorage.getInstance();
        currentUser = mAuth.getCurrentUser();
        storageReference = firebaseStoreage.getReference();
    }

    private void checkPermission() {


        List<String> permissionArray = new ArrayList<String>();
        permissionArray.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        if (ContextCompat.checkSelfPermission(ChangeImageProduct.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
        {

            mTakePhoto.launch("image/*");
        }
        else
        {
            requestStoragePermission();
        }

    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(ChangeImageProduct.this,
                Manifest.permission.READ_EXTERNAL_STORAGE))
        {
            new AlertDialog.Builder(ChangeImageProduct.this)
                    .setTitle("Permission Needed")
                    .setMessage("This Permission Is Needed To Upload Your Image")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(ChangeImageProduct.this,
                                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                                    STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create()
                    .show();
        }
        else
        {
            ActivityCompat.requestPermissions(ChangeImageProduct.this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                mTakePhoto.launch("image/*");
            }
            else
            {
                Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {
            uploadImageBitmap = (Bitmap) data.getExtras().get("data");
            productImage.setImageBitmap(uploadImageBitmap);
        }
    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

}