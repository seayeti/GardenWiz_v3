package com.example.gardenwiz_v3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import API.resultsData;

public class PlantAdapter extends ArrayAdapter<Plant> {
    private static final String TAG = "PlantAdapter";
    private Context mContext;
    private int mResource;
    List<resultsData> results;


    public PlantAdapter(Context context, int resource, ArrayList<Plant> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        /// get plant infos
        String name = getItem(position).getName();
        String type = getItem(position).getType();
        String bp = getItem(position).getBloomPeriod();

        //Create the plant obj w info
//        Plant plant = new Plant(name,type,bp);


        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource,parent,false);

        TextView tvName = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvType = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvBP = (TextView) convertView.findViewById(R.id.textView3);

        tvName.setText(name);
        tvType.setText(type);
        tvBP.setText(bp);

        return convertView;
    }




}