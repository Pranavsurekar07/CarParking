package com.example.carparkingapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity{
CircleImageView dp;
Cursor cursor;
Bitmap selectedImage;
Animation rotation;
ImageButton editmail;
SQLiteDatabase mDatabase;
TextView t1,t2,t3,t4;
EditText t5;
    String onstartquery = "SELECT * FROM user WHERE state='"+MainActivity.login+"'";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        LoginActivity.mDatabase=openOrCreateDatabase(MainActivity.DATABASE_NAME,MODE_PRIVATE,null);
        dp=(CircleImageView)findViewById(R.id.profile);
        t1=(TextView)findViewById(R.id.name);
        t2=(TextView)findViewById(R.id.username);
        t3=(TextView)findViewById(R.id.pass);
        t4=(TextView)findViewById(R.id.mobile);
        t5=(EditText) findViewById(R.id.email);
        t5.setEnabled(true);
        rotation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.rotate);
        editmail=(ImageButton)findViewById(R.id.editemail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("  Profile");
        getSupportActionBar().setIcon(R.drawable.ic_user_profile);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        showprofileinfo();
        editmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newemail=t5.getText().toString().trim();
                if(validateEmail()) {
                    try {
                        String sql = "UPDATE user SET email='" + newemail + "' where state='" + MainActivity.login + "'";
                        LoginActivity.mDatabase.execSQL(sql);
                        Toast.makeText(getApplicationContext(), "Email Updated Successfully", Toast.LENGTH_SHORT).show();
                        t5.setEnabled(false);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed to Update Email", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private boolean validateEmail(){
        String email1=t5.getText().toString().trim();
        if(email1.isEmpty()){
            t5.setError("email can't be empty");
            return false; }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
            t5.setError("Please enter valid email address");
            return false;
        }else{
            t5.setError(null);
            return true;
        }
    }
    public void showprofileinfo(){
        cursor=LoginActivity.mDatabase.rawQuery(onstartquery,null);
        if(cursor.moveToFirst()) {
            do {
                t1.setText(cursor.getString(1));
                t2.setText(cursor.getString(2));
                t3.setText(cursor.getString(3));
                t4.setText(cursor.getString(4));
                t5.setText(cursor.getString(5));
                String c=cursor.getString(2);
                String stnew=c.toUpperCase();
                String  st= String.valueOf(stnew.charAt(0));
                if(st.equals("A")){dp.setBackgroundResource(R.drawable.a);}
                else if(st.equals("B")){dp.setBackgroundResource(R.drawable.b);}
                else if(st.equals("C")){dp.setBackgroundResource(R.drawable.c);}
                else if(st.equals("D")){dp.setBackgroundResource(R.drawable.d);}
                else if(st.equals("E")){dp.setBackgroundResource(R.drawable.e);}
                else if(st.equals("F")){dp.setBackgroundResource(R.drawable.f);}
                else if(st.equals("G")){dp.setBackgroundResource(R.drawable.g);}
                else if(st.equals("H")){dp.setBackgroundResource(R.drawable.h);}
                else if(st.equals("I")){dp.setBackgroundResource(R.drawable.i);}
                else if(st.equals("J")){dp.setBackgroundResource(R.drawable.j);}
                else if(st.equals("K")){dp.setBackgroundResource(R.drawable.k);}
                else if(st.equals("L")){dp.setBackgroundResource(R.drawable.l);}
                else if(st.equals("M")){dp.setBackgroundResource(R.drawable.m);}
                else if(st.equals("N")){dp.setBackgroundResource(R.drawable.n);}
                else if(st.equals("O")){dp.setBackgroundResource(R.drawable.o);}
                else if(st.equals("P")){dp.setBackgroundResource(R.drawable.p);}
                else if(st.equals("Q")){dp.setBackgroundResource(R.drawable.q);}
                else if(st.equals("R")){dp.setBackgroundResource(R.drawable.rv);}
                else if(st.equals("S")){dp.setBackgroundResource(R.drawable.s);}
                else if(st.equals("T")){dp.setBackgroundResource(R.drawable.t);}
                else if(st.equals("U")){dp.setBackgroundResource(R.drawable.u);}
                else if(st.equals("V")){dp.setBackgroundResource(R.drawable.v);}
                else if(st.equals("W")){dp.setBackgroundResource(R.drawable.w);}
                else if(st.equals("X")){dp.setBackgroundResource(R.drawable.x);}
                else if(st.equals("Y")){dp.setBackgroundResource(R.drawable.y);}
                else if(st.equals("Z")){dp.setBackgroundResource(R.drawable.z);}
                dp.startAnimation(rotation);
               /* byte[] data = cursor.getBlob(cursor.getColumnIndex("profile"));
                ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
                Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
                dp.setImageBitmap(bitmap);*/
            }while (cursor.moveToNext());
        }
    }
}
