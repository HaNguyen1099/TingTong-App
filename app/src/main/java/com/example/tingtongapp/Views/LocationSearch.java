package com.example.tingtongapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tingtongapp.Controller.locationSearchController;
import com.example.tingtongapp.R;

public class LocationSearch extends AppCompatActivity implements TextWatcher {
    ImageView ivBack;
    LinearLayout LinearContainSuggestions;
    EditText edTSearch;
    Button btnFilter;
    ImageView ivOk;
    RecyclerView recyclerSuggestion,recyclerHistorySearch;
    locationSearchController SearchController;
    boolean isSearchViewCall;
    String stringFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_search);
        initControl();

        // Intent là một cơ chế để truyền dữ liệu giữa các thành phần khác nhau của ứng dụng Android
        Intent intent = getIntent();
        if(intent != null){
            // Kiểm tra xem giá trị được truyền từ Intent với khóa là searchView.REQUEST có bằng searchView.REQUEST_DISTRICT không
            if(intent.getIntExtra(searchView.REQUEST,0) == searchView.REQUEST_DISTRICT){
                isSearchViewCall=true;
                Log.d("check3", "onCreate: ");
            }
            else {
                isSearchViewCall=false;
                Log.d("check3", "onCreate2: ");
            }
        }
        // locationSearchController là một lớp được tạo ra để quản lý logic và tương tác với dữ liệu trong màn hình location_search
        SearchController = new locationSearchController(this);
    }

    private void initControl(){
        edTSearch = findViewById(R.id.edT_search);
        edTSearch.addTextChangedListener(this);
        LinearContainSuggestions = findViewById(R.id.Linear_contain_suggestions);
        recyclerSuggestion = findViewById(R.id.recycler_suggestion);
        recyclerHistorySearch = findViewById(R.id.recycler_history_search);
        ivOk = findViewById(R.id.iv_ok);
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnFilter = findViewById(R.id.btn_filter);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edTSearch.setText("");
            }
        });
    }

    private void getDataFromControl(){
        //Lấy chuỗi từ user
        stringFilter = edTSearch.getText().toString();
        //Chuẩn hóa chuỗi

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        //Lấy chuỗi từ user và chuẩn hóa
        getDataFromControl();
        //Gọi controller
        SearchController.loadDistrictInData(recyclerSuggestion, stringFilter, isSearchViewCall);
    }
}