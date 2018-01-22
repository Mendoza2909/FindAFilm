package com.example.colin.findafilm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    UserNameDatabase contact = new UserNameDatabase(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {public void onClick(View v)
        {

            final EditText etEmail = (EditText) findViewById(R.id.etEmail);
            final EditText etUsername = (EditText) findViewById(R.id.etUsername);
            final EditText etPassword = (EditText) findViewById(R.id.etPassword);

            //final Button bRegister = (Button) findViewById(R.id.bRegister);

            String emailStr = etEmail.getText().toString();
            String usernameStr = etUsername.getText().toString();
            String passwordStr = etPassword.getText().toString();
            //String emailStr = etEmail.getText().toString();

            //String emailStr = etEmail.getText().toString();

            //Insert details in database

            final UserName us = new UserName();
            us.setUsername(usernameStr);
            us.setEmail(emailStr);
            us.setPassword(passwordStr);
            contact.insertContact(us);

            Log.d("Set details " , "user "+ usernameStr + " pass " + passwordStr);
        }
        });


    }



}
