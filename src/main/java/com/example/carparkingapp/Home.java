package com.example.carparkingapp;


import android.content.Intent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final Pattern CARNUMBER_PATTERN=Pattern.compile("^"+ "[A-Z]{2}"+ "[0-9]{2}"+ "[A-Z]{2}"+ "[0-9]{4}"+ "$");
    EditText edt_Date,edt_time;
    String s="book";
    Animation rotation;
    String z="zero";
    char st;
    String formattedDate;
    private DrawerLayout mDrawerlayout;
    SQLiteDatabase mDatabase;
    TextView tv,park;
Button search_btn,sp1,sp2,sp3,sp4,sp5,sp6,sp7,sp8,sp9,sp10;
    TextInputEditText ed,ed1,ed2;
    TableLayout tb;
    private ActionBarDrawerToggle mToggle;
    public static String Log_Out_query="UPDATE user SET state='"+MainActivity.logout+"' WHERE state='"+MainActivity.login+"'";
    String onstartquery = "SELECT * FROM user WHERE state='"+MainActivity.login+"'";
    private static long backpress;
    String getcount="SELECT  COUNT(*) FROM parking";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final SwipeRefreshLayout swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_dark,android.R.color.holo_blue_light,android.R.color.holo_green_dark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        Calendar calendar=Calendar.getInstance();
                        int minute=calendar.get(Calendar.MINUTE);
                        int hourofday = calendar.get(Calendar.HOUR_OF_DAY);
                        edt_time.setText(hourofday+" : "+ minute);
                    }
                },3000);
            }
        });
        initialize();
        rotation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.rotate);
        LoginActivity.mDatabase=openOrCreateDatabase(MainActivity.DATABASE_NAME,MODE_PRIVATE,null);
        park=(TextView)findViewById(R.id.parkcount);
        Cursor cursor1=LoginActivity.mDatabase.rawQuery(getcount,null);
        if (cursor1.moveToLast()) {
            park.setText("Total Parkings : "+cursor1.getInt(0));
            park.setTextColor(Color.WHITE);
        }

        NavigationView navigationView=(NavigationView)findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);
         CircleImageView profileImageView=(CircleImageView) headerView.findViewById(R.id.navuser);
        TextView navUsername = (TextView) headerView.findViewById(R.id.tv1);
        TextView navEmail = (TextView) headerView.findViewById(R.id.tv2);
        Cursor cursor=LoginActivity.mDatabase.rawQuery(onstartquery,null);
        if(cursor.moveToFirst()) {
            do {
                String c=cursor.getString(2);
                String stnew=c.toUpperCase();
                String  st= String.valueOf(stnew.charAt(0));
                if(st.equals("A")){profileImageView.setBackgroundResource(R.drawable.a);}
                else if(st.equals("B")){profileImageView.setBackgroundResource(R.drawable.b);}
                else if(st.equals("C")){profileImageView.setBackgroundResource(R.drawable.c);}
                else if(st.equals("D")){profileImageView.setBackgroundResource(R.drawable.d);}
                else if(st.equals("E")){profileImageView.setBackgroundResource(R.drawable.e);}
                else if(st.equals("F")){profileImageView.setBackgroundResource(R.drawable.f);}
                else if(st.equals("G")){profileImageView.setBackgroundResource(R.drawable.g);}
                else if(st.equals("H")){profileImageView.setBackgroundResource(R.drawable.h);}
                else if(st.equals("I")){profileImageView.setBackgroundResource(R.drawable.i);}
                else if(st.equals("J")){profileImageView.setBackgroundResource(R.drawable.j);}
                else if(st.equals("K")){profileImageView.setBackgroundResource(R.drawable.k);}
                else if(st.equals("L")){profileImageView.setBackgroundResource(R.drawable.l);}
                else if(st.equals("M")){profileImageView.setBackgroundResource(R.drawable.m);}
                else if(st.equals("N")){profileImageView.setBackgroundResource(R.drawable.n);}
                else if(st.equals("O")){profileImageView.setBackgroundResource(R.drawable.o);}
                else if(st.equals("P")){profileImageView.setBackgroundResource(R.drawable.p);}
                else if(st.equals("Q")){profileImageView.setBackgroundResource(R.drawable.q);}
                else if(st.equals("R")){profileImageView.setBackgroundResource(R.drawable.rv);}
                else if(st.equals("S")){profileImageView.setBackgroundResource(R.drawable.s);}
                else if(st.equals("T")){profileImageView.setBackgroundResource(R.drawable.t);}
                else if(st.equals("U")){profileImageView.setBackgroundResource(R.drawable.u);}
                else if(st.equals("V")){profileImageView.setBackgroundResource(R.drawable.v);}
                else if(st.equals("W")){profileImageView.setBackgroundResource(R.drawable.w);}
                else if(st.equals("X")){profileImageView.setBackgroundResource(R.drawable.x);}
                else if(st.equals("Y")){profileImageView.setBackgroundResource(R.drawable.y);}
                else if(st.equals("Z")){profileImageView.setBackgroundResource(R.drawable.z);}
                navUsername.setText("Welcome "+cursor.getString(2));
                navEmail.setText(cursor.getString(5));
                //byte[] image=cursor.getBlob(7);
                //Bitmap bmp= BitmapFactory.decodeByteArray(image,0,image.length);
                //profileImageView.setImageBitmap(bmp);
                navUsername.setTextColor(Color.WHITE);
                navEmail.setTextColor(Color.WHITE);
                profileImageView.startAnimation(rotation);
            } while (cursor.moveToNext());
        }
        search_btn=(Button)findViewById(R.id.search);
        edt_time=(EditText)findViewById(R.id.time);


        tb=(TableLayout)findViewById(R.id.spacetable);
        tb.setVisibility(View.INVISIBLE);
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        formattedDate = df.format(c);

        Calendar calendar=Calendar.getInstance();
        edt_Date=(EditText)findViewById(R.id.date);
        edt_Date.setText(formattedDate);
        int minute=calendar.get(Calendar.MINUTE);
        int hourofday = calendar.get(Calendar.HOUR_OF_DAY);
        edt_time.setText(hourofday+" : "+ minute);
        edt_time.setEnabled(false);
        edt_Date.setEnabled(false);
        mDrawerlayout=(DrawerLayout)findViewById(R.id.drawer);
        mToggle =new ActionBarDrawerToggle(this,mDrawerlayout,R.string.Open,R.string.Close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        LoginActivity.mDatabase=openOrCreateDatabase(MainActivity.DATABASE_NAME,MODE_PRIVATE,null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_home_button_for_interface);
        navigationView.setNavigationItemSelectedListener(this);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String searchsql1="SELECT * FROM slots WHERE s1 LIKE '%"+s+"%'";
                Cursor cursor3=LoginActivity.mDatabase.rawQuery(searchsql1,null);
                if(cursor3.moveToFirst()) {
                    do {
                        String space1=cursor3.getString(4);
                        if (s.equals(space1)) {
                            Drawable d = getResources().getDrawable(R.drawable.booked);
                            sp1.setBackgroundDrawable(d);
                            sp1.setEnabled(false);
                        }
                    } while (cursor3.moveToNext());
                }

                String searchsql2="SELECT * FROM slots WHERE s2 LIKE '%"+s+"%'";
                Cursor cursor4=LoginActivity.mDatabase.rawQuery(searchsql2,null);
                if(cursor4.moveToFirst()) {
                    do {
                        String space2=cursor4.getString(5);
                        if (s.equals(space2)) {
                            Drawable d = getResources().getDrawable(R.drawable.booked);
                            sp2.setBackgroundDrawable(d);
                            sp2.setEnabled(false);
                        }
                    } while (cursor4.moveToNext());
                }
                String searchsql3="SELECT * FROM slots WHERE s3 LIKE '%"+s+"%'";
                Cursor cursor5=LoginActivity.mDatabase.rawQuery(searchsql3,null);
                if(cursor5.moveToFirst()) {
                    do {
                        String space3=cursor5.getString(6);
                        if (s.equals(space3)) {
                            Drawable d = getResources().getDrawable(R.drawable.booked);
                            sp3.setBackgroundDrawable(d);
                            sp3.setEnabled(false);
                        }
                    } while (cursor5.moveToNext());
                }
                String searchsql4="SELECT * FROM slots WHERE s4 LIKE '%"+s+"%'";
                Cursor cursor6=LoginActivity.mDatabase.rawQuery(searchsql4,null);
                if(cursor6.moveToFirst()) {
                    do {
                        String space4=cursor6.getString(7);
                        if (s.equals(space4)) {
                            Drawable d = getResources().getDrawable(R.drawable.booked);
                            sp4.setBackgroundDrawable(d);
                            sp4.setEnabled(false);
                        }
                    } while (cursor4.moveToNext());
                }
                String searchsql5="SELECT * FROM slots WHERE s5 LIKE '%"+s+"%'";
                Cursor cursor7=LoginActivity.mDatabase.rawQuery(searchsql5,null);
                if(cursor7.moveToFirst()) {
                    do {
                        String space5=cursor7.getString(8);
                        if (s.equals(space5)) {
                            Drawable d = getResources().getDrawable(R.drawable.booked);
                            sp5.setBackgroundDrawable(d);
                            sp5.setEnabled(false);
                        }
                    } while (cursor4.moveToNext());
                }
                String searchsql6="SELECT * FROM slots WHERE s6 LIKE '%"+s+"%'";
                Cursor cursor8=LoginActivity.mDatabase.rawQuery(searchsql6,null);
                if(cursor8.moveToFirst()) {
                    do {
                        String space6=cursor8.getString(9);
                        if (s.equals(space6)) {
                            Drawable d = getResources().getDrawable(R.drawable.booked);
                            sp6.setBackgroundDrawable(d);
                            sp6.setEnabled(false);
                        }
                    } while (cursor4.moveToNext());
                }
                String searchsql7="SELECT * FROM slots WHERE s7 LIKE '%"+s+"%'";
                Cursor cursor9=LoginActivity.mDatabase.rawQuery(searchsql7,null);
                if(cursor9.moveToFirst()) {
                    do {
                        String space7=cursor9.getString(10);
                        if (s.equals(space7)) {
                            Drawable d = getResources().getDrawable(R.drawable.booked);
                            sp7.setBackgroundDrawable(d);
                            sp7.setEnabled(false);
                        }
                    } while (cursor4.moveToNext());
                }
                String searchsql8="SELECT * FROM slots WHERE s8 LIKE '%"+s+"%'";
                Cursor cursor10=LoginActivity.mDatabase.rawQuery(searchsql8,null);
                if(cursor10.moveToFirst()) {
                    do {
                        String space8=cursor10.getString(11);
                        if (s.equals(space8)) {
                            Drawable d = getResources().getDrawable(R.drawable.booked);
                            sp8.setBackgroundDrawable(d);
                            sp8.setEnabled(false);
                        }
                    } while (cursor4.moveToNext());
                }
                String searchsql9="SELECT * FROM slots WHERE s9 LIKE '%"+s+"%'";
                Cursor cursor11=LoginActivity.mDatabase.rawQuery(searchsql9,null);
                if(cursor11.moveToFirst()) {
                    do {
                        String space9=cursor11.getString(12);
                        if (s.equals(space9)) {
                            Drawable d = getResources().getDrawable(R.drawable.booked);
                            sp9.setBackgroundDrawable(d);
                            sp9.setEnabled(false);
                        }
                    } while (cursor4.moveToNext());
                }
                String searchsql10="SELECT * FROM slots WHERE s10 LIKE '%"+s+"%'";
                Cursor cursor12=LoginActivity.mDatabase.rawQuery(searchsql10,null);
                if(cursor12.moveToFirst()) {
                    do {
                        String space10=cursor12.getString(13);
                        if (s.equals(space10)) {
                            Drawable d = getResources().getDrawable(R.drawable.booked);
                            sp10.setBackgroundDrawable(d);
                            sp10.setEnabled(false);
                        }
                    } while (cursor4.moveToNext());
                }
                tb.setVisibility(View.VISIBLE);
            }
        });
        sp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(Home.this);
                View mView=getLayoutInflater().inflate(R.layout.add_parking,null);
                mBuilder.setView(mView);
                final AlertDialog dialog=mBuilder.create();
                Button confirm_login=(Button)mView.findViewById(R.id.save_parking);
                Button close=(Button)mView.findViewById(R.id.closedialog);
                ed=(TextInputEditText)mView.findViewById(R.id.custname);
                ed1=(TextInputEditText)mView.findViewById(R.id.custmobile);
                ed2=(TextInputEditText)mView.findViewById(R.id.custvehicleno);

                confirm_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkcarnumber()==true){
                            if (validatename()==true){
                                if (validatemobile()){
                                    addparking1();
                                    dialog.dismiss();
                                }
                            }
                        }
                    }
                });
                dialog.setIcon(R.drawable.appicon_round);
                dialog.setTitle("Parking Details");
                dialog.show();
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });
            }
        });

        sp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(Home.this);
                View mView=getLayoutInflater().inflate(R.layout.add_parking,null);
                mBuilder.setView(mView);
                final AlertDialog dialog=mBuilder.create();
                Button confirm_login=(Button)mView.findViewById(R.id.save_parking);
                Button close=(Button)mView.findViewById(R.id.closedialog);
                ed=(TextInputEditText)mView.findViewById(R.id.custname);
                ed1=(TextInputEditText)mView.findViewById(R.id.custmobile);
                ed2=(TextInputEditText)mView.findViewById(R.id.custvehicleno);
                confirm_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkcarnumber()==true){
                            if (validatename()==true){
                                if (validatemobile()){
                                    addparking2();
                                    dialog.dismiss();
                                }
                            }
                        }
                    }
                });
                dialog.setIcon(R.drawable.appicon_round);
                dialog.setTitle("Parking Details");
                dialog.show();
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });
            }
        });

        sp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(Home.this);
                View mView=getLayoutInflater().inflate(R.layout.add_parking,null);
                mBuilder.setView(mView);
                final AlertDialog dialog=mBuilder.create();
                Button confirm_login=(Button)mView.findViewById(R.id.save_parking);
                Button close=(Button)mView.findViewById(R.id.closedialog);
                ed=(TextInputEditText)mView.findViewById(R.id.custname);
                ed1=(TextInputEditText)mView.findViewById(R.id.custmobile);
                ed2=(TextInputEditText)mView.findViewById(R.id.custvehicleno);
                confirm_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkcarnumber()==true){
                            if (validatename()==true){
                                if (validatemobile()){
                                    addparking3();
                                    dialog.dismiss();
                                }
                            }
                        }
                    }
                });
                dialog.setIcon(R.drawable.appicon_round);
                dialog.setTitle("Parking Details");
                dialog.show();
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });
            }
        });

        sp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(Home.this);
                View mView=getLayoutInflater().inflate(R.layout.add_parking,null);
                mBuilder.setView(mView);
                final AlertDialog dialog=mBuilder.create();
                Button confirm_login=(Button)mView.findViewById(R.id.save_parking);
                Button close=(Button)mView.findViewById(R.id.closedialog);
                ed=(TextInputEditText)mView.findViewById(R.id.custname);
                ed1=(TextInputEditText)mView.findViewById(R.id.custmobile);
                ed2=(TextInputEditText)mView.findViewById(R.id.custvehicleno);
                confirm_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkcarnumber()==true){
                            if (validatename()==true){
                                if (validatemobile()){
                                    addparking4();
                                    dialog.dismiss();
                                }
                            }
                        }
                    }
                });
                dialog.setIcon(R.drawable.appicon_round);
                dialog.setTitle("Parking Details");
                dialog.show();
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });
            }
        });

        sp5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(Home.this);
                View mView=getLayoutInflater().inflate(R.layout.add_parking,null);
                mBuilder.setView(mView);
                final AlertDialog dialog=mBuilder.create();
                Button confirm_login=(Button)mView.findViewById(R.id.save_parking);
                Button close=(Button)mView.findViewById(R.id.closedialog);
                ed=(TextInputEditText)mView.findViewById(R.id.custname);
                ed1=(TextInputEditText)mView.findViewById(R.id.custmobile);
                ed2=(TextInputEditText)mView.findViewById(R.id.custvehicleno);
                confirm_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkcarnumber()==true){
                            if (validatename()==true){
                                if (validatemobile()){
                                    addparking5();
                                    dialog.dismiss();
                                }
                            }
                        }
                    }
                });
                dialog.setIcon(R.drawable.appicon_round);
                dialog.setTitle("Parking Details");
                dialog.show();
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });
            }
        });

        sp6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(Home.this);
                View mView=getLayoutInflater().inflate(R.layout.add_parking,null);
                mBuilder.setView(mView);
                final AlertDialog dialog=mBuilder.create();
                Button confirm_login=(Button)mView.findViewById(R.id.save_parking);
                Button close=(Button)mView.findViewById(R.id.closedialog);
                ed=(TextInputEditText)mView.findViewById(R.id.custname);
                ed1=(TextInputEditText)mView.findViewById(R.id.custmobile);
                ed2=(TextInputEditText)mView.findViewById(R.id.custvehicleno);
                confirm_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkcarnumber()==true){
                            if (validatename()==true){
                                if (validatemobile()){
                                    addparking6();
                                    dialog.dismiss();
                                }
                            }
                        }
                    }
                });
                dialog.setIcon(R.drawable.appicon_round);
                dialog.setTitle("Parking Details");
                dialog.show();
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });
            }
        });

        sp7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(Home.this);
                View mView=getLayoutInflater().inflate(R.layout.add_parking,null);
                mBuilder.setView(mView);
                final AlertDialog dialog=mBuilder.create();
                Button confirm_login=(Button)mView.findViewById(R.id.save_parking);
                Button close=(Button)mView.findViewById(R.id.closedialog);
                ed=(TextInputEditText)mView.findViewById(R.id.custname);
                ed1=(TextInputEditText)mView.findViewById(R.id.custmobile);
                ed2=(TextInputEditText)mView.findViewById(R.id.custvehicleno);
                confirm_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkcarnumber()==true){
                            if (validatename()==true){
                                if (validatemobile()){
                                    addparking7();
                                    dialog.dismiss();
                                }
                            }
                        }
                    }
                });
                dialog.setIcon(R.drawable.appicon_round);
                dialog.setTitle("Parking Details");
                dialog.show();
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });
            }
        });

        sp8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(Home.this);
                View mView=getLayoutInflater().inflate(R.layout.add_parking,null);
                mBuilder.setView(mView);
                final AlertDialog dialog=mBuilder.create();
                Button confirm_login=(Button)mView.findViewById(R.id.save_parking);
                Button close=(Button)mView.findViewById(R.id.closedialog);
                ed=(TextInputEditText)mView.findViewById(R.id.custname);
                ed1=(TextInputEditText)mView.findViewById(R.id.custmobile);
                ed2=(TextInputEditText)mView.findViewById(R.id.custvehicleno);
                confirm_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkcarnumber()==true){
                            if (validatename()==true){
                                if (validatemobile()){
                                    addparking8();
                                    dialog.dismiss();
                                }
                            }
                        }
                    }
                });
                dialog.setIcon(R.drawable.appicon_round);
                dialog.setTitle("Parking Details");
                dialog.show();
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });
            }
        });

        sp9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(Home.this);
                View mView=getLayoutInflater().inflate(R.layout.add_parking,null);
                mBuilder.setView(mView);
                final AlertDialog dialog=mBuilder.create();
                Button confirm_login=(Button)mView.findViewById(R.id.save_parking);
                Button close=(Button)mView.findViewById(R.id.closedialog);
                ed=(TextInputEditText)mView.findViewById(R.id.custname);
                ed1=(TextInputEditText)mView.findViewById(R.id.custmobile);
                ed2=(TextInputEditText)mView.findViewById(R.id.custvehicleno);
                confirm_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkcarnumber()==true){
                            if (validatename()==true){
                                if (validatemobile()){
                                    addparking9();
                                    dialog.dismiss();
                                }
                            }
                        }
                    }
                });
                dialog.setIcon(R.drawable.appicon_round);
                dialog.setTitle("Parking Details");
                dialog.show();
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });
            }
        });

        sp10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(Home.this);
                View mView=getLayoutInflater().inflate(R.layout.add_parking,null);
                mBuilder.setView(mView);
                final AlertDialog dialog=mBuilder.create();
                Button confirm_login=(Button)mView.findViewById(R.id.save_parking);
                Button close=(Button)mView.findViewById(R.id.closedialog);
                ed=(TextInputEditText)mView.findViewById(R.id.custname);
                ed1=(TextInputEditText)mView.findViewById(R.id.custmobile);
                ed2=(TextInputEditText)mView.findViewById(R.id.custvehicleno);
                confirm_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkcarnumber()==true){
                            if (validatename()==true){
                                if (validatemobile()){
                                    addparking10();
                                    dialog.dismiss();
                                }
                            }
                        }
                    }
                });
                dialog.setIcon(R.drawable.appicon_round);
                dialog.setTitle("Parking Details");
                dialog.show();
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });
            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
      return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        if(backpress + 2000 > System.currentTimeMillis())
        {
            Intent intent=new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(),"Press Back again to Exit", Toast.LENGTH_SHORT).show();
        }
        backpress=System.currentTimeMillis();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id=menuItem.getItemId();
        if(id==R.id.profile){
            startActivity(new Intent(Home.this,Profile.class));
        }
        if (id==R.id.changepassword){
            startActivity(new Intent(Home.this,Changepassword.class));
        }
        if (id==R.id.viewparking){
            startActivity(new Intent(Home.this,ViewParking.class));
        }
        if(id==R.id.aboutus) {
            startActivity(new Intent(Home.this,About.class));
        }
        if(id==R.id.logout){
            try{
                LoginActivity.mDatabase.execSQL(Log_Out_query);
                startActivity(new Intent(this,LoginActivity.class));
                finish();
                Toast.makeText(getApplicationContext(),"Logout Successful",Toast.LENGTH_SHORT).show();
            }
            catch (Exception e)
            {
                Toast.makeText(getApplicationContext(),"Cannot Logout", Toast.LENGTH_SHORT).show();
            }
        }
        if (id==R.id.feedback){
            startActivity(new Intent(Home.this,Feedback.class));
        }
        return false;
    }
    public boolean checkcarnumber(){
        String carno=ed2.getText().toString().trim();
        if(carno.isEmpty()){
            ed2.setError("Please enter Car Number");
            return false;
        }else if(!CARNUMBER_PATTERN.matcher(carno).matches()){
            ed2.setError("Please enter valid Car Number");
            return false;
        }else{
            ed2.setError(null);
            return true;
        }
    }
    public boolean validatename() {
        String name = ed.getText().toString().trim();
        if (name.isEmpty()) {
            ed.setError("Please enter Customer Full name!");
            ed.requestFocus();
            return false;
        } else {
            ed.setError(null);
            return true;
        }
    }
    public boolean validatemobile(){
        String mobile=ed1.getText().toString().trim();
        if (mobile.isEmpty())
        {
            ed1.setError("Please enter Mobile Number");
            ed1.requestFocus();
            return false;
        }
        else if (ed1.length()!=10)
        {
            ed1.setError("Please enter valid Mobile Number");
            ed1.requestFocus();
            return false;
        }else{
            ed1.setError(null);
            return true;
        }
    }
    public void addparking1(){
        String insertSQL = "INSERT INTO parking \n" +
                "(date,vehicle_number,customer_name,customer_mobile,in_time,out_time,fare,status)\n" +
                "VALUES \n" +
                "(?,?,?,?,?,?,?,?);";
        String sql="INSERT INTO slots \n"+
                "(vehicle_number,date,in_time,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10)\n"+
                "VALUES \n"+
                "(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        String vehno=ed2.getText().toString().trim();
        String parkdate=edt_Date.getText().toString().trim();
        String updateslot="UPDATE slots SET s1='"+s+"' WHERE vehicle_number='"+vehno+"'";
        try
        {
            LoginActivity.mDatabase.execSQL(insertSQL,new String[]{edt_Date.getText().toString().trim(),ed2.getText().toString().trim(),ed.getText().toString().trim(),ed1.getText().toString().trim(),edt_time.getText().toString().trim(),null,null,z});
            LoginActivity.mDatabase.execSQL(sql,new String[]{ed2.getText().toString().trim(),edt_Date.getText().toString().trim(),edt_time.getText().toString().trim(),null,null,null,null,null,null,null,null,null,null});
            Toast.makeText(getApplicationContext(),"Parking reserved Successfully",Toast.LENGTH_SHORT).show();
            ed.setText("");
            ed1.setText("");
            ed2.setText("");

                    try {
                        LoginActivity.mDatabase.execSQL(updateslot);
                        Toast.makeText(getApplicationContext(), "Slot Updated Successfully", Toast.LENGTH_SHORT).show();
                        Drawable d=getResources().getDrawable(R.drawable.selected);
                        sp1.setBackgroundDrawable(d);
                        sp1.setEnabled(false);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed to Update Slot", Toast.LENGTH_SHORT).show();
                    }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
        }
    }
    public void addparking2(){
        String insertSQL = "INSERT INTO parking \n" +
                "(date,vehicle_number,customer_name,customer_mobile,in_time,out_time,fare,status)\n" +
                "VALUES \n" +
                "(?,?,?,?,?,?,?,?);";
        String sql="INSERT INTO slots \n"+
                "(vehicle_number,date,in_time,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10)\n"+
                "VALUES \n"+
                "(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        String vehno=ed2.getText().toString().trim();
        String updateslot="UPDATE slots SET s2='"+s+"' WHERE vehicle_number='"+vehno+"'";
        try
        {
            LoginActivity.mDatabase.execSQL(insertSQL,new String[]{edt_Date.getText().toString().trim(),ed2.getText().toString().trim(),ed.getText().toString().trim(),ed1.getText().toString().trim(),edt_time.getText().toString().trim(),null,null,z});
            LoginActivity.mDatabase.execSQL(sql,new String[]{ed2.getText().toString().trim(),edt_Date.getText().toString().trim(),edt_time.getText().toString().trim(),null,null,null,null,null,null,null,null,null,null});
            Toast.makeText(getApplicationContext(),"Parking reserved Successfully",Toast.LENGTH_SHORT).show();
            ed.setText("");
            ed1.setText("");
            ed2.setText("");

            try {
                LoginActivity.mDatabase.execSQL(updateslot);
                Toast.makeText(getApplicationContext(), "Slot Updated Successfully", Toast.LENGTH_SHORT).show();
                Drawable d=getResources().getDrawable(R.drawable.selected);
                sp2.setBackgroundDrawable(d);
                sp2.setEnabled(false);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to Update Slot", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
        }
    }
    public void addparking3(){
        String insertSQL = "INSERT INTO parking \n" +
                "(date,vehicle_number,customer_name,customer_mobile,in_time,out_time,fare,status)\n" +
                "VALUES \n" +
                "(?,?,?,?,?,?,?,?);";
        String sql="INSERT INTO slots \n"+
                "(vehicle_number,date,in_time,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10)\n"+
                "VALUES \n"+
                "(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        String vehno=ed2.getText().toString().trim();
        String updateslot="UPDATE slots SET s3='"+s+"' WHERE vehicle_number='"+vehno+"'";
        try
        {
            LoginActivity.mDatabase.execSQL(insertSQL,new String[]{edt_Date.getText().toString().trim(),ed2.getText().toString().trim(),ed.getText().toString().trim(),ed1.getText().toString().trim(),edt_time.getText().toString().trim(),null,null,z});
            LoginActivity.mDatabase.execSQL(sql,new String[]{ed2.getText().toString().trim(),edt_Date.getText().toString().trim(),edt_time.getText().toString().trim(),null,null,null,null,null,null,null,null,null,null});
            Toast.makeText(getApplicationContext(),"Parking reserved Successfully",Toast.LENGTH_SHORT).show();
            ed.setText("");
            ed1.setText("");
            ed2.setText("");

            try {
                LoginActivity.mDatabase.execSQL(updateslot);
                Toast.makeText(getApplicationContext(), "Slot Updated Successfully", Toast.LENGTH_SHORT).show();
                Drawable d=getResources().getDrawable(R.drawable.selected);
                sp3.setBackgroundDrawable(d);
                sp3.setEnabled(false);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to Update Slot", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
        }
    }
    public void addparking4(){
        String insertSQL = "INSERT INTO parking \n" +
                "(date,vehicle_number,customer_name,customer_mobile,in_time,out_time,fare,status)\n" +
                "VALUES \n" +
                "(?,?,?,?,?,?,?,?);";
        String sql="INSERT INTO slots \n"+
                "(vehicle_number,date,in_time,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10)\n"+
                "VALUES \n"+
                "(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        String vehno=ed2.getText().toString().trim();
        String updateslot="UPDATE slots SET s4='"+s+"' WHERE vehicle_number='"+vehno+"'";
        try
        {
            LoginActivity.mDatabase.execSQL(insertSQL,new String[]{edt_Date.getText().toString().trim(),ed2.getText().toString().trim(),ed.getText().toString().trim(),ed1.getText().toString().trim(),edt_time.getText().toString().trim(),null,null,z});
            LoginActivity.mDatabase.execSQL(sql,new String[]{ed2.getText().toString().trim(),edt_Date.getText().toString().trim(),edt_time.getText().toString().trim(),null,null,null,null,null,null,null,null,null,null});
            Toast.makeText(getApplicationContext(),"Parking reserved Successfully",Toast.LENGTH_SHORT).show();
            ed.setText("");
            ed1.setText("");
            ed2.setText("");

            try {
                LoginActivity.mDatabase.execSQL(updateslot);
                Toast.makeText(getApplicationContext(), "Slot Updated Successfully", Toast.LENGTH_SHORT).show();
                Drawable d=getResources().getDrawable(R.drawable.selected);
                sp4.setBackgroundDrawable(d);
                sp4.setEnabled(false);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to Update Slot", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
        }
    }
    public void addparking5(){
        String insertSQL = "INSERT INTO parking \n" +
                "(date,vehicle_number,customer_name,customer_mobile,in_time,out_time,fare,status)\n" +
                "VALUES \n" +
                "(?,?,?,?,?,?,?,?);";
        String sql="INSERT INTO slots \n"+
                "(vehicle_number,date,in_time,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10)\n"+
                "VALUES \n"+
                "(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        String vehno=ed2.getText().toString().trim();
        String updateslot="UPDATE slots SET s5='"+s+"' WHERE vehicle_number='"+vehno+"'";
        try
        {
            LoginActivity.mDatabase.execSQL(insertSQL,new String[]{edt_Date.getText().toString().trim(),ed2.getText().toString().trim(),ed.getText().toString().trim(),ed1.getText().toString().trim(),edt_time.getText().toString().trim(),null,null,z});
            LoginActivity.mDatabase.execSQL(sql,new String[]{ed2.getText().toString().trim(),edt_Date.getText().toString().trim(),edt_time.getText().toString().trim(),null,null,null,null,null,null,null,null,null,null});
            Toast.makeText(getApplicationContext(),"Parking reserved Successfully",Toast.LENGTH_SHORT).show();
            ed.setText("");
            ed1.setText("");
            ed2.setText("");

            try {
                LoginActivity.mDatabase.execSQL(updateslot);
                Toast.makeText(getApplicationContext(), "Slot Updated Successfully", Toast.LENGTH_SHORT).show();
                Drawable d=getResources().getDrawable(R.drawable.selected);
                sp5.setBackgroundDrawable(d);
                sp5.setEnabled(false);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to Update Slot", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
        }
    }
    public void addparking6(){
        String insertSQL = "INSERT INTO parking \n" +
                "(date,vehicle_number,customer_name,customer_mobile,in_time,out_time,fare,status)\n" +
                "VALUES \n" +
                "(?,?,?,?,?,?,?,?);";
        String sql="INSERT INTO slots \n"+
                "(vehicle_number,date,in_time,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10)\n"+
                "VALUES \n"+
                "(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        String vehno=ed2.getText().toString().trim();
        String updateslot="UPDATE slots SET s6='"+s+"' WHERE vehicle_number='"+vehno+"'";
        try
        {
            LoginActivity.mDatabase.execSQL(insertSQL,new String[]{edt_Date.getText().toString().trim(),ed2.getText().toString().trim(),ed.getText().toString().trim(),ed1.getText().toString().trim(),edt_time.getText().toString().trim(),null,null,z});
            LoginActivity.mDatabase.execSQL(sql,new String[]{ed2.getText().toString().trim(),edt_Date.getText().toString().trim(),edt_time.getText().toString().trim(),null,null,null,null,null,null,null,null,null,null});
            Toast.makeText(getApplicationContext(),"Parking reserved Successfully",Toast.LENGTH_SHORT).show();
            ed.setText("");
            ed1.setText("");
            ed2.setText("");

            try {
                LoginActivity.mDatabase.execSQL(updateslot);
                Toast.makeText(getApplicationContext(), "Slot Updated Successfully", Toast.LENGTH_SHORT).show();
                Drawable d=getResources().getDrawable(R.drawable.selected);
                sp6.setBackgroundDrawable(d);
                sp6.setEnabled(false);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to Update Slot", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
        }
    }
    public void addparking7(){
        String insertSQL = "INSERT INTO parking \n" +
                "(date,vehicle_number,customer_name,customer_mobile,in_time,out_time,fare,status)\n" +
                "VALUES \n" +
                "(?,?,?,?,?,?,?);";
        String sql="INSERT INTO slots \n"+
                "(vehicle_number,date,in_time,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10)\n"+
                "VALUES \n"+
                "(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        String vehno=ed2.getText().toString().trim();
        String updateslot="UPDATE slots SET s7='"+s+"' WHERE vehicle_number='"+vehno+"'";
        try
        {
            LoginActivity.mDatabase.execSQL(insertSQL,new String[]{edt_Date.getText().toString().trim(),ed2.getText().toString().trim(),ed.getText().toString().trim(),ed1.getText().toString().trim(),edt_time.getText().toString().trim(),null,null,z});
            LoginActivity.mDatabase.execSQL(sql,new String[]{ed2.getText().toString().trim(),edt_Date.getText().toString().trim(),edt_time.getText().toString().trim(),null,null,null,null,null,null,null,null,null,null});
            Toast.makeText(getApplicationContext(),"Parking reserved Successfully",Toast.LENGTH_SHORT).show();
            ed.setText("");
            ed1.setText("");
            ed2.setText("");

            try {
                LoginActivity.mDatabase.execSQL(updateslot);
                Toast.makeText(getApplicationContext(), "Slot Updated Successfully", Toast.LENGTH_SHORT).show();
                Drawable d=getResources().getDrawable(R.drawable.selected);
                sp7.setBackgroundDrawable(d);
                sp7.setEnabled(false);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to Update Slot", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
        }
    }
    public void addparking8(){
        String insertSQL = "INSERT INTO parking \n" +
                "(date,vehicle_number,customer_name,customer_mobile,in_time,out_time,fare,status)\n" +
                "VALUES \n" +
                "(?,?,?,?,?,?,?,?);";
        String sql="INSERT INTO slots \n"+
                "(vehicle_number,date,in_time,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10)\n"+
                "VALUES \n"+
                "(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        String vehno=ed2.getText().toString().trim();
        String updateslot="UPDATE slots SET s8='"+s+"' WHERE vehicle_number='"+vehno+"'";
        try
        {
            LoginActivity.mDatabase.execSQL(insertSQL,new String[]{edt_Date.getText().toString().trim(),ed2.getText().toString().trim(),ed.getText().toString().trim(),ed1.getText().toString().trim(),edt_time.getText().toString().trim(),null,null,z});
            LoginActivity.mDatabase.execSQL(sql,new String[]{ed2.getText().toString().trim(),edt_Date.getText().toString().trim(),edt_time.getText().toString().trim(),null,null,null,null,null,null,null,null,null,null});
            Toast.makeText(getApplicationContext(),"Parking reserved Successfully",Toast.LENGTH_SHORT).show();
            ed.setText("");
            ed1.setText("");
            ed2.setText("");

            try {
                LoginActivity.mDatabase.execSQL(updateslot);
                Toast.makeText(getApplicationContext(), "Slot Updated Successfully", Toast.LENGTH_SHORT).show();
                Drawable d=getResources().getDrawable(R.drawable.selected);
                sp8.setBackgroundDrawable(d);
                sp8.setEnabled(false);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to Update Slot", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
        }
    }
    public void addparking9(){
        String insertSQL = "INSERT INTO parking \n" +
                "(date,vehicle_number,customer_name,customer_mobile,in_time,out_time,fare,status)\n" +
                "VALUES \n" +
                "(?,?,?,?,?,?,?,?);";
        String sql="INSERT INTO slots \n"+
                "(vehicle_number,date,in_time,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10)\n"+
                "VALUES \n"+
                "(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        String vehno=ed2.getText().toString().trim();
        String updateslot="UPDATE slots SET s9='"+s+"' WHERE vehicle_number='"+vehno+"'";
        try
        {
            LoginActivity.mDatabase.execSQL(insertSQL,new String[]{edt_Date.getText().toString().trim(),ed2.getText().toString().trim(),ed.getText().toString().trim(),ed1.getText().toString().trim(),edt_time.getText().toString().trim(),null,null,z});
            LoginActivity.mDatabase.execSQL(sql,new String[]{ed2.getText().toString().trim(),edt_Date.getText().toString().trim(),edt_time.getText().toString().trim(),null,null,null,null,null,null,null,null,null,null});
            Toast.makeText(getApplicationContext(),"Parking reserved Successfully",Toast.LENGTH_SHORT).show();
            ed.setText("");
            ed1.setText("");
            ed2.setText("");

            try {
                LoginActivity.mDatabase.execSQL(updateslot);
                Toast.makeText(getApplicationContext(), "Slot Updated Successfully", Toast.LENGTH_SHORT).show();
                Drawable d=getResources().getDrawable(R.drawable.selected);
                sp9.setBackgroundDrawable(d);
                sp9.setEnabled(false);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to Update Slot", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
        }
    }
    public void addparking10(){
        String insertSQL = "INSERT INTO parking \n" +
                "(date,vehicle_number,customer_name,customer_mobile,in_time,out_time,fare,status)\n" +
                "VALUES \n" +
                "(?,?,?,?,?,?,?,?);";
        String sql="INSERT INTO slots \n"+
                "(vehicle_number,date,in_time,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10)\n"+
                "VALUES \n"+
                "(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        String vehno=ed2.getText().toString().trim();
        String updateslot="UPDATE slots SET s10='"+s+"' WHERE vehicle_number='"+vehno+"'";
        try
        {
            LoginActivity.mDatabase.execSQL(insertSQL,new String[]{edt_Date.getText().toString().trim(),ed2.getText().toString().trim(),ed.getText().toString().trim(),ed1.getText().toString().trim(),edt_time.getText().toString().trim(),null,null,z});
            LoginActivity.mDatabase.execSQL(sql,new String[]{ed2.getText().toString().trim(),edt_Date.getText().toString().trim(),edt_time.getText().toString().trim(),null,null,null,null,null,null,null,null,null,null});
            Toast.makeText(getApplicationContext(),"Parking reserved Successfully",Toast.LENGTH_SHORT).show();
            ed.setText("");
            ed1.setText("");
            ed2.setText("");

            try {
                LoginActivity.mDatabase.execSQL(updateslot);
                Toast.makeText(getApplicationContext(), "Slot Updated Successfully", Toast.LENGTH_SHORT).show();
                Drawable d=getResources().getDrawable(R.drawable.selected);
                sp10.setBackgroundDrawable(d);
                sp10.setEnabled(false);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to Update Slot", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
        }
    }

    public void initialize(){
        sp1=(Button)findViewById(R.id.s1);
        sp2=(Button)findViewById(R.id.s2);
        sp3=(Button)findViewById(R.id.s3);
        sp4=(Button)findViewById(R.id.s4);
        sp5=(Button)findViewById(R.id.s5);
        sp6=(Button)findViewById(R.id.s6);
        sp7=(Button)findViewById(R.id.s7);
        sp8=(Button)findViewById(R.id.s8);
        sp9=(Button)findViewById(R.id.s9);
        sp10=(Button)findViewById(R.id.s10);
    }

}
