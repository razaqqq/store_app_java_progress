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

import com.example.storeapps.Home.AccountSecurity.AccountSecurityActivity;
import com.example.storeapps.Home.AccountSecurity.CommunitySettingsActivity;
import com.example.storeapps.Home.AccountSecurity.HelpCenterActivity;
import com.example.storeapps.Home.AccountSecurity.InformationsActivity;
import com.example.storeapps.Home.AccountSecurity.MyStoreValueActivity;
import com.example.storeapps.Home.AccountSecurity.MyStorelawActivity;
import com.example.storeapps.Home.AccountSecurity.SubmitYourAccountToDeleteActivity;
import com.example.storeapps.Home.AccountSecurity.TipsAndTrickActivity;
import com.example.storeapps.R;

import java.util.ArrayList;

public class BuyAdapter5 extends RecyclerView.Adapter<BuyAdapter5.ViewHolder> {

    Context context;
    ArrayList<String> textList;

    public BuyAdapter5(Context context, ArrayList<String> textList) {
        this.context = context;
        this.textList = textList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.bu_fragment_layout_1, parent, false);
        return new BuyAdapter5.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (position)
        {
            case 0:
                holder.icon.setImageResource(R.drawable.ic_baseline_account_balance_wallet_24);
                holder.text.setText(this.textList.get(position));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, HelpCenterActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;
            case 1:
                holder.icon.setImageResource(R.drawable.ic_baseline_account_balance_wallet_24);
                holder.text.setText(this.textList.get(position));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, TipsAndTrickActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;
            case 2:
                holder.icon.setImageResource(R.drawable.ic_baseline_account_balance_wallet_24);
                holder.text.setText(this.textList.get(position));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, CommunitySettingsActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;
            case 3:
                holder.icon.setImageResource(R.drawable.ic_baseline_account_balance_wallet_24);
                holder.text.setText(this.textList.get(position));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, MyStorelawActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;
            case 4:
                holder.icon.setImageResource(R.drawable.ic_baseline_account_balance_wallet_24);
                holder.text.setText(this.textList.get(position));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, MyStoreValueActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;
            case 5:
                holder.icon.setImageResource(R.drawable.ic_baseline_account_balance_wallet_24);
                holder.text.setText(this.textList.get(position));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, InformationsActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;
            case 6:
                holder.icon.setImageResource(R.drawable.ic_baseline_account_balance_wallet_24);
                holder.text.setText(this.textList.get(position));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, SubmitYourAccountToDeleteActivity.class);
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


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView icon;
        TextView text;
        ImageView rightArrow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.image_left);
            text = itemView.findViewById(R.id.textss);
            rightArrow = itemView.findViewById(R.id.rightArrow);
        }
    }
}
