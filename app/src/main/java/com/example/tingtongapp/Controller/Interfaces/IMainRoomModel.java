package com.example.tingtongapp.Controller.Interfaces;

import com.example.tingtongapp.Model.Room;

public interface IMainRoomModel {
    public void getListMainRoom(Room valueRoom);
    public void makeToast(String message);
    public void setButtonLoadMoreVerifiedRooms();
    public void setProgressBarLoadMore();
    public void setQuantityTop(int quantity);
    public void setQuantityLoadMore(int quantityLoaded);
}
