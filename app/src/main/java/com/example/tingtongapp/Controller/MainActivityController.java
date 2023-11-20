package com.example.tingtongapp.Controller;

import android.content.Context;
import android.widget.GridView;

import androidx.annotation.NonNull;

import com.example.tingtongapp.Adapters.AdapterLocation;
import com.example.tingtongapp.Model.LocationModel;
import com.example.tingtongapp.Model.Room;
import com.example.tingtongapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivityController {
    Context context;

    public MainActivityController(Context context) {
        this.context = context;
    }

    public void loadTopLocation(GridView grVLocation) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ListRoom");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Map<String, Integer> listDistrict = new HashMap<>();
                    String districts[] = context.getResources().getStringArray(R.array.ha_noi_districts);
                    for(String districtName : districts){
                        listDistrict.put(districtName, 0);
                    }

                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        try {
                            Room room = dataSnapshot.getValue(Room.class);
                            String districtName = room.getDistrict();
                            listDistrict.put(districtName, listDistrict.get(districtName) + 1);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    AdapterLocation adapterLocation = new AdapterLocation(context, listDistrict);
                    grVLocation.setAdapter(adapterLocation);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
