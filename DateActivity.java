package com.example.murrayapplication;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DateActivity extends AppCompatActivity {

    private static final String TAG = "DateActivity";

    private TextView theDate;
    private Button btnGoCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_layout);
        theDate = (TextView) findViewById(R.id.date);
        btnGoCalendar = (Button) findViewById(R.id.btnGoCalender);

        //Takes Intent From Calendar and prints date into String
        Intent calenderIntent = getIntent();
        String date = calenderIntent.getStringExtra("date");
        theDate.setText(date);

        //Back to Calendar Button
        btnGoCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DateActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
    }
}