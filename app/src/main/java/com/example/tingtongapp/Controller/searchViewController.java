package com.example.tingtongapp.Controller;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tingtongapp.ClassOther.myFilter;
import com.example.tingtongapp.Model.SearchRoomModel;

import java.util.List;

public class searchViewController {
    Context context;
    SearchRoomModel searchRoomModel;
    String UID;

    // khai báo các biến liên quan tới load more.
    int quantityRoomsLoaded = 0;
    int quantityRoomsEachTime = 4;

    public searchViewController(Context context, String district, List<myFilter> filterList, String UID) {
        this.context = context;
        this.searchRoomModel = new SearchRoomModel(district,filterList);
        this.UID = UID;
    }

    public void loadSearchRoom(RecyclerView recyclerSearchRoom, TextView txtNumberRoom, ProgressBar progressBarLoad,
                               LinearLayout lnLtResultReturn, NestedScrollView nestedScrollSearchView,
                               ProgressBar progressBarLoadMore){

    }
}
