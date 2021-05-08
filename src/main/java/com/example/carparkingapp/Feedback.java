package com.example.carparkingapp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class Feedback extends AppCompatActivity {
    RatingBar star;
    Button btn;
    TextView ratin;
    TextInputEditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" Feedback");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_feedback_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LoginActivity.mDatabase=openOrCreateDatabase(MainActivity.DATABASE_NAME,MODE_PRIVATE,null);

        ed=(TextInputEditText) findViewById(R.id.feedback);
        star=(RatingBar)findViewById(R.id.ratingbar);
        btn=(Button)findViewById(R.id.feedback_btn);
        ratin=(TextView)findViewById(R.id.selectedrating);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float star_rating=star.getRating();
                String typing=ed.getText().toString();
                StringBuffer feedback=new StringBuffer();
                feedback.append(typing);
                feedback.append("\n\n\nRating : ");
                feedback.append(star_rating);
                String Feedback=feedback.toString();
                String[] To={"pranavsurekar00@gmail.com"};
                String Subject="Feedback of Car Parking Application";

                Intent i=new Intent(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_EMAIL,To);
                i.putExtra(Intent.EXTRA_SUBJECT,Subject);
                i.putExtra(Intent.EXTRA_TEXT,Feedback);
                i.setType("text/email");
                startActivity(Intent.createChooser(i,"Choose an email client"));
            }
        });
        star.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratin.setText("Selected Rating is:"+String.valueOf(rating));
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Feedback.this,Home.class));
        finish();
    }
}
