package com.example.tingtongapp.Controller;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tingtongapp.Adapters.AdapterRecyclerMyRoom;
import com.example.tingtongapp.Controller.Interfaces.IMainRoomModel;
import com.example.tingtongapp.Model.LocationModel;
import com.example.tingtongapp.Model.RoomModel;
import com.example.tingtongapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivityController {
    Context context;
    RoomModel roomModel;
    LocationModel locationModel;
    String UID;

    // khai báo các biến liên quan tới load more.
    int quantityGridMainRoomLoaded = 0;
    int quantityGridMainRoomEachTime = 4;
    int quantityVerifiedRoomLoaded = 0;
    int quantityVerifiedRoomEachTime = 4;

    public MainActivityController(Context context, String UID) {
        this.context = context;
        this.roomModel = new RoomModel();
        this.locationModel = new LocationModel();
        this.UID = UID;
    }

    public void ListRoomUser(String userID, RecyclerView recyclerMainRoom, TextView txtQuantity, ProgressBar progressBarMyRooms,
                             LinearLayout lnLtQuantityTopMyRooms, NestedScrollView nestedScrollMyRoomsView,
                             ProgressBar progressBarLoadMoreMyRooms) {
        final List<RoomModel> roomModelList = new ArrayList<>();

        //Tạo layout cho danh sách trọ tìm kiếm nhiều nhất
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerMainRoom.setLayoutManager(layoutManager);

        //Tạo adapter cho recycle view
        AdapterRecyclerMyRoom adapterRecyclerMyRoom;
        adapterRecyclerMyRoom = new AdapterRecyclerMyRoom(context, roomModelList, R.layout.room_user_list_view);

        //Cài adapter cho recycle
        recyclerMainRoom.setAdapter(adapterRecyclerMyRoom);
        ViewCompat.setNestedScrollingEnabled(recyclerMainRoom, false);

        IMainRoomModel iMainRoomModel = new IMainRoomModel() {
            @Override
            public void getListMainRoom(RoomModel valueRoom) {
                // Load ảnh nén
//                valueRoom.setCompressionImageFit(Picasso.get().load(valueRoom.getCompressionImage()).fit());

                //Thêm vào trong danh sách trọ
                roomModelList.add(valueRoom);

                //Thông báo là đã có thêm dữ liệu
                adapterRecyclerMyRoom.notifyDataSetChanged();
            }

            @Override
            public void makeToast(String message) {

            }

            @Override
            public void setButtonLoadMoreVerifiedRooms() {

            }

            @Override
            public void setProgressBarLoadMore() {
                progressBarMyRooms.setVisibility(View.GONE);
                progressBarLoadMoreMyRooms.setVisibility(View.GONE);
            }

            @Override
            public void setQuantityTop(int quantity) {
                lnLtQuantityTopMyRooms.setVisibility(View.VISIBLE);
                // Hiển thị kết quả trả về
                txtQuantity.setText(quantity + "");
            }

            @Override
            public void setQuantityLoadMore(int quantityLoaded) {

            }

        };
        nestedScrollMyRoomsView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
                // check xem có scroll đc ko
                View child = nestedScrollView.getChildAt(0);
                if (child != null) {
                    int childHeight = child.getHeight();
                    // Nếu scroll đc
                    if (nestedScrollView.getHeight() < childHeight + nestedScrollView.getPaddingTop() + nestedScrollView.getPaddingBottom()) {
                        View lastItemView = nestedScrollView.getChildAt(nestedScrollView.getChildCount() - 1);
                        if (lastItemView != null) {
                            if (i1 >= lastItemView.getMeasuredHeight() - nestedScrollView.getMeasuredHeight()) {
                                // Hiển thị progress bar
                                progressBarLoadMoreMyRooms.setVisibility(View.VISIBLE);
                                roomModel.ListRoomUser(iMainRoomModel, userID,
                                        quantityVerifiedRoomLoaded + quantityVerifiedRoomEachTime,
                                        quantityVerifiedRoomLoaded);
                            }
                        }
                    }
                }
            }
        });

        //Gọi hàm lấy dữ liệu trong model
        roomModel.ListRoomUser(iMainRoomModel, userID,
                quantityVerifiedRoomLoaded + quantityVerifiedRoomEachTime,
                quantityVerifiedRoomLoaded);
    }
}