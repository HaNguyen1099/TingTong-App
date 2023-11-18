package com.example.tingtongapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.tingtongapp.Model.UserModel;

import com.example.tingtongapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountView extends Fragment implements View.OnClickListener {

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

    // inflate: chuyển đổi tệp layout XML thành đối tượng View
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.account_view, container, false);
        initControl();
        return layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        displayUserInfo();
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

    private void displayUserInfo(){
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        TextView usernameTv = (TextView) layout.findViewById(R.id.tv_hi2);

        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object receivedData = dataSnapshot.getValue();
                Log.d("Firebase", "Received data: " + receivedData);
                if (dataSnapshot.exists()) {
                    UserModel currentUser = dataSnapshot.getValue(UserModel.class);
                    if (currentUser != null) {
                        String userName = currentUser.getName();
                        usernameTv.setText(userName);
                    } else {
                        usernameTv.setText("User");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý lỗi khi truy cập cơ sở dữ liệu
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_edit_account){
            Intent intent = new Intent(getContext(), PersonalPage.class);
            startActivity(intent);
        }
        else if (id == R.id.btn_my_Room) {
            Intent intent1 = new Intent(getContext(), RoomManagement.class);
            startActivity(intent1);
        }
        else if(id == R.id.btn_logout){
            FirebaseAuth.getInstance().signOut();
            Intent logout = new Intent(requireActivity(), LoginView.class);
            startActivity(logout);
        }
    }
}