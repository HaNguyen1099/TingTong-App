package com.example.tingtongapp.Views;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tingtongapp.Adapters.AdapterRoomSuggestion;
import com.example.tingtongapp.Controller.MainActivityController;
import com.example.tingtongapp.Model.Room;
import com.example.tingtongapp.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends Fragment {
    RecyclerView recyclerGridMainRoom;
    ProgressBar progressBarMain;
    NestedScrollView nestedScrollMainView;
    ProgressBar progressBarLoadMoreGridMainRoom;
    GridView grVLocation;
    EditText edTSearch;
    View layout;
    MainActivityController mainActivityController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermission();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.activity_main, container, false);
        initControl();
        initRecyclerView();
        clickSearchRoom();
        return layout;
    }

    private void initControl() {
        grVLocation = (GridView) layout.findViewById(R.id.grV_location);

        edTSearch = (EditText) layout.findViewById(R.id.edT_search);

        nestedScrollMainView = (NestedScrollView) layout.findViewById(R.id.nested_scroll_main_view);
        progressBarLoadMoreGridMainRoom = (ProgressBar) layout.findViewById(R.id.progress_bar_grid_main_rooms);
        progressBarLoadMoreGridMainRoom.getIndeterminateDrawable().setColorFilter(Color.parseColor("#F54500"),
                android.graphics.PorterDuff.Mode.MULTIPLY);

        recyclerGridMainRoom = (RecyclerView)layout.findViewById(R.id.recycler_Grid_Main_Room);
        progressBarMain = (ProgressBar)layout.findViewById(R.id.Progress_Main);
        progressBarMain.getIndeterminateDrawable().setColorFilter(Color.parseColor("#F54500"),
                android.graphics.PorterDuff.Mode.MULTIPLY);
    }

    private void initRecyclerView() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("ListRoom");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Room> roomArrayList = new ArrayList<>();

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    try {
                        Room room = dataSnapshot.getValue(Room.class);
                        roomArrayList.add(room);
                    }catch (Exception e){
                        Toast.makeText(getContext(), "Lỗi tải dữ liệu phòng", Toast.LENGTH_SHORT).show();
                    }
                }

                AdapterRoomSuggestion listRoomSuggestions = new AdapterRoomSuggestion(getActivity(), roomArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                RecyclerView recyclerViewListRoomSuggestions = layout.findViewById(R.id.recycler_Grid_Main_Room);
                recyclerViewListRoomSuggestions.setLayoutManager(linearLayoutManager);
                recyclerViewListRoomSuggestions.setAdapter(listRoomSuggestions);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Lỗi kết nối cơ sở dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setView() {
        // Display progress bar main
        progressBarMain.setVisibility(View.VISIBLE);
        // Hide progress bar load more grid main rooms
        progressBarLoadMoreGridMainRoom.setVisibility(View.GONE);
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(getActivity(),new String[]{ACCESS_FINE_LOCATION},1);
    }

    private void clickSearchRoom(){
        edTSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSearchLocation = new Intent(getContext(),LocationSearch.class);
                startActivity(intentSearchLocation);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        setView();

        //Load top location has many rooms
        mainActivityController = new MainActivityController(getContext());
        mainActivityController.loadTopLocation(grVLocation);
        progressBarMain.setVisibility(View.GONE);
    }
}