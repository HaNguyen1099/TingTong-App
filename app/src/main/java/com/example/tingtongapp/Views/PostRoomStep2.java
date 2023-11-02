package com.example.tingtongapp.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.tingtongapp.R;

public class PostRoomStep2 extends Fragment implements View.OnClickListener{
    RadioButton rBtnType1PushRoom,rBtnType2PushRoom,rBtnType3PushRoom,rBtnType4PushRoom,rBtnMale,rBtnFemale;
    EditText edtNumberPeoplePushRoom,edtLengthPushRoom,edtWidthPushRoom;
    EditText edtPriceRoomPushRoom,edtElectricBillPushRoom,edtWaterBillPushRoom,edtInternetPushRoom,edtParkingPushRoom;
    CheckBox chBoxFreeElectricPushRoom,chBoxFreeWaterPushRoom,chBoxFreeInternetPushRoom,chBoxFreeParkingPushRoom;
    Button btnNextStep2PostRoom;
    PostRoom postRoom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.post_room_step2, container, false);
        //Liên kết các giá trị trên control
        initControl(view);
        //Lấy ra activity hiện tại
        postRoom = (PostRoom) getContext();
        return view;
    }

    private void initControl(View view){
        rBtnType1PushRoom = view.findViewById(R.id.rBtn_type1_push_room);
        rBtnType2PushRoom = view.findViewById(R.id.rBtn_type2_push_room);
        rBtnType3PushRoom = view.findViewById(R.id.rBtn_type3_push_room);
        rBtnType4PushRoom = view.findViewById(R.id.rBtn_type4_push_room);
        rBtnMale=view.findViewById(R.id.rBtn_male);
        rBtnFemale=view.findViewById(R.id.rBtn_female);

        edtNumberPeoplePushRoom =view.findViewById(R.id.edt_number_people_push_room);
        edtLengthPushRoom =view.findViewById(R.id.edt_length_push_room);
        edtWidthPushRoom =view.findViewById(R.id.edt_width_push_room);

        edtPriceRoomPushRoom =view.findViewById(R.id.edt_priceRoom_push_room);
        edtElectricBillPushRoom =view.findViewById(R.id.edt_electricBill_push_room);
        edtWaterBillPushRoom =view.findViewById(R.id.edt_waterBill_push_room);
        edtInternetPushRoom =view.findViewById(R.id.edt_internet_push_room);
        edtParkingPushRoom =view.findViewById(R.id.edt_parking_push_room);

        chBoxFreeElectricPushRoom = view.findViewById(R.id.chBox_freeElectric_push_room);
        chBoxFreeWaterPushRoom = view.findViewById(R.id.chBox_freeWater_push_room);
        chBoxFreeInternetPushRoom = view.findViewById(R.id.chBox_freeInternet_push_room);
        chBoxFreeParkingPushRoom = view.findViewById(R.id.chBox_freeParking_push_room);

        btnNextStep2PostRoom = view.findViewById(R.id.btn_nextStep2_post_room);

        btnNextStep2PostRoom.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}