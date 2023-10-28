package com.example.tingtongapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ResetPassword extends AppCompatActivity {
    private EditText edtEmailResetPass;
    private Button btnResetPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);

        edtEmailResetPass = findViewById(R.id.edt_email_reset);
        btnResetPass = findViewById(R.id.btn_reset_pw);
    }
}