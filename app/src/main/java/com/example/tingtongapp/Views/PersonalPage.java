package com.example.tingtongapp.Views;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tingtongapp.Model.UserModel;
import com.example.tingtongapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PersonalPage extends AppCompatActivity {
    private EditText edtNamePersonalPage, edtPhonePersonalPage ;
    private Spinner spnGenderPersonalPage ;
    private CheckBox chBoxEditPersonalPage;
    private TextView txtPasswordPersonalPage;
    private EditText edtOldPasswordPersonalPage,edtNewPasswordPersonalPage,edtRetypeNewPasswordPersonalPage;
    private Button btnChangePasswordPersonalPage;
    private ImageView ivBack;
    String name,gender,phoneNumber;
    boolean isMale;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_page_view);

        edtNamePersonalPage = findViewById(R.id.edt_name_personal_page);
        spnGenderPersonalPage = findViewById(R.id.spn_gender_personal_page);
        edtPhonePersonalPage = findViewById(R.id.edt_phone_personal_page);
        chBoxEditPersonalPage = findViewById(R.id.chBox_edit_personal_page);
        txtPasswordPersonalPage = findViewById(R.id.tv_password_personal_page);
        edtOldPasswordPersonalPage = findViewById(R.id.edt_old_password_personal_page);
        edtNewPasswordPersonalPage = findViewById(R.id.edt_new_password_personal_page);
        edtRetypeNewPasswordPersonalPage = findViewById(R.id.edt_retype_new_password_personal_page);
        btnChangePasswordPersonalPage = findViewById(R.id.btn_change_password_personal_page);
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        initControl();
        EditPersonal();
    }

    private void getDataFromControl(){
        name = edtNamePersonalPage.getText().toString();
        phoneNumber = edtPhonePersonalPage.getText().toString();
    }

    // Display info current user
    private void initControl(){
        // Create layout display drop down for user gender
        List<String> list = new ArrayList<>();
        list.add("Nam");
        list.add("Nữ");

        // ArrayAdapter chuyển đổi danh sách thành các mục hiển thị trên spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGenderPersonalPage.setAdapter(adapter);
        spnGenderPersonalPage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gender = adapterView.getItemAtPosition(i).toString();
                if(i == 0) isMale = true;
                else isMale = false;
                spnGenderPersonalPage.setEnabled(false);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // Get current user
        String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference nodeUser = FirebaseDatabase.getInstance().getReference().child("Users").child(UID);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Display info current user
                UserModel userModel = dataSnapshot.getValue(UserModel.class);
                edtNamePersonalPage.setText(userModel.getName());
                edtPhonePersonalPage.setText(userModel.getPhoneNumber());
                if(userModel.isGender() == true) {
                    spnGenderPersonalPage.setSelection(0);
                }
                else {
                    spnGenderPersonalPage.setSelection(1);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        nodeUser.addListenerForSingleValueEvent(valueEventListener);
    }

    private void EditPersonal(){
        // Get info current user
        String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference nodeUser = FirebaseDatabase.getInstance().getReference().child("Users").child(UID);

        // Checkbox listener
        chBoxEditPersonalPage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    chBoxEditPersonalPage.setBackground(getDrawable(R.drawable.ic_svg_check_24));
                    edtNamePersonalPage.setEnabled(true);
                    spnGenderPersonalPage.setEnabled(true);
                    edtPhonePersonalPage.setEnabled(true);
                    txtPasswordPersonalPage.setText("Đổi mật khẩu");
                }
                else{
                    chBoxEditPersonalPage.setBackground(getDrawable(R.drawable.ic_svg_edit_24));
                    edtNamePersonalPage.setEnabled(false);
                    spnGenderPersonalPage.setEnabled(false);
                    edtPhonePersonalPage.setEnabled(false);
                    txtPasswordPersonalPage.setText("");
                    edtOldPasswordPersonalPage.setVisibility(View.GONE);
                    edtNewPasswordPersonalPage.setVisibility(View.GONE);
                    edtRetypeNewPasswordPersonalPage.setVisibility(View.GONE);
                    btnChangePasswordPersonalPage.setVisibility(View.GONE);

                    getDataFromControl();
                    nodeUser.child("gender").setValue(isMale);
                    nodeUser.child("name").setValue(name);
                    nodeUser.child("phoneNumber").setValue(phoneNumber);
                }
            }
        });
    }

    public void ChangePass(View v){
        edtOldPasswordPersonalPage.setVisibility(View.VISIBLE);
        edtNewPasswordPersonalPage.setVisibility(View.VISIBLE);
        edtRetypeNewPasswordPersonalPage.setVisibility(View.VISIBLE);
        btnChangePasswordPersonalPage.setVisibility(View.VISIBLE);

        // When user click change password
        btnChangePasswordPersonalPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = firebaseUser.getEmail();
                final String newPass = edtNewPasswordPersonalPage.getText().toString();
                final String retypeNewPass = edtRetypeNewPasswordPersonalPage.getText().toString();
                final String oldPass = edtOldPasswordPersonalPage.getText().toString();

                if(oldPass.equals("")==true||newPass.equals("")==true||retypeNewPass.equals("")==true){
                    Toast.makeText(PersonalPage.this, "Hãy điền đủ thông tin yêu cầu", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Update password if new password is different from old password
                    AuthCredential credential = EmailAuthProvider.getCredential(email, oldPass);
                    firebaseUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                if (newPass.equals(retypeNewPass) == true) {
                                    firebaseUser.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (!task.isSuccessful()) {
                                                Toast.makeText(PersonalPage.this, "Lỗi!!!Xin thực hiện lại", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(PersonalPage.this, "Thay đổi mật khẩu thành  công", Toast.LENGTH_SHORT).show();
                                                edtOldPasswordPersonalPage.setVisibility(View.INVISIBLE);
                                                edtNewPasswordPersonalPage.setVisibility(View.INVISIBLE);
                                                edtRetypeNewPasswordPersonalPage.setVisibility(View.INVISIBLE);
                                                btnChangePasswordPersonalPage.setVisibility(View.INVISIBLE);
                                            }
                                        }
                                    });
                                }
                                else {
                                    Toast.makeText(PersonalPage.this, "Xác nhận mật khẩu mới không đúng!", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(PersonalPage.this, "Xác thực người dùng thất bại do nhập mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}