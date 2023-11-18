package com.example.tingtongapp.Views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.tingtongapp.Adapters.AdapterRecyclerFilter;
import com.example.tingtongapp.Adapters.AdapterRecyclerSuggestions;
import com.example.tingtongapp.ClassOther.myFilter;
import com.example.tingtongapp.Controller.Interfaces.ICallBackSearchView;
import com.example.tingtongapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchView extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, ICallBackSearchView {

    public static final String REQUEST = "requestcode";
    public final static int REQUEST_DISTRICT = 99;

    // Save state of 4 fragment instead reload
    private HashMap<Integer, Fragment> fragmentHashMap = new HashMap<>();
    ImageView ivBack;
    CheckBox chBoxPrice, chBoxType, chBoxNumber, chBoxConvenient;
    RecyclerView recyclerFilter,recyclerSearchRoom;
    Button btnsSubmit;
    EditText edTSearch;
    ImageButton btnDeleteAllFilter ;
    ProgressBar progessBarLoad;
    LinearLayout lnLtResultReturnSearchView;
    TextView txtNumberRoom;
    NestedScrollView nestedScrollSearchView;
    ProgressBar progressBarLoadMoreSearchView;
    FrameLayout fragmentContainer;
    String district;
    List<myFilter> filterList;
    AdapterRecyclerFilter adapterRecyclerFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        initData();
        initControl();
        getDistrict();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Gọi hàm tìm kiếm
        callSearchRoomController();
    }

    private void getDistrict(){
        Intent intent = getIntent();
        district = intent.getStringExtra(AdapterRecyclerSuggestions.INTENT_DISTRICT);
        //Set text cho district
        edTSearch.setText(district);
    }

    private void initData(){
        filterList = new ArrayList<myFilter>();
    }

    private void initControl(){
        edTSearch = findViewById(R.id.edT_search);
        edTSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchView.this, LocationSearch.class);
                intent.putExtra(REQUEST,REQUEST_DISTRICT);
                startActivityForResult(intent,REQUEST_DISTRICT);
            }
        });

        txtNumberRoom = findViewById(R.id.txt_number_room);

        progessBarLoad = findViewById(R.id.progress_bar_search_view);
        // Edit color for progressbar
        progessBarLoad.getIndeterminateDrawable().setColorFilter(Color.parseColor("#F54500"),
                android.graphics.PorterDuff.Mode.MULTIPLY);
        // Hide on the first load
        progessBarLoad.setVisibility(View.GONE);

        lnLtResultReturnSearchView = (LinearLayout) findViewById(R.id.lnLt_resultReturn_search_view);

        nestedScrollSearchView = (NestedScrollView) findViewById(R.id.nested_scroll_search_view);
        progressBarLoadMoreSearchView = (ProgressBar) findViewById(R.id.progress_bar_load_more_search_view);
        progressBarLoadMoreSearchView.getIndeterminateDrawable().setColorFilter(Color.parseColor("#F54500"),
                android.graphics.PorterDuff.Mode.MULTIPLY);

        chBoxPrice=findViewById(R.id.chBox_price);
        chBoxConvenient=findViewById(R.id.chBox_convenient);
        chBoxType=findViewById(R.id.chBox_type);
        chBoxNumber=findViewById(R.id.chBox_number);

        chBoxPrice.setOnClickListener(this);
        chBoxConvenient.setOnClickListener(this);
        chBoxType.setOnClickListener(this);
        chBoxNumber.setOnClickListener(this);

        chBoxPrice.setOnCheckedChangeListener(this);
        chBoxConvenient.setOnCheckedChangeListener(this);
        chBoxType.setOnCheckedChangeListener(this);
        chBoxNumber.setOnCheckedChangeListener(this);

        fragmentContainer = findViewById(R.id.fragment_container);

        recyclerFilter = findViewById(R.id.recycler_filter);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
        recyclerFilter.setLayoutManager(staggeredGridLayoutManager);

        adapterRecyclerFilter = new AdapterRecyclerFilter(this,R.layout.search_filter_element_recyclerview, filterList);
        recyclerFilter.setAdapter(adapterRecyclerFilter);

        recyclerSearchRoom = findViewById(R.id.recycler_search_room);

        btnsSubmit = findViewById(R.id.btn_submit);
        btnsSubmit.setOnClickListener(this);

        // Hide for the first create
        btnsSubmit.setVisibility(View.GONE);

        btnDeleteAllFilter = findViewById(R.id.btn_delete_all_filter);
        btnDeleteAllFilter.setOnClickListener(this);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }

    @Override
    public void addFilter(myFilter filter) {

    }

    @Override
    public void replaceFilter(myFilter filter) {

    }

    @Override
    public void removeFilter(myFilter filter) {

    }

    private void callSearchRoomController(){
        // Display progress bar
        progessBarLoad.setVisibility(View.VISIBLE);
        // Hide quantity result
        lnLtResultReturnSearchView.setVisibility(View.GONE);
        // Hide progress bar load more
        progressBarLoadMoreSearchView.setVisibility(View.GONE);
    }
}