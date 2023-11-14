package com.example.tingtongapp.Views;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.tingtongapp.Model.ImageRoomModel;
import com.example.tingtongapp.Model.RoomModel;
import com.example.tingtongapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class PostRoomStep4 extends Fragment implements View.OnClickListener {
    Button btnNextStep4PostRoom;
    EditText edtNamePushRoom, edtDescribePushRoom;

    PostRoom postRoom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.post_room_step4, container, false);
        initControl(layout);

        //Lấy context của fragment
        postRoom = (PostRoom) getContext();
        return layout;
    }

    private void initControl(View view){
        edtNamePushRoom = view.findViewById(R.id.edt_name_push_room);
        edtDescribePushRoom = view.findViewById(R.id.edt_describe_push_room);

        btnNextStep4PostRoom =view.findViewById(R.id.btn_nextStep4_post_room);
        btnNextStep4PostRoom.setOnClickListener(this);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.btn_nextStep4_post_room){
            String title = edtNamePushRoom.getText().toString().trim();
            String descriptionRoom = edtDescribePushRoom.getText().toString().trim();

            if(title.equals("") || descriptionRoom.equals("")){
                Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                //  Get data from SharedPreferences
                SharedPreferences preferences = getActivity().getSharedPreferences("postRoomData", Context.MODE_PRIVATE);
                boolean checkStep1 = preferences.getBoolean("checkStep1", false);
                boolean checkStep2 = preferences.getBoolean("checkStep2", false);
                boolean checkStep3 = preferences.getBoolean("checkStep3", false);

                if (!checkStep1) {
                    Toast.makeText(getContext(), "Vui lòng nhập dữ liệu cho bước 1", Toast.LENGTH_SHORT).show();
                } else if (!checkStep2) {
                    Toast.makeText(getContext(), "Vui lòng nhập dữ liệu cho bước 2", Toast.LENGTH_SHORT).show();
                } else if (!checkStep3) {
                    Toast.makeText(getContext(), "Vui lòng nhập dữ liệu cho bước 3", Toast.LENGTH_SHORT).show();
                } else {
                    String addressRoom = preferences.getString("addressRoom", "Chưa cập nhật");
                    String typeOfRoom = preferences.getString("typeOfRoom", "Trọ");
                    int amountOfPeople = preferences.getInt("amountOfPeople", 0);
                    int rentingPriceRoom = preferences.getInt("rentingPriceRoom", 0);
                    int lengthRoom = preferences.getInt("lengthRoom", 0);
                    int widthRoom = preferences.getInt("widthRoom", 0);
                    int electricityPrice = preferences.getInt("electricityPrice", 0);
                    int waterPrice = preferences.getInt("waterPrice", 0);
                    int internetPrice = preferences.getInt("internetPrice", 0);
                    int parkingFee = preferences.getInt("parkingFee", 0);
                    ArrayList<Uri> listImageUris = new ArrayList<>();
                    Map<String, Boolean> listServicesRoom = new LinkedHashMap<>();

                    // Read list imageUris from SharedPreferences
                    int imageUrisSize = preferences.getInt("imageUrisSize", 0);

                    if(imageUrisSize > 0){
                        for(int i=0; i<imageUrisSize; ++i){
                            String uriString = preferences.getString("imageUri" + i, "null");
                            listImageUris.add(Uri.parse(uriString));
                        }
                    }

                    // Read Map<String, Boolean> listServicesRoom from SharedPreferences
                    for (Map.Entry<String, ?> entry : preferences.getAll().entrySet()) {
                        if (entry.getValue() instanceof Boolean) {
                            listServicesRoom.put(entry.getKey(), (Boolean) entry.getValue());
                        }
                    }

                    ImageRoomModel imageRoomModel = new ImageRoomModel();
                    for(Uri uri : listImageUris){
                        imageRoomModel.addImage(uri);
                    }

                    RoomModel newPostRoom = new RoomModel();
                    newPostRoom.setTitle(title);
                    newPostRoom.setDescription(descriptionRoom);
                    newPostRoom.setTypeOfRoom(typeOfRoom);
                    newPostRoom.setAddress(addressRoom);
                    newPostRoom.setAmountOfPeople(amountOfPeople);
                    newPostRoom.setLengthRoom(lengthRoom);
                    newPostRoom.setWidthRoom(widthRoom);
                    newPostRoom.setRentingPrice(rentingPriceRoom + "");
                    newPostRoom.setPricePostRoom(electricityPrice, waterPrice, internetPrice, parkingFee);
                    newPostRoom.setImagesRoom(imageRoomModel);
                    newPostRoom.setListServicesRoom(listServicesRoom);

                    // Post room up to Firebase
                    DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference newPostRef = databaseRef.child("listRoom").push();
                    newPostRef.setValue(newPostRoom);

                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
