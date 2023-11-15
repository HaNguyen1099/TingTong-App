package com.example.tingtongapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tingtongapp.Model.ImageRoomModel;
import com.example.tingtongapp.Model.Room;
import com.example.tingtongapp.R;
import com.example.tingtongapp.Views.DetailRoom;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class AdapterRoomSuggestion extends RecyclerView.Adapter<AdapterRoomSuggestion.RoomViewHolder>{
    private ArrayList<Room> rooms;
    private Context context;

    public AdapterRoomSuggestion(Context context, ArrayList<Room> rooms){
        this.context = context;
        this.rooms = rooms;
    }
    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_element_list_view, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = rooms.get(position);
        if(room == null)
            return;
        // display info room
        try {
            holder.title.setText(room.getTitle());
            holder.typeOfRoom.setText(room.getTypeOfRoom());
            holder.address.setText(room.getAddress());

            int rentingRoomPrice = Integer.parseInt(room.getRentingPrice());
            holder.rentingPrice.setText((float)((float)rentingRoomPrice/1000000.0) + " tr/tháng");
            holder.sizeRoom.setText(room.getLengthRoom() + "m x " + room.getWidthRoom() + "m");

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                String dateAddedString = room.getDateAdded();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dateAdded = LocalDate.parse(dateAddedString, formatter);
                LocalDate now = null;
                now = LocalDate.now();
                long daysBetween = ChronoUnit.DAYS.between(dateAdded, now);
                if (daysBetween == 0) {
                    holder.dateAdded.setText("Hôm nay");
                } else if (daysBetween < 7) {
                    holder.dateAdded.setText(daysBetween + " ngày trước");
                } else {
                    holder.dateAdded.setText(dateAdded.toString());
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            ImageRoomModel imageRoomModel = room.getImagesRoom();
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
        } catch (Exception e){
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Room room = rooms.get(holder.getAdapterPosition());

                if(room != null){
                    Intent intent = new Intent(context, DetailRoom.class);
                    intent.putExtra("intentDetailRoom", room.getIdRoom());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(rooms != null){
            return rooms.size();
        }
        return 0;
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
