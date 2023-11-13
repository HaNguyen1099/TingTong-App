package com.example.tingtongapp.Model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.tingtongapp.Controller.Interfaces.IInfoOfAllRoomUser;
import com.example.tingtongapp.Controller.Interfaces.IMainRoomModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RoomModel implements Parcelable {
    private String idRoom, title, description, address, typeOfRoom, rentingPrice, timeCreated, owner, conditionRoom;
    private int acreageRoom, amountOfPeople, lengthRoom, widthRoom;
    private LocalDate dateAdded;
    private ImageRoomModel imagesRoom;
    private Map<String, Boolean> listServicesRoom = new LinkedHashMap<>();

    //id để generate từ firebase
    private String typeID;

    private double rentalCosts;

    //Chủ phòng trọ
    private UserModel roomOwner;
    private String no, county, street, ward, city;
    private DatabaseReference nodeRoot;

    private DataSnapshot dataRoot;
    private DataSnapshot dataNode;

    private List<String> listRoomsID = new ArrayList<>();
    private List<RoomPriceModel> listRoomPrice;
    private long currentNumber, maxNumber;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public RoomModel(){
        imagesRoom = new ImageRoomModel();
        initListServicesRoom();
        nodeRoot = FirebaseDatabase.getInstance().getReference();
        dateAdded = LocalDate.now();
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

    public List<String> getListRoomsID() {
        return listRoomsID;
    }

    public List<RoomPriceModel> getListRoomPrice() {
        return listRoomPrice;
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

    public void setListRoomsID(List<String> listRoomsID) {
        this.listRoomsID = listRoomsID;
    }

    public void setListRoomPrice(List<RoomPriceModel> listRoomPrice) {
        this.listRoomPrice = listRoomPrice;
    }

    public void setCurrentNumber(long currentNumber) {
        this.currentNumber = currentNumber;
    }

    public void setMaxNumber(long maxNumber) {
        this.maxNumber = maxNumber;
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
        listRoomPrice = new ArrayList<>();
        in.readTypedList(listRoomPrice, RoomPriceModel.CREATOR);

        int size = in.readInt();
        listServicesRoom = new LinkedHashMap<String, Boolean>(size);
        for(int i = 0; i < size; i++) {
            String key = in.readString();
            Boolean value = in.readInt() != 0;
            listServicesRoom.put(key, value);
        }

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
        dest.writeTypedList(listRoomPrice);
        dest.writeInt(listServicesRoom.size());
        for(Map.Entry<String, Boolean> entry : listServicesRoom.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeInt(entry.getValue() ? 1 : 0);
        }

        dest.writeLong(currentNumber);
        dest.writeLong(maxNumber);
        dest.writeString(typeID);
        dest.writeDouble(rentalCosts);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getDateAdded(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = dateAdded.format(formatter);

        return formattedDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void infoOfAllRoomOfUser(String UID, IInfoOfAllRoomUser iInfoOfAllRoomUser) {
        // truy vấn đến csdl và lọc các phòng của từng người dùng
        Query nodeRoomOrderbyUserID = nodeRoot.child("Room")
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

    public void getPartSpecialListRoom(IMainRoomModel mainRoomModelInterface,
                                       int quantityRoomToLoad, int quantityRoomLoaded) {
        int i = 0;
        // Chạy từ cuối list đến đầu list (list truyền vào đã sắp xếp theo thời gian)
        for (String RoomID : listRoomsID) {
            // Nếu đã lấy đủ số lượng rooms tiếp theo thì ra khỏi vòng lặp
            if (i == quantityRoomToLoad) {
                break;
            }
            // Bỏ qua những room đã load
            if (i < quantityRoomLoaded) {
                i++;
                continue;
            }
            i++;
            //Duyệt vào room cần lấy dữ liệu
            DataSnapshot dataSnapshotValueRoom = dataRoot.child("Room").child(RoomID);
            //Lấy ra giá trị ép kiểu qua kiểu RoomModel
            RoomModel roomModel = dataSnapshotValueRoom.getValue(RoomModel.class);
            roomModel.setIdRoom(RoomID);

            //Set loại phòng trọ
            String tempType = dataRoot.child("RoomTypes")
                    .child(roomModel.getTypeID())
                    .getValue(String.class);

            roomModel.setTypeOfRoom(tempType);

            //Thêm ảnh
            DataSnapshot imagesSnapshot = dataSnapshotValueRoom.child("imagesRoom");
            ImageRoomModel imageRoomModel = new ImageRoomModel();
            for (DataSnapshot imageSnapshot : imagesSnapshot.getChildren()) {
                String imageUrl = imageSnapshot.getValue(String.class);
                imageRoomModel.addImageUrl(imageUrl);
            }
            roomModel.setImagesRoom(imageRoomModel);

            // Thêm danh sách dịch vụ
            DataSnapshot servicesSnapshot = dataSnapshotValueRoom.child("listServicesRoom");
            Map<String, Boolean> servicesMap = new LinkedHashMap<>();
            for (DataSnapshot serviceSnapshot : servicesSnapshot.getChildren()) {
                String serviceName = serviceSnapshot.getKey();
                Boolean serviceValue = serviceSnapshot.getValue(Boolean.class);
                servicesMap.put(serviceName, serviceValue);
            }
            roomModel.setListServicesRoom(servicesMap);

            //Thêm danh sách giá của phòng trọ

            DataSnapshot dataSnapshotRoomPrice = dataRoot.child("RoomPrice").child(RoomID);
            List<RoomPriceModel> tempRoomPriceList = new ArrayList<RoomPriceModel>();
            //Duyệt tất cả các giá trị trong node tương ứng
            for (DataSnapshot valueRoomPrice : dataSnapshotRoomPrice.getChildren()) {
                String roomPriceId = valueRoomPrice.getKey();
                double price = valueRoomPrice.getValue(double.class);

                if (roomPriceId.equals("IDRPT4")) {
                    continue;
                }
                RoomPriceModel roomPriceModel = dataRoot.child("RoomPriceType").child(roomPriceId).getValue(RoomPriceModel.class);
                roomPriceModel.setRoomPriceID(roomPriceId);
                roomPriceModel.setPrice(price);

                tempRoomPriceList.add(roomPriceModel);
            }

            roomModel.setListRoomPrice(tempRoomPriceList);


            //Thêm thông tin chủ sở hữu cho phòng trọ
            UserModel tempUser = dataRoot.child("Users").child(roomModel.getOwner()).getValue(UserModel.class);
            tempUser.setUserID(roomModel.getOwner());
            roomModel.setRoomOwner(tempUser);

            //End thêm thông tin chủ sở hữu cho phòng trọ

            //Kích hoạt interface
            mainRoomModelInterface.getListMainRoom(roomModel);
        }

        // Ẩn progress bar load more.
        mainRoomModelInterface.setProgressBarLoadMore();
    }

    public void ListRoomUser(final IMainRoomModel mainRoomModelInterface, String userID, int quantityRoomToLoad, int quantityRoomLoaded) {
        Query nodeRoomUser = nodeRoot.child("Room")
                .orderByChild("owner")
                .equalTo(userID);

        // Tạo listen cho nodeRoomUser.
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataNode = dataSnapshot;

                // Tạo listen cho nodeRoot.
                ValueEventListener valueSpecialListRoomEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataRoot = dataSnapshot;

                        // set view
                        mainRoomModelInterface.setQuantityTop(listRoomsID.size());

                        //Thêm dữ liệu và gửi về lại UI
                        getPartSpecialListRoom(mainRoomModelInterface, quantityRoomToLoad, quantityRoomLoaded);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };

                //Gán sự kiện listen cho nodeRoot
                nodeRoot.addListenerForSingleValueEvent(valueSpecialListRoomEventListener);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        if (dataNode != null) {
            if (dataRoot != null) {
                //Thêm dữ liệu và gửi về lại UI
                getPartSpecialListRoom(mainRoomModelInterface, quantityRoomToLoad, quantityRoomLoaded);
            }
        } else {
            //Gán sự kiện listen cho nodeRoomUser
            nodeRoomUser.addListenerForSingleValueEvent(valueEventListener);
        }
    }
}