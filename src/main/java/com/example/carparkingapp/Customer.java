package com.example.carparkingapp;

public class Customer {
    int id,mobile;
    String name,date,vehiclenumber,fare,intime,outtime;

    public Customer(int id, int mobile, String name, String date, String vehiclenumber, String fare, String intime, String outtime) {
        this.id = id;
        this.mobile = mobile;
        this.name = name;
        this.date = date;
        this.vehiclenumber = vehiclenumber;
        this.fare = fare;
        this.intime = intime;
        this.outtime = outtime;
    }

    public int getId() {
        return id;
    }

    public int getMobile() {
        return mobile;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getVehiclenumber() {
        return vehiclenumber;
    }

    public String getFare() {
        return fare;
    }

    public String getIntime() {
        return intime;
    }

    public String getOuttime() {
        return outtime;
    }
}
