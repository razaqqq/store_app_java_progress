package com.example.storeapps.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.storeapps.MainActivity;
import com.example.storeapps.R;

public class PayFragment extends Fragment {

    RelativeLayout relativeLayout;
    ImageView payImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pay_fragment, container, false);

        relativeLayout = view.findViewById(R.id.relative_layout_pay_fragment);
        payImage = view.findViewById(R.id.pay_fragment_add_card);

        payImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PayFragmentActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }



}
