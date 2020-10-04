package com.example.paidbonusad;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class Register extends AppCompatActivity {

    EditText mFullname, mEmail, mMobilenumber, mPassword, mcnfPassword;
    TextView mtvloginhere;
    private Member member;
    FirebaseDatabase database;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullname = findViewById(R.id.ptname);
        mEmail = findViewById(R.id.ptEmail);
        mMobilenumber = findViewById(R.id.ptMobile);
        mPassword = findViewById(R.id.ptpassword);
        mcnfPassword = findViewById(R.id.pt_cnf_password);
        mtvloginhere = findViewById(R.id.tvloginhere);
        mtvloginhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(Register.this, Login.class);
                startActivity(loginIntent);


            }
        });


        member = new Member();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("Member");

    }



                public void btnregister(View view) {


        member.setPtname(mFullname.getText().toString());
        member.setPtEmail(mEmail.getText().toString());
        member.setPtMobile(mMobilenumber.getText().toString());
        member.setPtpassword(mPassword.getText().toString());
        member.setPt_cnf_password(mcnfPassword.getText().toString());

        if(TextUtils.isEmpty(member.getPtname())){
            mFullname.setError("Name is required !");
            return;
        }
        if(TextUtils.isEmpty(member.getPtEmail())) {
            mEmail.setError("E-mail is required !");
            return;
        }
                    if(TextUtils.isEmpty(member.getPtMobile())) {
                        mMobilenumber.setError("Mobile number is required !");
                        return;
                    }
                    if(TextUtils.isEmpty(member.getPtpassword())) {
                        mPassword.setError("Password is required !");
                        return;
                    }
                    if(TextUtils.isEmpty(member.getPt_cnf_password())){
                        mcnfPassword.setError("Retype the password here ! ");
                        return;}
                    if(!Patterns.EMAIL_ADDRESS.matcher(member.getPtEmail()).matches()){
                        mEmail.setError("Please enter a valid E-mail !");
                        return;
                    }
                    if(!member.getPtMobile().startsWith("0") || member.getPtMobile().length()!=10){
                        mMobilenumber.setError("Please enter a valid mobile number !");
                        return;
                    }
                    if(mPassword.length()<7){
                        mPassword.setError("Password should be greater than six characters !");
                        return;}

                    if(!member.getPtpassword().equals(member.getPt_cnf_password())){
                        mcnfPassword.setError("Password must be same !");
                        return;
                    }

        ref.child(member.getPtMobile()).setValue(member).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(mFullname!=null && mEmail!=null && mMobilenumber!=null && mPassword!=null && mcnfPassword!=null){
                    Toast.makeText(Register.this, "You have successfully registered", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Register.this, "Please fill all details !", Toast.LENGTH_LONG).show();
                }
            }
        });
                }
        @Override
        public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
