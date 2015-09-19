package com.example.javierhuinocana.grupo03_cibertec.adap_spiner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.javierhuinocana.grupo03_cibertec.R;

import java.util.List;

/**
 * Created by Javier Huiñocana on 07/09/2015.
 */
public class SpinerAdapter extends ArrayAdapter<String> {

    public SpinerAdapter(Context context, List<String> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_spiner_fitro, parent, false);
        TextView tvMainItemSpcn = (TextView) convertView.findViewById(R.id.lblItemSpiner);
        String dato = getItem(position);
        tvMainItemSpcn.setText(dato);
        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_spiner_fitro, parent, false);
        TextView tvMainItemSpcn = (TextView) convertView.findViewById(R.id.lblItemSpiner);
        String dato = getItem(position);
        tvMainItemSpcn.setText(dato);
        return convertView;
    }
}
