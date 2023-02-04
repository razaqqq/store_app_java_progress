package com.example.storeapps.Home;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.storeapps.Model.Videos;
import com.example.storeapps.R;
import com.example.storeapps.VideoActivityNoVideos;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment {

    ViewPager2 videoViewpager2;
    FrameLayout frameLayout;

    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    FirebaseUser currentUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_fragment, container, false);


        videoViewpager2 = view.findViewById(R.id.viewPager2VideoFragment);
        frameLayout = view.findViewById(R.id.video_fragment_frame_layout);

        setUpFirebase();

        List<VideoItem> videoList = new ArrayList<>();

        databaseReference.child(getString(R.string.store_app))
                .child(getString(R.string.video_global))
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists())
                        {
                            for (DataSnapshot snapshot1 : snapshot.getChildren())
                            {
                                videoList.add(snapshot1.getValue(VideoItem.class));
                            }
                            videoViewpager2.setAdapter(new VideoFragmentAdapter(getActivity(),videoList, frameLayout, R.id.video_fragment_frame_layout));
                        }
                        else
                        {
                            Toast.makeText(getActivity(), "ASUUUIUIUUU", Toast.LENGTH_SHORT).show();
                            UploadVideoFragment uploadVideoFragment = new UploadVideoFragment();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.setCustomAnimations(R.anim.from_bottom, R.anim.exit);
                            transaction.replace(R.id.main_activity_frame_layout, uploadVideoFragment).commit();



                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

//        videoItemList.add(new VideoItem("Razaq", "Video Desc",
//                "Video Sub Desc", "https://media.geeksforgeeks.org/wp-content/uploads/20201217192146/Screenrecorder-2020-12-17-19-17-36-828.mp4?_=1", "99 RB",
//                "999 RB", "999 RB", R.drawable.bikini_08,
//                R.drawable.bikini_10));
//        videoItemList.add(new VideoItem("Razaq", "Video Desc",
//                "Video Sub Desc", "https://firebasestorage.googleapis.com/v0/b/store-firebase-7f203.appspot.com/o/_my_store%2Fvideo_1.mp4?alt=media&token=8d12fe54-5eea-46eb-8218-86818c121b6b", "99 RB",
//                "999 RB", "999 RB", R.drawable.bikini_08,
//                R.drawable.bikini_10));
//        videoItemList.add(new VideoItem("Razaq", "Video Desc",
//                "Video Sub Desc", "https://firebasestorage.googleapis.com/v0/b/store-firebase-7f203.appspot.com/o/_my_store%2Fvideo_1.mp4?alt=media&token=8d12fe54-5eea-46eb-8218-86818c121b6b", "99 RB",
//                "999 RB", "999 RB", R.drawable.bikini_08,
//                R.drawable.bikini_10));
//        videoItemList.add(new VideoItem("Razaq", "Video Desc",
//                "Video Sub Desc", "https://firebasestorage.googleapis.com/v0/b/store-firebase-7f203.appspot.com/o/_my_store%2Fvideo_4.mp4?alt=media&token=005d963e-7bcf-4353-948c-c215d83b4d5a", "99 RB",
//                "999 RB", "999 RB", R.drawable.bikini_08,
//                R.drawable.bikini_10));
//        videoItemList.add(new VideoItem("Razaq", "Video Desc",
//                "Video Sub Desc", "https://firebasestorage.googleapis.com/v0/b/store-firebase-7f203.appspot.com/o/_my_store%2Fvideo_4.mp4?alt=media&token=005d963e-7bcf-4353-948c-c215d83b4d5a", "99 RB",
//                "999 RB", "999 RB", R.drawable.bikini_08,
//                R.drawable.bikini_10));
//        videoItemList.add(new VideoItem("Razaq", "Video Desc",
//                "Video Sub Desc", "https://firebasestorage.googleapis.com/v0/b/store-firebase-7f203.appspot.com/o/_my_store%2Fvideo_4.mp4?alt=media&token=005d963e-7bcf-4353-948c-c215d83b4d5a", "99 RB",
//                "999 RB", "999 RB", R.drawable.bikini_08,
//                R.drawable.bikini_10));








        ArrayList<Integer> sliderListVideo = new ArrayList<>();


        sliderListVideo.add(R.drawable.bikini_08);
        sliderListVideo.add(R.drawable.bikini_09);
        sliderListVideo.add(R.drawable.bikini_10);
        sliderListVideo.add(R.drawable.bikini_11);
        sliderListVideo.add(R.drawable.bikini_12);
        sliderListVideo.add(R.drawable.bikini_13);
        sliderListVideo.add(R.drawable.bikini_14);
        sliderListVideo.add(R.drawable.bikini_15);
        sliderListVideo.add(R.drawable.bikini_16);
        sliderListVideo.add(R.drawable.bikini_17);









//        VideoFragmentAdapter videoFragmentAdapter = new VideoFragmentAdapter(getActivity(), sliderListVideo, videoViewPager2);

//        videoViewPager2.setAdapter(videoFragmentAdapter);
//        videoViewPager2.setClipToPadding(false);
////        videoViewPager2.setOffscreenPageLimit(5);
//        videoViewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
//
//        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
//        compositePageTransformer.addTransformer(new MarginPageTransformer(30));
//        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
//            @Override
//            public void transformPage(@NonNull View page, float position) {
//                float r = 1 - Math.abs(position);
//                page.setScaleX(0.85f + r*0.15f);
//            }
//        });
//
//
//        videoViewPager2.setPageTransformer(compositePageTransformer);

        return view;
    }

    private void setUpFirebase() {
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        currentUser = mAuth.getCurrentUser();
    }
}
