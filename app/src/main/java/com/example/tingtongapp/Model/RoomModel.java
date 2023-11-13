package com.example.tingtongapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.tingtongapp.Controller.Interfaces.IInfoOfAllRoomUser;
import com.example.tingtongapp.Controller.Interfaces.IMainRoomModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RoomModel implements Parcelable {
    private String idRoom, title, description, location, typeOfRoom, rentingPrice, timeCreated, owner;
    private int acreageRoom, amountOfPeople, lengthRoom, widthRoom;
    private ImageRoomModel imagesRoom;
    private Map<String, Boolean> listServicesRoom = new LinkedHashMap<>();

    //id để generate từ firebase
    private String typeID;

    private double rentalCosts;

    private long currentNumber, maxNumber;

    //Chủ phòng trọ
    private UserModel roomOwner;

    private String no, county, street, ward, city;
    private DatabaseReference nodeRoot;

    private DataSnapshot dataRoot;
    private DataSnapshot dataNode;

    private List<String> listRoomsID = new ArrayList<>();
    private List<RoomPriceModel> listRoomPrice;

    public RoomModel(){
        imagesRoom = new ImageRoomModel();
        initListServicesRoom();
        nodeRoot = FirebaseDatabase.getInstance().getReference();
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
        currentNumber = in.readLong();
        maxNumber = in.readLong();
        typeID = in.readString();
        location = in.readString();
        typeOfRoom = in.readString();
        rentingPrice = in.readString();
        rentalCosts = in.readDouble();
        timeCreated = in.readString();
        acreageRoom = in.readInt();
        amountOfPeople = in.readInt();
        lengthRoom = in.readInt();
        widthRoom = in.readInt();
        imagesRoom = in.readParcelable(ImageRoomModel.class.getClassLoader());
        roomOwner = in.readParcelable(UserModel.class.getClassLoader());
        no = in.readString();
        county = in.readString();
        street = in.readString();
        ward = in.readString();
        city = in.readString();
        listRoomPrice = new ArrayList<>();
        in.readTypedList(listRoomPrice, RoomPriceModel.CREATOR);
    }

    public String getIdRoom() {
        return idRoom;
    }
    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public UserModel getRoomOwner() {
        return roomOwner;
    }
    public void setRoomOwner(UserModel roomOwner) {
        this.roomOwner = roomOwner;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTypeID() {
        return typeID;
    }
    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getTypeOfRoom() {
        return typeOfRoom;
    }
    public void setTypeOfRoom(String typeOfRoom) {
        this.typeOfRoom = typeOfRoom;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String name) {
        this.title = name;
    }

    public String getTimeCreated() {
        return timeCreated;
    }
    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getNo() {
        return no;
    }
    public void setNo(String apartmentNumber) {
        this.no = apartmentNumber;
    }

    public String getCounty() {
        return county;
    }
    public void setCounty(String county) {
        this.county = county;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }
    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public ImageRoomModel getImages() {
        return imagesRoom;
    }
    public void setImages(ImageRoomModel images) {
        this.imagesRoom = images;
    }

    public List<RoomPriceModel> getListRoomPrice() {
        return listRoomPrice;
    }

    public void setListRoomPrice(List<RoomPriceModel> listRoomPrice) {
        this.listRoomPrice = listRoomPrice;
    }

    public long getMaxNumber() {
        return maxNumber;
    }
    public void setMaxNumber(long maxNumber) {
        this.maxNumber = maxNumber;
    }

    public double getRentalCosts() {
        return rentalCosts;
    }
    public void setRentalCosts(double rentalCosts) {
        this.rentalCosts = rentalCosts;
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
            roomModel.setImages(imageRoomModel);

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
