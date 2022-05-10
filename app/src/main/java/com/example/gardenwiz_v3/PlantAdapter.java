package com.example.gardenwiz_v3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import API.plantData;
import API.resultsData;
import API.runsData;

public class PlantAdapter extends RecyclerView.Adapter<com.example.gardenwiz_v3.PlantAdapter.MyViewHolder> {

    String data1[], data2[];
    String images[];
    Context context;
    plantData plants;
    runsData runs;
    List<plantData> results;
    private  String JWT = null;
    private  String gUserID = null;

    public PlantAdapter(Context ct, String[] s1, String[] s2, String[] img, List<plantData> result, String passedJWT, String userID){
        context = ct;
        data1 = s1;
        data2 = s2;
        images = img;
        results =result;
        System.out.println(results.get(0).getCommonName());
        JWT = passedJWT;
        gUserID = userID;
    }

    @NonNull
    @Override
    public com.example.gardenwiz_v3.PlantAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);





        return new com.example.gardenwiz_v3.PlantAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.gardenwiz_v3.PlantAdapter.MyViewHolder holder, int position) {
        holder.myText1.setText(results.get(position).getCommonName());


        holder.myText2.setText(String.valueOf(results.get(position).getBloomPeriod()));
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.image_loading_logo);
        requestOptions.error(R.drawable.image_loading_logo);

        if(images[position] != null) {
            Glide.with(this.context)
                    .load(images[position])
                    .apply(requestOptions)
                    .into(holder.myImage);

        }else {
            holder.myImage.setImageResource(R.drawable.dandelion);
        }
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PlantPage.class);

                intent.putExtra("dataName", results.get(position).getCommonName());
                intent.putExtra("data2", results.get(position).getBloomPeriod());
                intent.putExtra("JWT",JWT);
                intent.putExtra("userID",gUserID);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1, myText2;
        ImageView myImage;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            myText1 = itemView.findViewById(R.id.plant_name_res);
            myText2 = itemView.findViewById(R.id.state_data);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            myImage = itemView.findViewById(R.id.imageView);
            //myText1.setText();
        }
    }
}