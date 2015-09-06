package com.bewary.Listeners;


import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bewary.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class OnIntentToCreateEvent implements GoogleMap.OnMapLongClickListener{
    private FragmentActivity context;
    private View mapView;

    public OnIntentToCreateEvent(View mapView, FragmentActivity context) {
        this.context = context;
        this.mapView = mapView;
    }

    @Override
    public void onMapLongClick(final LatLng latLng) {
        Geocoder gcd = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = gcd.getFromLocation(latLng.latitude, latLng.longitude, 1);
            View verifyView = View.inflate(context, R.layout.view_verify_location, null);
            ((TextView) verifyView.findViewById(R.id.verify_text))
                    .setText("Is this address correct: " + addresses.get(0).getAddressLine(0) + " " + addresses.get(0).getAddressLine(1) + "?");

            ((ViewGroup) mapView.findViewById(R.id.map_top_info_pane)).addView(verifyView);

            verifyView.findViewById(R.id.verify_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ViewGroup) mapView.findViewById(R.id.map_top_info_pane)).removeAllViews();
                }
            });

            verifyView.findViewById(R.id.verify_confirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ViewGroup) mapView.findViewById(R.id.map_top_info_pane)).removeAllViews();
                    GoogleMap map = ((SupportMapFragment) context.getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
                    map.addMarker(new MarkerOptions().position(latLng));
                    CircleOptions circleOptions = new CircleOptions()
                            .fillColor(Color.argb(85, 255, 255, 148))
                            .strokeColor(Color.argb(85, 255, 255, 148))
                            .center(new LatLng(latLng.latitude,latLng.longitude))
                            .radius(900.34); // In meters

                    map.addCircle(circleOptions);
                    ((ViewGroup) mapView.findViewById(R.id.map_top_info_pane)).addView(View.inflate(context, R.layout.form_create_event, null));

                    mapView.findViewById(R.id.create_event_submit).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((ViewGroup) mapView.findViewById(R.id.map_top_info_pane)).removeAllViews();
                        }
                    });
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
