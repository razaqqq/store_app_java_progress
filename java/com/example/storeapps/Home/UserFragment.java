package com.example.storeapps.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.storeapps.Home.UserFragmentChild.BuyFragment;
import com.example.storeapps.Home.UserFragmentChild.PengaturanAccount;
import com.example.storeapps.Home.UserFragmentChild.VideoUserFragment;
import com.example.storeapps.Model.Store;
import com.example.storeapps.Model.User;
import com.example.storeapps.Model.UserAccountSetting;
import com.example.storeapps.R;
import com.example.storeapps.Util.SectionPagerAdapter;
import com.example.storeapps.Util.UserPagerAdapter;
import com.example.storeapps.databinding.UserFragmentBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UserFragment extends Fragment {

    // Widget;

    ViewPager viewPager;
    TabLayout tabLayout;
    TextView username, members,  follower, following;
    ImageView setting, basket, chat;


    // Firebase
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    FirebaseUser currentUser;

    User user;
    UserAccountSetting userAccountSetting;
    Store store = new Store();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment, container, false);

        initView(view);
        initFirebase();


        Query query = database.getReference()
                .child(getString(R.string.db_store_app))
                .child(getString(R.string.db_user))
                .child(currentUser.getUid())
                .child(getString(R.string.store_information));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    store = snapshot.getValue(Store.class);

                    username.setText(store.getStoreName());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PengaturanAccount.class);
                startActivity(intent);
            }
        });

        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyBascet.class);
                startActivity(intent);
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Chat.class);
                startActivity(intent);
            }
        });


//        username.setText(user.getUsername().toString());






        setUpViewPager();

        return view;
    }

    private void retrieveDataFirebase() {



    }

    private void initFirebase() {
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        currentUser = mAuth.getCurrentUser();
    }

    private void setUpViewPager()
    {
        UserPagerAdapter mAdapter = new UserPagerAdapter(getChildFragmentManager());
        mAdapter.addFragment(new BuyFragment());
        mAdapter.addFragment(new VideoUserFragment());


        viewPager.setAdapter(mAdapter);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("Buy");
        tabLayout.getTabAt(1).setText("Video");



    }

    private void initView(View view)
    {
        viewPager = view.findViewById(R.id.view_pager_user_fragment);
        tabLayout = view.findViewById(R.id.tab_layout);
        username = view.findViewById(R.id.user_fragment_username);
        members = view.findViewById(R.id.user_fragment_members);
        follower = view.findViewById(R.id.user_fragment_follower);
        following = view.findViewById(R.id.user_fragment_following);
        setting = view.findViewById(R.id.user_fragment_setting);
        basket = view.findViewById(R.id.user_fragment_basket);
        chat = view.findViewById(R.id.user_fragment_chat);
    }

}
