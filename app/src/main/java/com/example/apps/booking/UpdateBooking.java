package com.example.apps.booking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.apps.ContactUser.MainContact;
import com.example.apps.R;
import com.example.apps.functions.Fungsi;
import com.example.apps.functions.VolleyObjectResult;
import com.example.apps.functions.VolleyObjectService;

import org.json.JSONObject;

import java.util.HashMap;


public class UpdateBooking extends AppCompatActivity {

    private ListView listbooking;
    VolleyObjectResult volleyObjectResult, vor = null;
    VolleyObjectService volleyObjectService, vos;
    Fungsi fungsi = new Fungsi();
    String urlbooking = "/update";
    JSONObject data = null;

    EditText nama, harga, alamat, nohp, uid;
    String _nama, _harga, _alamat, _nohp, _uid;
    Button _update, _delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_booking);

        nama = (EditText) findViewById(R.id.unamalapangan);
        alamat = (EditText) findViewById(R.id.ualamat);
        harga = (EditText) findViewById(R.id.uharga);
        nohp = (EditText) findViewById(R.id.unohp);
        uid = (EditText) findViewById(R.id.uid);

        if(getIntent().getExtras()!=null){
            /**
             * Jika Bundle ada, ambil data dari Bundle
             */
            Bundle bundle = getIntent().getExtras();
            nama.setText(bundle.getString("lapangan"));
            harga.setText(bundle.getString("harga"));
            alamat.setText(bundle.getString("alamat"));
            nohp.setText(bundle.getString("nohp"));
            uid.setText(bundle.getString("id"));

        }else{
            /**
             * Apabila Bundle tidak ada, ambil dari Intent
             */
            nama.setText(getIntent().getStringExtra("lapangan"));
            harga.setText(getIntent().getStringExtra("harga"));
            alamat.setText(getIntent().getStringExtra("alamat"));
            nohp.setText(getIntent().getStringExtra("nohp"));
            uid.setText(getIntent().getStringExtra("id"));
        }

        _update = (Button)findViewById(R.id.btn_update);
        _update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                _nama = nama.getText().toString();
                _harga = harga.getText().toString();
                _alamat = alamat.getText().toString();
                _nohp = nohp.getText().toString();
                _uid = uid.getText().toString();

                HashMap<String, String> dt = new HashMap<String, String>();
                dt.put("lapangan", _nama);
                dt.put("harga", _harga);
                dt.put("alamat", _alamat);
                dt.put("nohp", _nohp);
                final JSONObject data = new JSONObject(dt);

                volleyObjectResult = new VolleyObjectResult() {
                    @Override
                    public void resSuccess(String requestType, JSONObject response) {
                        try {
                            String message = response.getString("message");

                            Intent toList = new Intent(UpdateBooking.this, MainBooking.class);
                            toList.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            UpdateBooking.this.startActivity(toList);
                            Toast.makeText(UpdateBooking.this, message, Toast.LENGTH_LONG).show();

                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void resError(String requestType, VolleyError error) {

                    }
                };
                volleyObjectService = new VolleyObjectService(volleyObjectResult, UpdateBooking.this);
                volleyObjectService.postJsonObject("POSTCALL", fungsi.urlbooking() + "/update/"+ _uid, data);

            }
        });

        _delete = (Button) findViewById(R.id.btn_delete);
        _delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _uid = uid.getText().toString();

                vor = new VolleyObjectResult() {
                    @Override
                    public void resSuccess(String requestType, JSONObject response) {

                        try {
                            String message = response.getString("message");

                            Intent toList = new Intent(UpdateBooking.this, MainBooking.class);
                            toList.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            UpdateBooking.this.startActivity(toList);
                            Toast.makeText(UpdateBooking.this, message, Toast.LENGTH_LONG).show();

                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void resError(String requestType, VolleyError error) {

                    }
                };
                vos = new VolleyObjectService(vor, UpdateBooking.this);
                vos.postJsonObject("GET", fungsi.urlbooking() +"/delete/"+ _uid, data);

            }
        });
    }
}
