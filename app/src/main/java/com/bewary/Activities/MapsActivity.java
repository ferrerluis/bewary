package com.bewary.Activities;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.bewary.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {

    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            mMap.setMyLocationEnabled(true);
            if (mMap != null) {
                mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {

                    @Override
                    public void onMyLocationChange(Location arg0) {
                        mMap.clear();

                        final LatLng position = new LatLng(arg0.getLatitude(), arg0.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(position).title("It's Me!"));

                        mMap.animateCamera(CameraUpdateFactory.zoomTo(14.0f), 2000, new GoogleMap.CancelableCallback() {
                            @Override
                            public void onFinish() {
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
                            }

                            @Override
                            public void onCancel() {

                            }
                        });
                    }
                });

            }
        }
    }
}