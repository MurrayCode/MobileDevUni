package com.example.murrayapplication;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;
    private Button mBackHome;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);

        //Go Home Button
        mBackHome = (Button) findViewById(R.id.backHome);
        mBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(CalendarActivity.this, HomeActivity.class);
                startActivity(home);
            }
        });

        //Go to View Date, Adds date into a string (must add +1 to month as does months 0 - 11)
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String date = (dayOfMonth) + "/" + (month + 1) + "/" + year;

                Intent intent   = new Intent(CalendarActivity.this, DateActivity.class);
                intent.putExtra("date",date);
                startActivity(intent);
            }
        });
    }
}
