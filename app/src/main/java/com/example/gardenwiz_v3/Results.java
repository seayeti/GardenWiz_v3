package com.example.gardenwiz_v3;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import API.RetrofitBuilder;
import API.plantApi;
import API.resultsData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Results extends Activity {
    Context context = Results.this;
    RecyclerView recyclerView;
    ImageView mainImage;
    TextView RunName, Desc;

    String runName, desc;
    static String runID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);



        recyclerView = findViewById(R.id.recyclerView);
        Retrofit retrofit = RetrofitBuilder.getInstance();
        plantApi myPlantAPI = retrofit.create(plantApi.class);
        int runid = Integer.parseInt(runID);
        Call<List<resultsData>> list = myPlantAPI.getresultsData(runid);
        list.enqueue(new Callback<List<resultsData>>() {
            @Override
            public void onResponse(Call<List<resultsData>> call, Response<List<resultsData>> response3) {
                String[] plantNames = new String[response3.body().size()];
                String[] betyID = new String[response3.body().size()];
                String[] state = new String[response3.body().size()];
                String[] type = new String[response3.body().size()];
                String[] shadeT = new String[response3.body().size()];
                String[] edible = new String[response3.body().size()];
                String[] bloomP = new String[response3.body().size()];
                String[] phMin = new String[response3.body().size()];
                String[] phMax = new String[response3.body().size()];
                String[] flowerColor = new String[response3.body().size()];
                String[] symbol = new String[response3.body().size()];

                List<resultsData> resultsdata = response3.body();
                for (int i = 0; i < response3.body().size(); i++) {

                    plantNames[i] = String.valueOf(response3.body().get(i).getCommonName());
                    betyID[i] = String.valueOf(response3.body().get(i).getBetyID());

                    state[i] = String.valueOf(response3.body().get(i).getState());
                    type[i] = String.valueOf(response3.body().get(i).getType());
                    shadeT[i] = String.valueOf(response3.body().get(i).getShadeTol());
                    edible[i] = String.valueOf(response3.body().get(i).getEdible());
                    bloomP[i] = String.valueOf(response3.body().get(i).getBloomPeriod());
//                    phMin[i] = String.valueOf(response3.body().get(i).getPhMin());
//                    phMax[i] = String.valueOf(response3.body().get(i).getPhMax());
                    flowerColor[i] = String.valueOf(response3.body().get(i).getFlowerColor());
                    symbol[i] = String.valueOf(response3.body().get(i).getSymbol());

                    //System.out.println(response3.body().get(i).getBetyID());
                }
                int[] images = null;
                //
                //MyResultsAdapter myAdapter = new MyResultsAdapter(context, plantNames, betyID, images, resultsdata );
                //
                MyResultsAdapter myAdapter = new MyResultsAdapter(context, plantNames, betyID, images, resultsdata, type, bloomP, state,  edible, shadeT, flowerColor, symbol);

                recyclerView.setAdapter(myAdapter);
                //plantList.add();
            }

            @Override
            public void onFailure(Call<List<resultsData>> call, Throwable t) {
                call.toString();
                System.out.println(call.toString());
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}