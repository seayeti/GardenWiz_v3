package com.example.gardenwiz_v3;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import API.resultsData;

public class PlantPage extends Activity {
    Context context = PlantPage.this;
    RecyclerView recyclerView;
    ImageView mainImage;
    TextView PlantName, Desc,PlantType, PlantState,PlantShadeT,PlantEdible,PlantBloomP;
    TextView PlantPHMin,PlantPHMax,PlantFlowerColor;
    List<resultsData> results;

    String plantName, desc="";
    String plantType, plantState,plantShadeT,plantEdible,plantBloomP,plantFlowerColor, plantSymbol;
    double plantPHMinInt,plantPHMaxInt;
    String plantPHMin,plantPHMax;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_page);


        //image
        mainImage = findViewById(R.id.imageView3);

        // UNCHECK
        Desc = findViewById(R.id.myText0);

        PlantName = findViewById(R.id.myText1);
        PlantType = findViewById(R.id.myText3);
        PlantState = findViewById(R.id.myText2);
        PlantShadeT = findViewById(R.id.myText4);
        PlantEdible = findViewById(R.id.myText5);
        PlantBloomP = findViewById(R.id.myText6);
        PlantPHMin = findViewById(R.id.myText7);
        PlantPHMax = findViewById(R.id.myText8);
        PlantFlowerColor = findViewById(R.id.myText9);

        // image
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.drawable.dandelion);
//        requestOptions.error(R.drawable.dandelion);
//
//        Glide.with(PlantPage.this)
//                .load("https://plants.sc.egov.usda.gov/ImageLibrary/original/acba3_001_php.jpg")
//                .apply(requestOptions)
//                .into(mainImage);
        //

        plantName = "testing";
        getData();
        setData();

    }
    private void getData(){
        if(getIntent().hasExtra("data1") && getIntent().hasExtra("data2")){

            plantName = getIntent().getStringExtra("data1");

            // UNCHECK
            desc = getIntent().getStringExtra("data2");

//            plantType = "hi";
//            plantState = "hi";
//            plantShadeT = "hi";
//            plantEdible = "hi";
//            plantBloomP = "hi";
//            plantPHMinInt = getIntent().getDoubleExtra("dataPhMin",0);
//            plantPHMin = "hi";
//            plantPHMaxInt = getIntent().getDoubleExtra("dataPhMax",0);
//            plantPHMax = "hi";
//            plantFlowerColor = "hi";
//            plantSymbol = "hi";

            plantType = getIntent().getStringExtra("dataType");
            plantState = getIntent().getStringExtra("dataState");
            plantShadeT = getIntent().getStringExtra("dataShadeT");
            plantEdible = getIntent().getStringExtra("dataEdible");
            plantBloomP = getIntent().getStringExtra("dataBloomP");
            plantPHMinInt = getIntent().getDoubleExtra("dataPhMin",0);
            plantPHMin = String.valueOf(plantPHMinInt);
            plantPHMaxInt = getIntent().getDoubleExtra("dataPhMax",0);
            plantPHMax = String.valueOf(plantPHMaxInt);
            plantFlowerColor = getIntent().getStringExtra("dataFlowerColor");
            plantSymbol = getIntent().getStringExtra("dataSymbol");

        }
        else if(getIntent().hasExtra("dataName")){
            plantName = getIntent().getStringExtra("dataName");
            plantType = getIntent().getStringExtra("dataType");
            plantState = getIntent().getStringExtra("dataState");
            plantShadeT = getIntent().getStringExtra("dataShadeT");
            plantEdible = getIntent().getStringExtra("dataEdible");
            plantBloomP = getIntent().getStringExtra("dataBloomP");
            plantPHMinInt = getIntent().getDoubleExtra("dataPhMin",0);
            plantPHMin = String.valueOf(plantPHMinInt);
            plantPHMaxInt = getIntent().getDoubleExtra("dataPhMax",0);
            plantPHMax = String.valueOf(plantPHMaxInt);
            plantFlowerColor = getIntent().getStringExtra("dataFlowerColor");
            plantSymbol = getIntent().getStringExtra("dataSymbol");

        }
        else{
            plantName = "test";
        }

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
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.drawable.image_loading_logo);
//        requestOptions.error(R.drawable.image_loading_logo);
//        String sym = plantSymbol.toLowerCase();
//        Glide.with(PlantPage.this)
//                .load("https://plants.sc.egov.usda.gov/ImageLibrary/original/"+sym+"_002_php.jpg")
//                .apply(requestOptions)
//                .into(mainImage);
    }


}
