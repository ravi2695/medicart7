package com.medikart.otp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.medikart.otp.Common.Common;

public class MainActivity extends AppCompatActivity {

     private  Button btnsignin,btnsignup;
     private EditText number,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         btnsignin=findViewById(R.id.buttonSignin);
         btnsignup = findViewById(R.id.signup);

         number = findViewById(R.id.edtmobileno);
         pass = findViewById(R.id.editTextPassword);

         btnsignup.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(MainActivity.this,RegisterActivity.class
                 ));
             }
         });

         // here i am fetching the information from database

        final FirebaseDatabase database =FirebaseDatabase.getInstance();

        final DatabaseReference databaseReference = database.getReference("Users");

         btnsignin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 databaseReference.addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(DataSnapshot dataSnapshot) {

                         // if data not exists in database

                         if(dataSnapshot.child(number.getText().toString()).exists()) {


                             // here we taking the user information

                             User user = dataSnapshot.child(number.getText().toString()).getValue(User.class);
                             user.setPhone(number.getText().toString());  // set phone number


                             if (user.getPassword().equals(pass.getText().toString())) {
                                // Toast.makeText(MainActivity.this, "valid user", Toast.LENGTH_SHORT).show();

                                     Intent intent = new Intent(MainActivity.this,menu.class);
                                     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                     Common.currentUser = user;
                                     startActivity(intent);
                                     finish();
                                 } else {
                                     Toast.makeText(MainActivity.this, "Invalid user", Toast.LENGTH_SHORT).show();
                                 }
                         }

                         else
                         {
                             Toast.makeText(MainActivity.this, "user not exists", Toast.LENGTH_SHORT).show();
                         }

                     }

                     @Override
                     public void onCancelled(DatabaseError databaseError) {

                     }
                 });



             }
         });

    }




}
