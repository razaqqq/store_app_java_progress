package com.example.storeapps.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.storeapps.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class LiveFragment extends Fragment {


    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton floatingButton;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.live_fragment, container, false);

        tabLayout = view.findViewById(R.id.live_fragment_tab_layout);
        viewPager = view.findViewById(R.id.live_fragment_view_pager);
        floatingButton = view.findViewById(R.id.live_fragment_floating_button);

        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateNewVideoLive.class);
                getActivity().startActivity(intent);
            }
        });

        initViewPager();

        return view;
    }

    private void initViewPager() {

        LiveFragmentTabAdapter liveFragmentTabAdapter = new LiveFragmentTabAdapter(getChildFragmentManager());

        liveFragmentTabAdapter.addFragment(new SemuaLiveFragment());
        liveFragmentTabAdapter.addFragment(new TerbaruLiveFragment());
        liveFragmentTabAdapter.addFragment(new AkanDimulaiLiveFragment());
        liveFragmentTabAdapter.addFragment(new PutarUlangLiveFragment());

        viewPager.setAdapter(liveFragmentTabAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setMinimumWidth(100);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_videocam_24);
        tabLayout.getTabAt(0).setText("Semua");
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_videocam_24);
        tabLayout.getTabAt(1).setText("Terbaru");
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_videocam_24);
        tabLayout.getTabAt(2).setText("Akan Dimulai");
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_baseline_videocam_24);
        tabLayout.getTabAt(3).setText("Putar Ulang");





    }
}
