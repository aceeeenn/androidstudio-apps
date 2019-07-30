package com.example.apps.booking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.apps.ContactUser.ContactAdapter;
import com.example.apps.ContactUser.ContactDataSet;
import com.example.apps.Hasil;
import com.example.apps.MainActivity;
import com.example.apps.R;
import com.example.apps.functions.Fungsi;
import com.example.apps.functions.VolleyObjectResult;
import com.example.apps.functions.VolleyObjectService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;


public class MainBooking extends AppCompatActivity {

    Button booking, logout;

    private ListView listlapangan;
    VolleyObjectResult volleyObjectResult, vor = null;
    VolleyObjectService volleyObjectService, vos;
    Fungsi fungsi = new Fungsi();
    private List<ContactDataSetBooking> list = new ArrayList<ContactDataSetBooking>();
    private ContactAdapterBooking contactAdapterBooking;
    JSONObject data = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_booking);

        booking = (Button) findViewById(R.id.booking);
        logout = (Button) findViewById(R.id.logout);
        listlapangan = (ListView) findViewById(R.id.listlapangan);

        vor = new VolleyObjectResult() {
            @Override
            public void resSuccess(String requestType, JSONObject response) {
                //        Toast.makeText(MainContact.this, response.toString(), Toast.LENGTH_LONG).show();

                try {
                    JSONArray jsonArray = response.getJSONArray("values");
                    for (int i=0; i<jsonArray.length(); i++) {
                        JSONObject object =  jsonArray.getJSONObject(i);
                        ContactDataSetBooking nds = new ContactDataSetBooking();
                        nds.setLapangan(object.getString("lapangan"));
//                        nds.setEmail(object.getString("email"));
                        nds.setAlamat(object.getString("alamat"));
                        nds.setNohp(object.getString("nohp"));
                        nds.setId(object.getString("id"));
                        list.add(nds);
                    }
                    contactAdapterBooking = new ContactAdapterBooking(MainBooking.this, list);
                    contactAdapterBooking.notifyDataSetChanged();
                    listlapangan.setAdapter(contactAdapterBooking);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void resError(String requestType, VolleyError error) {
                View view = findViewById(R.id.coordinator);
                String message = "Maaf data kontak tidak ditemukan.";
            }
        };
        vos = new VolleyObjectService(vor, MainBooking.this);
        vos.getJsonObject("GETCALL", fungsi.url());
    }
};

