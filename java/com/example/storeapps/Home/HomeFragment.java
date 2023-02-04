package com.example.storeapps.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.storeapps.Model.Products;
import com.example.storeapps.MyStoreFoodActivity;
import com.example.storeapps.R;

import com.example.storeapps.login.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ViewPager2 viewPager2;
    private RecyclerView rec_video;
    private RecyclerView rec_product;
    private ImageView bascet, chat;

    private LinearLayout lihatSemua, myStorePayLater, myStoreLocal,
            myStoreFood, myStoreGames, myStoreBarokah, myStorePayNearby, myStoreMall, pulsaAndTagihan
            , gratisOngkir;


    FirebaseAuth mAuth;
    FirebaseDatabase database;
    FirebaseUser currentUser;
    DatabaseReference reference;

    RelativeLayout qrCodeScanner, myStorePay, myCoin, transfer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.home_fragment, container, false);


        viewPager2 = view.findViewById(R.id.images_slader_view_pager);
        rec_video = view.findViewById(R.id.rec_video);
        rec_product = view.findViewById(R.id.home_fragment_rec_view_01);
        lihatSemua = view.findViewById(R.id.home_fragment_lihat_semua);
        gratisOngkir = view.findViewById(R.id.gratis_ongkir_home_fragment);
        pulsaAndTagihan = view.findViewById(R.id.pulsa_tagihan_home_fragment);
        myStoreMall = view.findViewById(R.id.myStore_mall_home_fragment);
        myStorePayNearby = view.findViewById(R.id.myStorePayNearby);
        myStoreBarokah = view.findViewById(R.id.MyStoreBarokah);
        myStoreGames = view.findViewById(R.id.MyStoreGames);
        myStoreFood = view.findViewById(R.id.myStoreFood);
        myStoreLocal = view.findViewById(R.id.myStoreLocal);
        myStorePayLater = view.findViewById(R.id.myStorePayLater);
        bascet = view.findViewById(R.id.bascet_home_fragment);
        chat = view.findViewById(R.id.chat_home_fragment);
        qrCodeScanner = view.findViewById(R.id.home_fragment_qr_code_scanner);
        myStorePay = view.findViewById(R.id.home_fragment_my_store_pay);
        myCoin = view.findViewById(R.id.home_fragment_coin);
        transfer = view.findViewById(R.id.home_fragment_transfer);

        setUpFirebase();

        qrCodeScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "QR CODE SCANNER", Toast.LENGTH_SHORT).show();
            }
        });
        myStorePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PayFragmentActivity.class);
                startActivity(intent);
            }
        });
        myCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyCoin.class);
                startActivity(intent);
            }
        });
        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TransferActivity.class);
                startActivity(intent);
            }
        });

        gratisOngkir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GratisOngkirActivity.class);
                getActivity().startActivity(intent);
            }
        });

        pulsaAndTagihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PulsadanTagihanActivity.class);
                getActivity().startActivity(intent);
            }
        });

        myStoreMall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyStoreMall.class);
                getActivity().startActivity(intent);
            }
        });

        myStorePayNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyStorepayNearby.class);
                getActivity().startActivity(intent);
            }
        });

        myStoreBarokah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyStoreBarokaActivity.class);
                getActivity().startActivity(intent);
            }
        });

        myStoreGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyStoreGamesActivity.class);
                getActivity().startActivity(intent);
            }
        });

        myStoreFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyStoreFoodActivity.class);
                getActivity().startActivity(intent);
            }
        });

        myStoreLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyStoreLocalActivity.class);
                getActivity().startActivity(intent);
            }
        });

        myStorePayLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyStorePayLater.class);
                getActivity().startActivity(intent);
            }
        });

        bascet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyBascet.class);
                getActivity().startActivity(intent);
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Chat.class);
                getActivity().startActivity(intent);
            }
        });





        lihatSemua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SemuaCategoryActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.inage_slider_1));
        sliderItems.add(new SliderItem(R.drawable.inage_slider_2));
        sliderItems.add(new SliderItem(R.drawable.inage_slider_3));
        sliderItems.add(new SliderItem(R.drawable.inage_slider_4));
        sliderItems.add(new SliderItem(R.drawable.inage_slider_5));
        sliderItems.add(new SliderItem(R.drawable.inage_slider_6));

        SlideAdapter2 slideAdapter2 = new SlideAdapter2(getActivity(), sliderItems, viewPager2);
        viewPager2.setAdapter(slideAdapter2);
        viewPager2.setClipToPadding(false);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(30));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleX(0.85f + r*0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);


        ArrayList<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.green_hills);
        imageList.add(R.drawable.green_hills);
        imageList.add(R.drawable.green_hills);
        imageList.add(R.drawable.green_hills);
        imageList.add(R.drawable.green_hills);
        imageList.add(R.drawable.green_hills);
        imageList.add(R.drawable.green_hills);
        imageList.add(R.drawable.green_hills);


        HomeAdapterVideo homeAdapterVideo = new HomeAdapterVideo(getActivity(), imageList);
        rec_video.setAdapter(homeAdapterVideo);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rec_video.setLayoutManager(layoutManager);



        ArrayList<Products> productsArrayList = new ArrayList<>();

        reference.child(getString(R.string.store_app))
                .child(getString(R.string.product)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            Products products = dataSnapshot.getValue(Products.class);
                            productsArrayList.add(products);
                        }
                        RecViewProductAdapter productAdapter = new RecViewProductAdapter(getActivity(), productsArrayList);

                        rec_product.setAdapter(productAdapter);
                        GridLayoutManager layoutManager1 = new GridLayoutManager(getActivity(), 2);

                        rec_product.setLayoutManager(layoutManager1);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_female
//        , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_female
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_male
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_male
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_male
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_male
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_male
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_male
//                , 10000, 10000, 5, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_male
//                , 10000, 10000, 4.7f, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_male
//                , 10000, 10000, 4.7f, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_male
//                , 10000, 10000, 4.7f, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_male
//                , 10000, 10000, 4.7f, 5, 999, 12345
//        ));
//        productsArrayList.add(new Products("Woman Shirt", "Beutiful Woman Shirt", R.drawable.black_shirt_male
//                , 10000, 10000, 4.7f, 5, 999, 12345
//        ));










        return view;
    }

    private void setUpFirebase() {
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        currentUser = mAuth.getCurrentUser();
        reference = database.getReference();

    }
}
