package com.example.tingtongapp.Controller.Interfaces;

import com.example.tingtongapp.Model.RoomModel;

public interface ISearchRoomModel {
    public void sendDataRoom(RoomModel roomModel, boolean ishadFound);
    public void setProgressBarLoadMore();
    public void setQuantityTop(int quantity);
}
