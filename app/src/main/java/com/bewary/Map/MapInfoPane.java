package com.bewary.Map;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.bewary.R;

public class MapInfoPane {
    private View mapView;
    private Context context;
    private RelativeLayout pane;

    public MapInfoPane(View mapView, Context context) {
        this.mapView = mapView;
        this.context = context;

        pane = (RelativeLayout) this.mapView.findViewById(R.id.map_info_pane);
    }

    public void addCard(String title, String description, String date){
        View card = View.inflate(context, R.layout.view_event, null);
        ((TextView) card.findViewById(R.id.event_view_title)).setText(title);
        ((TextView) card.findViewById(R.id.event_view_description)).setText(description);
        ((TextView) card.findViewById(R.id.event_view_date)).setText(date);

        card.setVisibility(View.INVISIBLE);
        pane.addView(card);

        card.startAnimation(AnimationUtils.loadAnimation(context, R.anim.bottom_up));
        card.setVisibility(View.VISIBLE);

        card.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int height = card.getMeasuredHeight();

        mapView.findViewById(R.id.fab).animate().translationY(-400).setDuration(500);

//        ((ImageView) card.findViewById(R.id.close_card_btn)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                removeCard();
//            }
//        });
    }

    public final void removeCard(){
        ((ViewGroup) this.mapView).removeView((CardView) this.mapView.findViewById(R.id.card_view));
    }
}