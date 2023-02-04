package com.example.storeapps.Home.UserFragmentChild;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storeapps.Home.LastSeenActivity;
import com.example.storeapps.Home.LiveFragment;
import com.example.storeapps.Home.MyCouponActivity;
import com.example.storeapps.Home.MyFaforit;
import com.example.storeapps.Home.MyStoreLive;
import com.example.storeapps.Home.MyStoreMember;
import com.example.storeapps.Home.MyValueActivity;
import com.example.storeapps.R;

import java.util.ArrayList;

public class BuyAdapter1 extends RecyclerView.Adapter<BuyAdapter1.Viewholder> {

    private ArrayList<String> textList;
    private Context context;

    public BuyAdapter1(ArrayList<String> textList, Context context) {
        this.textList = textList;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.bu_fragment_layout_1, parent, false);
        return  new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        switch (position)
        {
            case 0:
                holder.icon.setImageResource(R.drawable.ic_baseline_card_membership_24);
                holder.text.setText(textList.get(0));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, MyStoreMember.class);
                        context.startActivity(intent);
                    }
                });
                break;
            case 1:
                holder.icon.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                holder.text.setText(textList.get(1));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, MyFaforit.class);
                        context.startActivity(intent);
                    }
                });
                break;
            case 2:
                holder.icon.setImageResource(R.drawable.ic_baseline_access_time_24);
                holder.text.setText(textList.get(2));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, LastSeenActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;
            case 3:
                holder.icon.setImageResource(R.drawable.ic_baseline_video_library_24);
                holder.text.setText(textList.get(3));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, LiveFragment.class);
                        context.startActivity(intent);
                    }
                });
                break;
            case 4:
                holder.icon.setImageResource(R.drawable.ic_baseline_stars_24);
                holder.text.setText(textList.get(4));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, MyValueActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;
            case 5:
                holder.icon.setImageResource(R.drawable.ic_baseline_how_to_vote_24);
                holder.text.setText(textList.get(5));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, MyCouponActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return textList.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder
    {

        ImageView icon;
        TextView text;
        ImageView rightArrow;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.image_left);
            text = itemView.findViewById(R.id.textss);
            rightArrow = itemView.findViewById(R.id.rightArrow);
        }
    }
}
