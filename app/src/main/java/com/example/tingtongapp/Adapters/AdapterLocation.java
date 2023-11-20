package com.example.tingtongapp.Adapters;

import static com.example.tingtongapp.Adapters.AdapterRecyclerSuggestions.INTENT_DISTRICT;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tingtongapp.Model.LocationModel;
import com.example.tingtongapp.R;
import com.example.tingtongapp.Views.SearchView;
import com.squareup.picasso.Picasso;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AdapterLocation extends BaseAdapter {
    private Context context;
    private int layout;
    private List<LocationModel> listLocation = new ArrayList<>();

    public AdapterLocation(Context context, Map<String, Integer> listDistrict) {
        this.context = context;
        this.layout = R.layout.row_element_grid_view_locaion;

        for(Map.Entry<String, Integer> entry : listDistrict.entrySet()){
            LocationModel locationModel = new LocationModel();
            locationModel.setRoomNumber(entry.getValue());
            locationModel.setCounty(entry.getKey());
            String imageNameResource = convertDistrictNameToImageName(entry.getKey());
            int resId = context.getResources().getIdentifier(imageNameResource, "drawable", context.getPackageName());
            locationModel.setImage(resId);

            listLocation.add(locationModel);
        }
        Collections.sort(listLocation);
    }

    public String convertDistrictNameToImageName(String districtName) {
        // Convert districtName to unsigned text
        String normalized = Normalizer.normalize(districtName.toLowerCase().trim(), Normalizer.Form.NFD);
        String withoutD = normalized.replace('đ', 'd');
        String normalizedDistrictName = withoutD.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return normalizedDistrictName.replaceAll("\\s+", "");
    }

    @Override
    public int getCount() {
        return this.listLocation.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            ViewHolder holder = new ViewHolder();

            holder.image = (ImageView) view.findViewById(R.id.img_location);
            holder.name = (TextView) view.findViewById(R.id.txt_name_location);
            holder.room = (TextView) view.findViewById(R.id.txt_room_location);
            holder.rLTImage =view.findViewById(R.id.rLT_image);
            view.setTag(holder);
        }

        // gan gia tri
        LocationModel itemLocation = listLocation.get(position);

        ViewHolder holder = (ViewHolder) view.getTag();

        Picasso.get().load(itemLocation.getImage()).centerCrop().fit().into(holder.image);
        holder.name.setText(itemLocation.getCounty());
        holder.room.setText(itemLocation.getRoomNumber() + " Phòng");

        holder.rLTImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SearchView.class);
                intent.putExtra(INTENT_DISTRICT, listLocation.get(position).getCounty());
                context.startActivity(intent);
            }
        });

        return view;
    }

    class ViewHolder {
        ImageView image;
        TextView name;
        TextView room;
        RelativeLayout rLTImage;
    }
}
