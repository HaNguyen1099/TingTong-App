package com.example.tingtongapp.Views;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.core.app.ActivityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.view.View;

import com.example.tingtongapp.R;

public class MainActivity extends Fragment {
    RecyclerView recyclerGridMainRoom;
    ProgressBar progressBarMain;
    NestedScrollView nestedScrollMainView;
    ProgressBar progressBarLoadMoreGridMainRoom;

    GridView grVLocation;

    EditText edTSearch;
    View layout;

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
    @Override
    public void onStart() {
        super.onStart();

        setView();

    }
}