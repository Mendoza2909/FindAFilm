package com.example.colin.findafilm;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;
import com.google.firebase.database.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RateMovieActivity extends AppCompatActivity {
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference mDataRef = mDatabase.getReference();

    protected void onCreate(Bundle savedInstanceState) {

        //Get value from previous intent
        Bundle bnd = getIntent().getExtras();
        //String genreName = "";
        final String genreName = bnd.getString("genre");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_movie);

        TextView tvGenreName = (TextView) findViewById(R.id.tvGenreName);
        TextView tvMovieName = (TextView) findViewById(R.id.tvMovieName);
        RatingBar rtMovie = (RatingBar) findViewById(R.id.rtMovie);

        tvGenreName.setText(genreName);

        //Log.d("GenreFlag is ","Flag " + genreName.replaceAll("\\s","")+"Movies");

        //Get random movie from relevant list as defined by selected genre, and put to screen
        int movieArrayId = getResources().getIdentifier(genreName.replaceAll("\\s","")+"Movies","array",getPackageName());
        //Log.d("Id is ","Id " + movieArrayId);
        String[] movieArray = getResources().getStringArray(movieArrayId);
        //String[] movieArray = getResources().getStringArray(R.array.);
        List<String> movieList = Arrays.asList(movieArray);
        Collections.shuffle(movieList);

        final String movieName = movieList.get(0);
        tvMovieName.setText(movieName);

        //Rate the movie and restart activity, i.e. generate next movie to rate.
        //In future have to work out how to stop generating new movies after given number of times, so recommendation can be made.
        // Maybe store rating count within Firebase
        rtMovie.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.d("Change", "Rating Changed");
                mDataRef.child(movieName).setValue(ratingBar.getRating());
                //Restart activity
                finish();
                startActivity(getIntent());
            }
        });

    }

}