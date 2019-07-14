package com.example.apps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.apps.R;


public class Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Contact.this.setTitle("Contact");
    }

}

