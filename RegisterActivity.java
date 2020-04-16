package com.example.murrayapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    Database db;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new Database(this);
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mTextCnfPassword = (EditText)findViewById(R.id.edittext_password);

        //Takes back to login
        mTextViewLogin = (TextView)findViewById(R.id.textview_register);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent (RegisterActivity.this, MainActivity.class);
                startActivity(LoginIntent);
            }
        });

        //Register User
        mButtonRegister = (Button)findViewById(R.id.Reg_button);
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnf_pwd = mTextPassword.getText().toString().trim();

                //Checks If password entered and confirm password are the same, attempts to add them to DB
                if(pwd.equals(cnf_pwd)) {
                   long val = db.addUser(user,pwd);
                   if(val > 0) {
                       Toast.makeText(RegisterActivity.this,"Successfully Registered!",Toast.LENGTH_SHORT).show();
                       Intent moveToLogin = new Intent(RegisterActivity.this,MainActivity.class);
                       startActivity(moveToLogin);
                   }
                   //If fails to add gives alert
                   else{
                       Toast.makeText(RegisterActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                   }
                   //If successful Gives alert and moves back to Login page
                    Toast.makeText(RegisterActivity.this,"Successfully Registered!",Toast.LENGTH_SHORT).show();
                    Intent moveToLogin = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(moveToLogin);
                }
                else {
                    Toast.makeText(RegisterActivity.this,"Password is not a match",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
