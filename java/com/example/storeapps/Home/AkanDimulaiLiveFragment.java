package com.example.storeapps.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.storeapps.R;

import java.util.ArrayList;


public class AkanDimulaiLiveFragment extends Fragment {

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_akan_dimulai_live, container, false);

        recyclerView = view.findViewById(R.id.fragment_akan_dimulai_live_rec_view);

        ArrayList<Integer> imageList = new ArrayList<>();

        imageList.add(R.drawable.bikini_01);
        imageList.add(R.drawable.bikini_02);
        imageList.add(R.drawable.bikini_03);
        imageList.add(R.drawable.bikini_04);
        imageList.add(R.drawable.bikini_05);
        imageList.add(R.drawable.bikini_06);
        imageList.add(R.drawable.bikini_07);

        AkanDimulaRecViewAdapter akanDimulaRecViewAdapter = new AkanDimulaRecViewAdapter(getActivity(), imageList);

        recyclerView.setAdapter(akanDimulaRecViewAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);


        return view;
    }
}