package com.example.tingtongapp.Views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.tingtongapp.Adapters.AdapterRecyclerSuggestions;
import com.example.tingtongapp.R;

public class searchView extends AppCompatActivity {

    public static final String REQUEST = "requestcode";
    public final static int REQUEST_DISTRICT = 99;

    CheckBox chBoxPrice, chBoxType, chBoxNumber;
    RecyclerView recyclerFilter,recyclerSearchRoom;
    Button btnsSubmit;
    EditText edTSearch;
    ImageButton btnDeleteAllFilter ;
    TextView txtCancel;

    ProgressBar progessBarLoad;
    LinearLayout lnLtResultReturnSearchView;
    TextView txtNumberRoom;

    NestedScrollView nestedScrollSearchView;
    ProgressBar progressBarLoadMoreSearchView;

    FrameLayout fragmentContainer;

    String district;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);

        initControl();
        getDistrict();
    }

    private void getDistrict(){
        Intent intent = getIntent();
        district = intent.getStringExtra(AdapterRecyclerSuggestions.INTENT_DISTRICT);
        //Set text cho district
        edTSearch.setText(district);
    }

    private void initControl(){

    }
}