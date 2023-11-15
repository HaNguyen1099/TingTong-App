package com.example.tingtongapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tingtongapp.Model.ImageRoomModel;
import com.example.tingtongapp.Model.Room;
import com.example.tingtongapp.Model.RoomModel;
import com.example.tingtongapp.R;
import com.example.tingtongapp.Views.DetailRoom;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class AdapterRoomSuggestion extends RecyclerView.Adapter<AdapterRoomSuggestion.RoomViewHolder>{
    private ArrayList<Room> roomModels;
    private Context context;

    public AdapterRoomSuggestion(Context context, ArrayList<Room> roomModels){
        this.context = context;
        this.roomModels = roomModels;
    }
    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_element_list_view, parent, false);
        return new RoomViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room roomModel = roomModels.get(position);
        if(roomModel == null)
            return;
        // display info room
        try {
            holder.title.setText(roomModel.getTitle());
            holder.typeOfRoom.setText(roomModel.getTypeOfRoom());
            holder.address.setText(roomModel.getAddress());

            int rentingRoomPrice = Integer.parseInt(roomModel.getRentingPrice());
            holder.rentingPrice.setText((float)((float)rentingRoomPrice/1000000.0) + " tr/tháng");
            holder.sizeRoom.setText(roomModel.getLengthRoom() + "m x " + roomModel.getWidthRoom() + "m");

            String dateAddedString = roomModel.getDateAdded();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dateAdded = LocalDate.parse(dateAddedString, formatter);
            LocalDate now = LocalDate.now();

            long daysBetween = ChronoUnit.DAYS.between(dateAdded, now);

            if (daysBetween == 0) {
                holder.dateAdded.setText("Hôm nay");
            } else if (daysBetween < 7) {
                holder.dateAdded.setText(daysBetween + " ngày trước");
            } else {
                holder.dateAdded.setText(dateAdded.toString());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        ImageRoomModel imageRoomModel = roomModel.getImagesRoom();
        Picasso.get().load(imageRoomModel.getFirstImage()).into(holder.demoRoomImg, new Callback() {
            @Override
            public void onSuccess() {
                // Do nothing
            }

            @Override
            public void onError(Exception e) {
                int resId = context.getResources().getIdentifier("ic_room", "drawable", context.getPackageName());
                holder.demoRoomImg.setImageResource(resId);
                e.printStackTrace();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Room roomModel = roomModels.get(holder.getAdapterPosition());

                if(roomModel != null){
                    Intent intent = new Intent(context, DetailRoom.class);
                    intent.putExtra("intentDetailRoom", roomModel);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(roomModels != null){
            return roomModels.size();
        }
        return roomModels != null ? roomModels.size() : 0;
    }

    class RoomViewHolder extends RecyclerView.ViewHolder{
        private TextView title, address, typeOfRoom, rentingPrice, sizeRoom, dateAdded;
        private ImageView demoRoomImg;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            if (itemView == null)
                return;
            title = (TextView) itemView.findViewById(R.id.txt_name);
            typeOfRoom = (TextView) itemView.findViewById(R.id.txt_type);
            rentingPrice = (TextView) itemView.findViewById(R.id.txt_price);
            address = (TextView) itemView.findViewById(R.id.txt_address);
            sizeRoom = (TextView) itemView.findViewById(R.id.txt_area);
            dateAdded = (TextView) itemView.findViewById(R.id.txt_timeCreated);

            demoRoomImg = (ImageView) itemView.findViewById(R.id.img_room);
        }
    }
}
