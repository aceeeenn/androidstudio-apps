package com.example.apps.booking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;

import com.android.volley.VolleyError;
import com.example.apps.ContactUser.ContactAdapter;
import com.example.apps.ContactUser.ContactDataSet;
import com.example.apps.ContactUser.MainContact;
import com.example.apps.Login;
import com.example.apps.MainActivity;
import com.example.apps.R;
import com.example.apps.functions.Fungsi;
import com.example.apps.functions.VolleyObjectResult;
import com.example.apps.functions.VolleyObjectService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ActivityTeam extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
    }
}
