package com.bewary.Database;

import android.content.Context;
import android.util.Log;

import com.bewary.Models.*;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.SaveCallback;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBManager {

    public DBManager(Context context){

        Parse.enableLocalDatastore(context);
        Parse.initialize(context, "BWIvBV36lUfJiqKkIY5Z2otHttHTMszdzgmFlORR", "m6gTcnDGvxnP2uorPcVztIEn50pwqOJiMm8C04ez");
    }

    public ParseObject getParseObj(String id, String table) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery(table);

        try {
            return query.get(id);
        } catch (ParseException e) {
            return null;
        }
    }

    public EventType getEventType(String id) {

        ParseObject response = getParseObj(id, "eventType");

        EventType eventType = new EventType(
                response.getString("name"),
                response.getInt("level")
        );

        return eventType;
    }

    public Event getEvent(String id) {

        ParseObject response = getParseObj(id, "event");

        ParseGeoPoint geoPoint = response.getParseGeoPoint("location");
        ParseObject eventTypeObj = ((ParseObject) response.get("eventType"));
        ParseObject authorObj = ((ParseObject) response.get("author"));

        Event event = new Event(
                new EventType(eventTypeObj.getString("name"), eventTypeObj.getInt("level")),
                new Location(geoPoint.getLatitude(), geoPoint.getLongitude()),
                new User(authorObj.getString("name"), authorObj.getString("email"), authorObj.getString("picture")),
                response.getDate("date"),
                response.getString("description")
        );

        return event;
    }

//    public EventType getEventTypeByName(String name) {
//
//        ParseObject eventTypeObj = getEventTypeObj(name);
//
//        EventType eventType = new EventType(
//                eventTypeObj.getString("name"),
//                eventTypeObj.getInt("level")
//        );
//
//        return eventType;
//
//    }

    public ParseObject getEventTypeObj(EventType eventType) {

        String name = eventType.getName();
        int level = eventType.getLevel();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("eventType");

        ParseObject eventTypeObj;

        try {

            eventTypeObj = query.whereEqualTo("name", name).getFirst();
        } catch (ParseException e) {

            eventTypeObj = addEventType(name, level);
        }

        return eventTypeObj;

    }

    public List<ParseObject> getEventObj(Event event) {

        User author = event.getAuthor();
        Date date = event.getDate();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("event");

        List<ParseObject> eventObj;

        try {

            eventObj = query.whereEqualTo("author", author).whereEqualTo("date", date).find();
        } catch (ParseException e) {

            eventObj = null;
        }

        return eventObj;

    }

    public ParseObject addEventType(String name, int level) {

        ParseObject eventTypeObj = new ParseObject("eventType");

        eventTypeObj.put("name", name);
        eventTypeObj.put("level", level);

        eventTypeObj.saveInBackground();

        return eventTypeObj;
    }

    public ParseObject getUserObj(String email) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("user");

        ParseObject userObj;

        try {

            userObj = query.whereEqualTo("email", email).getFirst();
        } catch (ParseException e) {

            userObj = null;
        }

        return userObj;
    }

    public ArrayList<Comment> getCommentsByEvent(Event event) {

        final ArrayList<Comment> comments = new ArrayList<Comment>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("event").whereEqualTo("event", getUserObj(event.getAuthor().getEmail()));

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> commentParse, ParseException e) {
                if (e == null) {

                    for (ParseObject comment : commentParse) {

                        comments.add(new Comment(
                                getUser(comment.getParseObject("author").getObjectId()),
                                getEvent(comment.getParseObject("event").getObjectId()),
                                comment.getDate("date"),
                                comment.getString("message")
                        ));
                    }

                    Log.d("score", "Retrieved " + commentParse.size() + " scores");
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        return comments;
    }

    public User getUser(String id) {

        ParseObject response = getParseObj(id, "user");

        User user = new User(
                response.getString("name"),
                response.getString("email"),
                response.getString("picture")
        );

        return user;
    }

    public ArrayList<Event> getEvents() {

        final ArrayList<Event> events = new ArrayList<Event>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("event");

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> eventParse, ParseException e) {
                if (e == null) {

                    for (ParseObject event : eventParse) {

                        ParseGeoPoint GeoPointLocation = event.getParseGeoPoint("location");

                        events.add(new Event(
                                getEventType(event.getParseObject("eventType").getObjectId()),
                                new Location(GeoPointLocation.getLatitude(), GeoPointLocation.getLongitude()),
                                getUser(event.getParseObject("author").getObjectId()),
                                event.getDate("date"),
                                event.getString("description")
                        ));
                    }

                    Log.d("score", "Retrieved " + eventParse.size() + " scores");
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        return  events;
    }

    public ParseObject addEvent(Event event) {

        ParseObject eventObj = new ParseObject("event");
        ParseObject eventTypeObj = getEventTypeObj(event.getEventType());
        ParseObject authorObj = getUserObj(event.getAuthor().getEmail());

        eventObj.put("eventType", eventTypeObj);
        eventObj.put("location", event.getLocation().getGeoPoint());
        eventObj.put("author", authorObj);
        eventObj.put("date", event.getDate());
        eventObj.put("description", event.getDescription());

        eventObj.saveInBackground();

        return eventObj;
    }

    public ParseObject addComment(Comment comment) {

        ParseObject commentObj = new ParseObject("comment");
//        ParseObject eventObj = getEventObj(comment.getEvent());
        ParseObject authorObj = getUserObj(comment.getAuthor().getEmail());

        commentObj.put("author", authorObj);
//        commentObj.put("event", eventObj);
        commentObj.put("date", comment.getDate());
        commentObj.put("message", comment.getMessage());

        commentObj.saveInBackground();

        return commentObj;
    }
}
