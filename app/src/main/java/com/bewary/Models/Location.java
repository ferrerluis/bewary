package com.bewary.Models;

import com.google.android.gms.maps.model.LatLng;

public class Location {
    private int id;
    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        this.id = 0;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public LatLng getLatLng(){
        return new LatLng(getLatitude(), getLongitude());
    }
}
