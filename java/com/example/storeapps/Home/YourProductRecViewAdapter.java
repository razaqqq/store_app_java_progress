package com.example.storeapps.Home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storeapps.Model.Products;
import com.example.storeapps.Model.Store;
import com.example.storeapps.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class YourProductRecViewAdapter extends RecyclerView.Adapter<YourProductRecViewAdapter.ViewHolder> {

    Context context;
    ArrayList<Products> products;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseStorage storage;
    StorageReference reference;


    public YourProductRecViewAdapter(ArrayList<Products> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        setUpFirebase();

        reference = storage.getReference().child(context.getString(R.string._my_store))
                        .child(context.getString(R.string._product_image_user))
                        .child(currentUser.getUid())
                        .child(products.get(position).getTitle() + ".");

        try {
            final File localFile = File.createTempFile(products.get(position).getTitle() + ".", "jpg");

            reference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            holder.productImage.setImageBitmap(bitmap);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }



        holder.description.setText(this.products.get(position).getDescription());
        holder.priceTag.setText(String.valueOf(this.products.get(position).getPrice()));
        holder.saledTag.setText(String.valueOf(this.products.get(position).getSalePrice() ));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
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
    private void setUpFirebase() {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        storage = FirebaseStorage.getInstance();

    }



}
