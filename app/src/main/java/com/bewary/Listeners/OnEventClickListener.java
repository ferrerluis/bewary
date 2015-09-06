package com.bewary.Listeners;

import android.content.Context;
import android.location.Location;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
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
    private View mapView;
    private Context context;
    private MapInfoPane mapInfoPane;

    public OnEventClickListener(ArrayList<Event> events, View mapView, Context context) {
        this.events = events;
        this.mapView = mapView;
        this.context = context;
        mapInfoPane = new MapInfoPane(mapView, context);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if(mapView.findViewById(R.id.card_view) != null){
            (mapView.findViewById(R.id.card_view)).startAnimation(AnimationUtils.loadAnimation(context, R.anim.bottom_down));
            (mapView.findViewById(R.id.card_view)).setVisibility(View.INVISIBLE);
            ((ViewGroup) mapView.findViewById(R.id.map_bottom_info_pane)).removeAllViews();
        }

        if(mapView.findViewById(R.id.add_event_comment_layout) != null){
            mapView.findViewById(R.id.add_event_comment_layout).startAnimation(AnimationUtils.loadAnimation(context, R.anim.top_up));
            mapView.findViewById(R.id.add_event_comment_layout).setVisibility(View.INVISIBLE);
            ((ViewGroup) mapView.findViewById(R.id.map_top_info_pane)).removeAllViews();
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
