package com.example.storeapps.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storeapps.R;

import java.util.ArrayList;

public class TerbaruLiveRecViewAdapter extends RecyclerView.Adapter<TerbaruLiveRecViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Integer> imageList;

    public TerbaruLiveRecViewAdapter(Context context, ArrayList<Integer> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.video_live_img_layout, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.videoImage.setImageResource(imageList.get(position));
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView videoImage;
        TextView comment, like, description, username;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            videoImage = itemView.findViewById(R.id.video_live_image_layout_video_image);
            comment = itemView.findViewById(R.id.video_live_image_layout_comment);
            like = itemView.findViewById(R.id.video_live_image_layout_like);
            description = itemView.findViewById(R.id.video_live_image_layout_description);
            username = itemView.findViewById(R.id.video_live_image_layout_comment_username);
        }
    }
}
