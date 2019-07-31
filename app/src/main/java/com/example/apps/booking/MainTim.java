package com.example.apps.booking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.apps.R;

public class MainTim extends AppCompatActivity {

    ImageView cecep, acen, asep, dachi, adit;
    Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim);

        cecep = (ImageView) findViewById(R.id.cecep);
        acen = (ImageView) findViewById(R.id.acen);
        asep = (ImageView) findViewById(R.id.asep);
        dachi = (ImageView) findViewById(R.id.dachi);
        adit = (ImageView) findViewById(R.id.adit);

        }
}

