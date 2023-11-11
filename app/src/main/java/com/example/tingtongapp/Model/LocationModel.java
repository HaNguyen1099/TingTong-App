package com.example.tingtongapp.Model;

public class LocationModel implements Comparable<LocationModel> {
    private int image;
    private String county;
    private int roomNumber;

    public LocationModel(){
    }

    public LocationModel(int image,String county,int roomNumber){
        this.image = image;
        this.county = county;
        this.roomNumber = roomNumber;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public int compareTo(LocationModel locationModel) {
        return 0;
    }
}
