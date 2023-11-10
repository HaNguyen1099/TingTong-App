package com.example.tingtongapp.Controller;

import android.content.Context;
import android.widget.TextView;

import com.example.tingtongapp.Controller.Interfaces.IInfoOfAllRoomUser;
import com.example.tingtongapp.Model.RoomModel;

public class RoomManagementControlller {
    RoomModel roomModel;
    Context context;

    public RoomManagementControlller(Context context){
        this.context = context;
        roomModel = new RoomModel();
    }

    public void loadQuantityInfo(String UID, TextView txtQuantityRoom){
        IInfoOfAllRoomUser iInfoOfAllRoomUser = new IInfoOfAllRoomUser() {
            @Override
            public void sendQuantity(int value) {
                txtQuantityRoom.setText(String.valueOf(value));
            }
        };
        roomModel.infoOfAllRoomOfUser(UID,iInfoOfAllRoomUser);
    }
}
