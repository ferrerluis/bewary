package com.bewary.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.bewary.Database.DBManager;
import com.bewary.Models.Comment;
import com.bewary.Models.Event;
import com.bewary.Models.EventType;
import com.bewary.Models.Location;
import com.bewary.Models.User;
import com.bewary.R;
import com.bewary.Utils.SharedPreferenceHelper;
import com.bewary.Utils.UserInfoFetcher;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            Event testEvent = new Event(
                    new EventType("Robbery", 2),
                    new Location(13.5, 14.0),
                    new User("admin", "admin"),
                    new Date(),
                    "Someone robbed someone"
            );

            DBManager manager = new DBManager(this);
            Log.d("connection", "Created connection");
            manager.addComment(new Comment(
                    new User("admin", "admin"),
                    testEvent,
                    new Date(),
                    "Awesome!"));
            Log.d("events", manager.getCommentsByEvent(testEvent).toString());
            Log.d("test", "Test sent");
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

    public void doSignIn(View view) {
        String name = ((EditText) findViewById(R.id.sign_in_name)).getText().toString();
        String email = UserInfoFetcher.getEmail(this);

        try {
            SharedPreferenceHelper.store(this, "user_name", name);
            SharedPreferenceHelper.store(this, "email", email);
        } catch (NoSuchMethodException e) {
            Snackbar.make(findViewById(android.R.id.content), "There was an error saving your information.", Snackbar.LENGTH_LONG)
                    .setActionTextColor(Color.RED)
                    .show();
        }

        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);

        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        Snackbar.make(findViewById(android.R.id.content), "Success!", Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.WHITE)
                .show();

    }
}
