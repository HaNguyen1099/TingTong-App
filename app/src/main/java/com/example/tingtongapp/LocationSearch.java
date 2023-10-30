package com.example.tingtongapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class LocationSearch extends AppCompatActivity {
    ImageView ivBack;
    LinearLayout LinearContainSuggestions;
    EditText edTSearch;
    Button btnClear;
    RecyclerView recyclerSuggestion,recyclerHistorySearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_search);
        initControl();
    }
    private void initControl(){
        edTSearch = findViewById(R.id.edT_search);
        LinearContainSuggestions = findViewById(R.id.Linear_contain_suggestions);
        recyclerSuggestion = findViewById(R.id.recycler_suggestion);
        recyclerHistorySearch = findViewById(R.id.recycler_history_search);
        btnClear = findViewById(R.id.btn_clear);
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}