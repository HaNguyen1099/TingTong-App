package com.example.tingtongapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tingtongapp.Model.Room;
import com.example.tingtongapp.R;
import com.example.tingtongapp.Views.DetailRoom;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class AdapterMyRoom extends RecyclerView.Adapter<AdapterMyRoom.MyRoomViewHolder>{
    private ArrayList<Room> rooms;
    private Context context;

    public AdapterMyRoom(Context context, ArrayList<Room> rooms){
        this.context = context;
        this.rooms = rooms;
    }
    @NonNull
    @Override
    public MyRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_room_element_list_view, parent, false);
        return new MyRoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRoomViewHolder holder, int position) {
        Room room = rooms.get(position);
        if(room == null)
            return;
        // display info room
        try {
            holder.title.setText(room.getTitle());
            holder.typeOfRoom.setText(room.getTypeOfRoom());
            holder.address.setText(room.getAddress());

            int rentingRoomPrice = Integer.parseInt(room.getRentingPrice());
            float rentingPriceFloat = (float)((float)rentingRoomPrice/1000000.0);
            holder.rentingPrice.setText(String.format("%.2f", rentingPriceFloat) + " tr/tháng");
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

            holder.updateRoom.setText("Chưa thuê");

        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            Picasso.get().load(room.getImageUrlNew()).into(holder.demoRoomImg, new Callback() {
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

    class MyRoomViewHolder extends RecyclerView.ViewHolder{
        private TextView title, address, typeOfRoom, rentingPrice, sizeRoom, dateAdded;
        private ImageView demoRoomImg;
        private Button updateRoom, deleteRoom;

        public MyRoomViewHolder(@NonNull View itemView) {
            super(itemView);
            if (itemView == null)
                return;
            title = (TextView) itemView.findViewById(R.id.my_room_text_view_name_room);
            typeOfRoom = (TextView) itemView.findViewById(R.id.my_room_text_view_type_room);
            rentingPrice = (TextView) itemView.findViewById(R.id.my_room_text_view_price_room);
            address = (TextView) itemView.findViewById(R.id.my_room_text_view_address_room);
            sizeRoom = (TextView) itemView.findViewById(R.id.my_room_text_view_area_room);
            dateAdded = (TextView) itemView.findViewById(R.id.my_room_text_view_time_added);

            demoRoomImg = (ImageView) itemView.findViewById(R.id.my_room_image_view_demo_room);
            updateRoom = (Button) itemView.findViewById(R.id.my_room_button_update_room);
            deleteRoom = (Button) itemView.findViewById(R.id.my_room_button_delete_room);
        }
    }
}




//package com.example.tingtongapp.Adapters;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.tingtongapp.Model.Room;
//import com.example.tingtongapp.R;
//import com.example.tingtongapp.Views.DetailRoom;
//import com.squareup.picasso.Callback;
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//
//public class AdapterMyRoom extends RecyclerView.Adapter<AdapterMyRoom.MyRoomHolder>{
//    private ArrayList<Room> rooms = new ArrayList<>();
//    private Context context;
//
//    public AdapterMyRoom(ArrayList<Room> rooms, Context context){
//        this.rooms = rooms;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public MyRoomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_room_element_list_view, parent, false);
//        return new MyRoomHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyRoomHolder holder, int position) {
//        Room room = rooms.get(position);
//
//        if(room == null)
//            return;
//
//        try {
//            holder.dateAddedRoom.setText(room.getDateAdded());
//            holder.typeOfRoom.setText(room.getTypeOfRoom());
//            holder.titleRoom.setText(room.getTitle());
//            holder.areaRoom.setText(room.getLengthRoom() + "m x " + room.getWidthRoom() + "m");
//            holder.addressRoom.setText(room.getAddress());
//
//            int price = Integer.parseInt(room.getRentingPrice());
//            float rentingPriceFloat = (float)((float)price / 1000000.0);
//            holder.rentingPriceRoom.setText(String.format("%.2f", rentingPriceFloat) + " tr/tháng");
//
//            if(room.getConditionRoom().equals("Còn")){
//                holder.updateRoom.setText("Chưa thuê");
//            }else{
//                holder.updateRoom.setText("Đã thuê");
//            }
//
//            Picasso.get().load(room.getImageUrlNew()).into(holder.imageDemoRoom, new Callback() {
//                @Override
//                public void onSuccess() {
//                    // Do nothing
//                }
//
//                @Override
//                public void onError(Exception e) {
//                    int resId = context.getResources().getIdentifier("ic_room", "drawable", context.getPackageName());
//                    holder.imageDemoRoom.setImageResource(resId);
//                    e.printStackTrace();
//                }
//            });
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Room room = rooms.get(holder.getAdapterPosition());
//
//                if(room != null){
//                    Intent intent = new Intent(context, DetailRoom.class);
//                    intent.putExtra("intentDetailRoom", room.getIdRoom());
//                    context.startActivity(intent);
//                }
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    class MyRoomHolder extends RecyclerView.ViewHolder {
//        TextView dateAddedRoom, titleRoom, typeOfRoom, rentingPriceRoom, areaRoom, addressRoom;
//        ImageView imageDemoRoom;
//        Button updateRoom, deleteRoom;
//
//        public MyRoomHolder(@NonNull View itemView) {
//            super(itemView);
//
//            dateAddedRoom = (TextView) itemView.findViewById(R.id.my_room_text_view_time_added);
//            titleRoom = (TextView) itemView.findViewById(R.id.my_room_text_view_name_room);
//            typeOfRoom = (TextView) itemView.findViewById(R.id.my_room_text_view_type_room);
//            rentingPriceRoom = (TextView) itemView.findViewById(R.id.my_room_text_view_price_room);
//            areaRoom = (TextView) itemView.findViewById(R.id.my_room_text_view_address_room);
//
//            imageDemoRoom = (ImageView) itemView.findViewById(R.id.my_room_image_view_demo_room);
//
//            updateRoom = (Button) itemView.findViewById(R.id.my_room_button_update_room);
//            deleteRoom = (Button) itemView.findViewById(R.id.my_room_button_delete_room);
//        }
//    }
//}
