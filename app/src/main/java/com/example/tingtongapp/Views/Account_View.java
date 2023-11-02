package com.example.tingtongapp.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewKt;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tingtongapp.R;

public class Account_View extends Fragment implements View.OnClickListener {

    private Button btnEditAccount;
    private Button btnMyRoom;
    private Button btnMyFavoriteRoom;
    private Button btnMyFindRoom;
    private Button btnLogout;

    View layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.account_view, container, false);
        initControl();
        return layout;
    }

    private void initControl() {
        btnEditAccount = (Button) layout.findViewById(R.id.btn_edit_account);
        btnMyRoom = layout.findViewById(R.id.btn_my_Room);

        btnEditAccount.setOnClickListener(this);
        btnMyRoom.setOnClickListener(this);

        btnMyFavoriteRoom = layout.findViewById(R.id.btn_my_favorite_room);
        btnMyFavoriteRoom.setOnClickListener(this);

        btnMyFindRoom = layout.findViewById(R.id.btn_my_find_room);
        btnMyFindRoom.setOnClickListener(this);

        btnLogout = layout.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
//        switch (id) {
//            case R.id.btn_edit_account:
//                Intent intent = new Intent(getContext(), personalPage.class);
//                startActivity(intent);
//                break;
//            case R.id.btn_my_Room:
//                Intent intent1 = new Intent(getContext(), roomManagementModel.class);
//                startActivity(intent1);
//                break;
//
//            case R.id.btn_my_favorite_room:
//                Intent intentFavoriteRooms = new Intent(getContext(), favoriteRoomsView.class);
//                startActivity(intentFavoriteRooms);
//                break;
//
//            case R.id.btn_my_find_room:
//                Intent intentMyFindRooms = new Intent(getContext(), FindRoomMine.class);
//                startActivity(intentMyFindRooms);
//                break;
//
//            case R.id.btn_logout:
//                //Khởi tạo firebaseAuth
//                firebaseAuth = FirebaseAuth.getInstance();
//                //Text Đăng xuất
//                firebaseAuth.signOut();
//                getActivity().finish();
//        }
    }
}