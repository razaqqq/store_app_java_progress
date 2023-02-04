package com.example.storeapps.Home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storeapps.Home.UserFragmentChild.BuyAdapter5;
import com.example.storeapps.R;

import java.util.ArrayList;

public class HomeAdapterVideo extends RecyclerView.Adapter<HomeAdapterVideo.ViewHolder> {

    Context context;
    ArrayList<Integer> imgList;

    public HomeAdapterVideo(Context context, ArrayList<Integer> img) {
        this.context = context;
        this.imgList = img;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.video_home_fragment, parent, false);
        return new HomeAdapterVideo.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.videoImage.setImageResource(imgList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HomeVideoActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView videoImage;

       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           videoImage = itemView.findViewById(R.id.video_home_fragment);
       }
   }
}
