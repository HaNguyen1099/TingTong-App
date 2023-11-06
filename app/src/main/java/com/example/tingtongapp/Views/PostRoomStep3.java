package com.example.tingtongapp.Views;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tingtongapp.Model.ImageRoomModel;
import com.example.tingtongapp.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class PostRoomStep3 extends Fragment implements View.OnClickListener{
    private ImageRoomModel imageRoom;
    private ArrayList<Uri> tempImageUris = new ArrayList<>();
    private ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
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
                    }
                }
            }
    );
    CheckBox chBoxWifi,chBoxClock,chBoxBed,chBoxFridge,chBoxWardrobe, chBoxParking,chBoxWashmachine,chBoxWaterheater,chBoxSecurity,chBoxArcondition;

    ImageButton btnImgUpLoadPushRoom;
    RecyclerView recyclerImgUpload;
    Button btnNextStep3PostRoom;

    PostRoom postRoom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.post_room_step3, container, false);
        //khoi tao view
        initControl(view);
        imageRoom = new ImageRoomModel();

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

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.btnImg_upLoad_push_room){
            // Tạo Intent để chọn hình ảnh từ bộ nhớ ngoài
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            pickImageLauncher.launch(intent);
        }else if(id == R.id.btn_nextStep3_post_room){
            for(Uri imageUri : tempImageUris) {
                imageRoom.addImage(imageUri);
            }
            tempImageUris.clear();

            Map<String, Boolean> listServicesRoom = new LinkedHashMap<>();

            listServicesRoom.put("Tự do", chBoxClock.isChecked());
            listServicesRoom.put("Giường", chBoxBed.isChecked());
            listServicesRoom.put("Tủ lạnh", chBoxFridge.isChecked());
            listServicesRoom.put("Máy giặt", chBoxWashmachine.isChecked());
            listServicesRoom.put("Wifi", chBoxWifi.isChecked());
            listServicesRoom.put("Tủ quần áo", chBoxWardrobe.isChecked());
            listServicesRoom.put("Điều hóa", chBoxArcondition.isChecked());
            listServicesRoom.put("Nóng lạnh", chBoxWaterheater.isChecked());
            listServicesRoom.put("An ninh", chBoxSecurity.isChecked());
            listServicesRoom.put("Chỗ để xe", chBoxParking.isChecked());


        }
    }
}