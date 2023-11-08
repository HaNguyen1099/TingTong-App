package com.example.tingtongapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tingtongapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginView extends AppCompatActivity implements View.OnClickListener{
    Button btn_login;
    EditText edt_username_login, edt_password_login;
    TextView tvForgotPassword, tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);

        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            Intent iMain = new Intent(LoginView.this, Main_Menu.class);
            startActivity(iMain);
            finish();
        }

        btn_login = (Button) findViewById(R.id.btn_login);
        edt_username_login = (EditText) findViewById(R.id.edt_username_login);
        edt_password_login = (EditText) findViewById(R.id.edt_password_login);
        tvForgotPassword = (TextView) findViewById(R.id.tv_forgot_password);
        tvSignUp = (TextView) findViewById(R.id.tv_signup);

        tvForgotPassword.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.tv_signup){
            Intent iSignup = new Intent(LoginView.this, SignupView.class);
            startActivity(iSignup);
        }
        else if(id == R.id.tv_forgot_password){
            Intent iReset = new Intent(LoginView.this, ResetPassword.class);
            startActivity(iReset);
        }
        else if(id == R.id.btn_login){
            String email = edt_username_login.getText().toString().trim();
            String password = edt_password_login.getText().toString().trim();

            if (email.equals("") || password.equals("")){
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }else{
                FirebaseAuth auth = FirebaseAuth.getInstance();

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginView.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                                    Intent iMain = new Intent(LoginView.this, Main_Menu.class);
                                    startActivity(iMain);
                                } else {
                                    Toast.makeText(LoginView.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
    }
}