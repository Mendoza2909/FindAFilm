package com.example.colin.findafilm;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.*;
import android.graphics.Color;

public class UserAreaActivity extends AppCompatActivity {

FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
DatabaseReference mDataRef = mDatabase.getReference();




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        TextView welcomeMessage = (TextView) findViewById(R.id.fsWelcomeMsg);
        RatingBar rtBar = (RatingBar) findViewById(R.id.rtBar);

        welcomeMessage.setText("Welcome, " + getIntent().getExtras().getString("username"));

        rtBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.d("Change", "Rating Changed");
                mDataRef.child("Rating").setValue(ratingBar.getRating());
            }
        });

    }








    private boolean isNetworkConnected() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE); // 1
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo(); // 2
        return networkInfo != null && networkInfo.isConnected(); // 3
    }

    private boolean isWifiConnected() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && (ConnectivityManager.TYPE_WIFI == networkInfo.getType()) && networkInfo.isConnected();
    }

}
