package com.example.tyler.jpm;


import java.io.Serializable;

/**
 * Created by Tyler on 1/9/2017.
 * Container for detailed information response from api
 * serialzable for ease of use to pass as an extra
 */
public class AtmJson implements Serializable {


    String state;
    String locType;
    String label;
    String address;
    String city;
    String zip;
    String name;
    String lat;
    String lng;
    String bank;
    String type;
    int atms;
    String phone;
    double distance;
    //lobby and driveUpHours

    //Overriding toString so this object can use the default ArrayAdapter
    @Override
    public String toString(){
        return label;
    }
}
