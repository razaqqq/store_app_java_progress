package com.example.storeapps.Home.UserFragmentChild;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.storeapps.R;


public class ProductImageFragment extends Fragment {



    ImageView back;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_image, container, false);

        back = view.findViewById(R.id.fragment_product_image_close);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

                fragmentTransaction.remove(ProductImageFragment.this).commit();

            }
        });

        return  view;

    }
}