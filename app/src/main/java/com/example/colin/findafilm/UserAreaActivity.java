package com.example.colin.findafilm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    //private String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);


        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        TextView welcomeMessage = (TextView) findViewById(R.id.fsWelcomeMsg);

        welcomeMessage.setText("Welcome, " + getIntent().getExtras().getString("username"));

    }
}
