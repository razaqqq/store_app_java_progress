package com.example.storeapps.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.storeapps.R;

import java.util.ArrayList;

public class SlideAdapter2 extends RecyclerView.Adapter<SlideAdapter2.ViewHolder> {
    private Context context;
    private ArrayList<SliderItem> sliderList;
    private ViewPager2 viewPager2;

    public SlideAdapter2(Context context, ArrayList<SliderItem> sliderList, ViewPager2 viewPager2) {
        this.context = context;
        this.sliderList = sliderList;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.slide_item_container, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageSlider.setImageResource(this.sliderList.get(position).getImg());

    }

    @Override
    public int getItemCount() {
        return sliderList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageSlider;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageSlider = itemView.findViewById(R.id.image_slide);
        }
    }

}
