package com.example.tingtongapp.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tingtongapp.R;

public class PostRoomStep3 extends Fragment implements View.OnClickListener{

    CheckBox chBoxWifi,chBoxClock,chBoxBed,chBoxFridge,chBoxWardrobe, chBoxParking,chBoxWashmachine,chBoxWaterheater,chBoxSecurity,chBoxArcondition;

    ImageButton btnImgUpLoadPushRoom;
    RecyclerView recyclerImgUpload;
    Button btnNextStep3PostRoom;

    PostRoom postRoom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.post_room_step3, container, false);
        //khoi tao view
        initControl(view);
        //Lấy ra activity hiện tại
        postRoom = (PostRoom) getContext();
        return view;
    }

    private void initControl(View view){
        chBoxWifi = view.findViewById(R.id.chBox_wifi);
        chBoxClock = view.findViewById(R.id.chBox_clock);
        chBoxBed = view.findViewById(R.id.chBox_bed);
        chBoxFridge = view.findViewById(R.id.chBox_fridge);
        chBoxWardrobe = view.findViewById(R.id.chBox_wardrobe);
        chBoxParking = view.findViewById(R.id.chBox_parking);
        chBoxWashmachine = view.findViewById(R.id.chBox_washmachine);
        chBoxWaterheater = view.findViewById(R.id.chBox_waterheater);
        chBoxSecurity = view.findViewById(R.id.chBox_security);
        chBoxArcondition = view.findViewById(R.id.chBox_arcondition);

        btnNextStep3PostRoom = view.findViewById(R.id.btn_nextStep3_post_room);
        btnNextStep3PostRoom.setOnClickListener(this);

        btnImgUpLoadPushRoom = view.findViewById(R.id.btnImg_upLoad_push_room);
        btnImgUpLoadPushRoom.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

    }
}