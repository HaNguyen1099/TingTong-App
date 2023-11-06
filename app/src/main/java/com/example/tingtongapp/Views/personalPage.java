package com.example.tingtongapp.Views;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tingtongapp.R;

public class personalPage extends AppCompatActivity {
    private EditText edtNamePersonalPage ;
    private Spinner spnGenderPersonalPage ;
    private EditText edtPhonePersonalPage;
    private CheckBox chBoxEditPersonalPage;
    private TextView txtPasswordPersonalPage;
    private EditText edtOldPasswordPersonalPage,edtNewPasswordPersonalPage,edtRetypeNewPasswordPersonalPage;
    private Button btnChangePasswordPersonalPage;
    private ImageView ivBack;
    Uri uri;
    String name,gender,phoneNumber;
    boolean isMale;
    ProgressDialog progressDialog;

//    FirebaseAuth firebaseAuth;
//    FirebaseUser firebaseUser;

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
        progressDialog = new ProgressDialog(this);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseUser = firebaseAuth.getCurrentUser();

//        initControl();
//        EditPersonal();
//        cancel();
    }

//    private void initControl(){
//        ivBack = findViewById(R.id.iv_back);
//        ivBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//    }
//        List<String> list = new ArrayList<>();
//        list.add("Nam");
//        list.add("Nữ");
//
//        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,list);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spnGenderPersonalPage.setAdapter(adapter);
//        spnGenderPersonalPage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                gender = adapterView.getItemAtPosition(i).toString();
//                if(i==0){
//                    isMale=true;
//                }
//                else {
//                    isMale=false;
//                }
//                spnGenderPersonalPage.setEnabled(false);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//
//        SharedPreferences sharedPreferences2 = this.getSharedPreferences(LoginView.PREFS_DATA_NAME, Context.MODE_PRIVATE);
//        String UID = sharedPreferences2.getString(LoginView.SHARE_UID,"");
//
//        DatabaseReference nodeUser = databaseReference.child("Users").child(UID);
//
//        ValueEventListener valueEventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                UserModel userModel = dataSnapshot.getValue(UserModel.class);
//
//                edtNamePersonalPage.setText(userModel.getName());
//                edtPhonePersonalPage.setText(userModel.getPhoneNumber());
//                if(userModel.isGender()==true){
//                    spnGenderPersonalPage.setSelection(0);
//                }
//                else{
//                    spnGenderPersonalPage.setSelection(1);
//                }
//                //mImageUrl=userModel.getAvatar();
//                Picasso.get().load(userModel.getAvatar()).into(imgAvtPersonalPage);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//
//        };
//
//        nodeUser.addListenerForSingleValueEvent(valueEventListener);
//    }
}
