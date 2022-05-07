package com.example.gardenwiz_v3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;


public class SearchPlants extends AppCompatActivity {
    private Button button;
    public EditText plantName;
    public Spinner spinnerS,spinnerST,spinnerT,spinnerBP,spinnerD,spinnerDT, spinnerC;
    public Switch edible;
    private  String JWT = null;
    private  String gUserID = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_plants);

        Bundle bundle = getIntent().getExtras();
        JWT = bundle.getString("JWT");
        gUserID = bundle.getString("userID");

        // Switches

        //edible
        edible = (Switch) findViewById(R.id.edibleSwitch);


        // Plant name search, fetch input
        plantName = (EditText) findViewById(R.id.name_input);

        //Spinners

        //season
        spinnerS = (Spinner)findViewById(R.id.seasonSpinner);
        ArrayAdapter<CharSequence> adapter_season = ArrayAdapter.createFromResource(this, R.array.seasonDrop, android.R.layout.simple_spinner_item);
        adapter_season.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerS.setAdapter(adapter_season);

        //type
        spinnerT = (Spinner)findViewById(R.id.spinner_type);
        ArrayAdapter<CharSequence> adapter_type = ArrayAdapter.createFromResource(this, R.array.typeDrop, android.R.layout.simple_spinner_item);
        adapter_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerT.setAdapter(adapter_type);

        //state
        spinnerST = (Spinner)findViewById(R.id.spinner_state);
        ArrayAdapter<CharSequence> adapter_state = ArrayAdapter.createFromResource(this, R.array.StateDrop, android.R.layout.simple_spinner_item);
        adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerST.setAdapter(adapter_state);

        //bloom period
        spinnerBP = (Spinner)findViewById(R.id.bloomSpin);
        ArrayAdapter<CharSequence> adapter_bp = ArrayAdapter.createFromResource(this, R.array.bloomDrop, android.R.layout.simple_spinner_item);
        adapter_bp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBP.setAdapter(adapter_bp);

        //duration
        spinnerD = (Spinner)findViewById(R.id.durationSpin);
        ArrayAdapter<CharSequence> adapter_duration = ArrayAdapter.createFromResource(this, R.array.durationDrop, android.R.layout.simple_spinner_item);
        adapter_duration.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerD.setAdapter(adapter_duration);

        //drought
        spinnerDT = (Spinner)findViewById(R.id.droughtSpin);
        ArrayAdapter<CharSequence> adapter_droughtT = ArrayAdapter.createFromResource(this, R.array.droughtDrop, android.R.layout.simple_spinner_item);
        adapter_droughtT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDT.setAdapter(adapter_droughtT);

        //commercial
        spinnerC = (Spinner)findViewById(R.id.commSpin);
        ArrayAdapter<CharSequence> adapter_comm = ArrayAdapter.createFromResource(this, R.array.commDrop, android.R.layout.simple_spinner_item);
        adapter_comm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerC.setAdapter(adapter_comm);


        button = (Button) findViewById(R.id.searchButton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Boolean edi = edible.isChecked();
                String edibleInput;
                if (edi == true) {
                    edibleInput = "yes";
                } else {
                    edibleInput = "no";
                }
                openSearchGO(edibleInput);
            }
        });
    }
    public void openSearchGO(String edibleInput){
        Intent intent = new Intent(this, DisplayList.class);
        // passing searchbar inputs    (trim to eliminate white spaces)
        intent.putExtra("msg1", plantName.getText().toString().trim()); //name
        Bundle bundle = new Bundle();
        bundle.putString("JWT", JWT);
        bundle.putString("userID", gUserID);
        //passing spinner inputs
        intent.putExtra("spinnerST", spinnerST.getSelectedItem().toString());
        intent.putExtra("spinnerT", spinnerT.getSelectedItem().toString());
        intent.putExtra("spinnerS", spinnerS.getSelectedItem().toString());
        intent.putExtra("spinnerBP", spinnerBP.getSelectedItem().toString());
        intent.putExtra("spinnerDT", spinnerDT.getSelectedItem().toString());
        intent.putExtra("spinnerD", spinnerD.getSelectedItem().toString());
        intent.putExtra("spinnerC", spinnerC.getSelectedItem().toString());
        intent.putExtra("edibleInput", edibleInput);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}