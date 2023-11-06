package com.example.tingtongapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tingtongapp.Model.UserModel;
import com.example.tingtongapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupView extends AppCompatActivity implements View.OnClickListener{
    EditText edt_email_signUp, edt_password_signUp, edt_name_signUp, edt_phone_signUp;
    CheckBox cb_checkBox;   // Check Term of Use is checked
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
        }else if(id == R.id.btn_signup) {
            String name = edt_name_signUp.getText().toString().trim();
            String email = edt_email_signUp.getText().toString().trim();
            String phone = edt_phone_signUp.getText().toString().trim();
            String password = edt_password_signUp.getText().toString().trim();

            if (name.equals("") || email.equals("") || phone.equals("") || password.equals("")) {
                Toast.makeText(this, "Vui lòng không để trống bất kỳ trường nào", Toast.LENGTH_SHORT).show();
            } else {
                Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
                Matcher emailMatcher = emailPattern.matcher(email);

                Pattern phonePattern = Pattern.compile("\\d{10}");
                Matcher phoneMatcher = phonePattern.matcher(phone);

                if (!emailMatcher.matches()) {
                    Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                } else if (!phoneMatcher.matches()) {
                    Toast.makeText(this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                } else if (cb_checkBox.isChecked()){
                    // Check if registration information exists
                    FirebaseAuth auth = FirebaseAuth.getInstance();

                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignupView.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

                                        String uid = task.getResult().getUser().getUid();
                                        UserModel newUser = new UserModel(name, email, false, false, phone);
                                        newUser.addUser(newUser, uid);

                                        Intent iSignup = new Intent(SignupView.this, LoginView.class);
                                        startActivity(iSignup);
                                        finish();
                                    } else {
                                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                            Toast.makeText(SignupView.this, "Email đã được sử dụng", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(SignupView.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });
                } else {
                    Toast.makeText(this, "Vui lòng đồng ý với các điều khoản của chúng tôi", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}