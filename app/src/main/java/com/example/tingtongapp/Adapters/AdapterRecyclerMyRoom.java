package com.example.tingtongapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.tingtongapp.Model.RoomModel;
import com.example.tingtongapp.R;

import java.util.List;

public class AdapterRecyclerMyRoom extends Adapter<AdapterRecyclerMyRoom.ViewHolder>{
    List<RoomModel> RoomModelList;
    //Là biến lưu giao diện custom của từng row
    int resource;
    Context context;

    public AdapterRecyclerMyRoom(Context context, List<RoomModel> RoomModelList, int resource) {
        this.context = context;
        this.RoomModelList = RoomModelList;
        this.resource = resource;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTimeCreated, txtName, txtAddress;
        ImageView imgRoom,imgVerified;
        Button btnUpdate, btnDelete, btnChange;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTimeCreated = (TextView) itemView.findViewById(R.id.txt_timeCreated);
            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtAddress = (TextView) itemView.findViewById(R.id.txt_address);
            imgRoom = (ImageView) itemView.findViewById(R.id.img_room);
        }
    }
    @NonNull
    @Override
    public AdapterRecyclerMyRoom.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerMyRoom.ViewHolder viewHolder, int position) {
        final RoomModel roomModel = RoomModelList.get(position);

        viewHolder.txtName.setText(roomModel.getTitle());
        //Set address for room
        String longAddress = roomModel.getNo() + " " + roomModel.getStreet() + ", "
                + roomModel.getWard() + ", " + roomModel.getCounty() + ", " + roomModel.getCity();
        viewHolder.txtAddress.setText(longAddress);
        //End Set address for room
        viewHolder.txtTimeCreated.setText(roomModel.getTimeCreated());
        // hiển thị ảnh từ roomModel vào imgRoom
        roomModel.getImagesRoom().loadInto(viewHolder.imgRoom);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
