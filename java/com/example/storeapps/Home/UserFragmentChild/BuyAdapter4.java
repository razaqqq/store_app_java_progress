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
import com.example.storeapps.Home.AccountSecurity.BlockedUserActivity;
import com.example.storeapps.Home.AccountSecurity.ChatSettingActivity;
import com.example.storeapps.Home.AccountSecurity.LanguangeActivity;
import com.example.storeapps.Home.AccountSecurity.NotificationSettingActivity;
import com.example.storeapps.Home.AccountSecurity.PrivacySettingActivity;
import com.example.storeapps.R;

import java.util.ArrayList;

public class BuyAdapter4 extends RecyclerView.Adapter<BuyAdapter4.ViewHolder> {

    Context context;
    ArrayList<String> textList;

    public BuyAdapter4(Context context, ArrayList<String> textList) {
        this.context = context;
        this.textList = textList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.bu_fragment_layout_1, parent, false);
        return new BuyAdapter4.ViewHolder(view);
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
                        Intent intent = new Intent(context, ChatSettingActivity.class);
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
                        Intent intent = new Intent(context, NotificationSettingActivity.class);
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
                        Intent intent = new Intent(context, PrivacySettingActivity.class);
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
                        Intent intent = new Intent(context, BlockedUserActivity.class);
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
                        Intent intent = new Intent(context, LanguangeActivity.class);
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
