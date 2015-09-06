package com.bewary.Map;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.bewary.Listeners.OnEventClickListener;
import com.bewary.Models.Event;
import com.bewary.Models.EventType;
import com.bewary.Models.User;
import com.bewary.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.*;

import java.util.ArrayList;
import java.util.Date;

public class MapActivity extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            mMap.setMyLocationEnabled(true);

            Log.d("initializing map", "d");
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        while(mLastLocation == null){
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            Log.d("maps", "Tring to get map");
        }

        setCurrentLocationMarker();

        ArrayList<Event> events = new ArrayList<>();

        events.add(new Event(
                new EventType("Homelessness", 1),
                new com.bewary.Models.Location(mLastLocation.getLatitude()- .25, mLastLocation.getLongitude() + .35),
                new Date(),
                new User("jane", "bob@gmail.com", "image.jpg"),
                "Sarah Taylor was harassed by a homeless person yesterday. Take 4th Ave. to avoid this area."));

        events.add(new Event(
                new EventType("Robbery", 2),
                new com.bewary.Models.Location(mLastLocation.getLatitude() + .15, mLastLocation.getLongitude() - .15),
                new Date(),
                new User("john", "jogn@gmail.com", "image.jpg"),
                "Dan Brown was robbed earlier this week and the perpetrator is still at large. You might  want to avoid this area today."));

        mMap.setOnMapClickListener(new OnEventClickListener(events, findViewById(R.id.mapp_view_root), this));

        for(Event event: events){
            int color;
            switch(event.getEventType().getLevel()){
                case 1:
                    color = Color.argb(85, 255, 255, 148);
                    break;
                default:
                    color = Color.argb(85, 255,0,0);
            }
            CircleOptions circleOptions = new CircleOptions()
                    .fillColor(color)
                    .strokeColor(color)
                    .center(new LatLng(event.getLocation().getLatitude(), event.getLocation().getLongitude()))
                    .radius(900.34); // In meters

            mMap.addMarker(new MarkerOptions().position(event.getLocation().getLatLng()).title("You"));
            mMap.addCircle(circleOptions);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("googleapi-s", String.valueOf(i));
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("googleapi", "failed map".concat(connectionResult.toString()));
    }

    private void setCurrentLocationMarker() {
        final LatLng position = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        mMap.addMarker(new MarkerOptions().position(position).title("You")).showInfoWindow();

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(position)
                .zoom(12.5f)
                .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}