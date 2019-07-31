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


public class MainBooking extends AppCompatActivity {

    private ListView listlapangan;
    VolleyObjectResult volleyObjectResult, vor = null;
    VolleyObjectService volleyObjectService, vos;
    Fungsi fungsi = new Fungsi();
    private List<ContactDataSetBooking> list = new ArrayList<ContactDataSetBooking>();
    private ContactAdapterBooking contactAdapterBooking;
    JSONObject data = null;

    Button logout, update, view, booking_, tim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_booking);
        listlapangan = (ListView) findViewById(R.id.listlapangan);

        booking_ = (Button) findViewById(R.id.booking_);
        logout = (Button) findViewById(R.id.logout);
        tim = (Button) findViewById(R.id.tim);
        view = (Button) findViewById(R.id.view);

        listlapangan = (ListView) findViewById(R.id.listlapangan);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainBooking.this);
                String email = prefs.getString("email", null);
                String password = prefs.getString("password", null);
                Log.d("debug", "email:" + email + "password" + password);

                if (email != null && password != null) {

                    final SharedPreferences sesdata = PreferenceManager
                        .getDefaultSharedPreferences(MainBooking.this);
                    SharedPreferences.Editor lds = sesdata.edit();
                    lds.clear();
                    lds.commit();

                    Intent intentLogin = new Intent(MainBooking.this, Login.class);
                    intentLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intentLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    MainBooking.this.startActivity(intentLogin);
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewbooking = new Intent(MainBooking.this, ViewBooking.class);
                viewbooking.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainBooking.this.startActivity(viewbooking);

            }
        });

        booking_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insertbooking = new Intent(MainBooking.this, InsertBooking.class);
                insertbooking.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainBooking.this.startActivity(insertbooking);

            }
        });

//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent updatebooking = new Intent(MainBooking.this, ViewBooking.class);
//                updatebooking.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                MainBooking.this.startActivity(updatebooking);
//
//            }
//        });

        tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tim = new Intent(MainBooking.this, ActivityTeam.class);
                tim.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(tim);

            }
        });

    }
}
