package com.example.storeapps.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.storeapps.R;

import java.util.ArrayList;


public class SemuaLiveFragment extends Fragment {


    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_semua_live, container, false);

        recyclerView = view.findViewById(R.id.fragment_semua_live_rec_view);


        ArrayList<Integer> imageList = new ArrayList<>();

        imageList.add(R.drawable.hawaii_rainforest);
        imageList.add(R.drawable.green_hills);
        imageList.add(R.drawable.foggy_iceland);
        imageList.add(R.drawable.havasu_falls);

        SemuaVideoAdapter semuaVideoAdapter = new SemuaVideoAdapter(getActivity(), imageList);
        recyclerView.setAdapter(semuaVideoAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);


        return view;
    }
}