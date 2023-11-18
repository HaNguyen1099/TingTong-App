package com.example.tingtongapp.Views;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tingtongapp.Adapters.AdapterMyRoom;
import com.example.tingtongapp.Model.Room;
import com.example.tingtongapp.Model.UserModel;
import com.example.tingtongapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RoomManagement extends AppCompatActivity {
    RecyclerView recyclerMainRoom;
    ProgressBar progressBarMyRooms, progressBarLoadMoreMyRooms;
    LinearLayout lnLtQuantityTopMyRooms;
    TextView txtQuantityMyRooms, txtQuantityRoom;
    String UID;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_management_user_view);

        this.UID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        initControl();
    }

    private void initControl() {
        lnLtQuantityTopMyRooms = (LinearLayout) findViewById(R.id.lnLt_quantity_top_my_rooms);
        txtQuantityMyRooms = (TextView) findViewById(R.id.txt_quantity_my_rooms);
        recyclerMainRoom = (RecyclerView)findViewById(R.id.recycler_Main_Room);
        txtQuantityRoom = (TextView) findViewById(R.id.txt_quantity_room);

        progressBarMyRooms = (ProgressBar) findViewById(R.id.progress_bar_my_rooms);
        progressBarMyRooms.getIndeterminateDrawable().setColorFilter(Color.parseColor("#F54500"),
                android.graphics.PorterDuff.Mode.MULTIPLY);

        progressBarLoadMoreMyRooms = (ProgressBar) findViewById(R.id.progress_bar_load_more_my_rooms);
        progressBarLoadMoreMyRooms.getIndeterminateDrawable().setColorFilter(Color.parseColor("#F54500"),
                android.graphics.PorterDuff.Mode.MULTIPLY);

        toolbar = findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Phòng của bạn");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void setView() {
        // Hide progress bar.
        progressBarMyRooms.setVisibility(View.VISIBLE);
        // Hide progress bar load more.
        progressBarLoadMoreMyRooms.setVisibility(View.GONE);
        // Hide layout
        lnLtQuantityTopMyRooms.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setView();
        getData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    // Get user's room list
    private void getData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        Query query = databaseReference.child("ListRoom").orderByChild("idOwner").equalTo(currentUser.getUid());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Room> listUserRoom = new ArrayList<>();

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    try {
                        Room room = data.getValue(Room.class);
                        listUserRoom.add(room);
                    } catch (Exception e){
                        Toast.makeText(RoomManagement.this, "Lỗi tải dữ liệu phòng", Toast.LENGTH_SHORT).show();
                    }
                }

                Collections.sort(listUserRoom, new Comparator<Room>() {
                    DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                    @Override
                    public int compare(Room r1, Room r2) {
                        try {
                            return f.parse(r2.getDateAdded()).compareTo(f.parse(r1.getDateAdded()));
                        } catch (ParseException e) {
                            throw new IllegalArgumentException(e);
                        }
                    }
                });

                int quantityMyRooms = listUserRoom.size();
                lnLtQuantityTopMyRooms.setVisibility(View.VISIBLE);
                progressBarMyRooms.setVisibility(View.GONE);
                txtQuantityMyRooms.setText("" + quantityMyRooms);
                txtQuantityRoom.setText("" + quantityMyRooms);

                AdapterMyRoom listMyRoom = new AdapterMyRoom(RoomManagement.this, listUserRoom);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RoomManagement.this);
                RecyclerView recyclerViewListMyRoom =  (RecyclerView)findViewById(R.id.recycler_Main_Room);
                recyclerViewListMyRoom.setLayoutManager(linearLayoutManager);
                recyclerViewListMyRoom.setAdapter(listMyRoom);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(RoomManagement.this, "Lỗi kết nối cơ sở dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
