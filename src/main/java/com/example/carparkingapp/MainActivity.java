package com.example.carparkingapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;


public class MainActivity extends AwesomeSplash {
    public static final int login=1;
    public static final int logout=0;
    public static final String DATABASE_NAME="mydatabase.db";

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase=openOrCreateDatabase(MainActivity.DATABASE_NAME,MODE_PRIVATE,null);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent I= new Intent(MainActivity.this,LoginActivity.class);
                startActivity(I);
                finish();
            }
        }, SPLASH_TIME_OUT);


    }*/

    @Override
    public void initSplash(ConfigSplash configSplash) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        configSplash.setBackgroundColor(R.color.black);
        configSplash.setAnimCircularRevealDuration(2000);
        configSplash.setRevealFlagX(Flags.REVEAL_LEFT);
        configSplash.setRevealFlagX(Flags.REVEAL_BOTTOM);

        configSplash.setLogoSplash(R.drawable.appicon_round);
        configSplash.setAnimLogoSplashDuration(2000);
        configSplash.setAnimLogoSplashTechnique(Techniques.Flash);

        configSplash.setTitleSplash("CAR PARKING");
        configSplash.setAnimTitleDuration(1000);
        configSplash.setAnimTitleTechnique(Techniques.FadeIn);

    }

    @Override
    public void animationsFinished() {
        Intent home=new Intent(MainActivity.this,LoginActivity.class);
        startActivity(home);
        finish();
    }


}
