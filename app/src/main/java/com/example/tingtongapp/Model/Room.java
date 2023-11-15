package com.example.tingtongapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Room implements Parcelable {
    private String idRoom = "n", title = "n", description = "n", address = "n", typeOfRoom = "n", rentingPrice = "n", timeCreated = "n", owner, conditionRoom = "n", dateAdded = "15/11/2023";
    private int acreageRoom = 0, amountOfPeople = 0, lengthRoom = 0, widthRoom = 0, electricityPrice = 0, waterPrice = 0, internetPrice = 0, parkingFee = 0;
    private ImageRoomModel imagesRoom = new ImageRoomModel();
    private Map<String, Boolean> listServicesRoom = new LinkedHashMap<>();

    //id để generate từ firebase
    private String typeID = "ids";
    private double rentalCosts = 3.11;
    private UserModel roomOwner = new UserModel();
    private String no = "n", county = "n", street = "n", ward = "n", city = "n";
    private long currentNumber = 0, maxNumber = 0;
    private List<String> listRoomsID = new ArrayList<>();
    private List<RoomPriceModel> listRoomPrice = new ArrayList<>();

    public Room(){
        initListServicesRoom();
    }

    public String getIdRoom(){
        return idRoom;
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

    public void setDateAdded(){
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
        return acreageRoom;
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

    public ImageRoomModel getImagesRoom() {
        return imagesRoom;
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

    public void setAcreageRoom(int acreageRoom) {
        this.acreageRoom = acreageRoom;
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

    public void setImagesRoom(ImageRoomModel imagesRoom) {
        this.imagesRoom = imagesRoom;
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

    protected Room(Parcel in) {
        idRoom = in.readString();
        title = in.readString();
        typeOfRoom = in.readString();
        conditionRoom = in.readString();
        address = in.readString();
        rentingPrice = in.readString();
        acreageRoom = in.readInt();
        amountOfPeople = in.readInt();
        lengthRoom = in.readInt();
        widthRoom = in.readInt();
        description = in.readString();
        roomOwner = in.readParcelable(UserModel.class.getClassLoader());
        imagesRoom = in.readParcelable(ImageRoomModel.class.getClassLoader());
        timeCreated = in.readString();
        typeID = in.readString();
        no = in.readString();
        county = in.readString();
        street = in.readString();
        ward = in.readString();
        city = in.readString();

        currentNumber = in.readLong();
        maxNumber = in.readLong();
        typeID = in.readString();
        rentalCosts = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idRoom);
        dest.writeString(title);
        dest.writeString(typeOfRoom);
        dest.writeString(conditionRoom);
        dest.writeString(address);
        dest.writeString(rentingPrice);
        dest.writeInt(acreageRoom);
        dest.writeInt(amountOfPeople);
        dest.writeInt(lengthRoom);
        dest.writeInt(widthRoom);
        dest.writeString(description);
        dest.writeParcelable(roomOwner, flags);
        dest.writeParcelable(imagesRoom, flags);
        dest.writeString(timeCreated);
        dest.writeString(typeID);
        dest.writeString(no);
        dest.writeString(county);
        dest.writeString(street);
        dest.writeString(ward);
        dest.writeString(city);

        dest.writeLong(currentNumber);
        dest.writeLong(maxNumber);
        dest.writeString(typeID);
        dest.writeDouble(rentalCosts);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}