package com.example.storeapps.Home.UserFragmentChild;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storeapps.Home.GiveSomeRatingActivity;
import com.example.storeapps.Home.MulaiJual;
import com.example.storeapps.Home.NotYetPaidActivity;
import com.example.storeapps.Home.Packing;
import com.example.storeapps.Home.SendedActivity;
import com.example.storeapps.Home.StoreActivity;
import com.example.storeapps.Model.Store;
import com.example.storeapps.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BuyFragment extends Fragment {

    RelativeLayout pengaturan_account;
    RecyclerView recView1;
    RecyclerView recView2;
    RecyclerView recView3;
    RelativeLayout relativeLayout, mulaiJualRelLayout, relMulaiJual, relStoreInformation;
    LinearLayout linNotYetPaid, linPacking, linSended, linGiveSomeRating;
    final Store[] store = new Store[1];


    // Firebase
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseUser currentUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.buy_fragment, container, false);

        recView1 = view.findViewById(R.id.rec_view_1);
        recView2 = view.findViewById(R.id.rec_view_2);
        relativeLayout  = view.findViewById(R.id.rel_parent);
        mulaiJualRelLayout = view.findViewById(R.id.rel_3_mulai_jual);
        relMulaiJual = view.findViewById(R.id.rel_3_mulai_jual);
        relStoreInformation = view.findViewById(R.id.rel_3_store_information);
        recView3 = view.findViewById(R.id.rec_view_1);
        linNotYetPaid = view.findViewById(R.id.buy_fragment_not_yet_paid);
        linPacking = view.findViewById(R.id.buy_fragment_packing);
        linSended = view.findViewById(R.id.buy_fragment_sended);
        linGiveSomeRating = view.findViewById(R.id.buy_fragment_give_some_ratings);




        setUpFirebase();

        linNotYetPaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NotYetPaidActivity.class);
                getActivity().startActivity(intent);
            }
        });
        linPacking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Packing.class);
                getActivity().startActivity(intent);
            }
        });
        linSended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SendedActivity.class);
                getActivity().startActivity(intent);
            }
        });
        linGiveSomeRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GiveSomeRatingActivity.class);
                getActivity().startActivity(intent);
            }
        });

        Query query = reference.child(getString(R.string.store_app))
                .child(getString(R.string.user))
                .child(currentUser.getUid())
                .child(getString(R.string.store_information));

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    Store store = new Store();
                    store = snapshot.getValue(Store.class);

                    Boolean haveCreatedStore = store.getHasCreatedStore();

                    if (haveCreatedStore)
                    {
                        relMulaiJual.setVisibility(View.GONE);
                        relStoreInformation.setVisibility(View.VISIBLE);
                    }

                }
                else
                {
                    relMulaiJual.setVisibility(View.VISIBLE);
                    relStoreInformation.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        relStoreInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StoreActivity.class);
                getActivity().startActivity(intent);
            }
        });




//


//

        ArrayList<String> textList = new ArrayList<>();

        textList.add("Shopee Member");
        textList.add("Faforit Saya");
        textList.add("Terakhir Dilihat");
        textList.add("Shopee Live");
        textList.add("Penilaian Saya");
        textList.add("Voucher Saya");

        BuyAdapter1 buyAdapter1 = new BuyAdapter1(textList, getActivity());
        recView1.setAdapter(buyAdapter1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recView1.setLayoutManager(layoutManager);

        ArrayList<String> textList2 = new ArrayList<>();
        textList2.add("Pengaturan Akun");
        textList2.add("Pusat Bantuan");
        textList2.add("Chat Dengan Shopee");

        BuyAdapter2 buyAdapter2 = new BuyAdapter2(getActivity(), textList2, relativeLayout);
        recView2.setAdapter(buyAdapter2);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recView2.setLayoutManager(linearLayoutManager);


        mulaiJualRelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MulaiJual.class);
                getActivity().startActivity(intent);
            }
        });


        return view;
    }

    private void setUpFirebase() {
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        currentUser = mAuth.getCurrentUser();
    }
}
