package com.example.storeapps.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storeapps.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class AkanDimulaRecViewAdapter extends RecyclerView.Adapter<AkanDimulaRecViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Integer> imageList;

    public AkanDimulaRecViewAdapter(Context context, ArrayList<Integer> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.akan_dimulai_rec_view_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(this.imageList.get(position));
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.akan_dimulai_image_left);
        }
    }
}
