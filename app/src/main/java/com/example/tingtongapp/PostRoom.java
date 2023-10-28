package com.example.tingtongapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class PostRoom extends AppCompatActivity implements View.OnClickListener {
    ImageButton btnImgLocationPushRoom, btnImgInformationPushRoom, btnImgUtilityPushRoom, btnImgConfirmPushRoom;
    TextView txtLocationPushRoom, txtInfomationPushRoom, txtUtilityPushRoom, txtComfirmPushRoom;
    Toolbar toolbar;

    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_room_main);
        initControl();
    }

    private void initControl() {
        txtLocationPushRoom = findViewById(R.id.txt_location_push_room);
        txtComfirmPushRoom = findViewById(R.id.txt_comfirm_push_room);
        txtInfomationPushRoom = findViewById(R.id.txt_infomation_push_room);
        txtUtilityPushRoom = findViewById(R.id.txt_utility_push_room);

        btnImgLocationPushRoom = findViewById(R.id.btnImg_location_push_room);
        btnImgConfirmPushRoom = findViewById(R.id.btnImg_confirm_push_room);
        btnImgUtilityPushRoom = findViewById(R.id.btnImg_utility_push_room);
        btnImgInformationPushRoom = findViewById(R.id.btnImg_information_push_room);

        btnImgLocationPushRoom.setOnClickListener(this);
        btnImgConfirmPushRoom.setOnClickListener(this);
        btnImgUtilityPushRoom.setOnClickListener(this);
        btnImgInformationPushRoom.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btnImg_location_push_room){
            Intent intent = new Intent(PostRoom.this, PostRoomStep1.class);
            startActivity(intent);
        }
        else if(id == R.id.btnImg_information_push_room){
            Intent intent = new Intent(PostRoom.this, PostRoomStep2.class);
            startActivity(intent);
        }
        else if(id == R.id.btnImg_utility_push_room){
            Intent intent = new Intent(PostRoom.this, PostRoomStep3.class);
            startActivity(intent);
        }
        else if(id == R.id.btnImg_confirm_push_room){
            Intent intent = new Intent(PostRoom.this, PostRoomStep4.class);
            startActivity(intent);
        }
    }

}