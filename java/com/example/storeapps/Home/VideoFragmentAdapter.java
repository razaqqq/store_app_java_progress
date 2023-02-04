package com.example.storeapps.Home;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storeapps.Model.Videos;
import com.example.storeapps.R;

import java.util.List;

public class VideoFragmentAdapter extends RecyclerView.Adapter<VideoFragmentAdapter.ViewHolder> {

    private Context context;
    private List<VideoItem> mVideoItems;
    private FrameLayout frameLayout;
    private Integer layout;

    public VideoFragmentAdapter(Context context, List<VideoItem> mVideoItems, FrameLayout frameLayout, Integer layout) {
        this.context = context;
        this.mVideoItems = mVideoItems;
        this.frameLayout = frameLayout;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_fragment_container, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {






            holder.setVideoData(this.mVideoItems.get(position));

            holder.addVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(context, "kasjdkasjkldjsakldljksa", Toast.LENGTH_SHORT).show();
                    UploadVideoFragment uploadVideoFragment = new UploadVideoFragment();

                    FragmentTransaction transaction = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.from_bottom, R.anim.exit);
                    transaction.replace(R.id.video_fragment_container_frame_layout, uploadVideoFragment).commit();
                }
            });




    }

    @Override
    public int getItemCount() {
        return mVideoItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView username;
        TextView description;
        TextView subDescription;
        VideoView videoView;
        ImageView profileImageBottom, addVideo;
        TextView shareCount;
        TextView chatCount;
        TextView likeCount;
        ImageView profileImage;
        FrameLayout frameLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.video_vragment_username);
            description = itemView.findViewById(R.id.video_fragment_description);
            subDescription = itemView.findViewById(R.id.video_fragment_sub_description);
            videoView = itemView.findViewById(R.id.video_fragmnet_container_video_1);
            profileImageBottom = itemView.findViewById(R.id.video_faragment_profile_image_bottom);
            shareCount = itemView.findViewById(R.id.video_fragment_share_text);
            chatCount = itemView.findViewById(R.id.video_fragment_chat_text);
            likeCount = itemView.findViewById(R.id.video_fragment_favorite_text);
            profileImage = itemView.findViewById(R.id.video_fragment_profile_image);
            addVideo = itemView.findViewById(R.id.video_fragment_add_video_01);
            frameLayout = itemView.findViewById(R.id.video_fragment_container_frame_layout);

        }

        void setVideoData(VideoItem videoItem)
        {
            username.setText(videoItem.username);
            description.setText(videoItem.videoDesc);
            subDescription.setText(videoItem.videoSubDesc);
            profileImageBottom.setImageResource(videoItem.profileImageBottom);
            shareCount.setText(videoItem.shareCount);
            chatCount.setText(videoItem.chatCount);
            likeCount.setText(videoItem.profileImage);
            profileImage.setImageResource(videoItem.profileImage);

            videoView.setVideoURI(Uri.parse(videoItem.getVideoUrl()));



            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {

                    mediaPlayer.start();
                    float videoRatio = mediaPlayer.getVideoWidth() / (float) mediaPlayer.getVideoHeight();
                    float screenRatio = videoView.getWidth() / (float) videoView.getHeight();
                    float scale = videoRatio / screenRatio;
                    if (scale >= 1f)
                    {
                        Toast.makeText(context, "sakdhkjashkj", Toast.LENGTH_SHORT).show();
                        videoView.setScaleX(scale);
                    }
                    else
                    {
                        videoView.setScaleY(1f / scale);
                    }



                }
            });
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
        }
    }



}
