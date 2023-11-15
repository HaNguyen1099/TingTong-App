package com.example.tingtongapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserModel implements Parcelable {
    private String userID;  // uid in firebase of user
    private String name = "0", email = "0", phoneNumber = "0";
    private boolean owner = false, gender = false;

    protected UserModel(Parcel in){
        userID = in.readString();
        name = in.readString();
        email = in.readString();
        phoneNumber = in.readString();
        owner = in.readByte() != 0;
        gender = in.readByte() != 0;
    }

    public UserModel(String name, String email, boolean gender, boolean owner, String phone) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.owner = owner;
        this.phoneNumber = phone;
    }

    public UserModel(){

    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isOwner() {
        return owner;
    }

    public boolean isGender() {
        return gender;
    }
    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userID);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(phoneNumber);
        dest.writeByte((byte) (owner ? 1 : 0));
        dest.writeByte((byte) (gender ? 1 : 0));
    }

    public void addUser(UserModel newUserModel, String uid) {
        DatabaseReference nodeUser = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        nodeUser.setValue(newUserModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                }
            }
        });
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
