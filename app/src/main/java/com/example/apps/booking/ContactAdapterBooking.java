package com.example.apps.booking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.apps.ContactUser.ContactDataSet;
import com.example.apps.ContactUser.Update;
import com.example.apps.R;

import java.util.ArrayList;
import java.util.List;


public class ContactAdapterBooking extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ContactDataSetBooking> list;
    private ArrayList<ContactDataSetBooking> arrayList;


    public ContactAdapterBooking(Activity activity, List<ContactDataSetBooking> list) {
        this.activity = activity;
        this.list = list;
        this.arrayList = new ArrayList<ContactDataSetBooking>();
        this.arrayList.addAll(list);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row_lapangan, null);
        TextView lapangan = (TextView) convertView.findViewById(R.id.lapangan);
        TextView alamat = (TextView) convertView.findViewById(R.id.alamat);
        TextView nohp = (TextView) convertView.findViewById(R.id.nohp);
        TextView harga = (TextView) convertView.findViewById(R.id.harga);
//        Spinner jam = (Spinner) convertView.findViewById(R.id.jam);

        final ContactDataSetBooking mds = list.get(position);
        lapangan.setText(mds.getLapangan());
        alamat.setText(mds.getAlamat());
        nohp.setText(mds.getNohp());
        harga.setText(mds.getHarga());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iSlider = new Intent(activity, UpdateBooking.class);
                Bundle extras = new Bundle();
                extras.putString("id", list.get(position).getId());
                extras.putString("lapangan",  list.get(position).getLapangan());
                extras.putString("harga",  list.get(position).getHarga());
                extras.putString("alamat",  list.get(position).getAlamat());
                extras.putString("nohp",  list.get(position).getNohp());
                iSlider.putExtras(extras);
                activity.startActivity(iSlider);
            }
        });

        return convertView;
    }
}
