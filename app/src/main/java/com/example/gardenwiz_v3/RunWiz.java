package com.example.gardenwiz_v3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;


public class RunWiz extends MainActivity implements QuickQuery.OnMyDialogResult {
    Context context = RunWiz.this;
    RecyclerView recyclerView;
    private TextView runNameT;
    private TextView durationValT;
    private TextView humidValT;
    private TextView moistureValT;
    private TextView lightValT;
    private TextView tempValT;
    private TextView phValT;
    private TextView rainValT;
    private Button viewRes;

    private int piStatus = 0;
    private ListView listView;
    private int runID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pigo);

        //for listing plants
        listView = findViewById(R.id.plantslistView);

        // TextView vars w/ locations
        runNameT = (TextView) findViewById(R.id.name_of_run);
        durationValT = (TextView) findViewById(R.id.durationList);
        humidValT = (TextView) findViewById(R.id.humidity);
        moistureValT = (TextView) findViewById(R.id.moisture);
        lightValT = (TextView) findViewById(R.id.lumen);
        tempValT = (TextView) findViewById(R.id.temperature);
        phValT = (TextView) findViewById(R.id.ph);
        rainValT = (TextView) findViewById(R.id.rain);

        // buttons
        Button quickRun = findViewById(R.id.quickRun);
        Button advRun = findViewById(R.id.advRun);
        Button viewRes = findViewById(R.id.allRes);

        quickRun.setOnClickListener(v -> showQuickDialog());
        advRun.setOnClickListener(v -> showAdvDialog());
        viewRes.setOnClickListener(v -> getResPage());
    }
    //helper methods

    //Dialogs
    //Quick Run Dialog
    public void showQuickDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new QuickQuery();
        dialog.show(getSupportFragmentManager(), "quickDialog");

    }
    //Adv Run Dialog
    public void showAdvDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new AdvQuery(this);
        dialog.show(getSupportFragmentManager(), "AdvQuery");
    }

    //Res page
    public void getResPage() {
        if (runID != 0) {
            Intent intent = new Intent(context, Results.class);
            Results.runID = String.valueOf(runID);
            context.startActivity(intent);


        }
    }


    @Override
    public void applyTexts(String[] plantNames, int run_id, String runName, String humidVal,
                           String moistureVal, String lightVal, String tempVal, String rainVal,
                           String phVal, int Duration) {

        String durationTemp = "" + Duration;
        String[] nullArr = new String[]{"None","None"};
        runID = run_id;
        runNameT.setText("Run ID: " + runName);
        humidValT.setText("Humidity: " + humidVal + "%");
        moistureValT.setText("Moisture: " + moistureVal + "%");
        lightValT.setText("Light: " + lightVal + "%");
        tempValT.setText("Temperature: " + tempVal + "\u00B0F");
        rainValT.setText("Rainfall: " + rainVal);
        phValT.setText("PH: " + phVal);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, plantNames);
        listView.setAdapter(adapter);

    }

}