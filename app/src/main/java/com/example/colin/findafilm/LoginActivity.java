package com.example.colin.findafilm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    UserNameDatabase contact = new UserNameDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //private static final String TAG2 = "LoginCreate";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);

        //Clicking Login button
        bLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d("Login click", "Entering login onClick");
                String usernameStr = etUsername.getText().toString();
                String passwordStr = etPassword.getText().toString();
                String dbPassword = contact.searchPass(usernameStr);

                if (passwordStr.equals(dbPassword)) {
                    Intent userAreaIntent = new Intent(LoginActivity.this, UserAreaActivity.class);
                    userAreaIntent.putExtra("username", usernameStr);
                    LoginActivity.this.startActivity(userAreaIntent);
                } else {
                    Toast temp = Toast.makeText(LoginActivity.this, "Username and password don't match", Toast.LENGTH_SHORT);
                    temp.show();
                }
            }
        });

        //Clicking register link
        registerLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
    }
}
