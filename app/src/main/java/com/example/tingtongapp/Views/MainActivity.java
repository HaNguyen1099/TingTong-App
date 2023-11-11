package com.example.tingtongapp.Views;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tingtongapp.Adapters.AdapterRoomSuggestions;
import com.example.tingtongapp.Model.RoomModel;
import com.example.tingtongapp.R;

import java.util.ArrayList;

public class MainActivity extends Fragment {
    RecyclerView recyclerGridMainRoom;
    ProgressBar progressBarMain;
    NestedScrollView nestedScrollMainView;
    ProgressBar progressBarLoadMoreGridMainRoom;
    GridView grVLocation;

    EditText edTSearch;
    View layout;

    private RecyclerView listRoomSuggestions;
    private ArrayList<RoomModel> listRoom;
    private AdapterRoomSuggestions adapterListRoom;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermission();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initRecyclerView() {
        listRoomSuggestions = layout.findViewById(R.id.recycler_Grid_Main_Room);
        listRoom = new ArrayList<>();
        adapterListRoom = new AdapterRoomSuggestions(getActivity(), listRoom);

        RoomModel a1 = new RoomModel();
        a1.setTitle("Phong tro sinh viên");
        a1.setTypeOfRoom("Phong tro");
        a1.setLengthRoom(10);
        a1.setWidthRoom(5);
        a1.setAddress("Ha Dong, Ha Noi");
        a1.setRentingPrice("2.5tr/thang");

        RoomModel a2 = new RoomModel();
        a2.setTitle("Phong tro giá rẻ cho ngưỡi đi làm");
        a2.setTypeOfRoom("Căn hộ");
        a2.setLengthRoom(15);
        a2.setWidthRoom(8);
        a2.setAddress("Cau giay, Ha Noi");
        a2.setRentingPrice("3.2tr/thang");

        listRoom.add(a1);
        listRoom.add(a2);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        listRoomSuggestions.setLayoutManager(linearLayoutManager);
        listRoomSuggestions.setAdapter(adapterListRoom);
    }

    private void setView() {
        // Hiển thị progress bar main
        progressBarMain.setVisibility(View.VISIBLE);
        // Ẩn progress bar load more grid main rooms
        progressBarLoadMoreGridMainRoom.setVisibility(View.GONE);
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(getActivity(),new String[]{ACCESS_FINE_LOCATION},1);
    }

    private void clickSearchRoom(){
        edTSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("checkclick", "onClick: ");
                Intent intentSearchLocation = new Intent(getContext(),LocationSearch.class);
                startActivity(intentSearchLocation);
            }
        });
    }
}