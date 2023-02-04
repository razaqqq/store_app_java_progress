package com.example.storeapps.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.storeapps.R;

public class NotificationFragment extends Fragment {

    RelativeLayout relMyStorePromo, relLikeAndVideo, relContactAndFriend, relSomeNewsFromOurTeam, relMyGames;
    Button btnBerlanjaSekarang;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notification_fragment, container, false);

        relMyStorePromo = view.findViewById(R.id.rel_notification_fragment_promo_my_store);
        relLikeAndVideo = view.findViewById(R.id.rel_notification_fragment_like_and_video);
        relContactAndFriend = view.findViewById(R.id.rel_notification_fragment_contact_and_friend);
        relSomeNewsFromOurTeam = view.findViewById(R.id.rel_notification_some_news_from_our_team);
        relMyGames = view.findViewById(R.id.rel_notification_fragment_my_games);

        btnBerlanjaSekarang = view.findViewById(R.id.btn_notification_fragment_belanja_sekarang);

        relMyStorePromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PromoMyStore.class);
                getActivity().startActivity(intent);
            }
        });

        relLikeAndVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LiveAndVideoNotification.class);
                getActivity().startActivity(intent);
            }
        });

        relContactAndFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContactandFriendNotification.class);
                getActivity().startActivity(intent);
            }
        });

        relSomeNewsFromOurTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SomeNewsFromOurTeam.class);
                getActivity().startActivity(intent);
            }
        });

        relMyGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyGameNotificationss.class);
                getActivity().startActivity(intent);
            }
        });

        btnBerlanjaSekarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BuySomethingNow.class);
                getActivity().startActivity(intent);
            }
        });


        return view;
    }
}
