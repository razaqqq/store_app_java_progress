package com.example.storeapps.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.storeapps.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class UploadVidePreviewActivity extends AppCompatActivity {


    ImageView back;
    Button play, upload;
    Uri videoUris;
    VideoView video;
    FrameLayout frameLayout;

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_vide_preview);
        back = findViewById(R.id.activity_upload_video_preview_back);
        play = findViewById(R.id.activity_upload_video_play);
        upload = findViewById(R.id.activity_upload_video_preview_upload);
        video = findViewById(R.id.activity_upload_video_preview_video);
        frameLayout = findViewById(R.id.activity_upload_video_preview_framelayout);



        Intent intent  = getIntent();
        String videoUri = intent.getStringExtra("video_uri");

        setUpFirebase();

        videoUris = Uri.parse(videoUri);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UploadVidePreviewActivity.this, "Play Button Clicked", Toast.LENGTH_SHORT).show();
                video.setVideoURI(videoUris);
                video.start();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UploadVideoPreviewActivityUploadFragment uploadVideoPreviewActivityUploadFragment = new UploadVideoPreviewActivityUploadFragment();
                Bundle bundle = new Bundle();
                bundle.putString("video_uris", videoUris.toString());
                uploadVideoPreviewActivityUploadFragment.setArguments(bundle);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.from_bottom, R.anim.exit);
                transaction.replace(R.id.activity_upload_video_preview_framelayout, uploadVideoPreviewActivityUploadFragment)
                        .commit();

//                databaseReference.child(getString(R.string._my_store))
//                        .child(getString(R.string.user))
//                        .child(currentUser.getUid())
//                        .child(getString(R.string.video_user)).push().setValue();
//                databaseReference.child(getString(R.string._my_store))
//                        .child(getString(R.string.video_global)).push().setValue();
//
//                storageReference.child(getString(R.string._my_store))
//                        .child(getString(R.string.video_user))
//                        .child(currentUser.getUid())
//                        .child();// Put The Name Of Video
//
//                storageReference.child(getString(R.string._my_store))
//                        .child(getString(R.string.video_global))
//                        .child() // Put Name Of The Video
//                        .putFile()
            }
        });





    }

    private void setUpFirebase() {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
    }
}