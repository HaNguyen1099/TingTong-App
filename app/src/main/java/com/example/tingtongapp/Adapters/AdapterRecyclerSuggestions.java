package com.example.tingtongapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tingtongapp.R;
import com.example.tingtongapp.Views.searchView;

import java.util.List;

public class AdapterRecyclerSuggestions extends RecyclerView.Adapter<AdapterRecyclerSuggestions.ViewHolder> {
    public final static String INTENT_DISTRICT = "DICTRICT";
    Context context;
    int resource;
    List<String> stringListDistrictLocation;
    boolean isSearchRoomCall;

    public AdapterRecyclerSuggestions(Context context,int resource,List<String> stringListDistrictLocation,boolean isSearchRoomCall){
        this.context = context;
        this.resource = resource;
        this.stringListDistrictLocation = stringListDistrictLocation;
        this.isSearchRoomCall = isSearchRoomCall;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtLocation;
        LinearLayout LinearContainElement;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLocation = itemView.findViewById(R.id.txt_location);
            LinearContainElement = itemView.findViewById(R.id.Linear_contain_element);
        }
    }

    @NonNull
    @Override
    public AdapterRecyclerSuggestions.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        // liên kết View với các thành phần của ViewHolder
        return viewHolder;
    }

    // Gán dữ liệu từ danh sách vào ViewHolder tại vị trí position
    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerSuggestions.ViewHolder viewHolder, int i) {
        // Gán giá trị văn bản cho TextView (txtLocation) trong ViewHolder
        viewHolder.txtLocation.setText(stringListDistrictLocation.get(i));

        final int position = i;

        // Khi người dùng nhấp vào phần tử trong danh sách, sự kiện onClick sẽ được kích hoạt.
        viewHolder.LinearContainElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Trường hợp gọi từ màn hình chính
                if(isSearchRoomCall == false){
                    // Tạo một Intent để chuyển từ màn hình hiện tại (context) sang searchView (một màn hình khác).
                    Intent intent = new Intent(context, searchView.class);
                    // Đặt dữ liệu (quận) để chuyển đến màn hình mới thông qua Intent.
                    intent.putExtra(INTENT_DISTRICT, stringListDistrictLocation.get(position));
                    // Khởi chạy màn hình mới (searchView) bằng cách sử dụng Intent.
                    context.startActivity(intent);
                }
                else {
                    // Trường hợp gọi từ search room
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra(INTENT_DISTRICT, stringListDistrictLocation.get(position));
                    ((AppCompatActivity)context).setResult(Activity.RESULT_OK,returnIntent);
                    ((AppCompatActivity)context).finish();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringListDistrictLocation.size();
    }
}
