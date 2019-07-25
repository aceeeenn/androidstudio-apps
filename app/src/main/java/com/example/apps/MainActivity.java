package com.example.apps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apps.ContactUser.Insert;
import com.example.apps.ContactUser.MainContact;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button hitung, go_contact, hitung1, signout, add_contact;
    EditText alas, tinggi, panjang, lebar;
    TextView hasil, hasilpp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.this.setTitle("Sentosa");

        hitung = (Button) findViewById(R.id.button);
        alas = (EditText) findViewById(R.id.alas);
        tinggi = (EditText) findViewById(R.id.tinggi);
        hasil = (TextView) findViewById(R.id.hasil);
        panjang = (EditText) findViewById(R.id.panjang);
        lebar = (EditText) findViewById(R.id.lebar);
        hitung1 = (Button) findViewById(R.id.button1);
        hasilpp = (TextView) findViewById(R.id.hasilpp);
        signout = (Button) findViewById(R.id.logout);
        add_contact = (Button) findViewById(R.id.add_contact);

        hitung.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Double param_satu = Double.parseDouble(alas.getText().toString());
                Double param_dua = Double.parseDouble(tinggi.getText().toString());

                Double result = rumus(param_satu, param_dua);
                hasil.setText("Hasil =" + String.valueOf(result));

                Bundle bundle = new Bundle();
                bundle.putString("alas", alas.getText().toString());
                bundle.putString("tinggi", tinggi.getText().toString());
                bundle.putString("hasil", String.valueOf(result));

                Intent intent = new Intent ( MainActivity.this, Hasil.class);
                intent.putExtras(bundle);
                startActivity(intent);

                Toast.makeText(getApplication(),"Hasil =" + String.valueOf(result), Toast.LENGTH_LONG).show();
            }
        });

        hitung1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Double param_tiga = Double.parseDouble(panjang.getText().toString());
                Double param_empat = Double.parseDouble(lebar.getText().toString());

                Double result = rumus1(param_tiga, param_empat);
                hasilpp.setText("Hasil =" + String.valueOf(result));

                Bundle bundle = new Bundle();
                bundle.putString("panjang", panjang.getText().toString());
                bundle.putString("lebar", lebar.getText().toString());
                bundle.putString("hasilpp", String.valueOf(result));

                Intent intent = new Intent ( MainActivity.this, Hasil.class);
                intent.putExtras(bundle);
                startActivity(intent);

                //Toast.makeText(getApplication(),"Hasil =" + String.valueOf(result), Toast.LENGTH_LONG).show();
            }
        });

        go_contact = (Button) findViewById(R.id.contact);
        go_contact.setOnClickListener(this);

        signout = (Button) findViewById(R.id.logout);
        signout.setOnClickListener(this);

        add_contact.setOnClickListener(this);

    }

    public Double rumus (Double alas, Double tinggi){
        Double segitiga = 0.5 * alas * tinggi;
        return segitiga;
    }

    public Double rumus1 (Double panjang, Double lebar){
        Double persegi = panjang * lebar;
        return persegi;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.contact:
                Intent goContact = new Intent(MainActivity.this, MainContact.class);
                goContact.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainActivity.this.startActivity(goContact);
                break;
            case R.id.button:
                break;
            case R.id.logout:
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                String email = prefs.getString("email", null);
                String password = prefs.getString("password", null);
                Log.d("debug", "email:" + email + "password" + password);

                if (email != null && password !=null){

                 final SharedPreferences sesdata = PreferenceManager
                     .getDefaultSharedPreferences(MainActivity.this);
                 SharedPreferences.Editor lds = sesdata.edit();
                 lds.clear();
                 lds.commit();

                 Intent intentLogin = new Intent(MainActivity.this, Login.class);
                 intentLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 intentLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                 MainActivity.this.startActivity(intentLogin);
            }
            break;
            case R.id.add_contact:
                Intent addContact = new Intent(MainActivity.this, Insert.class);
                addContact.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainActivity.this.startActivity(addContact);
                break;
            default:
                break;
        }
    }
}
