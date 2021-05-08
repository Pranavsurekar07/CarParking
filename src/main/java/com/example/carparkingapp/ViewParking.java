package com.example.carparkingapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ViewParking extends AppCompatActivity {
    SQLiteDatabase mDatabase;
    List<Customer> customerList;
    EditText searchvehicle;
    Button search;
    CustomerAdapter adapter;
    TextView fare;
    ListView listView;
    ArrayList<Customer> listItem;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_parking);

        fare = (TextView) findViewById(R.id.textViewFare);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" View Parking");
        getSupportActionBar().setIcon(R.drawable.ic_visibility_button);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDatabase = openOrCreateDatabase(MainActivity.DATABASE_NAME, MODE_PRIVATE, null);
        customerList = new ArrayList<>();
        listItem=new ArrayList<>();
        listView = (ListView) findViewById(R.id.list_customer);
        searchvehicle=(EditText)findViewById(R.id.vehicle_no);
        search=(Button)findViewById(R.id.search);
        loadCustomersFromDatabase();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSearchCustomerFromDatabase();
            }
        });

    }

    private void loadCustomersFromDatabase() {
        String sta = "zero";
        String sql = "SELECT * FROM parking WHERE status='" + sta + "'";
        Cursor cursor = mDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                customerList.add(new Customer(
                        cursor.getInt(0),
                        cursor.getInt(4),
                        cursor.getString(3),
                        cursor.getString(2),
                        cursor.getString(1),
                        cursor.getString(7),
                        cursor.getString(5),
                        cursor.getString(6)
                ));
            } while (cursor.moveToNext());

            CustomerAdapter adapter = new CustomerAdapter(this, R.layout.list_layout_customer, customerList, mDatabase);
            listView.setAdapter(adapter);
        }
    }
    private void loadSearchCustomerFromDatabase() {
        String sta = "zero";
        String search=searchvehicle.getText().toString().trim();
        String sql1 = "SELECT * FROM parking WHERE vehicle_number='" + search + "' AND status='"+sta+"'";
        Cursor cursor = mDatabase.rawQuery(sql1, null);
        if (cursor.moveToFirst()) {
            customerList.clear();
            do {
                if (search.isEmpty()){
                    searchvehicle.setError("Car Number required !");
                    searchvehicle.requestFocus();
                }
                else{
                    if (cursor.getCount()<0){
                            Toast.makeText(ViewParking.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        customerList.add(new Customer(
                                cursor.getInt(0),
                                cursor.getInt(4),
                                cursor.getString(3),
                                cursor.getString(2),
                                cursor.getString(1),
                                cursor.getString(7),
                                cursor.getString(5),
                                cursor.getString(6)
                        ));
                    }
                }

            } while (cursor.moveToNext());

            CustomerAdapter adapter = new CustomerAdapter(this, R.layout.list_layout_customer, customerList, mDatabase);
            listView.setAdapter(adapter);
        }


    }
}
