package com.example.storeapps.Home.UserFragmentChild;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.storeapps.R;

import java.util.ArrayList;
import java.util.zip.Inflater;


public class PengaturanAccountChild extends Fragment {

    RecyclerView recView1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pengaturan_account_child, container, false);

        recView1 = view.findViewById(R.id.child_rec_view);

        ArrayList<String> textList = new ArrayList<>();

        textList.add("Account Security");
        textList.add("My Address");
        textList.add("Card and Bank");

        BuyAdapter3 buyAdaptere3 = new BuyAdapter3(getActivity(), textList);
        recView1.setAdapter(buyAdaptere3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recView1.setLayoutManager(layoutManager);

        return view;
    }
}