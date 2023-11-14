package com.example.tingtongapp.Adapters;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tingtongapp.R;

import java.util.ArrayList;

public class AdapterImagePostRoomDemo extends RecyclerView.Adapter<AdapterImagePostRoomDemo.ImageViewHolder> {
    private ArrayList<Uri> imageUris;

    public AdapterImagePostRoomDemo(ArrayList<Uri> imageUris) {
        this.imageUris = imageUris;
    }

    public ArrayList<Uri> getImageUris(){
        return imageUris;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_post_room_demo_view, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Uri imageUri = imageUris.get(position);
        holder.imageView.setImageURI(imageUri);
    }

    @Override
    public int getItemCount() {
        return imageUris.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageButton moveImgUpload;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_post_room_demo_view);
            moveImgUpload = itemView.findViewById(R.id.move_image_post_room);

            moveImgUpload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Activity activity = (Activity) itemView.getContext();
                        // Find the adapter from RecyclerView
                        RecyclerView recyclerView = activity.findViewById(R.id.recycler_img_upload);
                        AdapterImagePostRoomDemo adapter = (AdapterImagePostRoomDemo) recyclerView.getAdapter();
                        // Remove the item
                        adapter.imageUris.remove(position);
                        adapter.notifyItemRemoved(position);
                        adapter.notifyItemRangeChanged(position, adapter.imageUris.size());
                    }
                }
            });
        }
    }
}
