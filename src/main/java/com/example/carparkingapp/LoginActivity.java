package com.example.carparkingapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText uname;
    TextInputEditText pass;
    ConstraintLayout loginlayout;
    private static Animation shakeAnimation;
    public static SQLiteDatabase mDatabase;
    TextView register,forgotpassword;
    String updatequery = "UPDATE user SET state='" + MainActivity.login + "' WHERE username=? AND password=?";
    String Selectquery = "SELECT * FROM user";
    String onstartquery = "SELECT * FROM user WHERE state='" + MainActivity.login + "'";

    @Override
    protected void onStart() {
        super.onStart();
        Cursor cursor = this.mDatabase.rawQuery(onstartquery, null);
        if (cursor.moveToFirst()) {
            startActivity(new Intent(LoginActivity.this, Home.class));
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       loginlayout=(ConstraintLayout)findViewById(R.id.login_layout);
        forgotpassword=(TextView)findViewById(R.id.forgot_pass);
        login = findViewById(R.id.login_btn);
        register = findViewById(R.id.register);
         uname = (EditText)findViewById(R.id.username);
         pass =(TextInputEditText) findViewById(R.id.password);
        shakeAnimation = AnimationUtils.loadAnimation(this,R.anim.shakeanim);
        this.mDatabase = openOrCreateDatabase(MainActivity.DATABASE_NAME, MODE_PRIVATE, null);
        createTable();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               log_in();

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
            }
        });
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,forgotpass.class));
            }
        });
    }
    private void log_in() {

        String usr =uname.getText().toString().trim();
        String pas =pass.getText().toString().trim();

        Cursor cursor = this.mDatabase.rawQuery(Selectquery, null);

        if (!usr.isEmpty() && !pas.isEmpty()) {
            if (cursor.moveToFirst()) {
                do {
                    if (usr.equals(cursor.getString(2)) && pas.equals(cursor.getString(3))) {
                        try {
                            this.mDatabase.execSQL(updatequery, new String[]{uname.getText().toString().trim(), pass.getText().toString().trim()});
                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(LoginActivity.this, Home.class);
                            startActivity(i);
                            finish();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Username or Password Incorrect", Toast.LENGTH_SHORT).show();
                        uname.setError("Incorrect Username !");
                        uname.requestFocus();
                        pass.setError("Incorrect Password !");
                        pass.requestFocus();
                        loginlayout.startAnimation(shakeAnimation);
                    }
                } while (cursor.moveToNext());
            } else {
                Toast.makeText(getApplicationContext(), "No User Found. Please Register", Toast.LENGTH_SHORT).show();
                loginlayout.startAnimation(shakeAnimation);
            }
            cursor.close();
        } else {
           uname.setError("Please enter Username !");
           uname.requestFocus();
        }


    }
    private void createTable(){
        mDatabase.execSQL("CREATE TABLE if not exists user(\n"+
                "id INTEGER NOT NULL CONSTRAINT user_pk PRIMARY KEY AUTOINCREMENT,\n"+
                "fullname varchar(200) NOT NULL,\n"+
                "username varchar(200) NOT NULL,\n"+
                "password varchar(200) NOT NULL,\n"+
                "mobile INTEGER NOT NULL,\n"+
                "email varchar(200) NOT NULL,\n"+
                "state INTEGER NOT NULL,\n"+
                "profile blob \n"+
                ");");
        mDatabase.execSQL("CREATE TABLE if not exists parking(\n"+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "date TEXT,\n"+
                "vehicle_number TEXT,\n"+
                "customer_name TEXT,\n"+
                "customer_mobile INTEGER,\n"+
                "in_time TEXT,\n"+
                "out_time TEXT,\n"+
                "fare TEXT, \n"+
                "status TEXT \n"+
                ");");
        mDatabase.execSQL("CREATE TABLE if not exists slots(\n"+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "vehicle_number TEXT,\n"+
                "date TEXT,\n"+
                "in_time TEXT,\n"+
                "s1 TEXT,\n"+
                "s2 TEXT,\n"+
                "s3 TEXT,\n"+
                "s4 TEXT,\n"+
                "s5 TEXT,\n"+
                "s6 TEXT,\n"+
                "s7 TEXT,\n"+
                "s8 TEXT,\n"+
                "s9 TEXT,\n"+
                "s10 TEXT\n"+
                ");");
    }
}
