package com.example.tingtongapp.Controller.Interfaces;

import com.example.tingtongapp.Model.Room;

public interface ISearchRoomModel {
    public void sendDataRoom(Room roomModel, boolean ishadFound);
    public void setProgressBarLoadMore();
    public void setQuantityTop(int quantity);
}
