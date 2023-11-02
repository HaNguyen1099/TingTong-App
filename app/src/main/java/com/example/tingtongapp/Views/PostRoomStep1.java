package com.example.tingtongapp.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.tingtongapp.R;

public class PostRoomStep1 extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    private Spinner spnDistrictPushRoom, spnCityPushRoom, spnWardPushRoom;
    private EditText edtStreetPushRoom, edtNoPushRoom;
    private Button btnNextStep1PostRoom;
    private TextView txtChooseLocation;

    PostRoom postRoom;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.post_room_step1, container, false);
        initControl(layout);
        spnDistrictPushRoom.setOnItemSelectedListener(this);
        //Lấy ra activity hiện tại
        postRoom = (PostRoom) getContext();
        return layout;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String District = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void initControl(View view) {
        spnCityPushRoom = (Spinner) view.findViewById(R.id.spn_city_push_room);
        spnDistrictPushRoom = (Spinner) view.findViewById(R.id.spn_district_push_room);
        spnWardPushRoom = (Spinner) view.findViewById(R.id.spn_ward_push_room);
        edtStreetPushRoom = (EditText) view.findViewById(R.id.edt_street_push_room);
        edtNoPushRoom = (EditText) view.findViewById(R.id.edt_no_push_room);

        btnNextStep1PostRoom = (Button) view.findViewById(R.id.btn_nextStep1_post_room);
        btnNextStep1PostRoom.setOnClickListener(this);

        txtChooseLocation = view.findViewById(R.id.txt_choose_location);
        txtChooseLocation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
