package com.example.tingtongapp.Model;

import androidx.annotation.NonNull;

import com.example.tingtongapp.Controller.Interfaces.ILocationModel;
import com.example.tingtongapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    //Hàm trả về top location
    public void topLocation(ILocationModel locationModelInterface){
        DatabaseReference nodeRoot = FirebaseDatabase.getInstance().getReference();

        DatabaseReference nodeLocationRoom=nodeRoot.child("LocationRoom");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            // Init list location in Ha noi
                List<LocationModel> listLocationModel = new ArrayList<LocationModel>();
                listLocationModel.add(new LocationModel(R.drawable.qhd,"Quận Hà Đông",0));
                listLocationModel.add(new LocationModel(R.drawable.qhm,"Quận Hoàng Mai",0));
                listLocationModel.add(new LocationModel(R.drawable.qlb,"Quận Long Biên",0));
                listLocationModel.add(new LocationModel(R.drawable.qtx,"Quận Thanh Xuân",0));
                listLocationModel.add(new LocationModel(R.drawable.qbtl,"Quận Bắc Từ Liêm",0));
                listLocationModel.add(new LocationModel(R.drawable.qbd,"Quận Ba Đình",0));
                listLocationModel.add(new LocationModel(R.drawable.qcg,"Quận Cầu Giấy",0));
                listLocationModel.add(new LocationModel(R.drawable.qdd,"Quận Đống Đa",0));
                listLocationModel.add(new LocationModel(R.drawable.qhbt,"Quận Hai Bà Trưng",0));
                listLocationModel.add(new LocationModel(R.drawable.qhk,"Quận Hoàn Kiếm",0));
                listLocationModel.add(new LocationModel(R.drawable.qth,"Quận Tây Hồ",0));
                listLocationModel.add(new LocationModel(R.drawable.qntl,"Quận Nam Từ Liêm",0));

                //Lặp và thêm số lượng phòng vào trong list
                for(int i=0;i<12;i++){
                    DataSnapshot DStemp = dataSnapshot.child(listLocationModel.get(i).getCounty());
                    for (DataSnapshot dataWard:DStemp.getChildren()){
                        for(DataSnapshot dataStreet:dataWard.getChildren()){
                            int currentNumber = listLocationModel.get(i).getRoomNumber();
                            listLocationModel.get(i).setRoomNumber((int) (currentNumber+dataStreet.getChildrenCount()));
                        }
                    }
                }

                //Sắp xếp lại list theo thứ tự tăng dần của số phòng trọ
                Collections.sort(listLocationModel);

                //Tạo mới data và gửi
                List<LocationModel> dataSend= new ArrayList<LocationModel>();
                dataSend.add(listLocationModel.get(11));
                dataSend.add(listLocationModel.get(10));
                dataSend.add(listLocationModel.get(9));

                //Kích hoạt interface
                locationModelInterface.getListTopRoom(dataSend);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        nodeLocationRoom.addListenerForSingleValueEvent(valueEventListener);
    }

    @Override
    public int compareTo(LocationModel o) {
        return this.getRoomNumber() - o.getRoomNumber();
    }
}
