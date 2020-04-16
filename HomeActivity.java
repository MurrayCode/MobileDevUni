package com.example.murrayapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button mLocationButton;
    Button mGoCalendar;
    Button mSetActivity;
    Button mLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Log Out
        mLogout = (Button) findViewById(R.id.LogOut);
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Logout = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(Logout);
            }
        });
        //Go to Calendar Button
        mGoCalendar = (Button) findViewById(R.id.btnGoCalendar);
        mGoCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GoCalendar = new Intent(HomeActivity.this, CalendarActivity.class);
                startActivity(GoCalendar);
            }
        });
        //Find Location Button
        mLocationButton = (Button)findViewById(R.id.LocationButton);
        mLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent LocationFind = new Intent(HomeActivity.this,MapsActivity.class);
                startActivity(LocationFind);
            }
        });
        //Set Activity Button
        mSetActivity = (Button)findViewById(R.id.setActivity);
        mSetActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setActvity = new Intent(HomeActivity.this, SetActivity.class);
                startActivity(setActvity);
            }
        });
    }
}
