package com.example.tingtongapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.tingtongapp.Controller.Interfaces.IInfoOfAllRoomUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Room implements Parcelable {
    private String idRoom = "n", title = "n", description = "n", address = "n", typeOfRoom = "n", rentingPrice = "n", timeCreated = "n", owner, conditionRoom = "n", dateAdded = "15/11/2023";
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

    //id để generate từ firebase
    private String typeID = "ids";
    private double rentalCosts = 3.11;
    private UserModel roomOwner = new UserModel();
    private String no = "n", county = "n", street = "n", ward = "n", city = "n";
    private long currentNumber = 0, maxNumber = 0;
    private List<String> listRoomsID = new ArrayList<>();
    private List<RoomPriceModel> listRoomPrice = new ArrayList<>();
    private String listServices = "";
    private String idOwner = "";

    private void setIdOwner(){
        this.idOwner = roomOwner.getUserID();
    }

    public String getIdOwner(){
        return idOwner;
    }

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

    public String getTimeCreated() {
        return timeCreated;
    }

    public String getOwner() {
        return owner;
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

    public String getTypeID() {
        return typeID;
    }

    public double getRentalCosts() {
        return rentalCosts;
    }

    public long getCurrentNumber() {
        return currentNumber;
    }

    public long getMaxNumber() {
        return maxNumber;
    }

    public UserModel getRoomOwner() {
        return roomOwner;
    }

    public String getNo() {
        return no;
    }

    public String getCounty() {
        return county;
    }

    public String getStreet() {
        return street;
    }

    public String getWard() {
        return ward;
    }

    public String getCity() {
        return city;
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

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public void setRentalCosts(double rentalCosts) {
        this.rentalCosts = rentalCosts;
    }

    public void setRoomOwner(UserModel roomOwner) {
        this.roomOwner = roomOwner;
        setIdOwner();
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCurrentNumber(long currentNumber) {
        this.currentNumber = currentNumber;
    }

    public void setMaxNumber(long maxNumber) {
        this.maxNumber = maxNumber;
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
        typeID = in.readString();
        rentalCosts = in.readDouble();

        // Read UserModel from the Parcel
        roomOwner = in.readParcelable(UserModel.class.getClassLoader());

        no = in.readString();
        county = in.readString();
        street = in.readString();
        ward = in.readString();
        city = in.readString();
        currentNumber = in.readLong();
        maxNumber = in.readLong();
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
        dest.writeString(typeID);
        dest.writeDouble(rentalCosts);

        // Write UserModel to the Parcel
        dest.writeParcelable(roomOwner, flags);

        dest.writeString(no);
        dest.writeString(county);
        dest.writeString(street);
        dest.writeString(ward);
        dest.writeString(city);
        dest.writeLong(currentNumber);
        dest.writeLong(maxNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void infoOfAllRoomOfUser(String UID, IInfoOfAllRoomUser iInfoOfAllRoomUser) {
        // truy vấn đến csdl và lọc các phòng của từng người dùng
        Query nodeRoomOrderbyUserID = FirebaseDatabase.getInstance().getReference().child("Room")
                .orderByChild("owner")
                .equalTo(UID);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Lấy ra tổng số phòng
                int CountRoom = (int) dataSnapshot.getChildrenCount();
                //Gửi thông tin tổng số phòng về UI
                iInfoOfAllRoomUser.sendQuantity(CountRoom);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        nodeRoomOrderbyUserID.addListenerForSingleValueEvent(valueEventListener);
    }


}