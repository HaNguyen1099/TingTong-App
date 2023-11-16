package com.example.tingtongapp.Views;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tingtongapp.Adapters.AdapterMyRoom;
import com.example.tingtongapp.Controller.MainActivityController;
import com.example.tingtongapp.Controller.RoomManagementControlller;
import com.example.tingtongapp.Model.Room;
import com.example.tingtongapp.Model.RoomModel;
import com.example.tingtongapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class roomManagementModel extends AppCompatActivity {
    RecyclerView recyclerMainRoom;
    MainActivityController mainActivityController;
    RoomManagementControlller roomManagementControlller;
    List<RoomModel> roomModelList = new ArrayList<>();
    ProgressBar progressBarMyRooms;
    LinearLayout lnLtQuantityTopMyRooms;
    // Số lượng trả về.
    TextView txtQuantityMyRooms;
    TextView txtQuantityRoom;
    String UID;
    NestedScrollView nestedScrollMyRoomsView;
    ProgressBar progressBarLoadMoreMyRooms;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_management_user_view);

        this.UID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        initControl();
    }

    private void initControl() {
        recyclerMainRoom = (RecyclerView)findViewById(R.id.recycler_Main_Room);

        txtQuantityRoom = (TextView) findViewById(R.id.txt_quantity_room);

        progressBarMyRooms = (ProgressBar) findViewById(R.id.progress_bar_my_rooms);
        progressBarMyRooms.getIndeterminateDrawable().setColorFilter(Color.parseColor("#F54500"),
                android.graphics.PorterDuff.Mode.MULTIPLY);

        lnLtQuantityTopMyRooms = (LinearLayout) findViewById(R.id.lnLt_quantity_top_my_rooms);
        txtQuantityMyRooms = (TextView) findViewById(R.id.txt_quantity_my_rooms);

        nestedScrollMyRoomsView = (NestedScrollView) findViewById(R.id.nested_scroll_my_rooms);
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
        // Hiện progress bar.
        progressBarMyRooms.setVisibility(View.VISIBLE);
        // Ẩn progress bar load more.
        progressBarLoadMoreMyRooms.setVisibility(View.GONE);
        // Ẩn layout kết quả trả vể.
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

    // lấy và hiển thị dữ liệu của phòng
    private void getData(){
        roomManagementControlller = new RoomManagementControlller(this);
        roomManagementControlller.loadQuantityInfo(UID, txtQuantityRoom);
        // lấy thông tin về số lượng phòng của người dùng UID và hiển thị nó trong txtQuantityRoom

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("ListRoom");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Room> roomArrayList = new ArrayList<>();
                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = auth.getCurrentUser();
                String uidCurrentUser = currentUser.getUid();

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    try {
                        Room room = dataSnapshot.getValue(Room.class);

                        if(room.getIdOwner().equals(uidCurrentUser)){
                            roomArrayList.add(room);
                        }
                    }catch (Exception e){
                        Toast.makeText(roomManagementModel.this, "Lỗi tải dữ liệu phòng", Toast.LENGTH_SHORT).show();
                    }
                }

                AdapterMyRoom listMyRoom = new AdapterMyRoom(roomManagementModel.this, roomArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(roomManagementModel.this);
                RecyclerView recyclerViewListMyRoom =  (RecyclerView)findViewById(R.id.recycler_Main_Room);
                recyclerViewListMyRoom.setLayoutManager(linearLayoutManager);
                recyclerViewListMyRoom.setAdapter(listMyRoom);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(roomManagementModel.this, "Lỗi kết nối cơ sở dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
