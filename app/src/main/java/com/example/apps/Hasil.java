package com.example.apps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Hasil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);


        TextView alas = (TextView) findViewById(R.id.valuealas);
        TextView tinggi = (TextView) findViewById(R.id.valuetinggi);
        TextView hasil = (TextView) findViewById(R.id.valuehasil);
        TextView panjang = (TextView) findViewById(R.id.valuepanjang);
        TextView lebar = (TextView) findViewById(R.id.valuelebar);
        TextView hasil1 = (TextView) findViewById(R.id.valuehasilpersegi);

        if(getIntent().getExtras()!=null){
            /**
             * Jika Bundle ada, ambil data dari bundle
             */
            Bundle bundle = getIntent().getExtras();
            alas.setText(bundle.getString("alas"));
            tinggi.setText(bundle.getString("tinggi"));
            hasil.setText(bundle.getString("hasil"));
            panjang.setText(bundle.getString("panjang"));
            lebar.setText(bundle.getString("lebar"));
            hasil1.setText(bundle.getString("hasilpp"));
        }else{
            /**
             * Apabila Bundle tidak ada, ambil dari intent
             */
            alas.setText(getIntent().getStringExtra("alas"));
            tinggi.setText(getIntent().getStringExtra("tinggi"));
            hasil.setText(getIntent().getStringExtra("hasil"));
            panjang.setText(getIntent().getStringExtra("panjang"));
            lebar.setText(getIntent().getStringExtra("lebar"));
            hasil1.setText(getIntent().getStringExtra("hasilpp"));
        }
    }
}
