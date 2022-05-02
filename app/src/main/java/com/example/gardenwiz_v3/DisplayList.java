package com.example.gardenwiz_v3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import API.RetrofitBuilder;
import API.plantApi;
import API.plantData;
import API.resultsData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DisplayList extends AppCompatActivity {

    private static final String TAG = "DisplayList";
    public String msg1;
    public String seasonSpinner,typeSpinner,edibleSpinner,stateSpinner,bpSpinner,dtSpinner,dSpinner, cSpinner;
    public ArrayList<Plant> plantList;
    Context context = DisplayList.this;
    List<resultsData> results;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_go);

        // importing inputs from b1 xml
        Bundle bundle = getIntent().getExtras();
        msg1 = bundle.getString("msg1");

        // reading spinner inputs
        seasonSpinner = bundle.getString("spinnerS");
        typeSpinner = bundle.getString("spinnerT");
        edibleSpinner = bundle.getString("edibleInput");
        stateSpinner = bundle.getString("spinnerST");
        bpSpinner = bundle.getString("spinnerBP");
        dtSpinner = bundle.getString("spinnerDT");
        dSpinner = bundle.getString("spinnerD");
        cSpinner = bundle.getString("spinnerC");

        // changing names
        if(typeSpinner.equals("Flower")){
            typeSpinner = "Forb";
        }
        else if (typeSpinner.equals("Grass")){
            typeSpinner = "Graminoid";
        }

        ListView mListView =(ListView) findViewById(R.id.mListView);

        plantList = new ArrayList<>();

        Retrofit retrofit = RetrofitBuilder.getInstance();
        plantApi myPlantAPI = retrofit.create(plantApi.class);
        Call<List<plantData>> list = myPlantAPI.getsearchPlantsMin(msg1,typeSpinner,seasonSpinner,bpSpinner,stateSpinner,cSpinner,dtSpinner,edibleSpinner);
        list.enqueue(new Callback<List<plantData>>() {
            @Override
            public void onResponse(Call<List<plantData>> call, Response<List<plantData>> response3) {
                //String[] plantNames = new String[response3.body().size()];
                for (int i = 0; i < response3.body().size(); i++) {
                    System.out.println("NAME: "+response3.body().get(i).getCommonName());
                    System.out.println("   "+response3.body().get(i).getpH_Minimum());


                    plantList.add(new Plant(response3.body().get(i).getCommonName(),response3.body().get(i).getGrowthHabit(),"",response3.body().get(i).getBloomPeriod()));
                }
                PlantAdapter adapter = new PlantAdapter(context, R.layout.adapter_view_layout, plantList);
                mListView.setAdapter(adapter);
                Log.d("TESTING:", "size of plantList is: "+plantList.size()+"\n");

                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(DisplayList.this, PlantPage.class);
                        intent.putExtra("dataName", plantList.get(position).getName());
//                        intent.putExtra("dataState", plantList.get(position).getState());
                        intent.putExtra("dataType", plantList.get(position).getType());
//                        intent.putExtra("dataShadeT", plantList.get(position).getShadeTol());
//                        intent.putExtra("dataEdible", plantList.get(position).getEdible());
                        intent.putExtra("dataBloomP", plantList.get(position).getBloomPeriod());
//                        intent.putExtra("dataPhMin", plantList.get(position).getPhMin());
//                        intent.putExtra("dataPhMax", plantList.get(position).getPhMax());
//                        intent.putExtra("dataFlowerColor", plantList.get(position).getFlowerColor());
//                        intent.putExtra("dataSymbol", plantList.get(position).getSymbol());


                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<plantData>> call, Throwable t) {
                call.toString();
                System.out.println(call.toString());
            }

        });



    }
}