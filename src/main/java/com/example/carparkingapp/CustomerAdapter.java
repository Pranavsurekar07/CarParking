package com.example.carparkingapp;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.datatype.Duration;

public class CustomerAdapter extends ArrayAdapter<Customer> {
    Context mCtx;
    int layoutRes;
    List<Customer>customerList;
    SQLiteDatabase mDatabase;
    TextView ed;
    int hours,mins;
    double totalcharges,costperhour;
    TextView ed1;
    ArrayList<Customer>arrayList;
    public CustomerAdapter(Context mCtx, int layoutRes, List<Customer>customerList,SQLiteDatabase mDatabase){
        super(mCtx,layoutRes,customerList);
        this.mCtx=mCtx;
        this.layoutRes=layoutRes;
        this.customerList=customerList;
        this.mDatabase=mDatabase;
        this.arrayList=new ArrayList<Customer>();
        this.arrayList.addAll(customerList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final LayoutInflater inflater=LayoutInflater.from(mCtx);

        View view=inflater.inflate(layoutRes,null);
        TextView textViewName=view.findViewById(R.id.textViewName);
        TextView textViewMobile=view.findViewById(R.id.textViewMobile);
        TextView textViewVehicleNumber=view.findViewById(R.id.textViewVehicleNumber);
        TextView textViewDate=view.findViewById(R.id.textViewDate);
        final TextView textViewInTime=view.findViewById(R.id.textViewInTime);
        Button edittime=view.findViewById(R.id.buttonEditEmployee);
        Button delete=view.findViewById(R.id.buttonDeleteEmployee);
        TextView textViewOutTime=view.findViewById(R.id.textViewOutTime);
        final TextView textViewFare=view.findViewById(R.id.textViewFare);

        final Customer customer=customerList.get(position);

        textViewName.setText("Name :"+customer.getName());
        textViewMobile.setText("Mobile :"+String.valueOf(customer.getMobile()));
        textViewVehicleNumber.setText("Date:"+customer.getVehiclenumber());
        textViewDate.setText("Car Number :"+customer.getDate());
        textViewInTime.setText("In-Time :"+customer.getIntime());
        textViewOutTime.setText("Out-Time :"+customer.getOuttime());
        textViewFare.setText("Fare :"+customer.getFare());
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(mCtx);
                builder.setTitle("Car Parking");
                builder.setMessage("Are you sure ?");
                builder.setIcon(R.drawable.appicon_round);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteCustomer(customer);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

        edittime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(mCtx);
                View mView=inflater.inflate(R.layout.out_time,null);
                mBuilder.setView(mView);
                final AlertDialog dialog=mBuilder.create();
                final Button confirm_login=(Button)mView.findViewById(R.id.save_outtime);
                Button close=(Button)mView.findViewById(R.id.closedialog);
                Button calculate=(Button)mView.findViewById(R.id.calculate);
                ed=(TextView)mView.findViewById(R.id.outtime);
                ed1=(TextView)mView.findViewById(R.id.fare);
                final TextView intime=(TextView)mView.findViewById(R.id.intime);
                intime.setText(customer.getIntime());
                confirm_login.setEnabled(false);


                ed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        Calendar mcurrentTime = Calendar.getInstance();
                        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                        int minute = mcurrentTime.get(Calendar.MINUTE);

                        TimePickerDialog mTimePicker;
                        mTimePicker = new TimePickerDialog(mCtx, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                ed.setText(selectedHour+" : "+ selectedMinute);
                            }
                        }, hour, minute, true);//Yes 24 hour time

                        mTimePicker.setTitle("Select Time");
                        mTimePicker.setIcon(R.drawable.ic_access_time_black_24dp);
                        mTimePicker.show();
                    }
                });
                calculate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String w = ed.getText().toString().trim();
                        if (w.isEmpty()) {
                            ed.setError("Please Select time to calculate charges");
                            ed.requestFocus();
                            Toast.makeText(mCtx, "Please select time", Toast.LENGTH_LONG).show();
                            confirm_login.setEnabled(false);
                        } else {
                            String time1 = customer.getIntime();
                            String time2 = ed.getText().toString().trim();
                            confirm_login.setEnabled(true);
                            SimpleDateFormat format = new SimpleDateFormat("hh : mm");
                            try {
                                Date date1 = format.parse(time1);
                                Date date2 = format.parse(time2);
                                long difference = date2.getTime() - date1.getTime();
                                hours = (int) (difference) / (60 * 60 * 1000) % 24;
                                mins = (int) difference / (60 * 1000) % 60;
                                String diff = hours + ":" + mins;


                            } catch (ParseException e) {              // Insert this block.
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            costperhour=10;
                            totalcharges=costperhour*hours;
                            if (hours<0){
                                int newhours=Math.abs(-hours);
                                double newc=newhours*costperhour;
                                ed1.setText(String.valueOf(newc)+" Rs");
                            }

                            if (hours==0 && mins<=59 ){
                                ed1.setText("5.0 Rs");
                            }
                            else if (hours>=1 && hours<=23){
                                ed1.setText(String.valueOf(totalcharges)+" Rs");
                            }
                            else if (hours > 23) {
                                ed1.setText("250.0 Rs");
                            }
                        }
                    }
                });
                confirm_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateCustomer(customer);

                    }
                });
                dialog.setIcon(R.drawable.appicon_round);
                dialog.setTitle("Out-Time Details");
                dialog.show();
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });
            }
        });

        return view;
    }

    private void updateCustomer(Customer customer) {
        String outtime=ed.getText().toString().trim();
        String fare=ed1.getText().toString().trim();
        String updateSql="UPDATE parking SET out_time=? , fare=? WHERE in_time=?";
        mDatabase.execSQL(updateSql,new String[]{outtime,fare,customer.getIntime()});
        Toast.makeText(mCtx, "Updated Successfully", Toast.LENGTH_SHORT).show();
        reloadCustomerFromDatabase();
    }
    private void deleteCustomer(Customer customer) {

                String del="delet";
               String deleteSql="DELETE FROM slots WHERE vehicle_number=?";
                String updateparking="UPDATE parking SET status='"+del+"' WHERE vehicle_number=?";
                    mDatabase.execSQL(deleteSql,new String[]{customer.getDate()});
                    mDatabase.execSQL(updateparking,new String[]{customer.getDate()});
                    Toast.makeText(mCtx, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    reloadCustomerFromDatabase();
    }
    private void reloadCustomerFromDatabase(){
        String sta="zero";
        String sql="SELECT * FROM parking WHERE status='"+sta+"'";
        Cursor cursor=mDatabase.rawQuery(sql,null);
        if(cursor.moveToFirst()) {
            customerList.clear();
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
        }
        cursor.close();
        notifyDataSetChanged();
    }
}
