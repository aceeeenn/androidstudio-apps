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


public class ViewBooking extends AppCompatActivity {

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
        setContentView(R.layout.activity_booking);
        listlapangan = (ListView) findViewById(R.id.listlapangan);

        vor = new VolleyObjectResult() {
            @Override
            public void resSuccess(String requestType, JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("values");
                    for (int i=0; i<jsonArray.length(); i++) {
                        JSONObject object =  jsonArray.getJSONObject(i);
                        ContactDataSetBooking nds = new ContactDataSetBooking();
                        nds.setLapangan(object.getString("lapangan"));
                        nds.setHarga(object.getString("harga"));
                        nds.setAlamat(object.getString("alamat"));
                        nds.setNohp(object.getString("nohp"));
                        nds.setId(object.getString("id"));
                        list.add(nds);
                    }
                    contactAdapterBooking = new ContactAdapterBooking(ViewBooking.this, list);
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
        vos = new VolleyObjectService(vor, ViewBooking.this);
        vos.getJsonObject("GETCALL", fungsi.urlbooking());

    }
}
