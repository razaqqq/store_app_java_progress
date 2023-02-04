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
import com.example.storeapps.Home.AccountSecurity.BankAccountActivity;
import com.example.storeapps.Home.AccountSecurity.MyAddress;
import com.example.storeapps.R;

import java.util.ArrayList;

public class BuyAdapter3 extends RecyclerView.Adapter<BuyAdapter3.ViewHolder> {

    Context context;
    ArrayList<String> textList;

    public BuyAdapter3(Context context, ArrayList<String> textList) {
        this.context = context;
        this.textList = textList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.bu_fragment_layout_1, parent, false);
        return new BuyAdapter3.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == 0)
        {
            holder.icon.setImageResource(R.drawable.ic_baseline_account_balance_wallet_24);
            holder.text.setText(this.textList.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, AccountSecurityActivity.class);
                    context.startActivity(intent);
                }
            });
        }
        else if (position == 1)
        {
            holder.icon.setImageResource(R.drawable.ic_baseline_account_balance_wallet_24);
            holder.text.setText(this.textList.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MyAddress.class);
                    context.startActivity(intent);
                }
            });
        }
        else if (position == 2)
        {
            holder.icon.setImageResource(R.drawable.ic_baseline_account_balance_wallet_24);
            holder.text.setText(this.textList.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, BankAccountActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.textList.size();
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
