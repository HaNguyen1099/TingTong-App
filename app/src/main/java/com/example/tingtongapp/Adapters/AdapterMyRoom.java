package com.example.tingtongapp.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tingtongapp.Model.Room;
import com.example.tingtongapp.R;
import com.example.tingtongapp.Views.DetailRoom;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

            String conditionRoom = room.getConditionRoom();
            if(conditionRoom.equals("Còn")){
                holder.updateRoom.setText("Chưa thuê");
            }else{
                holder.updateRoom.setText("Đã thuê");
            }

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

        holder.updateRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialogUpdateConditionRoom = new Dialog(context);
                dialogUpdateConditionRoom.setContentView(R.layout.change_state_room_dialog);

                ImageButton closeDialog = (ImageButton) dialogUpdateConditionRoom.findViewById(R.id.img_button_close_dialog_update_room);
                RadioGroup conditionRoomChange = (RadioGroup) dialogUpdateConditionRoom.findViewById(R.id.radio_group_change_condition_room);

                conditionRoomChange.setEnabled(true);
                conditionRoomChange.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton radioButtonChangeCondition = (RadioButton) dialogUpdateConditionRoom.findViewById(checkedId);
                        if (radioButtonChangeCondition != null){
                            String condition = radioButtonChangeCondition.getText().toString();

                            updateConditionRoom(room, condition.equals("Còn phòng"));
                            dialogUpdateConditionRoom.cancel();
                        }
                    }
                });

                closeDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogUpdateConditionRoom.cancel();
                    }
                });

                dialogUpdateConditionRoom.show();
            }
        });

        holder.deleteRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialogConfirmDeleteRoom = new Dialog(context);
                dialogConfirmDeleteRoom.setContentView(R.layout.delete_dialog);

                Button confirmDeleteRoom = (Button) dialogConfirmDeleteRoom.findViewById(R.id.button_confirm_dialog_delete_room);
                Button closeDialogDeleteRoom = (Button) dialogConfirmDeleteRoom.findViewById(R.id.button_close_dialog_delete_room);

                confirmDeleteRoom.setEnabled(true);
                confirmDeleteRoom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteRoom(room);
                        dialogConfirmDeleteRoom.cancel();
                    }
                });

                closeDialogDeleteRoom.setEnabled(true);
                closeDialogDeleteRoom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogConfirmDeleteRoom.cancel();
                    }
                });

                dialogConfirmDeleteRoom.show();
            }
        });
    }

    private void updateConditionRoom(Room room, Boolean condition){
        String idRoom = room.getIdRoom();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("ListRoom");

        if(condition) {
            databaseReference.child(idRoom).child("conditionRoom").setValue("Còn").addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            databaseReference.child(idRoom).child("conditionRoom").setValue("Hết").addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void deleteRoom(Room room){
        String idRoom = room.getIdRoom();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("ListRoom");

        databaseReference.child(idRoom).removeValue().addOnSuccessListener(
            new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, "Có lỗi, vui lòng thử lại", Toast.LENGTH_SHORT).show();
                }
            }
        );
    }

    @Override
    public int getItemCount() {
        if(rooms != null){
            return rooms.size();
        }
        return 0;
    }

    class MyRoomViewHolder extends RecyclerView.ViewHolder{
        private TextView title, address, rentingPrice, sizeRoom, dateAdded;
        private ImageView demoRoomImg;
        private Button updateRoom, deleteRoom;

        public MyRoomViewHolder(@NonNull View itemView) {
            super(itemView);
            if (itemView == null)
                return;
            title = (TextView) itemView.findViewById(R.id.my_room_text_view_name_room);
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