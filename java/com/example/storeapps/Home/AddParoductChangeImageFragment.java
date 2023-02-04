package com.example.storeapps.Home;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.storeapps.Model.Products;
import com.example.storeapps.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class AddParoductChangeImageFragment extends Fragment {

    ImageView changeImage, imagePreview;
    private int STORAGE_PERMISSION_CODE = 1;
    private int CAMERA_PERMISSION_CODE = 2;

    FrameLayout frameLayout;
    LinearLayout saveLin;

    Button camera, storage;
    ActivityResultLauncher<String> takeImageFromStorage;
    ActivityResultLauncher<String> takePicture;

    private int CAMERA_REQUEST = 99;
    Bitmap uploadImageBitmap;

    FirebaseStorage firebaseStorage;
    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseUser currentUser;
    StorageReference storageReference;

    Uri imageUri;
    Bundle arg;
    Products products;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_paroduct_change_image, container, false);
        changeImage = view.findViewById(R.id.fragment_add_product_change_image_back);
        imagePreview = view.findViewById(R.id.fragment_add_product_change_image_preview);
        camera = view.findViewById(R.id.fragment_add_product_chane_image_camera);
        storage = view.findViewById(R.id.fragment_add_product_change_image_storage);
        saveLin = view.findViewById(R.id.fragment_add_product_change_image_save);

        frameLayout = view.findViewById(R.id.fragment_add_product_change_image_frame_layout);

        setUpfirebase();
        arg = getArguments();

        try {
            products = (Products) arg.getSerializable("product_temporary");
            Toast.makeText(getActivity(), products.getTitle(), Toast.LENGTH_SHORT).show();
        }catch (NullPointerException e)
        {
            Toast.makeText(getActivity(), "Null " + e.toString(), Toast.LENGTH_SHORT).show();
        }




        takeImageFromStorage = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        imageUri = result;
                        imagePreview.setImageURI(result);
                    }
                }
        );




        changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.from_bottom, R.anim.exit);
                transaction.remove(AddParoductChangeImageFragment.this).commit();
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCameraPermission();
            }
        });

        storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission();
            }
        });

        saveLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Anjinggggggg", Toast.LENGTH_SHORT).show();





//                if (products != null)
//                {
//                    storageReference.child(getString(R.string._my_store))
//                            .child(getString(R.string._product_image_user))
//                            .child(currentUser.getUid())
//                            .child(products.getTitle().toLowerCase().toString() + ".")
//                            .putFile(imageUri)
//                            .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                                @Override
//                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//
//                                }
//                            })
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Toast.makeText(getActivity(), "Error : " + e.toString(), Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                }
//                else
//                {
//                    Toast.makeText(getActivity(), "Product Is Null", Toast.LENGTH_SHORT).show();
//                }

                String imageUritoString;
                imageUritoString = imageUri.toString();

                Bundle bundle = new Bundle();
                bundle.putString("image_uri", imageUritoString);


                AddProducts addProducts = new AddProducts();
                addProducts.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.from_bottom, R.anim.exit);
                fragmentTransaction.replace(R.id.fragment_add_product_change_image_frame_layout, addProducts).commit();



            }
        });

        return view;
    }

    private void setUpfirebase() {
        firebaseStorage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        storageReference = firebaseStorage.getReference();
    }

    private void checkCameraPermission() {
        List<String> permission = new ArrayList<>();
        permission.add(Manifest.permission.CAMERA);

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
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
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA))
        {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Permission Needed")
                    .setMessage("This Permission is Needed to Upload Your Image From Your Camera")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(getActivity(),
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
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_CODE);
        }
    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE))
        {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Permission Needed")
                    .setMessage("This Permission Is Needed To Upload Your Image")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(getActivity(),
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
            ActivityCompat.requestPermissions(getActivity(),
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }

    }

    private void checkPermission() {


        List<String> permissionArray = new ArrayList<String>();
        permissionArray.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
        {
            takeImageFromStorage.launch("image/*");
        }
        else
        {
            requestStoragePermission();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                takeImageFromStorage.launch("image/*");
            }
            else
            {
                Toast.makeText(getActivity(), "Permission Not Granted", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        uploadImageBitmap = (Bitmap) data.getExtras().get("data");
        imageUri = getImageUri(getActivity(), uploadImageBitmap);
        imagePreview.setImageURI(getImageUri(getActivity(), uploadImageBitmap));
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

}