package com.example.storeapps.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.storeapps.Model.Products;
import com.example.storeapps.R;

import java.util.ArrayList;


public class SemuaFragment extends Fragment {

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_semua2, container, false);

        recyclerView = view.findViewById(R.id.fragment_semua_2_rec_view);

        setUpRecView();

        return view;

    }

    private void setUpRecView() {
        ArrayList<Products> productsArrayList = new ArrayList<>();

//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", "SHIT", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", "SHIT", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", "SHIT", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", "SHIT", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", "SHIT", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", "SHIT", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", "SHIT", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", "SHIT", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", "SHIT", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", "SHIT", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", "SHIT", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        )); productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", "SHIT", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", "SHIT", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));



        RecViewProductAdapter productAdapter = new RecViewProductAdapter(getActivity(), productsArrayList);

        recyclerView.setAdapter(productAdapter);
        GridLayoutManager layoutManager1 = new GridLayoutManager(getActivity(), 2);

        recyclerView.setLayoutManager(layoutManager1);
    }

}