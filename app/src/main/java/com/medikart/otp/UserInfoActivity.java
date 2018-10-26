package com.medikart.otp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserInfoActivity extends AppCompatActivity {


    private EditText edtfn,Email,Password,Phone;
    private Button btn;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        edtfn = findViewById(R.id.editText);
        Email = findViewById(R.id.editTextemail);
        Password = findViewById(R.id.editTextpass);
        Phone = findViewById(R.id.userInfomobileno);

        btn = findViewById(R.id.buttonsubmit1);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// changed by sneh

                Intent intent = new Intent(UserInfoActivity.this,menu.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

                // startActivity(new Intent(UserInfoActivity.this,menu.class));
                regis();



            }
        });

    }


    public void regis()
    {
        String name = edtfn.getText().toString().trim();
       String email = Email.getText().toString().trim();
       String password = Password.getText().toString().trim();
       String phone = Phone.getText().toString().trim();
       // String email = edtemail.getText().toString().trim();
        //String birthdate = edtdob.getText().toString().trim();
        //String ePass = edtepass.getText().toString().trim();
       // String cPass = edtcpass.getText().toString().trim();
        User user = new User(name,email,password,phone);
        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber()).setValue(user);
    }
}
