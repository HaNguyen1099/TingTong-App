package com.example.tingtongapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tingtongapp.Adapters.AdapterListServices;
import com.example.tingtongapp.Model.ImageRoomModel;
import com.example.tingtongapp.Model.RoomModel;
import com.example.tingtongapp.Model.UserModel;
import com.example.tingtongapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailRoom extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private RoomModel roomModel;
    private TextView typeOfRoom, title, rentingPrice, conditionRoom, amountOfPeople, acreageRoom, description, address, phoneContact;
    private ImageRoomModel imageRoomModel;
    private ArrayList<ImageView> imageView;
    private TextView moreImg;
    private RecyclerView listServicesRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_detail_view);
        initControl();

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("intentDetailRoom")) {
            this.roomModel = (RoomModel) intent.getParcelableExtra("intentDetailRoom");

            if(this.roomModel != null)
                setInfoRoom();
        }
    }

    private void initControl(){
        typeOfRoom = findViewById(R.id.txt_roomType);
        title = findViewById(R.id.txt_roomName);
        rentingPrice = findViewById(R.id.txt_roomPrice);
        conditionRoom = findViewById(R.id.txt_roomStatus);
        amountOfPeople = findViewById(R.id.tv_amountOfPeople);
        acreageRoom = findViewById(R.id.txt_roomArea);
        description = findViewById(R.id.txt_roomDescription);
        address = findViewById(R.id.txt_roomAddress);
        phoneContact = findViewById(R.id.txt_room_phonenumber);

        moreImg = findViewById(R.id.txt_more_img);
        imageView = new ArrayList<>();
        imageView.add((ImageView) findViewById(R.id.img_room1));
        imageView.add((ImageView) findViewById(R.id.img_room2));
        imageView.add((ImageView) findViewById(R.id.img_room3));
        imageView.add((ImageView) findViewById(R.id.img_room4));

        listServicesRoom = findViewById(R.id.recycler_convenients_room_detail);
        toolbar = findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Quay lại");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void setInfoRoom(){
        try {
            imageRoomModel = roomModel.getImagesRoom();
            ArrayList<String> imageUrls = imageRoomModel.getAll();

            if(imageUrls.size() > 4){
                moreImg.setText("+" + (imageUrls.size() - 4));
            }else{
                moreImg.setText("");
            }

            title.setText(roomModel.getTitle());
            typeOfRoom.setText(roomModel.getTypeOfRoom());
            rentingPrice.setText(roomModel.getRentingPrice() + " triệu");
            conditionRoom.setText(roomModel.getConditionRoom());
            amountOfPeople.setText(roomModel.getAmountOfPeople() + "");
            acreageRoom.setText(roomModel.getLengthRoom() + "m x " + roomModel.getWidthRoom() + "m");
            description.setText(roomModel.getDescription());
            address.setText(roomModel.getAddress());
            UserModel use1r = roomModel.getRoomOwner();
            phoneContact.setText(use1r.getPhoneNumber());

            for(int i = 0; i < 4; i++) {
                int indexImageView = i;
                if (indexImageView < imageUrls.size() && imageUrls.get(indexImageView) != null) {
                    Picasso.get().load(imageUrls.get(indexImageView)).into(imageView.get(indexImageView), new Callback() {
                        @Override
                        public void onSuccess() {
                            // Do nothing
                        }
                        @Override
                        public void onError(Exception e) {
                            int resId = getResources().getIdentifier("ic_room", "drawable", getPackageName());
                            imageView.get(indexImageView).setImageResource(resId);
                            e.printStackTrace();
                        }
                    });
                } else {
                    int resId = getResources().getIdentifier("ic_room", "drawable", getPackageName());
                    imageView.get(indexImageView).setImageResource(resId);
                }
            }

            // Set list services for recycler view
            AdapterListServices adapterListServices = new AdapterListServices(roomModel);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            listServicesRoom.setLayoutManager(gridLayoutManager);
            listServicesRoom.setAdapter(adapterListServices);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view){

    }
}
