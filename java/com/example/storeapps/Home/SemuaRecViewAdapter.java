package com.example.storeapps.Home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storeapps.Model.Products;
import com.example.storeapps.ProducPage;
import com.example.storeapps.R;

import java.util.ArrayList;

public class SemuaRecViewAdapter extends RecyclerView.Adapter<SemuaRecViewAdapter.ViewHolder> {

    Context context;
    ArrayList<Products> productsList;

    public SemuaRecViewAdapter(Context context, ArrayList<Products> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.product_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.productImage.setImageResource(productsList.get(position).getImage());
        holder.description.setText(productsList.get(position).getDescription());
        holder.priceTag.setText(String.valueOf(productsList.get(position).getPrice()));
        holder.saledTag.setText(String.valueOf(productsList.get(position).getSaledNumber()) + " selled");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ProducPage.class);
                intent.putExtra("products", productsList.get(position));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView description;
        private TextView priceTag;
        private TextView saledTag;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product);
            description = itemView.findViewById(R.id.keterangan_product);
            priceTag = itemView.findViewById(R.id.price_tag);
            saledTag = itemView.findViewById(R.id.selled_tag);
        }
    }
}
