package com.example.tingtongapp.Views;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tingtongapp.Adapters.AdapterImagePostRoomDemo;
import com.example.tingtongapp.R;

import java.util.ArrayList;

public class PostRoomStep3 extends Fragment implements View.OnClickListener{
    private ArrayList<Uri> imageUrisPostRoom = new ArrayList<>();
    private AdapterImagePostRoomDemo adapterImagePostRoomDemo;
    private ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        ArrayList<Uri> tempImageUris = new ArrayList<>();
                        if(data.getClipData() != null) {
                            ClipData clipData = data.getClipData();
                            for(int i = 0; i < clipData.getItemCount(); i++) {
                                Uri imageUri = clipData.getItemAt(i).getUri();
                                tempImageUris.add(imageUri);
                            }
                        } else if(data.getData() != null) {
                            Uri imageUri = data.getData();
                            tempImageUris.add(imageUri);
                        }

                        if(tempImageUris.size() > 0){
                            adapterImagePostRoomDemo = new AdapterImagePostRoomDemo(tempImageUris);
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
                            recyclerImgUpload.setLayoutManager(gridLayoutManager);
                            recyclerImgUpload.setAdapter(adapterImagePostRoomDemo);
                        }
                    }
                }
            }
    );
    private CheckBox chBoxWifi,chBoxClock,chBoxBed,chBoxFridge,chBoxWardrobe, chBoxParking,chBoxWashmachine,chBoxWaterheater,chBoxSecurity,chBoxArcondition;

    private ImageButton btnImgUpLoadPushRoom;
    private RecyclerView recyclerImgUpload;
    private Button btnNextStep3PostRoom;

    private PostRoom postRoom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.post_room_step3, container, false);
        initControl(view);
        postRoom = (PostRoom) getContext();
        return view;
    }

    private void initControl(View view){
        chBoxWifi = view.findViewById(R.id.chBox_wifi);
        chBoxClock = view.findViewById(R.id.chBox_clock);
        chBoxBed = view.findViewById(R.id.chBox_bed);
        chBoxFridge = view.findViewById(R.id.chBox_fridge);
        chBoxWardrobe = view.findViewById(R.id.chBox_wardrobe);
        chBoxParking = view.findViewById(R.id.chBox_parking);
        chBoxWashmachine = view.findViewById(R.id.chBox_washmachine);
        chBoxWaterheater = view.findViewById(R.id.chBox_waterheater);
        chBoxSecurity = view.findViewById(R.id.chBox_security);
        chBoxArcondition = view.findViewById(R.id.chBox_arcondition);

        btnNextStep3PostRoom = view.findViewById(R.id.btn_nextStep3_post_room);
        btnNextStep3PostRoom.setOnClickListener(this);
        btnImgUpLoadPushRoom = view.findViewById(R.id.btnImg_upLoad_push_room);
        btnImgUpLoadPushRoom.setOnClickListener(this);

        recyclerImgUpload = view.findViewById(R.id.recycler_img_upload);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btnImg_upLoad_push_room) {
            // Intent pick image from external storage
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            pickImageLauncher.launch(intent);
        } else {
            if(id == R.id.btn_nextStep3_post_room) {
                try {
                    imageUrisPostRoom = adapterImagePostRoomDemo.getImageUris();
                } catch (Exception e){
                    e.printStackTrace();
                }
                String listServicesRoom = "";

                if(chBoxClock.isChecked()){
                    listServicesRoom += (chBoxClock.getText().toString() + "|");
                }
                if(chBoxBed.isChecked()){
                    listServicesRoom += (chBoxBed.getText().toString() + "|");
                }
                if(chBoxFridge.isChecked()){
                    listServicesRoom += (chBoxFridge.getText().toString() + "|");
                }
                if(chBoxWashmachine.isChecked()){
                    listServicesRoom += (chBoxWashmachine.getText().toString() + "|");
                }
                if(chBoxWifi.isChecked()){
                    listServicesRoom += (chBoxWifi.getText().toString() + "|");
                }
                if(chBoxWardrobe.isChecked()){
                    listServicesRoom += (chBoxWardrobe.getText().toString() + "|");
                }
                if(chBoxArcondition.isChecked()){
                    listServicesRoom += (chBoxArcondition.getText().toString() + "|");
                }
                if(chBoxWaterheater.isChecked()){
                    listServicesRoom += (chBoxWaterheater.getText().toString() + "|");
                }
                if(chBoxSecurity.isChecked()){
                    listServicesRoom += (chBoxSecurity.getText().toString() + "|");
                }
                if(chBoxParking.isChecked()){
                    listServicesRoom += (chBoxParking.getText().toString() + "|");
                }

                if(imageUrisPostRoom.size() > 3){
                    // Save data to SharePreferences
                    SharedPreferences preferences = getActivity().getSharedPreferences("postRoomData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    int imageUrisSize = imageUrisPostRoom.size();
                    editor.putInt("imageUrisSize", imageUrisSize);
                    for(int i=0; i<imageUrisSize; ++i){
                        editor.putString("imageUri" + i, imageUrisPostRoom.get(i).toString());
                    }

                    editor.putString("listServices", listServicesRoom);
                    editor.putBoolean("checkStep3", true);
                    editor.apply();

                    // Go to next step post room
                    if (isAdded() && getActivity() != null) {
                        postRoom.setCurrentPage(3);
                    }
                } else {
                    Toast.makeText(getContext(), "Vui lòng chọn tối thiểu 4 ảnh", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}