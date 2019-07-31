package com.example.apps.booking;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.apps.R;
import com.example.apps.functions.Fungsi;
import com.example.apps.functions.VolleyObjectResult;
import com.example.apps.functions.VolleyObjectService;

import org.json.JSONObject;
import java.util.HashMap;

public class InsertBooking extends AppCompatActivity {

    private ListView listBooking;
    VolleyObjectResult volleyObjectResult, vor = null;
    VolleyObjectService volleyObjectService, vos;
    Fungsi fungsi = new Fungsi();
    String urlbooking = "/store";
    JSONObject data = null;

    EditText _lapangan, _alamat, _harga, _nohp;
    Button _simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_booking);

        _lapangan = (EditText) findViewById(R.id.inamalapangan);
        _alamat = (EditText) findViewById(R.id.ialamat);
        _nohp = (EditText) findViewById(R.id.inohp);
        _harga = (EditText) findViewById(R.id.iharga);
        _simpan = (Button) findViewById(R.id.btn_save);

        _simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lapangan = _lapangan.getText().toString();
                String harga = _harga.getText().toString();
                String alamat = _alamat.getText().toString();
                String nohp = _nohp.getText().toString();

                HashMap<String, String> dt = new HashMap<String, String>();
                dt.put("lapangan", lapangan);
                dt.put("harga", harga);
                dt.put("alamat", alamat);
                dt.put("nohp", nohp);
                final JSONObject data = new JSONObject(dt);

                vor = new VolleyObjectResult() {
                    @Override
                    public void resSuccess(String requestType, JSONObject response) {

                        try {
                            String message = response.getString("message");
                            Toast.makeText(InsertBooking.this, message, Toast.LENGTH_LONG).show();

                        } catch (Exception e) {

                            e.printStackTrace();
                            Toast.makeText(InsertBooking.this, "Terjadi kesalahan !!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void resError(String requestType, VolleyError error) {

                    }
                };
                vos = new VolleyObjectService(vor, InsertBooking.this);
                vos.postJsonObject("POSTCALL", fungsi.urlbooking() + urlbooking, data);
            }
        });
    }
}
