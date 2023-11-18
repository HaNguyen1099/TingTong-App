package com.example.tingtongapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Room implements Parcelable {
    private String idRoom = "n", title = "n", description = "n", address = "n", typeOfRoom = "n", rentingPrice = "n", timeCreated = "n", owner, conditionRoom = "Còn", dateAdded = "15/11/2023";
    private int amountOfPeople = 0, lengthRoom = 0, widthRoom = 0, electricityPrice = 0, waterPrice = 0, internetPrice = 0, parkingFee = 0;
    private String imageUrlNew = "";

    public void setImageUrlNew(String url){
        this.imageUrlNew += (url + " ");
    }

    public String getImageUrlNew(){
        return this.imageUrlNew;
    }

    public ArrayList<String> getAllImagesRoom(){
        ArrayList<String> img = new ArrayList<>();
        for(String i : imageUrlNew.split("\\s+")){
            img.add(i);
        }
        return img;
    }

    private UserModel roomOwner = new UserModel();
    private String listServices = "";
    private String idOwner = "";

    private void setIdOwner(){
        this.idOwner = roomOwner.getUserID();
    }

    public String getIdOwner(){
        return idOwner;
    }

    // Constructor
    public Room(){
        setDateAdded();
    }

    public String getIdRoom(){
        return idRoom;
    }

    public void setListServices(String input){
        this.listServices = input;
    }

    public String getListServices(){
        return listServices;
    }

    private void setDateAdded(){
        LocalDate currentDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = currentDate.format(formatter);
            this.dateAdded = formattedDate;
        }
    }

    public String getDateAdded(){
        return dateAdded;
    }

    public void setPricePostRoom(int electricityPrice, int waterPrice, int internetPrice, int parkingFee){
        this.electricityPrice = electricityPrice;
        this.waterPrice = waterPrice;
        this.internetPrice = internetPrice;
        this.parkingFee = parkingFee;
    }

    public int getElectricityPrice(){
        return electricityPrice;
    }

    public int getWaterPrice(){
        return waterPrice;
    }

    public int getInternetPrice(){
        return internetPrice;
    }

    public int getParkingFee(){
        return parkingFee;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getTypeOfRoom() {
        return typeOfRoom;
    }

    public String getRentingPrice() {
        return rentingPrice;
    }

    public String getConditionRoom() {
        return conditionRoom;
    }

    public int getAcreageRoom() {
        return lengthRoom * widthRoom;
    }

    public int getAmountOfPeople() {
        return amountOfPeople;
    }

    public int getLengthRoom() {
        return lengthRoom;
    }

    public int getWidthRoom() {
        return widthRoom;
    }

    public UserModel getRoomOwner() {
        return roomOwner;
    }

    public String getCity() {
        String s[] = this.address.split(", ");
        return s[s.length - 1];
    }

    public String getDistrict() {
        String s[] = this.address.split(", ");
        return s[s.length - 2];
    }

    public String getWard() {
        String s[] = this.address.split(", ");
        return s[s.length - 3];
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTypeOfRoom(String typeOfRoom) {
        this.typeOfRoom = typeOfRoom;
    }

    public void setRentingPrice(String rentingPrice) {
        this.rentingPrice = rentingPrice;
    }

    public void setConditionRoom(String conditionRoom) {
        this.conditionRoom = conditionRoom;
    }

    public void setAmountOfPeople(int amountOfPeople) {
        this.amountOfPeople = amountOfPeople;
    }

    public void setLengthRoom(int lengthRoom) {
        this.lengthRoom = lengthRoom;
    }

    public void setWidthRoom(int widthRoom) {
        this.widthRoom = widthRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public void setRoomOwner(UserModel roomOwner) {
        this.roomOwner = roomOwner;
        setIdOwner();
    }

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };

    protected Room(Parcel in) {
        idRoom = in.readString();
        title = in.readString();
        description = in.readString();
        address = in.readString();
        typeOfRoom = in.readString();
        rentingPrice = in.readString();
        timeCreated = in.readString();
        owner = in.readString();
        conditionRoom = in.readString();
        dateAdded = in.readString();

        amountOfPeople = in.readInt();
        lengthRoom = in.readInt();
        widthRoom = in.readInt();
        electricityPrice = in.readInt();
        waterPrice = in.readInt();
        internetPrice = in.readInt();
        parkingFee = in.readInt();
        listServices = in.readString();

        // Read UserModel from the Parcel
        roomOwner = in.readParcelable(UserModel.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idRoom);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(address);
        dest.writeString(typeOfRoom);
        dest.writeString(rentingPrice);
        dest.writeString(timeCreated);
        dest.writeString(owner);
        dest.writeString(conditionRoom);
        dest.writeString(dateAdded);

        dest.writeInt(amountOfPeople);
        dest.writeInt(lengthRoom);
        dest.writeInt(widthRoom);
        dest.writeInt(electricityPrice);
        dest.writeInt(waterPrice);
        dest.writeInt(internetPrice);
        dest.writeInt(parkingFee);
        dest.writeString(listServices);

        // Write UserModel to the Parcel
        dest.writeParcelable(roomOwner, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}