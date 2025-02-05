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

import java.util.Locale;

public class Ratings extends AppCompatActivity {

    private String name;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);

        RatingBar easeOfCheckoutRatingBar = findViewById(R.id.easeOfCheckoutRatingBar);
        RatingBar liquorRatingBar = findViewById(R.id.liquorRatingBar);
        RatingBar produceRatingBar = findViewById(R.id.produceRatingBar);
        RatingBar cheeseRatingBar = findViewById(R.id.cheeseRatingBar);
        RatingBar ratingBar3 = findViewById(R.id.ratingBar3);

        TextView ratingOutputTextView = findViewById(R.id.ratingOutputTextView);

        RatingBar.OnRatingBarChangeListener ratingBarChangeListener = new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                updateAverageRating(ratingOutputTextView);
            }
        };

        easeOfCheckoutRatingBar.setOnRatingBarChangeListener(ratingBarChangeListener);
        liquorRatingBar.setOnRatingBarChangeListener(ratingBarChangeListener);
        produceRatingBar.setOnRatingBarChangeListener(ratingBarChangeListener);
        cheeseRatingBar.setOnRatingBarChangeListener(ratingBarChangeListener);
        ratingBar3.setOnRatingBarChangeListener(ratingBarChangeListener);

        updateAverageRating(ratingOutputTextView);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        address = intent.getStringExtra("address");

        loadPrefs();
        navigateHomeButton();

        findViewById(R.id.saveButton).setOnClickListener(l -> savePrefs());
    }

    private void updateAverageRating(TextView ratingOutputTextView) {
        RatingBar easeOfCheckoutRatingBar = findViewById(R.id.easeOfCheckoutRatingBar);
        RatingBar liquorRatingBar = findViewById(R.id.liquorRatingBar);
        RatingBar produceRatingBar = findViewById(R.id.produceRatingBar);
        RatingBar cheeseRatingBar = findViewById(R.id.cheeseRatingBar);
        RatingBar ratingBar3 = findViewById(R.id.ratingBar3);

        float totalRating = easeOfCheckoutRatingBar.getRating() + liquorRatingBar.getRating() +
                produceRatingBar.getRating() + cheeseRatingBar.getRating() + ratingBar3.getRating();
        float averageRating = totalRating / 5;

        ratingOutputTextView.setText(String.format("%.1f", averageRating));
    }


    public void savePrefs() {
        TextView output = findViewById(R.id.ratingOutputTextView);
        SharedPreferences prefs = getSharedPreferences(this.name, Context.MODE_PRIVATE);

        prefs.edit().putString("name", name).apply();
        prefs.edit().putString("address", address).apply();

        RatingBar produceBar = findViewById(R.id.produceRatingBar);
        float produceNum = produceBar.getRating();
        prefs.edit().putFloat("produce", produceNum).apply();

        RatingBar meatBar = findViewById(R.id.ratingBar3);
        float meatNum = meatBar.getRating();
        prefs.edit().putFloat("meat", meatNum).apply();

        RatingBar liquorBar = findViewById(R.id.liquorRatingBar);
        float liquorNum = liquorBar.getRating();
        prefs.edit().putFloat("liquor", liquorNum).apply();

        RatingBar cheeseBar = findViewById(R.id.cheeseRatingBar);
        float cheeseNum = cheeseBar.getRating();
        prefs.edit().putFloat("cheese", cheeseNum).apply();

        RatingBar checkoutBar = findViewById(R.id.easeOfCheckoutRatingBar);
        float checkoutNum = checkoutBar.getRating();
        prefs.edit().putFloat("checkout", checkoutNum).apply();

        double avg = (produceNum + meatNum + liquorNum + cheeseNum + checkoutNum) / 5.0;
        prefs.edit().putFloat("avg", (float) avg).apply();

        output.setText(String.format(Locale.getDefault(), "%.2f", avg));

    }

    public void loadPrefs() {
        TextView output = findViewById(R.id.ratingOutputTextView);
        SharedPreferences prefs = getSharedPreferences(this.name, Context.MODE_PRIVATE);

        RatingBar produceBar = findViewById(R.id.produceRatingBar);
        produceBar.setRating(prefs.getFloat("produce", 0));

        RatingBar meatBar = findViewById(R.id.ratingBar3);
        meatBar.setRating(prefs.getFloat("meat", 0));

        RatingBar liquorBar = findViewById(R.id.liquorRatingBar);
        liquorBar.setRating(prefs.getFloat("liquor", 0));

        RatingBar cheeseBar = findViewById(R.id.cheeseRatingBar);
        cheeseBar.setRating(prefs.getFloat("cheese", 0));

        RatingBar checkoutBar = findViewById(R.id.easeOfCheckoutRatingBar);
        checkoutBar.setRating(prefs.getFloat("checkout", 0));

        float avg = prefs.getFloat("avg", 0);
        output.setText(String.format(Locale.getDefault(), "%.2f", avg));

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
