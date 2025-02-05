package com.example.supermarket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        rateButton();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void rateButton(){
        findViewById(R.id.rateButton).setOnClickListener(
                l -> {
                    Intent intent = new Intent(MainActivity.this, Ratings.class);

                    String name = ((EditText) findViewById(R.id.nameEditText)).getText().toString();
                    String address = ((EditText) findViewById(R.id.addressEditText)).getText().toString();

                    intent.putExtra("name", name);
                    intent.putExtra("address", address);

                    startActivity(intent);
                }
        );
    }
}


