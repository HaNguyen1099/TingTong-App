package com.example.tingtongapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class RoomModel implements Parcelable {
    private String idRoom, title, description, location, typeOfRoom, rentingPrice;
    private int acreageRoom, amountOfPeople, lengthRoom, widthRoom;
    private ImageRoomModel imagesRoom;
    private Map<String, Boolean> listServicesRoom = new LinkedHashMap<>();

    public RoomModel(){
        imagesRoom = new ImageRoomModel();
        initListServicesRoom();
    }

    public void initListServicesRoom(){
        listServicesRoom.put("Tự do", false);
        listServicesRoom.put("Giường", false);
        listServicesRoom.put("Tủ lạnh", false);
        listServicesRoom.put("Máy giặt", false);
        listServicesRoom.put("Wifi", false);

        listServicesRoom.put("Tủ quần áo", false);
        listServicesRoom.put("Điều hóa", false);
        listServicesRoom.put("Nóng lạnh", false);
        listServicesRoom.put("An ninh", false);
        listServicesRoom.put("Chỗ để xe", false);
    }

    public void setListServicesRoom(Map<String, Boolean> userInput){
        for (Map.Entry<String, Boolean> entry : userInput.entrySet()) {
            if (listServicesRoom.containsKey(entry.getKey())) {
                listServicesRoom.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public ArrayList<String> getListServicesAvailable(){
        ArrayList<String> listServiceAvailable = new ArrayList<>();
        for (Map.Entry<String, Boolean> service : listServicesRoom.entrySet()) {
            if (Boolean.TRUE.equals(service.getValue())) {
                listServiceAvailable.add(service.getKey());
            }
        }
        return listServiceAvailable;
    }

    public static final Creator<RoomModel> CREATOR = new Creator<RoomModel>() {
        @Override
        public RoomModel createFromParcel(Parcel in) {
            return new RoomModel(in);
        }

        @Override
        public RoomModel[] newArray(int size) {
            return new RoomModel[size];
        }
    };

    protected RoomModel(Parcel in) {
        idRoom = in.readString();
        title = in.readString();
        description = in.readString();
        location = in.readString();
        typeOfRoom = in.readString();
        rentingPrice = in.readString();
        acreageRoom = in.readInt();
        amountOfPeople = in.readInt();
        lengthRoom = in.readInt();
        widthRoom = in.readInt();
        imagesRoom = in.readParcelable(ImageRoomModel.class.getClassLoader());
    }

    public ImageRoomModel getImages() {
        return imagesRoom;
    }

    public void setImages(ImageRoomModel images) {
        this.imagesRoom = images;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idRoom);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(location);
        dest.writeString(typeOfRoom);
        dest.writeString(rentingPrice);
        dest.writeInt(acreageRoom);
        dest.writeInt(amountOfPeople);
        dest.writeInt(lengthRoom);
        dest.writeInt(widthRoom);
        dest.writeParcelable(imagesRoom, flags);
    }
}
