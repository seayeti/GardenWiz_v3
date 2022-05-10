package com.example.gardenwiz_v3;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.gardenwiz_v3.QuickQuery;

import java.util.List;

import API.RetrofitBuilder;
import API.SensorApi;
import API.plantApi;
import API.plantData;
import API.resultsData;
import API.runsData;
import API.sensorData;
import bluetooth.Devices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdvQuery extends DialogFragment implements OnItemSelectedListener {
    private Context context;
    private EditText input_name;
    private QuickQuery.OnMyDialogResult listener;
    private Spinner seasonSpin;
    private Spinner typeSpin;
    private Spinner bloomSpin;
    private Spinner droughtSpin;
    private Spinner durationSpin;
    private Spinner stateSpin;
    private Spinner commSpin;
    private Switch edible;
    private sensorData sensors;
    private int test =0;
    private  String JWT = null;
    private  String gUserID = null;

    public AdvQuery(Context context) {
        this.context = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View v = (inflater.inflate(R.layout.advdialog, null));
        JWT = getArguments().getString("JWT");
        gUserID = getArguments().getString("userID");

        //set edit texts
        input_name = v.findViewById(R.id.name_input);

        //set spinners
        //season
        seasonSpin = (Spinner) v.findViewById(R.id.seasonSpinner);
        ArrayAdapter<CharSequence> seasonAdapter = ArrayAdapter.createFromResource(context,
                R.array.seasonDrop, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        seasonAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        seasonSpin.setAdapter(seasonAdapter);
        seasonSpin.setOnItemSelectedListener(this);

        //type
        typeSpin = (Spinner) v.findViewById(R.id.spinner_type);
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(context,
                R.array.typeDrop, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        typeSpin.setAdapter(typeAdapter);
        typeSpin.setOnItemSelectedListener(this);

        //bloom
        bloomSpin = (Spinner) v.findViewById(R.id.bloomSpin);
        ArrayAdapter<CharSequence> bloomAdapter = ArrayAdapter.createFromResource(context,
                R.array.bloomDrop, android.R.layout.simple_spinner_dropdown_item);
        bloomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloomSpin.setAdapter(bloomAdapter);
        bloomSpin.setOnItemSelectedListener(this);

        //drought
        droughtSpin = (Spinner) v.findViewById(R.id.droughtSpin);
        ArrayAdapter<CharSequence> droughtAdapter = ArrayAdapter.createFromResource(context,
                R.array.droughtDrop, android.R.layout.simple_spinner_dropdown_item);
        droughtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        droughtSpin.setAdapter(droughtAdapter);
        droughtSpin.setOnItemSelectedListener(this);

        //duration
        durationSpin = (Spinner) v.findViewById(R.id.durationSpin);
        ArrayAdapter<CharSequence> durationAdapter = ArrayAdapter.createFromResource(context,
                R.array.durationDrop, android.R.layout.simple_spinner_dropdown_item);
        durationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        durationSpin.setAdapter(durationAdapter);
        durationSpin.setOnItemSelectedListener(this);

        //state
        stateSpin = (Spinner) v.findViewById(R.id.spinner_state);
        ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter.createFromResource(context,
                R.array.StateDrop, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        stateSpin.setAdapter(stateAdapter);
        stateSpin.setOnItemSelectedListener(this);

        //commercial
        commSpin = (Spinner) v.findViewById(R.id.commSpin);
        ArrayAdapter<CharSequence> commAdapter = ArrayAdapter.createFromResource(context,
                R.array.commDrop, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        commAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        commSpin.setAdapter(commAdapter);
        commSpin.setOnItemSelectedListener(this);
        //set Switches

        edible = (Switch) v.findViewById(R.id.edibleSwitch);


        builder.setTitle("Enter Parameters: ")
                .setPositiveButton(R.string.go, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Confirm choices
                        String nameInput = input_name.getText().toString();
                        String stateInput = stateSpin.getSelectedItem().toString();
                        String seasonInput = seasonSpin.getSelectedItem().toString();
                        String bloomInput = bloomSpin.getSelectedItem().toString();
                        String typeInput = typeSpin.getSelectedItem().toString();
                        String droughtInput = droughtSpin.getSelectedItem().toString();
                        String duration = durationSpin.getSelectedItem().toString();
                        String commInput = commSpin.getSelectedItem().toString();

                        Boolean edi = edible.isChecked();
                        String edibleInput;
                        if (edi == true) {
                            edibleInput = "yes";
                        } else {
                            edibleInput = "no";
                        }
                        int intduration;
                        if (duration == null) {
                            duration = "5s";
                        }
                        if (duration.contains("s")) {
                            duration = duration.replace("s", "");
                            intduration = Integer.parseInt(duration);
                        } else {
                            duration = duration.replace("hr", "");
                            intduration = Integer.parseInt(duration);
                            intduration = intduration * 60 * 60;
                        }


                        Retrofit retrofit = RetrofitBuilder.getInstance();
                        SensorApi mySensorAPI = retrofit.create(SensorApi.class);
                        Call<runsData> createRun = mySensorAPI.createRun("Bearer " + JWT, Integer.parseInt(gUserID), intduration, nameInput, "toimplement");

                        final int[] runid = new int[1];
                        final int finalIntduration1 = intduration;
                        createRun.enqueue(new Callback<runsData>() {
                            @Override
                            public void onResponse(Call<runsData> call, Response<runsData> response) {
                                runid[0] = response.body().getRunID();
                                test = 1;

                                listener.applySend("2," + nameInput + "," + finalIntduration1 + "," + runid[0]);
                                Retrofit retrofit = RetrofitBuilder.getInstance();

                                Call<runsData> createFilters = mySensorAPI.createFilters("Bearer " + JWT,runid[0], stateInput, seasonInput, bloomInput, typeInput, droughtInput, commInput, edibleInput);
                                createFilters.enqueue(new Callback<runsData>() {
                                    @Override
                                    public void onResponse(Call<runsData> call, Response<runsData> response2) {

                                    }

                                    @Override
                                    public void onFailure(Call<runsData> call, Throwable t) {

                                    }

                                });


                            }

                            @Override
                            public void onFailure(Call<runsData> call, Throwable t) {

                            }
                        });

                    }
                });

        builder.setView(v);
        return builder.create();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (QuickQuery.OnMyDialogResult) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement");
        }

    }

    public interface OnMyDialogResult {
        void applyTexts(String[] plantNames, int runID, String runName, String humidVal,
                        String moistureVal, String lightVal, String tempVal, String rainVal,
                        String phVal, int Duration);
        void applySend(String test);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        switch (parent.getId()) {
            case R.id.seasonSpinner:
                String seasonInput = seasonSpin.getItemAtPosition(pos).toString();

                break;
            case R.id.spinner_type:
                String typeInput = typeSpin.getItemAtPosition(pos).toString();

                break;
            case R.id.bloomSpin:
                String bloomInput = bloomSpin.getItemAtPosition(pos).toString();

                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        //todo
    }
}




