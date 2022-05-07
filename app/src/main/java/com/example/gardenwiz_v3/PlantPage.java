package com.example.gardenwiz_v3;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

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

public class PlantPage extends AppCompatActivity {
    Context context = PlantPage.this;
    RecyclerView recyclerView;
    ImageView mainImage;
    TextView PlantName, Desc,PlantType, PlantState,PlantShadeT,PlantEdible,PlantBloomP;
    TextView PlantPHMin,PlantPHMax,PlantFlowerColor;
    List<resultsData> results;

    String plantName, desc="";
    String plantType, plantState,plantShadeT,plantEdible,plantBloomP,plantFlowerColor, plantSymbol, plantSciName;
    double plantPHMinInt,plantPHMaxInt;
    String plantPHMin,plantPHMax;
    private  String JWT = null;
    private  String gUserID = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_page);
        Bundle bundle = getIntent().getExtras();
        JWT = bundle.getString("JWT");
        gUserID = bundle.getString("userID");

        //image
        mainImage = findViewById(R.id.plant_pic);

        // UNCHECK
        Desc = findViewById(R.id.plant_desc);

        PlantName = findViewById(R.id.plant_name_res);
        PlantType = findViewById(R.id.plant_type_data);
        PlantState = findViewById(R.id.state_data);
        PlantShadeT = findViewById(R.id.shade_data);
        PlantEdible = findViewById(R.id.edible_data);
        PlantBloomP = findViewById(R.id.bloomP_data);
        PlantPHMin = findViewById(R.id.ph_min_data);
        PlantPHMax = findViewById(R.id.ph_max_data);
        PlantFlowerColor = findViewById(R.id.flower_color_data);

        // image
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.dandelion);
        requestOptions.error(R.drawable.dandelion);
//
        Glide.with(PlantPage.this)
                .load("https://plants.sc.egov.usda.gov/ImageLibrary/original/acba3_001_php.jpg")
                .apply(requestOptions)
                .into(mainImage);


        plantName = "testing";
        getData();


    }
    private void getData(){

            plantName = getIntent().getStringExtra("dataName");

            // UNCHECK
            desc = getIntent().getStringExtra("data2");
            System.out.println("test");
            Retrofit retrofit = RetrofitBuilder.getInstance();
            plantApi myPlantAPI = retrofit.create(plantApi.class);
            Call<plantData> list = myPlantAPI.getplantData("Bearer " + JWT, plantName);
            list.enqueue(new Callback<plantData>() {
                @Override
                public void onResponse(Call<plantData> call, Response<plantData> response) {
                    System.out.println("test");
                    plantData plants = response.body();
                    plantType = plants.getGrowthHabit();
                    plantSciName = plants.getScientificName();
                    plantState = plants.getState();
                    plantShadeT = plants.getShadeTolerance();
                    plantEdible = plants.getPalatableHuman();
                    plantBloomP = plants.getBloomPeriod();
                    plantPHMinInt = plants.getpH_Minimum();
                    plantPHMin = String.valueOf(plants.getpH_Minimum());
                    plantPHMaxInt = plants.getpH_Maximum();
                    plantPHMax = String.valueOf(plants.getpH_Maximum());
                    plantFlowerColor = plants.getFlowerColor();
                    plantSymbol = plants.getFlowerColor();
                    setData();
                }

                @Override
                public void onFailure(Call<plantData> call, Throwable t) {
                    System.out.println("failed");
                    System.out.println(t.getMessage());
                }
            });


    }
    private void setData(){
        PlantName.setText(plantName);

        // UNCHECK
        Desc.setText(desc);


        PlantType.setText(plantType);
        PlantState.setText(plantState);
        PlantShadeT.setText(plantShadeT);
        PlantEdible.setText(plantEdible);
        PlantBloomP.setText(plantBloomP);
        PlantPHMin.setText(plantPHMin);
        PlantPHMax.setText(plantPHMax);
        PlantFlowerColor.setText(plantFlowerColor);


        // for images

        Retrofit retrofit = RetrofitImages.getInstance();
        plantApi myPlantAPI = retrofit.create(plantApi.class);
        System.out.println(plantSciName);
        Call<PlantImages> list = myPlantAPI.getplantImages("query","json","pageimages",plantSciName, "2","500");
        list.enqueue(new Callback<PlantImages>() {
            @Override
            public void onResponse(Call<PlantImages> call, Response<PlantImages> response) {
                System.out.println("------");

                PlantImages plants;
                if(response.body().getQuery().getPages().get(0).getMissing() == null) {
                    System.out.println(response.body().getQuery().getPages().get(0).getThumbnail().getSource());
                    System.out.println(response.body().getQuery().getPages().get(0).getThumbnail().getWidth());
                    System.out.println(response.raw().toString());


                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.placeholder(R.drawable.image_loading_logo);
                    requestOptions.error(R.drawable.image_loading_logo);
                    if (response.body().getQuery().getPages().get(0).getThumbnail().getSource() != null) {
                        Glide.with(PlantPage.this)
                                .load(response.body().getQuery().getPages().get(0).getThumbnail().getSource())
                                .apply(requestOptions)
                                .into(mainImage);
//
                    }else {
                        mainImage.setImageResource(R.drawable.dandelion);
                    }
                }
            }

            @Override
            public void onFailure(Call<PlantImages> call, Throwable t) {

            }
        });
//




    }


}



