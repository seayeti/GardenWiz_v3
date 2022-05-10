package com.example.gardenwiz_v3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import API.PlantImages;
import API.RetrofitBuilder;
import API.RetrofitImages;
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
    private  String JWT = null;
    private  String gUserID = null;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_go);
        Bundle bundle = getIntent().getExtras();
        JWT = bundle.getString("JWT");
        gUserID = bundle.getString("userID");


        // importing inputs from b1 xml
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

        //ListView mListView =(ListView) findViewById(R.id.mListView);
        recyclerView = findViewById(R.id.recyclerView2);
        plantList = new ArrayList<>();
        System.out.println("test");
        Retrofit retrofit = RetrofitBuilder.getInstance();
        plantApi myPlantAPI = retrofit.create(plantApi.class);
        Call<List<plantData>> list = myPlantAPI.getsearchPlantsMin("Bearer " + JWT, msg1,typeSpinner,seasonSpinner,bpSpinner,stateSpinner,cSpinner,dtSpinner,edibleSpinner);
        list.enqueue(new Callback<List<plantData>>() {
            @Override
            public void onResponse(Call<List<plantData>> call, Response<List<plantData>> response3) {
                System.out.println("plant work");
                String[] plantNames = new String[response3.body().size()];
                String[] betyID = new String[response3.body().size()];
                String[] sciName = new String[response3.body().size()];
                String[] state = new String[response3.body().size()];
                String[] type = new String[response3.body().size()];
                String[] shadeT = new String[response3.body().size()];
                String[] edible = new String[response3.body().size()];
                String[] bloomP = new String[response3.body().size()];
                String[] phMin = new String[response3.body().size()];
                String[] phMax = new String[response3.body().size()];
                String[] flowerColor = new String[response3.body().size()];
                String[] symbol = new String[response3.body().size()];
                String[] images = new String[response3.body().size()];
                List<plantData> plantData = response3.body();

//                System.out.println("test"+plantData.get(0).getCommonName());


                for (int i = 0; i < response3.body().size(); i++) {
                    //System.out.println(response3.body().get(i).getCommonName());
                    plantNames[i] = String.valueOf(response3.body().get(i).getCommonName());
                    betyID[i] = String.valueOf(response3.body().get(i).getBetydbspeciesid());
                    sciName[i] = String.valueOf(response3.body().get(i).getScientificName());


                Retrofit retrofit = RetrofitImages.getInstance();
                plantApi myPlantAPI = retrofit.create(plantApi.class);
                Call<PlantImages> list = myPlantAPI.getplantImages("query","json","pageimages",sciName[i], "2","100");
                int finalI = i;
                list.enqueue(new Callback<PlantImages>() {
                    @Override
                    public void onResponse(Call<PlantImages> call, Response<PlantImages> response) {
//                        System.out.println("------");
                        PlantImages plants;
                        if(response.body().getQuery().getPages().get(0).getMissing() == null) {
                            //System.out.println(response.body().getQuery().getPages().get(0).getThumbnail().getSource());
                            if (response.body().getQuery().getPages().get(0).getThumbnail() != null) {
                                images[finalI] = response.body().getQuery().getPages().get(0).getThumbnail().getSource();
                            }
                        }
                        if(finalI == response3.body().size()-1){
                            //
//                            MyResultsAdapter myAdapter = new MyResultsAdapter(context, plantNames, betyID, images, resultsdata );
                            //
                            PlantAdapter myAdapter = new PlantAdapter(context, plantNames, betyID, images, plantData,JWT,gUserID);


                            recyclerView.setAdapter(myAdapter);


                        }
                    }

                    @Override
                    public void onFailure(Call<PlantImages> call, Throwable t) {
                        System.out.println("plant fail");
                    }

                });

                }



//                recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Intent intent = new Intent(DisplayList.this, PlantPage.class);
//                        intent.putExtra("dataName", plantList.get(position).getName());
////                        intent.putExtra("dataState", plantList.get(position).getState());
//                        intent.putExtra("dataType", plantList.get(position).getType());
////                        intent.putExtra("dataShadeT", plantList.get(position).getShadeTol());
////                        intent.putExtra("dataEdible", plantList.get(position).getEdible());
//                        intent.putExtra("dataBloomP", plantList.get(position).getBloomPeriod());
////                        intent.putExtra("dataPhMin", plantList.get(position).getPhMin());
////                        intent.putExtra("dataPhMax", plantList.get(position).getPhMax());
////                        intent.putExtra("dataFlowerColor", plantList.get(position).getFlowerColor());
////                        intent.putExtra("dataSymbol", plantList.get(position).getSymbol());
//                        Bundle bundle = new Bundle();
//                        bundle.putString("JWT", JWT);
//                        bundle.putString("userID", gUserID);
//                        intent.putExtras(bundle);
//
//
//                        startActivity(intent);
//                    }
//                });
//            }
            }
                @Override
                public void onFailure (Call < List < plantData >> call, Throwable t){
                    call.toString();
                    System.out.println(call.toString());
                }


        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
