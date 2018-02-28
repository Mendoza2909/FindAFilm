package com.example.colin.findafilm;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Implement onClickListener so don't have to set for each button separately - this wasn't working in the end
public class SelectGenreActivity extends AppCompatActivity  {

    protected void onCreate(Bundle savedInstanceState) {
        Log.d("On create", "On create ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_genre);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //generate random values to assign to buttons - may not be needed but could use elsewhere
        //Get all genres (stored in strings.xml) and randomise order
        String[] genreArray = getResources().getStringArray(R.array.genreNames);
        List<String> genreList = Arrays.asList(genreArray);
        Collections.shuffle(genreList);

        //Loop through each button and set value
        Integer buttonCount = 6;
        for (int i = 1; i <= buttonCount; i++) {
            Button button = findViewById(getResources().getIdentifier("genreButton" + i, "id",
                    this.getPackageName()));
            button.setText(genreList.get(i - 1));
        }

        Log.d("Set button", "Set button ");
        defineButtons();
    }
        private View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            //Switch based on which button is pressed
               // Log.d("Switch", "Button Click " );
                switch (v.getId()) {

                    case R.id.genreButton1:
                        Log.d("Button Click", "Button Click " );
                        Button btn1 = findViewById(R.id.genreButton1);
                        StartActivityButton(btn1);
                        break;

                    case R.id.genreButton2:
                        Log.d("Button Click", "Button Click " );
                        Button btn2 = findViewById(R.id.genreButton2);
                        StartActivityButton(btn2);
                        break;

                    case R.id.genreButton3:
                        Log.d("Button Click", "Button Click " );
                        Button btn3 = findViewById(R.id.genreButton3);
                        StartActivityButton(btn3);
                        break;

                    case R.id.genreButton4:
                        Log.d("Button Click", "Button Click " );
                        Button btn4 = findViewById(R.id.genreButton4);
                        StartActivityButton(btn4);
                        break;

                    case R.id.genreButton5:
                        Log.d("Button Click", "Button Click " );
                        Button btn5 = findViewById(R.id.genreButton5);
                        StartActivityButton(btn5);
                        break;

                    case R.id.genreButton6:
                        Log.d("Button Click", "Button Click " );
                        Button btn6 = findViewById(R.id.genreButton6);
                        StartActivityButton(btn6);
                        break;
                }
            }
        };

    public void StartActivityButton (Button btn) {
        //This is the genre name that was selected. Pass to next Activity to get movies only from that genre
        String buttonText = btn.getText().toString();
        Log.d("Button Click startact ", "Button Click startact " + buttonText);
        Intent intent = new Intent(SelectGenreActivity.this, RateMovieActivity.class);
        Bundle bnd = new Bundle();
        bnd.putString("genre", buttonText);
        intent.putExtras(bnd); //Put the genre name to your next Intent
        startActivity(intent);
        finish();
    }

    public void defineButtons(){
        Log.d("Define buttons", "Define buttons");
        findViewById(R.id.genreButton1).setOnClickListener(onClickListener);
        findViewById(R.id.genreButton2).setOnClickListener(onClickListener);
        findViewById(R.id.genreButton3).setOnClickListener(onClickListener);
        findViewById(R.id.genreButton4).setOnClickListener(onClickListener);
        findViewById(R.id.genreButton5).setOnClickListener(onClickListener);
        findViewById(R.id.genreButton6).setOnClickListener(onClickListener);
    }
}