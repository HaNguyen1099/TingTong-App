package com.example.tingtongapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.tingtongapp.R;

public class Account_View extends Fragment implements View.OnClickListener {

    private Button btnEditAccount;
    private Button btnMyRoom;
    private Button btnMyFavoriteRoom;
    private Button btnMyFindRoom;
    private Button btnLogout;
//    FirebaseAuth firebaseAuth;
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
        if (id == R.id.btn_edit_account){
            Intent intent = new Intent(getContext(), personalPage.class);
            startActivity(intent);
        }
        else if (id == R.id.btn_my_Room) {
            Intent intent1 = new Intent(getContext(), roomManagementModel.class);
            startActivity(intent1);
        }

//            case R.id.btn_logout:
//                //Khởi tạo firebaseAuth
//                firebaseAuth = FirebaseAuth.getInstance();
//                //Text Đăng xuất
//                firebaseAuth.signOut();
//                getActivity().finish();
    }
}