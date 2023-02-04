package com.example.storeapps.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.storeapps.R;

public class ChatPageProductPage extends AppCompatActivity {

    ImageView backArrow, cartBottom, chatProduct;
    FrameLayout frameLayout;
    ActivityChatPageBottomFragment bottomFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page_product_page);

        backArrow = findViewById(R.id.activity_chat_page_product_page_back_arrow);



        bottomFragment = new ActivityChatPageBottomFragment();


        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });





    }
}