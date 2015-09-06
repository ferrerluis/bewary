package com.bewary.Map;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.bewary.Models.Comment;
import com.bewary.Models.Event;
import com.bewary.Models.EventType;
import com.bewary.Models.User;
import com.bewary.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MapInfoPane {
    private View mapView;
    private Context context;
    private RelativeLayout bottomPane;
    private ViewGroup topPane;

    public MapInfoPane(View mapView, Context context) {
        this.mapView = mapView;
        this.context = context;

        bottomPane = (RelativeLayout) this.mapView.findViewById(R.id.map_bottom_info_pane);
        topPane = (ViewGroup) this.mapView.findViewById(R.id.map_top_info_pane);
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

        card.findViewById(R.id.view_event_comment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewGroup) mapView).addView(View.inflate(context, R.layout.view_all_comments, null), 1);

                ArrayList<Comment> comments = new ArrayList();
                comments.add(new Comment(
                        new User("tommy", "tommt@gmail.com", "image.jpg"),
                        new Event(
                            new EventType("Homelessness", 1),
                            new com.bewary.Models.Location(56.1, 80.2),
                            new Date(),
                            new User("jane", "bob@gmail.com", "image.jpg"),
                            "Sarah Taylor was harassed by a homeless person yesterday. Take 4th Ave. to avoid this area.")
                        ,
                        new Date(),
                        "This is the first message. This needs to be a lot longer so here are some words that i thought you might like."));


                comments.add(new Comment(
                        new User("tommy", "tommt@gmail.com", "image.jpg"),
                        new Event(
                                new EventType("Homelessness", 1),
                                new com.bewary.Models.Location(56.1, 80.2),
                                new Date(),
                                new User("jane", "bob@gmail.com", "image.jpg"),
                                "Sarah Taylor was harassed by a homeless person yesterday. Take 4th Ave. to avoid this area.")
                        ,
                        new Date(),
                        "This is the first message. This needs to be a lot longer so here are some words that i thought you might like."));

                comments.add(new Comment(
                        new User("tommy", "tommt@gmail.com", "image.jpg"),
                        new Event(
                                new EventType("Homelessness", 1),
                                new com.bewary.Models.Location(56.1, 80.2),
                                new Date(),
                                new User("jane", "bob@gmail.com", "image.jpg"),
                                "Sarah Taylor was harassed by a homeless person yesterday. Take 4th Ave. to avoid this area.")
                        ,
                        new Date(),
                        "This is the first message. This needs to be a lot longer so here are some words that i thought you might like."));

                comments.add(new Comment(
                        new User("tommy", "tommt@gmail.com", "image.jpg"),
                        new Event(
                                new EventType("Homelessness", 1),
                                new com.bewary.Models.Location(56.1, 80.2),
                                new Date(),
                                new User("jane", "bob@gmail.com", "image.jpg"),
                                "Sarah Taylor was harassed by a homeless person yesterday. Take 4th Ave. to avoid this area.")
                        ,
                        new Date(),
                        "This is the first message. This needs to be a lot longer so here are some words that i thought you might like."));

                comments.add(new Comment(
                        new User("tommy", "tommt@gmail.com", "image.jpg"),
                        new Event(
                                new EventType("Homelessness", 1),
                                new com.bewary.Models.Location(56.1, 80.2),
                                new Date(),
                                new User("jane", "bob@gmail.com", "image.jpg"),
                                "Sarah Taylor was harassed by a homeless person yesterday. Take 4th Ave. to avoid this area.")
                        ,
                        new Date(),
                        "This is the first message. This needs to be a lot longer so here are some words that i thought you might like."));

                comments.add(new Comment(
                        new User("tommy", "tommt@gmail.com", "image.jpg"),
                        new Event(
                                new EventType("Homelessness", 1),
                                new com.bewary.Models.Location(56.1, 80.2),
                                new Date(),
                                new User("jane", "bob@gmail.com", "image.jpg"),
                                "Sarah Taylor was harassed by a homeless person yesterday. Take 4th Ave. to avoid this area.")
                        ,
                        new Date(),
                        "This is the first message. This needs to be a lot longer so here are some words that i thought you might like."));

                comments.add(new Comment(
                        new User("tommy", "tommt@gmail.com", "image.jpg"),
                        new Event(
                                new EventType("Homelessness", 1),
                                new com.bewary.Models.Location(56.1, 80.2),
                                new Date(),
                                new User("jane", "bob@gmail.com", "image.jpg"),
                                "Sarah Taylor was harassed by a homeless person yesterday. Take 4th Ave. to avoid this area.")
                        ,
                        new Date(),
                        "This is the first message. This needs to be a lot longer so here are some words that i thought you might like."));

                for(Comment comment: comments){
                    View commentView = View.inflate(context, R.layout.view_comment, null);
                    ((TextView) commentView.findViewById(R.id.comment_text)).setText(comment.getMessage());
                    ((TextView) commentView.findViewById(R.id.comment_author)).setText(comment.getUser().getName());
                    ((TextView) commentView.findViewById(R.id.comment_date)).setText(new SimpleDateFormat("yy-mm-dd h:mma", new Locale("en")).format(comment.getDate()));

                    ((ViewGroup) mapView.findViewById(R.id.comment_list)).addView(commentView);
                }

                mapView.findViewById(R.id.close_all_comment_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((ViewGroup) mapView).removeView((View) mapView.findViewById(R.id.comment_list).getParent());
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