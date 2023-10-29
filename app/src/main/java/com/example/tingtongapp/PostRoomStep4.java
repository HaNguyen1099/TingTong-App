package com.example.tingtongapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;

public class PostRoomStep4 extends Fragment implements View.OnClickListener {
    Button btnNextStep4PostRoom;
    EditText edtNamePushRoom,edtDescribePushRoom;

    PostRoom postRoom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.post_room_step4, container, false);
        initControl(layout);

        //Lấy context của fragment
        postRoom = (PostRoom) getContext();
        return layout;
    }

    private void initControl(View view){
        edtNamePushRoom = view.findViewById(R.id.edt_name_push_room);
        edtDescribePushRoom = view.findViewById(R.id.edt_describe_push_room);

        btnNextStep4PostRoom =view.findViewById(R.id.btn_nextStep4_post_room);
        btnNextStep4PostRoom.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

    }
}
