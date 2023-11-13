package com.example.tingtongapp.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tingtongapp.ClassOther.myFilter;
import com.example.tingtongapp.R;

import java.util.List;

public class AdapterRecyclerFilter extends RecyclerView.Adapter<AdapterRecyclerFilter.ViewHolder> {
    Context context;
    int resource;
    List<myFilter> listFilter;
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgDelete;
        TextView displayName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDelete = itemView.findViewById(R.id.img_delete);
            displayName = itemView.findViewById(R.id.display_name);
        }
    }
    public AdapterRecyclerFilter(Context context,int resource,List<myFilter> listFilter){
        this.context=context;
        this.resource=resource;
        this.listFilter = listFilter;
    }
    @NonNull
    @Override
    public AdapterRecyclerFilter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerFilter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
