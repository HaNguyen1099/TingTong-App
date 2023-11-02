package com.example.tingtongapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.tingtongapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main_Menu extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    FrameLayout fragmentContainer;

    MainActivity HomeView;

    Account_View AccountView;
    PostRoom PostRoomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main__menu);

        initControl();
        //Chạy lần đầu tiên sẽ load vào màn hình main
        HomeView = new MainActivity();
        setFragment(HomeView);
    }

    //Khởi tạo control
    private void initControl() {
        fragmentContainer = findViewById(R.id.fragment_container);
        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id == R.id.nav_home){
                    setFragment(HomeView);
                    return true;
                }
                else if(id == R.id.nav_room){
                    Intent intent = new Intent(Main_Menu.this, PostRoom.class);
                    startActivity(intent);
                    return true;
                }
                else if(id == R.id.nav_acount) {
                    AccountView = new Account_View();
                    setFragment(AccountView);
                    return true;
                }
                return false;
            }
        });
    }
    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }
}
