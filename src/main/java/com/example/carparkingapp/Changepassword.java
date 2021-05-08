package com.example.carparkingapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Changepassword extends AppCompatActivity {
TextInputEditText oldpass,newpass,verifypass;
Button changepass;
    SQLiteDatabase mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("  Change Password");
        getSupportActionBar().setIcon(R.drawable.ic_lock_black_24dp);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        LoginActivity.mDatabase=openOrCreateDatabase(MainActivity.DATABASE_NAME,MODE_PRIVATE,null);
        oldpass=(TextInputEditText)findViewById(R.id.oldpassword);
        newpass=(TextInputEditText)findViewById(R.id.newpassword);
        verifypass=(TextInputEditText)findViewById(R.id.verifypassword);
        changepass=(Button)findViewById(R.id.changepass_btn);
        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldpassword=oldpass.getText().toString().trim();
                String newpassword=newpass.getText().toString().trim();
                String verifypassword=verifypass.getText().toString().trim();

                if (oldpassword.isEmpty()){
                    oldpass.setError("Password can't be empty");
                    oldpass.requestFocus();
                    return;
                }
                if (newpassword.isEmpty()){
                    newpass.setError("New Password can't be empty");
                    newpass.requestFocus();
                    return;
                }
                if (verifypassword.equals(newpassword)){
                    try{
                        String sql="UPDATE user SET password='"+verifypassword+"' where password='"+oldpassword+"'";
                        LoginActivity.mDatabase.execSQL(sql);
                        Toast.makeText(getApplicationContext(),"Password Updated Successfully",Toast.LENGTH_SHORT).show();
                        oldpass.setText("");
                        newpass.setText("");
                        verifypass.setText("");
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),"Failed to Update Password", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"New password and Confirm password should be same !",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
