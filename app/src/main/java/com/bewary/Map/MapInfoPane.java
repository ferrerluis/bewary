package com.bewary.Map;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.bewary.R;

public class MapInfoPane {
    private View mapView;
    private Context context;
    private RelativeLayout bottomPane;
    private RelativeLayout topPane;

    public MapInfoPane(View mapView, Context context) {
        this.mapView = mapView;
        this.context = context;

        bottomPane = (RelativeLayout) this.mapView.findViewById(R.id.map_bottom_info_pane);
        topPane = (RelativeLayout) this.mapView.findViewById(R.id.map_top_info_pane);
    }

    public void addCard(String title, String description, String date){
        View card = View.inflate(context, R.layout.view_event, null);
        ((TextView) card.findViewById(R.id.event_view_title)).setText(title);
        ((TextView) card.findViewById(R.id.event_view_description)).setText(description);
        ((TextView) card.findViewById(R.id.event_view_date)).setText(date);

        card.setVisibility(View.INVISIBLE);
        bottomPane.addView(card);

        card.startAnimation(AnimationUtils.loadAnimation(context, R.anim.bottom_up));
        card.setVisibility(View.VISIBLE);

        card.findViewById(R.id.create_event_comment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View addComment = View.inflate(context, R.layout.view_add_event_comment, null);
                addComment.setVisibility(View.INVISIBLE);
                topPane.addView(addComment);

//                addComment.startAnimation(AnimationUtils.loadAnimation(context, R.anim.top_down));
                addComment.setVisibility(View.VISIBLE);

                topPane.findViewById(R.id.add_event_comment_close_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addComment.startAnimation(AnimationUtils.loadAnimation(context, R.anim.top_up));
                        addComment.setVisibility(View.INVISIBLE);
                        ((ViewGroup) addComment.getParent()).removeView(addComment);
                    }
                });

                topPane.findViewById(R.id.add_event_comment_submit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String text = ((EditText) topPane.findViewById(R.id.add_event_comment_text)).getText().toString();
                        // todo: finish adding a comment
                    }
                });
            }
        });

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