package com.example.gardenwiz_v3;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import API.RetrofitBuilder;
import API.plantApi;
import API.runsData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class History extends AppCompatActivity {

    RecyclerView recyclerView;

    String s1[], s2[];
    Context context = History.this;
    //int images[] = {R.drawable.dandelion, R.drawable.sunflower};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Retrofit retrofit = RetrofitBuilder.getInstance();
        plantApi myPlantAPI = retrofit.create(plantApi.class);
        Call<List<runsData>> list = myPlantAPI.getrunsData();

        list.enqueue(new Callback<List<runsData>>() {
            @Override
            public void onResponse(Call<List<runsData>> call, Response<List<runsData>> response3) {
                System.out.println("dumb");
                String[] plantNames = new String[response3.body().size()];
                String[] betyID = new String[response3.body().size()];
                for (int i = 0; i < response3.body().size(); i++) {

                    plantNames[i] = String.valueOf(response3.body().get(i).getRunName());
                    betyID[i] = String.valueOf(response3.body().get(i).getRunID());
                    System.out.println(response3.body().get(i).getRunID());
                }
                int[] images = null;
                MyAdapter myAdapter = new MyAdapter(context, plantNames, betyID, images);
                recyclerView.setAdapter(myAdapter);
                //plantList.add();
            }

            @Override
            public void onFailure(Call<List<runsData>> call, Throwable t) {
                System.out.println(t.getMessage());
                call.toString();
                System.out.println(call.toString());
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}