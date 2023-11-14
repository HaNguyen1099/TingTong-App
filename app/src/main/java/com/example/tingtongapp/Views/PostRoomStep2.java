package com.example.tingtongapp.Views;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.tingtongapp.R;

public class PostRoomStep2 extends Fragment implements View.OnClickListener{
    RadioButton rBtnType1PushRoom,rBtnType2PushRoom,rBtnType3PushRoom,rBtnType4PushRoom;
    EditText edtNumberPeoplePushRoom,edtLengthPushRoom,edtWidthPushRoom;
    EditText edtPriceRoomPushRoom,edtElectricBillPushRoom,edtWaterBillPushRoom,edtInternetPushRoom,edtParkingPushRoom;
    CheckBox chBoxFreeElectricPushRoom,chBoxFreeWaterPushRoom,chBoxFreeInternetPushRoom,chBoxFreeParkingPushRoom;
    Button btnNextStep2PostRoom;
    PostRoom postRoom;
    private RadioGroup radioGroupCheckTypeRoom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.post_room_step2, container, false);
        initControl(view);
        postRoom = (PostRoom) getContext();
        return view;
    }

    private void initControl(View view){
        rBtnType1PushRoom = view.findViewById(R.id.rBtn_type1_push_room);
        rBtnType2PushRoom = view.findViewById(R.id.rBtn_type2_push_room);
        rBtnType3PushRoom = view.findViewById(R.id.rBtn_type3_push_room);
        rBtnType4PushRoom = view.findViewById(R.id.rBtn_type4_push_room);
        radioGroupCheckTypeRoom = view.findViewById(R.id.radio_check_type_room);

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

        edtElectricBillPushRoom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // Before data is change
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // When data is change
                String newText = charSequence.toString();

                if (!newText.isEmpty() && !newText.equals("0")) {
                    chBoxFreeElectricPushRoom.setChecked(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // After data is change
            }
        });

        edtWaterBillPushRoom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // Before data is change
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // When data is change
                String newText = charSequence.toString();

                if (!newText.isEmpty() && !newText.equals("0")) {
                    chBoxFreeWaterPushRoom.setChecked(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // After data is change
            }
        });

        edtInternetPushRoom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // Before data is change
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // When data is change
                String newText = charSequence.toString();

                if (!newText.isEmpty() && !newText.equals("0")) {
                    chBoxFreeInternetPushRoom.setChecked(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // After data is change
            }
        });

        edtParkingPushRoom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // Before data is change
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // When data is change
                String newText = charSequence.toString();

                if (!newText.isEmpty() && !newText.equals("0")) {
                    chBoxFreeParkingPushRoom.setChecked(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // After data is change
            }
        });

        chBoxFreeElectricPushRoom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    edtElectricBillPushRoom.setText("0");
                }else{
                    String textOn = edtElectricBillPushRoom.getText().toString();

                    if(textOn.equals("0")) {
                        edtElectricBillPushRoom.setText("");
                    }
                }
            }
        });
        chBoxFreeWaterPushRoom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    edtWaterBillPushRoom.setText("0");
                }else{
                    String textOn = edtWaterBillPushRoom.getText().toString();

                    if(textOn.equals("0")) {
                        edtWaterBillPushRoom.setText("");
                    }
                }
            }
        });

        chBoxFreeInternetPushRoom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    edtInternetPushRoom.setText("0");
                }else{
                    String textOn = edtInternetPushRoom.getText().toString();

                    if(textOn.equals("0")) {
                        edtInternetPushRoom.setText("");
                    }
                }
            }
        });

        chBoxFreeParkingPushRoom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    edtParkingPushRoom.setText("0");
                }else{
                    String textOn = edtParkingPushRoom.getText().toString();

                    if(textOn.equals("0")) {
                        edtParkingPushRoom.setText("");
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if(viewId == R.id.btn_nextStep2_post_room){
            String typeOfRoom = "";
            String amountOfPeopleString = edtNumberPeoplePushRoom.getText().toString().trim();
            String rentingPriceRoomString = edtPriceRoomPushRoom.getText().toString().trim();
            String lengthRoomString = edtLengthPushRoom.getText().toString().trim();
            String widthRoomString = edtWidthPushRoom.getText().toString().trim();
            String electricityPriceString = edtElectricBillPushRoom.getText().toString().trim();
            String waterPriceString = edtWaterBillPushRoom.getText().toString().trim();
            String internetPriceString = edtInternetPushRoom.getText().toString().trim();
            String parkingFeeString = edtParkingPushRoom.getText().toString().trim();
            int amountOfPeople, rentingPriceRoom, lengthRoom, widthRoom, electricityPrice, waterPrice, internetPrice, parkingFee;
            boolean checkData = true;

            if(radioGroupCheckTypeRoom != null){
                int selectedId = radioGroupCheckTypeRoom.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = (RadioButton) getActivity().findViewById(selectedId);
                typeOfRoom = selectedRadioButton.getText().toString();
            }

            if (TextUtils.isEmpty(rentingPriceRoomString)
                    || TextUtils.isEmpty(lengthRoomString)
                    || TextUtils.isEmpty(widthRoomString)
                    || TextUtils.isEmpty(amountOfPeopleString)
            ){
                Toast.makeText(getContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                if(TextUtils.isEmpty(electricityPriceString) && !chBoxFreeElectricPushRoom.isChecked()){
                    checkData = false;
                }
                if(TextUtils.isEmpty(waterPriceString) && !chBoxFreeWaterPushRoom.isChecked()){
                    checkData = false;
                }
                if(TextUtils.isEmpty(internetPriceString) && !chBoxFreeInternetPushRoom.isChecked()){
                    checkData = false;
                }
                if(TextUtils.isEmpty(parkingFeeString) && !chBoxFreeParkingPushRoom.isChecked()){
                    checkData = false;
                }

                if(checkData){
                    try{
                        amountOfPeople = Integer.parseInt(amountOfPeopleString);
                        rentingPriceRoom = Integer.parseInt(rentingPriceRoomString);
                        lengthRoom = Integer.parseInt(lengthRoomString);
                        widthRoom = Integer.parseInt(widthRoomString);
                        electricityPrice = Integer.parseInt(electricityPriceString);
                        waterPrice = Integer.parseInt(waterPriceString);
                        internetPrice = Integer.parseInt(internetPriceString);
                        parkingFee = Integer.parseInt(parkingFeeString);

                        // Save data to SharePreferences
                        SharedPreferences preferences = getActivity().getSharedPreferences("postRoomData" ,Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("typeOfRoom", typeOfRoom);
                        editor.putInt("amountOfPeople", amountOfPeople);
                        editor.putInt("rentingPriceRoom", rentingPriceRoom);
                        editor.putInt("lengthRoom", lengthRoom);
                        editor.putInt("widthRoom", widthRoom);
                        editor.putInt("electricityPrice", electricityPrice);
                        editor.putInt("waterPrice", waterPrice);
                        editor.putInt("internetPrice", internetPrice);
                        editor.putInt("parkingFee", parkingFee);
                        editor.putBoolean("checkStep2", true);
                        editor.apply();

                        // Go to next step post room
                        if (isAdded() && getActivity() != null) {
                            postRoom.setCurrentPage(2);
                        }
                    } catch (Exception e){
                        Toast.makeText(getContext(), "Vui lòng nhập một số", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getContext(), "Vui lòng nhập một số", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}