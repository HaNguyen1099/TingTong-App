package com.example.tingtongapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class SignupView extends AppCompatActivity implements View.OnClickListener{
    EditText edt_email_signUp, edt_password_signUp, edt_name_signUp, edt_phone_signUp;
    CheckBox cb_checkBox;
    TextView tv_login;
    Button btn_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_view);

        edt_name_signUp = (EditText) findViewById(R.id.edt_username_signup);
        edt_email_signUp = (EditText) findViewById(R.id.edt_email_signup);
        edt_phone_signUp = (EditText) findViewById(R.id.edt_phone_signup);
        edt_password_signUp = (EditText) findViewById(R.id.edt_password_signup);
        cb_checkBox = (CheckBox) findViewById(R.id.cb_checkbox);
        tv_login = (TextView) findViewById(R.id.tv_login);
        btn_signUp = (Button) findViewById(R.id.btn_signup);

        tv_login.setOnClickListener(this);
        btn_signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.tv_login){
            Intent iSignup = new Intent(SignupView.this, LoginView.class);
            startActivity(iSignup);
        }
    }
}