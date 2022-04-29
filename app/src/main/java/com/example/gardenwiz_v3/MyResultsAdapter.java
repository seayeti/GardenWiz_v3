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

public class MyResultsAdapter extends RecyclerView.Adapter<com.example.gardenwiz_v3.MyResultsAdapter.MyViewHolder> {

    String data1[], data2[];
    String images[];
    Context context;
    plantData plants;
    runsData runs;
    List<resultsData> results;
    String type[],symbol[];
    String state[];
    String bloomPeriod[];
    String edible[], shadeTol[], flowerColor[];
    String phMin[],phMax[];


    public MyResultsAdapter(Context ct, String[] s1, String[] s2, String[] img, List<resultsData> result, String[] typ, String[] bloomP, String[] stat, String[] edib, String[] shadeT, String[] flowerClr, String[] sym){
        context = ct;
        data1 = s1;
        data2 = s2;
        images = img;
        results =result;
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


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);





        return new com.example.gardenwiz_v3.MyResultsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.gardenwiz_v3.MyResultsAdapter.MyViewHolder holder, int position) {
        holder.myText1.setText(results.get(position).getCommonName());


        holder.myText2.setText(String.valueOf(results.get(position).getRunID()));
        //holder.myImage.setImageResource(images[position]);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.image_loading_logo);
        requestOptions.error(R.drawable.image_loading_logo);

        if(images[position] != null) {
            Glide.with(this.context)
                    .load(images[position])
                    .apply(requestOptions)
                    .into(holder.myImage);
            System.out.println("test");
        }else {
            holder.myImage.setImageResource(R.drawable.dandelion);
        }


        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PlantPage.class);

                intent.putExtra("dataName", results.get(position).getCommonName());
                intent.putExtra("data2", results.get(position).getRunID());

                // miroos

//                intent.putExtra("dataState", results.get(position).getState());
//                intent.putExtra("dataType", results.get(position).getType());
//                intent.putExtra("dataShadeT", results.get(position).getShadeTol());
//                intent.putExtra("dataEdible", results.get(position).getEdible());
//                intent.putExtra("dataBloomP", results.get(position).getBloomPeriod());
//                intent.putExtra("dataPhMin", results.get(position).getPhMin());
//                intent.putExtra("dataPhMax", results.get(position).getPhMax());
//                intent.putExtra("dataFlowerColor", results.get(position).getFlowerColor());
//                intent.putExtra("dataSymbol", results.get(position).getSymbol());

                // end miroos

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





            myImage = itemView.findViewById(R.id.imageView);
            //myText1.setText();
        }
    }
}