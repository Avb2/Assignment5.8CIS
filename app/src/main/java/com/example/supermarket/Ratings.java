package com.example.supermarket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ratings extends AppCompatActivity {
    private String name;
    private String address;

    public Ratings (String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ratings);
        loadPrefs();



        navigateHomeButton();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }



    public void savePrefs() {
        TextView output = findViewById(R.id.ratingOutputTextView);
        SharedPreferences prefs = getSharedPreferences(name, Context.MODE_PRIVATE);

        prefs.edit().putString("name", name).apply();
        prefs.edit().putString("address", address).apply();

        // produce
        RatingBar produceBar = findViewById(R.id.produceRatingBar);
        float produceNum = produceBar.getNumStars();
        prefs.edit().putFloat("produce", produceNum).apply();

        // meat
        RatingBar meatBar = findViewById(R.id.ratingBar3);
        float meatNum = meatBar.getNumStars();
        prefs.edit().putFloat("meat", meatNum).apply();

        // liquor
        RatingBar liquorBar = findViewById(R.id.liquorRatingBar);
        float liquorNum = liquorBar.getNumStars();
        prefs.edit().putFloat("liquor", liquorNum).apply();

        // cheese
        RatingBar cheeseBar = findViewById(R.id.cheeseRatingBar);
        float cheeseNum = cheeseBar.getNumStars();
        prefs.edit().putFloat("cheese", cheeseNum).apply();

        // checkout
        RatingBar checkoutBar = findViewById(R.id.easeOfCheckoutRatingBar);
        float checkoutNum = checkoutBar.getNumStars();
        prefs.edit().putFloat("checkout", checkoutNum).apply();

        double avg = produceNum + meatNum + liquorNum + cheeseNum + checkoutNum / 5.0;
        output.setText(String.valueOf(avg));

    }

    public void loadPrefs() {
        TextView output = findViewById(R.id.ratingOutputTextView);
        SharedPreferences prefs = getSharedPreferences(name, Context.MODE_PRIVATE);

        // produce
        RatingBar produceBar = findViewById(R.id.produceRatingBar);
        prefs.getFloat("produce", 0);

        // meat
        RatingBar meatBar = findViewById(R.id.ratingBar3);
        prefs.getFloat("meat", 0);

        // liquor
        RatingBar liquorBar = findViewById(R.id.liquorRatingBar);
        prefs.getFloat("liquor", 0);

        // cheese
        RatingBar cheeseBar = findViewById(R.id.cheeseRatingBar);
        prefs.getFloat("cheese", 0);

        // checkout
        RatingBar checkoutBar = findViewById(R.id.easeOfCheckoutRatingBar);
        prefs.getFloat("checkout", 0);

        float avg = prefs.getFloat("avg", 0);
        output.setText(String.valueOf(avg));

    }



    private void navigateHomeButton() {

        findViewById(R.id.backNavButton).setOnClickListener(
                l -> {
                    Intent intent = new Intent(Ratings.this, MainActivity.class);

                    startActivity(intent);
                }
        );
    }
}