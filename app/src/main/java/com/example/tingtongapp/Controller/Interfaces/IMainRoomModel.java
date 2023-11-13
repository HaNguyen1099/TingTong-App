package com.example.tingtongapp.Controller.Interfaces;

import com.example.tingtongapp.Model.RoomModel;

public interface IMainRoomModel {
    public void getListMainRoom(RoomModel valueRoom);
    public void makeToast(String message);
    public void setButtonLoadMoreVerifiedRooms();
    public void setProgressBarLoadMore();
    public void setQuantityTop(int quantity);
    public void setQuantityLoadMore(int quantityLoaded);
}
