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

import java.util.List;

import API.plantData;
import API.resultsData;
import API.runsData;

public class MyPlantAdapter extends RecyclerView.Adapter<com.example.gardenwiz_v3.MyPlantAdapter.MyViewHolder> {

        String data1[], data2[];
        int images[];
        Context context;
        plantData plants;
        runsData runs;
        List<resultsData> results;


        public MyPlantAdapter(Context ct, String s1[], String s2[], int img[], List<resultsData> result){
            context = ct;
            data1 = s1;
            data2 = s2;
            images = img;
            results =result;

        }


        @NonNull
        @Override
        public com.example.gardenwiz_v3.MyPlantAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.my_row, parent, false);





            return new com.example.gardenwiz_v3.MyPlantAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull com.example.gardenwiz_v3.MyPlantAdapter.MyViewHolder holder, int position) {
            holder.myText1.setText(results.get(position).getCommonName());


            holder.myText2.setText(String.valueOf(results.get(position).getRunID()));
            //holder.myImage.setImageResource(images[position]);

            holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Results.class);

                    intent.putExtra("data1", results.get(position).getCommonName());
                    intent.putExtra("data2", results.get(position).getRunID());
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
                //myImage = itemView.findViewById(R.id.imageView);
                //myText1.setText();
            }
        }
    }



