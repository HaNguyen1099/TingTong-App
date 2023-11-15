package com.example.tingtongapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tingtongapp.Model.Room;
import com.example.tingtongapp.R;

import java.util.ArrayList;

public class AdapterListServices extends RecyclerView.Adapter<AdapterListServices.ListServiceViewHolder>{
    private ArrayList<String> listServicesRoom;
    public AdapterListServices(Room roomModel){
        listServicesRoom = roomModel.getListServicesAvailable();
    }

    @NonNull
    @Override
    public ListServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.utility_element_grid_room_detail_view, parent, false);
        return new ListServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListServiceViewHolder holder, int position) {
        String nameServiceString = listServicesRoom.get(position);
        holder.nameService.setText(nameServiceString);

        switch (nameServiceString) {
            case "Tự do":
                holder.serviceImgDemo.setImageResource(R.drawable.clock);
                break;
            case "Giường":
                holder.serviceImgDemo.setImageResource(R.drawable.bed);
                break;
            case "Tủ lạnh":
                holder.serviceImgDemo.setImageResource(R.drawable.refrigerator);
                break;
            case "Máy giặt":
                holder.serviceImgDemo.setImageResource(R.drawable.washing);
                break;
            case "Wifi":
                holder.serviceImgDemo.setImageResource(R.drawable.wifi);
                break;
            case "Tủ quần áo":
                holder.serviceImgDemo.setImageResource(R.drawable.wardrobe);
                break;
            case "Điều hòa":
                holder.serviceImgDemo.setImageResource(R.drawable.aircondition);
                break;
            case "Nóng lạnh":
                holder.serviceImgDemo.setImageResource(R.drawable.heater);
                break;
            case "An ninh":
                holder.serviceImgDemo.setImageResource(R.drawable.policeman);
                break;
            case "Chỗ để xe":
                holder.serviceImgDemo.setImageResource(R.drawable.motorcycle);
                break;
            case "WC khép kín":
                holder.serviceImgDemo.setImageResource(R.drawable.toilet);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (listServicesRoom != null){
            return listServicesRoom.size();
        }
        return 0;
    }

    class ListServiceViewHolder extends RecyclerView.ViewHolder {
        private ImageView serviceImgDemo;
        private TextView nameService;
        public ListServiceViewHolder(@NonNull View itemView){
            super(itemView);

            nameService = (TextView) itemView.findViewById(R.id.txt_nameUtility_utility_room_detail);
            serviceImgDemo = (ImageView) itemView.findViewById(R.id.img_utility_room_detail);
        }
    }
}
