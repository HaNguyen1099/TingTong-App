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
        edTSearch = findViewById(R.id.edT_search);
        edTSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(searchView.this, LocationSearch.class);
                intent.putExtra(REQUEST,REQUEST_DISTRICT);
                startActivityForResult(intent,REQUEST_DISTRICT);
            }
        });

        txtNumberRoom =findViewById(R.id.txt_number_room);
        //Ẩn text

        progessBarLoad = findViewById(R.id.progress_bar_search_view);
        //Đổi màu cho progessbar
        progessBarLoad.getIndeterminateDrawable().setColorFilter(Color.parseColor("#00DDFF"),
                android.graphics.PorterDuff.Mode.MULTIPLY);
        //Ẩn lần đầu
        progessBarLoad.setVisibility(View.GONE);

        lnLtResultReturnSearchView = (LinearLayout) findViewById(R.id.lnLt_resultReturn_search_view);

        nestedScrollSearchView = (NestedScrollView) findViewById(R.id.nested_scroll_search_view);
        progressBarLoadMoreSearchView = (ProgressBar) findViewById(R.id.progress_bar_load_more_search_view);
        progressBarLoadMoreSearchView.getIndeterminateDrawable().setColorFilter(Color.parseColor("#00DDFF"),
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

        adapterRecyclerFilter = new AdapterRecyclerFilter(this,R.layout.search_filter_element_recyclerview,filterList);
        recyclerFilter.setAdapter(adapterRecyclerFilter);

        recyclerSearchRoom = findViewById(R.id.recycler_search_room);

        btnsSubmit = findViewById(R.id.btn_submit);
        btnsSubmit.setOnClickListener(this);
        //Ẩn nút bấm lần đầu khởi tạo
        btnsSubmit.setVisibility(View.GONE);

        btnDeleteAllFilter = findViewById(R.id.btn_delete_all_filter);
        btnDeleteAllFilter.setOnClickListener(this);

        txtCancel = findViewById(R.id.txt_cancel);
        txtCancel.setOnClickListener(this);

    }
}