package com.example.tingtongapp.Model;

import androidx.annotation.NonNull;

import com.example.tingtongapp.ClassOther.ConvenientFilter;
import com.example.tingtongapp.ClassOther.GenderFilter;
import com.example.tingtongapp.ClassOther.PriceFilter;
import com.example.tingtongapp.ClassOther.TypeFilter;
import com.example.tingtongapp.ClassOther.myFilter;
import com.example.tingtongapp.Controller.Interfaces.ISearchRoomModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchRoomModel {
    String district;
    DatabaseReference nodeRoot;

    private GenderFilter genderFilter;
    private PriceFilter priceFilter;
    private TypeFilter typeFilter;
    private List<ConvenientFilter> convenientFilterList;

    public SearchRoomModel(String district,List<myFilter> filterList){
        // Lọc ra từng loại filter
        genderFilter = null;
        priceFilter = null;
        typeFilter = null;
        convenientFilterList = new ArrayList<ConvenientFilter>();

        for(myFilter dataFilter : filterList){
            if(dataFilter instanceof GenderFilter){
                genderFilter =(GenderFilter) dataFilter;
            }
            if(dataFilter instanceof PriceFilter){
                priceFilter =(PriceFilter) dataFilter;
            }
            if(dataFilter instanceof TypeFilter){
                typeFilter =(TypeFilter) dataFilter;
            }
            if(dataFilter instanceof ConvenientFilter){
                convenientFilterList.add((ConvenientFilter) dataFilter);
            }
        }

        this.district = district;
        nodeRoot = FirebaseDatabase.getInstance().getReference();
    }

    private DataSnapshot dataRoot;
    private ArrayList<Room> listSearchRoomsModel = new ArrayList<>();
    // Số lượng phòng đã duyệt khi load more.
    private int filterDataQuantity = 0;

    public void searchRoom(ISearchRoomModel searchRoomModelInterface, String currentRoomID, int quantityRoomsToLoad, int quantityRoomsLoaded){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataRoot = dataSnapshot;

                // Lấy số lượng search rooms mỗi lần click submit
//                getAllSearchRoom(dataRoot, currentRoomID, searchRoomModelInterface);
                getPartSearchRoom(dataRoot, currentRoomID, searchRoomModelInterface, quantityRoomsToLoad, quantityRoomsLoaded);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        if(dataRoot != null) {
            getPartSearchRoom(dataRoot, currentRoomID, searchRoomModelInterface, quantityRoomsToLoad, quantityRoomsLoaded);
        } else {
            nodeRoot.addListenerForSingleValueEvent(valueEventListener);
        }
    }

    private void getPartSearchRoom(DataSnapshot dataSnapshot, String currentRoomID, ISearchRoomModel searchRoomModelInterface, int quantityRoomsToLoad, int quantityRoomsLoaded) {

    }
}
