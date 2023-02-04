package com.example.storeapps.Home;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.storeapps.R;


public class UploadVideoFragment extends Fragment {

    private static int VIDEO_REQUEST = 101;
    private Uri videoUri = null;

    ImageView back;
    FrameLayout frameLayout;
    Button fromStorageButton, fromCameraButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upload_video_fragment, container, false);

        back = view.findViewById(R.id.fragment_upload_video_back);
        fromStorageButton = view.findViewById(R.id.fragment_upload_video_fragment_from_storage);
        fromCameraButton = view.findViewById(R.id.fragment_upload_video_fragment_from_camera);
        frameLayout = view.findViewById(R.id.fragment_upload_video_fragment_frame_layout);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.from_bottom, R.anim.exit);
                transaction.remove(UploadVideoFragment.this).commit();
            }
        });

        fromStorageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "FromStorage", Toast.LENGTH_SHORT).show();
            }
        });

        fromCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (videoIntent.resolveActivity(getActivity().getPackageManager()) != null)
                {
                    startActivityForResult(videoIntent, VIDEO_REQUEST);



                }




            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == VIDEO_REQUEST && resultCode == RESULT_OK)
        {
            videoUri = data.getData();

            // Intent
            Intent intent = new Intent(getActivity(), UploadVidePreviewActivity.class);
            intent.putExtra("video_uri", videoUri.toString());
            startActivity(intent);

        }
    }
}