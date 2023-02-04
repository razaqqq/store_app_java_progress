package com.example.storeapps.Home.UserFragmentChild;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storeapps.Home.ChatWithMyStore;
import com.example.storeapps.Home.HelpingCenterActivity;
import com.example.storeapps.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class BuyAdapter2 extends RecyclerView.Adapter<BuyAdapter2.viewHolder> {

    Context context;
    ArrayList<String> textList;
    RelativeLayout relativeLayout;
    RelativeLayout parent;

    public BuyAdapter2(Context context, ArrayList<String> textList, RelativeLayout relativeLayout) {
        this.context = context;
        this.textList = textList;
        this.relativeLayout = relativeLayout;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.bu_fragment_layout_1, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        switch (position)
        {
            case 0:
                holder.icon.setImageResource(R.drawable.ic_baseline_perm_identity_24);
                holder.text.setText(textList.get(0));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, PengaturanAccount.class);
                        context.startActivity(intent);
                    }
                });
                break;
            case 1:
                holder.icon.setImageResource(R.drawable.ic_baseline_help_center_24);
                holder.text.setText(textList.get(1));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, HelpingCenterActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;
            case 2:
                holder.icon.setImageResource(R.drawable.ic_baseline_chat_24);
                holder.text.setText(textList.get(2));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, ChatWithMyStore.class);
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

    public class viewHolder extends RecyclerView.ViewHolder
    {
        ImageView icon;
        TextView text;
        ImageView rightArrow;
        public viewHolder(@NonNull View itemView) {

            super(itemView);

            icon = itemView.findViewById(R.id.image_left);
            text = itemView.findViewById(R.id.textss);
            rightArrow = itemView.findViewById(R.id.rightArrow);

        }
    }
}
