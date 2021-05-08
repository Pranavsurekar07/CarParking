package com.example.carparkingapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
private static final Pattern PASSWORD_PATTERN=Pattern.compile("^"+
        "(?=.*[0-9])"+
        "(?=.*[a-z])"+
        "(?=.*[A-Z])"+
            "(?=.*[@#$%^&+=])"+
            "(?=\\S+$)"+
        ".{6,}"+
                "$");
TextView t1;
int n;
EditText e1,e2,e3,e4,e5,e6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final LinearLayout l1=(LinearLayout)findViewById(R.id.panel1);
        l1.setVisibility(View.INVISIBLE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.register_btn).setOnClickListener(this);
        final Button sendotp=(Button)findViewById(R.id.sendotp);
        e1=(EditText)findViewById(R.id.fullname);
        e2=(EditText)findViewById(R.id.username);
        e3=(EditText)findViewById(R.id.pass);
        e4=(EditText)findViewById(R.id.confirmpass);
        e5=(EditText)findViewById(R.id.mobile);
        e6=(EditText)findViewById(R.id.email);
        t1=(TextView)findViewById(R.id.loginlink);

        LoginActivity.mDatabase=openOrCreateDatabase(MainActivity.DATABASE_NAME,MODE_PRIVATE,null);
        t1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
        finish();
        }
        });

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateEmail() && e1.getText().toString().trim()!=null) {
                    sendEmail();
                    AlertDialog.Builder mBuilder=new AlertDialog.Builder(RegisterActivity.this);
                    View mView=getLayoutInflater().inflate(R.layout.verify_otp,null);
                    mBuilder.setView(mView);
                    final AlertDialog dialog=mBuilder.create();
                    final Button confirm_login=(Button)mView.findViewById(R.id.verifuotp);
                    Button close=(Button)mView.findViewById(R.id.closedialog);
                    final ImageView right=(ImageView)mView.findViewById(R.id.right);
                    final ImageView wrong=(ImageView)mView.findViewById(R.id.wrong);
                    right.setVisibility(View.INVISIBLE);
                    wrong.setVisibility(View.INVISIBLE);
                    final TextInputEditText ed=(TextInputEditText)mView.findViewById(R.id.edit_otp);
                    confirm_login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String otp=ed.getText().toString().trim();
                            if (otp.equals(String.valueOf(n))){
                                Toast.makeText(getApplicationContext(),"Email Verified Successfully",Toast.LENGTH_LONG).show();
                                l1.setVisibility(View.VISIBLE);
                                sendotp.setEnabled(false);
                                e6.setEnabled(false);
                                e1.setEnabled(false);
                                right.setVisibility(View.VISIBLE);
                                wrong.setVisibility(View.INVISIBLE);
                            }
                            else{
                                ed.setError("Entered OTP is incorrect");
                                ed.requestFocus();
                                l1.setVisibility(View.INVISIBLE);
                                right.setVisibility(View.INVISIBLE);
                                wrong.setVisibility(View.VISIBLE);
                            }

                        }
                    });
                    dialog.setIcon(R.drawable.appicon_round);
                    dialog.setTitle("Verify OTP");
                    dialog.show();
                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();

                        }
                    });
                }else
                {
                    if (e1.getText().toString().trim()==null){
                    e1.setError("Full Name can't be Empty");
                    e1.requestFocus();
                    }
                }

            }
        });
    }
    private boolean validateEmail(){
        String email1=e6.getText().toString().trim();
        if(email1.isEmpty()){
            e6.setError("email can't be empty");
            return false; }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
            e6.setError("Please enter valid email address");
           return false;
        }else{
            e6.setError(null);
            return true;
        }
    }
private boolean validatePassword(){
        String ps=e3.getText().toString().trim();
        if(ps.isEmpty()){
            e3.setError("Password can't be empty");
            return false;
        }else if(!PASSWORD_PATTERN.matcher(ps).matches()){
            e3.setError("Password too weak");
            return false;
        }else{
            e3.setError(null);
            return true;
        }
}
private boolean validateconfirmpass(){
        String pass=e3.getText().toString().trim();
        String conpass=e4.getText().toString().trim();
        if(pass.equals(conpass)){
            e4.setTextColor(Color.GREEN);
            return false;
        }else {
            e4.setTextColor(Color.RED);
            return true;
        }
}


    private void addUser(){
        String name=e1.getText().toString().trim();
        String username=e2.getText().toString().trim();

        String mobile=e5.getText().toString().trim();

        if(name.isEmpty()){
            e1.setError("Please enter your Full name!");
            e1.requestFocus();
            return;
        }
        if(username.isEmpty()){
            e2.setError("Username can't be empty");
            e2.requestFocus();
            return;
        }
        if(mobile.length()!=10){
            e5.setError("Please enter valid Mobile Number");
            e5.requestFocus();
            return;
        }

    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.register_btn:
            validateEmail();
            validatePassword();
            validateconfirmpass();
            if(e4.getCurrentTextColor() == Color.GREEN && validatePassword()){
                Regist();
            }
            addUser();
            break;

        }
    }
    public void Regist(){
        String sqlquery = "INSERT INTO user(fullname,username,password,mobile,email,state)" +
                "VALUES(?,?,?,?,?,?)";
        String username=e2.getText().toString().trim();
        String checkuser="SELECT username from user WHERE username='"+username+"'";
        Cursor cursor=LoginActivity.mDatabase.rawQuery(checkuser,null);
        if (cursor.getCount()>0)
        {
            Toast.makeText(this,"This Username already in use try different",Toast.LENGTH_LONG).show();
        }else {
            try {
                LoginActivity.mDatabase.execSQL(sqlquery, new String[]{e1.getText().toString().trim(), e2.getText().toString().trim(), e3.getText().toString().trim(), e5.getText().toString().trim(), e6.getText().toString().trim(), String.valueOf(MainActivity.logout)});
                Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage() + ". Something went Wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void sendEmail() {
        //Getting content for email
        n = 10000 + new Random().nextInt(90000);
        String email = e6.getText().toString().trim();
        String subject = "Email Verification";
        String message = "Your email verification code is : "+String.valueOf(n);

        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }
}
