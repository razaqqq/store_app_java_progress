package com.example.storeapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class VideoActivityNoVideos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_no_videos);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Please Upload New Video First", Toast.LENGTH_SHORT).show();
    }
}