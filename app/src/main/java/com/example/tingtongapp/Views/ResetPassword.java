package com.example.tingtongapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tingtongapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.time.Instant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResetPassword extends AppCompatActivity implements View.OnClickListener {
    private EditText edtEmailResetPass;
    private Button btnResetPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);

        edtEmailResetPass = findViewById(R.id.edt_email_reset);
        btnResetPass = findViewById(R.id.btn_reset_pw);

        btnResetPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        int id = view.getId();

        if(id == R.id.btn_reset_pw){
            String emailRecover = edtEmailResetPass.getText().toString().trim();

            if(emailRecover.equals("")){
                Toast.makeText(ResetPassword.this, "Vui lòng nhập email đã đãng ký tài khoản", Toast.LENGTH_SHORT).show();
            }else {
                Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
                Matcher emailMatcher = emailPattern.matcher(emailRecover);

                if (!emailMatcher.matches()) {
                    Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                }else{
                    FirebaseAuth.getInstance().sendPasswordResetEmail(emailRecover)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ResetPassword.this, "Gửi email khôi phục thành công, vui lòng làm theo hướng dẫn trong email để đặt lại mật khẩu", Toast.LENGTH_SHORT).show();

                                        Intent toSignIn = new Intent(ResetPassword.this, LoginView.class);
                                        startActivity(toSignIn);
                                        finish();
                                    } else {
                                        Toast.makeText(ResetPassword.this, "Thất bại. Vui lòng kiểm tra địa chỉ email và thử lại.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        }
    }
}