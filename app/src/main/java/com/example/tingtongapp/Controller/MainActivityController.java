package com.example.tingtongapp.Controller;

import android.content.Context;
import android.widget.GridView;

import com.example.tingtongapp.Adapters.AdapterLocation;
import com.example.tingtongapp.Controller.Interfaces.ILocationModel;
import com.example.tingtongapp.Model.LocationModel;
import com.example.tingtongapp.Model.Room;
import com.example.tingtongapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivityController {
    Context context;
    LocationModel locationModel;

    public MainActivityController(Context context) {
        this.context = context;
        this.locationModel = new LocationModel();
    }

    public void loadTopLocation(GridView grVLocation) {
        List<LocationModel> datalocation = new ArrayList<LocationModel>();

        AdapterLocation adapter = new AdapterLocation(context, R.layout.row_element_grid_view_locaion, datalocation);
        grVLocation.setAdapter(adapter);

        //Create new interface to send data from Model
        ILocationModel iLocationModel = new ILocationModel() {
            @Override
            public void getListTopRoom(List<LocationModel> topLocation) {
                datalocation.addAll(topLocation);
                adapter.notifyDataSetChanged();
            }
        };

        //Gọi hàm lấy dữ liệu và truyền vào interface
        locationModel.topLocation(iLocationModel);
    }
}
