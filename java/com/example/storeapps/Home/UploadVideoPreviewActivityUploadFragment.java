package com.example.storeapps.Home;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.storeapps.Model.Videos;
import com.example.storeapps.R;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.Continuation;
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

import java.net.URI;


public class UploadVideoPreviewActivityUploadFragment extends DialogFragment {


    ImageView close;
    Button save;

    EditText edVideoName, edVideoDescription, edVideoSubdescription;
    MediaController mediaController;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    FirebaseUser currentUser;
    Videos videos;
    VideoItem videoItem;
    UploadTask uploadTask;

    Bundle bundle;
    Uri videoUris;

    ProgressBar progressBar;
    
    Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_upload_video_preview_activity_upload, container, false);
        context = getActivity();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        bundle = getArguments();
        videoUris = Uri.parse(bundle.getString("video_uris"));


        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference();




        close = view.findViewById(R.id.fragment_upload_video_preview_activity_upload_close);
        save = view.findViewById(R.id.fragment_upload_video_preview_activity_upload_save);
        edVideoName = view.findViewById(R.id.fragment_upload_video_preview_activity_upload_ed_video_name);
        edVideoDescription = view.findViewById(R.id.fragment_upload_video_preview_activity_upload_ed_video_descriptions);
        edVideoSubdescription = view.findViewById(R.id.fragment_upload_video_preview_activity_upload_ed_video_sub_descriptions);
        progressBar = view.findViewById(R.id.fragment_upload_video_preview_activity_upload);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.from_bottom, R.anim.exit);
                transaction.remove(UploadVideoPreviewActivityUploadFragment.this).commit();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!edVideoName.getText().toString().equals("")
                        && !edVideoDescription.getText().toString().equals("")
                        && !edVideoSubdescription.getText().toString().equals(""))
                {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Are You Sure Want to Upload The Video "
                                    + edVideoName.getText().toString() + ". ")
                            .setMessage("Upload the Video to Database")
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {


                                    UploadVideo();


                                }
                            })
                            .setNegativeButton("BACK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Please Input Your Credential", Toast.LENGTH_SHORT).show();
                }


            }
        });


        return view;
    }

    private String getExt(Uri uri)
    {
        ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void UploadVideo()
    {
        String videoName = edVideoName.getText().toString();
        String videoDescription = edVideoDescription.getText().toString();
        String videoSubDescription = edVideoSubdescription.getText().toString();
        if (videoUris != null)
        {
            progressBar.setVisibility(View.VISIBLE);
            final StorageReference reference = storageReference.child(getString(R.string._my_store))
                    .child(getString(R.string._video_user))
                    .child(currentUser.getUid())
                    .child(videoName + "." + getExt(videoUris));
            uploadTask = reference.putFile(videoUris);

            Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful())
                    {
                        throw task.getException();
                    }
                    return reference.getDownloadUrl();
                }
            })
                    .addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {


                                Uri downloadUrl = task.getResult();

                                videos = new Videos(
                                        videoName,
                                        videoDescription,
                                        videoSubDescription,
                                        "",
                                        "",
                                        "",
                                        downloadUrl.toString()
                                        );

                                videoItem = new VideoItem(
                                        "Username",
                                        videoName,
                                        videoDescription,
                                        videoSubDescription,
                                        downloadUrl.toString(),
                                        "",
                                        "",
                                        "",
                                        R.drawable.bikini_01,
                                        R.drawable.bikini_01
                                );

                                databaseReference.child(context.getString(R.string.store_app))
                                                .child(context.getString(R.string.video_global))
                                                        .push().setValue(videoItem);

                                databaseReference.child(context.getString(R.string.store_app))
                                        .child(context.getString(R.string.user))
                                        .child(currentUser.getUid())
                                        .child(context.getString(R.string.video_user))
                                        .push().setValue(videoItem);

                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(context, "Video Saved " + videoName + ". ", Toast.LENGTH_SHORT).show();
                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                transaction.setCustomAnimations(R.anim.from_bottom, R.anim.exit);
                                transaction.remove(UploadVideoPreviewActivityUploadFragment.this).commit();
                            }
                            else
                            {
                                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    ;

        }
        else
        {
            Toast.makeText(getActivity(), "Video Uris is Null", Toast.LENGTH_SHORT).show();
        }
    }

}