package com.bewary.Listeners;

import android.content.Context;
import android.location.Location;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bewary.Map.MapInfoPane;
import com.bewary.Models.Event;
import com.bewary.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

// when a user clicks on the map, check if it's an event
public class OnEventClickListener implements GoogleMap.OnMapClickListener {

    private ArrayList<Event> events;
    private View v;
    private Context context;
    private MapInfoPane mapInfoPane;

    public OnEventClickListener(ArrayList<Event> events, View v, Context context) {
        this.events = events;
        this.v = v;
        this.context = context;
        mapInfoPane = new MapInfoPane(v, context);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if(v.findViewById(R.id.card_view) != null){
            (v.findViewById(R.id.card_view)).startAnimation(AnimationUtils.loadAnimation(context, R.anim.bottom_down));
            (v.findViewById(R.id.card_view)).setVisibility(View.INVISIBLE);
            ((ViewGroup) v).removeView(v.findViewById(R.id.card_view));
            v.findViewById(R.id.fab).animate().translationY(0).setDuration(500);
        }

        for(Event event: events){
            float[] results = new float[3];
            com.bewary.Models.Location location = event.getLocation();
            Location.distanceBetween(location.getLatitude(), location.getLongitude(), latLng.latitude, latLng.longitude, results);
            if(results[0] <= 900.34){
                mapInfoPane.addCard(
                        event.getEventType().getName(),
                        event.getDescription(),
                        new SimpleDateFormat("MM/dd/yy h:mma", new Locale("en")).format(event.getDate())
                );
            }
        }
    }
}
