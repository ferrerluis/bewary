package com.bewary.Models;

import com.google.android.gms.maps.model.LatLng;
import com.parse.ParseGeoPoint;

public class Location {

    private double latitude;
    private double longitude;

    public Location() {
    }

    public Location(double latitude, double longitude) {

        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public ParseGeoPoint getGeoPoint() {

        return new ParseGeoPoint(getLatitude(), getLongitude());
    }

    public LatLng getLatLng(){
        return new LatLng(getLatitude(), getLongitude());
    }
}
