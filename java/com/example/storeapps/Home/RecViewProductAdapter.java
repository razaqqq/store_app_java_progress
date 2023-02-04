package com.example.storeapps.Home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storeapps.Home.UserFragmentChild.BuyAdapter1;
import com.example.storeapps.Model.Products;
import com.example.storeapps.ProducPage;
import com.example.storeapps.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RecViewProductAdapter extends RecyclerView.Adapter<RecViewProductAdapter.ViewHolder> {

    Context context;
    ArrayList<Products> productsList;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseStorage storage;
    StorageReference reference;

    public RecViewProductAdapter(Context context, ArrayList<Products> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.product_layout, parent, false);
        return  new RecViewProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        setUpFirebase();

        if (productsList.get(position).getImage() == null)
        {
            holder.productImage.setImageResource(R.drawable.bikini_01);
        }
        else
        {
            reference = storage.getReference().child(context.getString(R.string._my_store))
                .child(context.getString(R.string._product_image_user))
                .child(currentUser.getUid())
                .child(productsList.get(position).getTitle() + ".");

            try {
                final File localFile = File.createTempFile(productsList.get(position).getTitle() + ".", "jpg");
                reference.getFile(localFile)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(context, "Succes", Toast.LENGTH_SHORT).show();
                                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                holder.productImage.setImageBitmap(bitmap);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Fail : " + e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }

        }


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

    private void setUpFirebase() {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        storage = FirebaseStorage.getInstance();

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
