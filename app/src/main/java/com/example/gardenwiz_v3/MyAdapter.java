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

import API.plantData;
import API.resultsData;
import API.runsData;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String data1[], data2[];
    String images[];
    Context context;
    plantData plants;
    runsData runs;
    resultsData results;
    String type[],symbol[];
    String state[];
    String bloomPeriod[];
    String edible[], shadeTol[], flowerColor[];
    String phMin[],phMax[];


    public MyAdapter(Context ct, String s1[], String s2[], String img[],plantData plants,  String typ[], String bloomP[], String stat[], String edib[], String shadeT[], String flowerClr[], String sym[]){
        context = ct;
        data1 = s1;
        data2 = s2;
        images = img;

        type = typ;
        bloomPeriod = bloomP;
        state = stat;
//        droughtTol = droughtT;
        edible = edib;
//        phMin = phmin;
//        phMax = phmax;
        shadeTol = shadeT;
        flowerColor = flowerClr;
        symbol = sym;


    }
    public MyAdapter(Context ct, String s1[], String s2[], String img[]){
        context = ct;
        data1 = s1;
        data2 = s2;
        images = img;


    }
    public MyAdapter(Context ct, String s1[], String s2[], String img[], runsData runs ){
        context = ct;
        data1 = s1;
        data2 = s2;
        images = img;


    }
    public MyAdapter(Context ct, String s1[], String s2[], String img[],resultsData results){
        context = ct;
        data1 = s1;
        data2 = s2;
        images = img;


    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);





        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText(data1[position]);
        holder.myText2.setText(data2[position]);



        //holder.myImage.setImageResource(images[position]);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Results.class);
                intent.putExtra("data1", data1[position]);
                intent.putExtra("data2", data2[position]);

                // miroos

                intent.putExtra("dataState", data2[position]);
                intent.putExtra("dataType", data2[position]);
                intent.putExtra("dataShadeT", data2[position]);
                intent.putExtra("dataEdible", data2[position]);
                intent.putExtra("dataBloomP", "HIII");
                intent.putExtra("dataPhMin", data2[position]);
                intent.putExtra("dataPhMax", data2[position]);
                intent.putExtra("dataFlowerColor", data2[position]);
                intent.putExtra("dataSymbol", data2[position]);

                // end miroos
                Results.runID = data2[position];
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
            myText1 = itemView.findViewById(R.id.myText1);
            myText2 = itemView.findViewById(R.id.myText2);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //myImage = itemView.findViewById(R.id.imageView);
            //myText1.setText();
        }
    }
}