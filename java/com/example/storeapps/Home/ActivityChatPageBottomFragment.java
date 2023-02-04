package com.example.storeapps.Home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.storeapps.Home.UserFragmentChild.ProductImageFragment;
import com.example.storeapps.Model.Products;
import com.example.storeapps.R;


public class ActivityChatPageBottomFragment extends Fragment {


    ImageView closeFragment, imagePreviews;
    RelativeLayout imagePreview;
    FrameLayout frameLayout;

    public ActivityChatPageBottomFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_chat_page_bottom, container, false);

        closeFragment = view.findViewById(R.id.fragment_activity_chat_page_bottom_close);
        imagePreview = view.findViewById(R.id.fragment_activity_chat_page_bottom_image_preview);
        frameLayout = view.findViewById(R.id.fragment_activity_chat_page_bottom_frame);
        imagePreviews = view.findViewById(R.id.image_preview);

        Bundle bundle = this.getArguments();
        Products products = (Products) bundle.getSerializable("products");

        if (bundle != null)
        {
            imagePreviews.setImageURI(Uri.parse(products.getImage()));
        }
        else
        {
            Toast.makeText(getActivity(), "Bundle Is Null", Toast.LENGTH_SHORT).show();
        }


        FragmentContainerView fragmentContainerView = new FragmentContainerView(getActivity());

        imagePreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), ProductImagePreview.class);
                intent.putExtra("products", products);
                getActivity().startActivity(intent);

            }
        });


        closeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.from_bottom, R.anim.exit);
                transaction.remove(ActivityChatPageBottomFragment.this).commit();
            }
        });

        return view;
    }
}