package com.example.paidbonusad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

     EditText ptMobile,ptpassword;
     TextView mtvregister;
     private DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ptMobile = findViewById(R.id.ptMobile);
        ptpassword = findViewById(R.id.ptpassword);
        mtvregister =findViewById(R.id.tvregister);
        mtvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Login.this,Register.class);
                startActivity(registerIntent);
            }
        });

        ref = FirebaseDatabase.getInstance().getReference().child("Member");
    }

    public void btnlogin(View view) {
        String mMobilenumber = ptMobile.getText().toString();
        final String mPassword = ptpassword.getText().toString();

        try{
        ref.child(mMobilenumber).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Member member = dataSnapshot.getValue(Member.class);
                if(member==null){
                    Toast.makeText(Login.this, "Please enter the correct mobile number !", Toast.LENGTH_LONG).show();}
                else if(mPassword.equals(member.getPtpassword())){
                    Toast.makeText(Login.this, "You have successfully logged in to account", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));

                }else{
                    Toast.makeText(Login.this, "Please enter the correct password !", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }catch (Exception ignored){
            Toast.makeText(Login.this, "Member doesnot exist", Toast.LENGTH_LONG).show();
        }}}
