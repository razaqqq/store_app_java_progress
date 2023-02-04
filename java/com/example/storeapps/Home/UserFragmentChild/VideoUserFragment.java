package com.example.storeapps.Home.UserFragmentChild;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.storeapps.Home.PostingNewVideo;
import com.example.storeapps.R;

public class VideoUserFragment extends Fragment {

    Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_user_fragment, container, false);

        button = view.findViewById(R.id.upload_video);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PostingNewVideo.class);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }
}
