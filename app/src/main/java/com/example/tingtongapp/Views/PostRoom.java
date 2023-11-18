package com.example.tingtongapp.Views;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.tingtongapp.R;

import java.util.HashMap;

public class PostRoom extends AppCompatActivity implements View.OnClickListener {
    ImageButton btnImgLocationPushRoom, btnImgInformationPushRoom, btnImgUtilityPushRoom, btnImgConfirmPushRoom;
    TextView txtLocationPushRoom, txtInfoPushRoom, txtUtilityPushRoom, txtConfirmPushRoom;
    Toolbar toolbar;
    private ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_room_main);
        initControl();
        addControl();
    }

    private void initControl() {
        txtLocationPushRoom = findViewById(R.id.txt_location_push_room);
        txtConfirmPushRoom = findViewById(R.id.txt_comfirm_push_room);
        txtInfoPushRoom = findViewById(R.id.txt_info_push_room);
        txtUtilityPushRoom = findViewById(R.id.txt_utility_push_room);

        btnImgLocationPushRoom = findViewById(R.id.btnImg_location_push_room);
        btnImgConfirmPushRoom = findViewById(R.id.btnImg_confirm_push_room);
        btnImgUtilityPushRoom = findViewById(R.id.btnImg_utility_push_room);
        btnImgInformationPushRoom = findViewById(R.id.btnImg_information_push_room);

        btnImgLocationPushRoom.setOnClickListener(this);
        btnImgConfirmPushRoom.setOnClickListener(this);
        btnImgUtilityPushRoom.setOnClickListener(this);
        btnImgInformationPushRoom.setOnClickListener(this);

        toolbar = findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Bài đăng của bạn");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btnImg_location_push_room){
            setCurrentPage(0);
        }
        else if(id == R.id.btnImg_information_push_room){
            setCurrentPage(1);
        }
        else if(id == R.id.btnImg_utility_push_room){
            setCurrentPage(2);
        }
        else if(id == R.id.btnImg_confirm_push_room){
            setCurrentPage(3);
        }
    }

    public void setCurrentPage(int position) {
        pager.setCurrentItem(position);
    }

    private void addControl() {
        pager = (ViewPager) findViewById(R.id.viewpager_post_room);
        //  tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        FragmentManager manager = getSupportFragmentManager();
        PagerAdapter adapter = new PagerAdapter(manager);
        pager.setAdapter(adapter);
    }

    public class PagerAdapter extends FragmentPagerAdapter {
        private HashMap<Integer, Fragment> fragmentHashMap = new HashMap<>();
        PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            if (fragmentHashMap.get(position) != null) {
                return fragmentHashMap.get(position);
            }
            switch (position) {
                case 0:
                    fragment = new PostRoomStep1();
                    fragmentHashMap.put(0, fragment);
                    break;
                case 1:
                    fragment = new PostRoomStep2();
                    fragmentHashMap.put(1, fragment);
                    break;
                case 2:
                    fragment = new PostRoomStep3();
                    fragmentHashMap.put(2, fragment);
                    break;
                case 3:
                    fragment = new PostRoomStep4();
                    fragmentHashMap.put(3, fragment);
                    break;
            }
            return fragment;
        }
        @Override
        public int getCount() {
            return 4;
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        SharedPreferences preferences = getSharedPreferences("postRoomData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        super.onBackPressed();
    }
}