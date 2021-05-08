package com.example.carparkingapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class forgotpass extends AppCompatActivity {
EditText ed1,ed2;
Button sendpass;
int n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" Forgot Password ?");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_lock_black_24dp);
        ed1=(EditText)findViewById(R.id.username);
        ed2=(EditText)findViewById(R.id.email);
        sendpass=(Button)findViewById(R.id.send);
        sendpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=ed1.getText().toString().trim();
                String email=ed2.getText().toString().trim();
                if (validateEmail() && username!=null){
                    String checkemail="SELECT email from user WHERE email='"+email+"'";
                    Cursor cursor=LoginActivity.mDatabase.rawQuery(checkemail,null);
                    if (cursor.getCount()>0)
                    {
                        try{
                            sendEmail();
                            String sql="UPDATE user SET password='"+String.valueOf(n)+"' where email='"+email+"'";
                            LoginActivity.mDatabase.execSQL(sql);
                            Toast.makeText(getApplicationContext(),"New Password sent to your email",Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),"Failed to send new Password", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(forgotpass.this, "Entered email is not registered", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    private boolean validateEmail(){
        String email1=ed2.getText().toString().trim();
        if(email1.isEmpty()){
            ed2.setError("email can't be empty");
            return false; }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
            ed2.setError("Please enter valid email address");
            return false;
        }else{
            ed2.setError(null);
            return true;
        }
    }
    private void sendEmail() {
        //Getting content for email
        n = 10000 + new Random().nextInt(90000);
        String email = ed2.getText().toString().trim();
        String username=ed1.getText().toString().trim();
        String subject = "Forgot Password ? \n Hi '"+username+"',";
        String message = "You requested that your password be reset. \n Your password has been changed to : "+String.valueOf(n);

        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }
}
