package com.example.murrayapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetActivity extends AppCompatActivity {
    ScheduleDatabase dbTwo;
    EditText mDate;
    EditText mAddress;
    EditText mActivity;
    Button mBtnSetActivity;
    Button mBtnGoHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        dbTwo = new ScheduleDatabase(this);
        mDate = (EditText) findViewById(R.id.edittext_date);
        mAddress = (EditText) findViewById(R.id.edittext_address);
        mActivity = (EditText) findViewById(R.id.edittext_activity);

        //Back to Homepage
        mBtnGoHome=(Button)findViewById(R.id.Homepage) ;
        mBtnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Home = new Intent(SetActivity.this,HomeActivity.class);
                startActivity(Home);
            }
        });

        //Add Activity to database
        mBtnSetActivity = (Button)findViewById(R.id.addActivity);
        mBtnSetActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = mDate.getText().toString().trim();
                String address = mAddress.getText().toString().trim();
                String activity = mActivity.getText().toString().trim();

                long val = dbTwo.addActivity(date,address,activity);               //Adds the Data into a database
                if (val > 0) {                                                     // Checks to see if Data was added
                                                                                    // if data added go back to homescreen, else Toast error
                    Toast.makeText(SetActivity.this,"Activity added to schedule!", Toast.LENGTH_SHORT).show();
                    Intent BackHome = new Intent(SetActivity.this,HomeActivity.class);
                    startActivity(BackHome);
                }
                else{
                    Toast.makeText(SetActivity.this,"Adding Activity Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
