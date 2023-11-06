package com.example.tingtongapp.Model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class ImageRoomModel implements Parcelable {
    private ArrayList<String> imageUrls;
    private StorageReference storageReference;

    public ImageRoomModel(){
        imageUrls = new ArrayList<>();
        storageReference = FirebaseStorage.getInstance().getReference();
    }

    public ArrayList<String> getAll() {
        return imageUrls;
    }

    public String getFirstImage() {
        if (imageUrls != null && imageUrls.size() > 0) {
            return imageUrls.get(0);
        }

        return "https://firebasestorage.googleapis.com/v0/b/roomfinder-ce2b5.appspot.com/o/images%2Fbackground.jpg?alt=media&token=7e18202d-7191-4191-8090-4d791d2dd5ad";
    }

    public void addImage(Uri imageUri) {
        String imageName = "image" + System.currentTimeMillis(); // Tạo tên ảnh duy nhất
        StorageReference imageRef = storageReference.child("images/" + imageName);

        UploadTask uploadTask = imageRef.putFile(imageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.e("ImageUpload", "Upload failed: " + e.getMessage());
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String imageUrl = uri.toString();
                        imageUrls.add(imageUrl);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Log.e("ImageDownload", "Download URL failed: " + e.getMessage());
                    }
                });
            }
        });
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public ImageRoomModel(Parcel in) {
        imageUrls = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(imageUrls);
    }

    public static final Creator<ImageRoomModel> CREATOR = new Creator<ImageRoomModel>() {
        @Override
        public ImageRoomModel createFromParcel(Parcel in) {
            return new ImageRoomModel(in);
        }

        @Override
        public ImageRoomModel[] newArray(int size) {
            return new ImageRoomModel[size];
        }
    };
}