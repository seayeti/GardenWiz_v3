package com.example.gardenwiz_v3;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.List;

import API.RetrofitBuilder;
import API.SensorApi;
import API.resultsData;
import API.runsData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuickQuery extends DialogFragment {
    private EditText name_input;
    private OnMyDialogResult listener;
    private  String JWT = null;
    private  String gUserID = null;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View v = (inflater.inflate(R.layout.quickdialog, null));
        name_input = v.findViewById(R.id.name_input);
        JWT = getArguments().getString("JWT");
        gUserID = getArguments().getString("userID");


        builder.setTitle("Enter Parameters: ")
                .setPositiveButton(R.string.go, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Confirm choices
                        String nameInput = name_input.getText().toString();
                        resultsData getPiData = new resultsData();
                        Retrofit retrofit = RetrofitBuilder.getInstance();
                        SensorApi mySensorAPI = retrofit.create(SensorApi.class);
                        Call<runsData> createRun = mySensorAPI.createRun("Bearer " + JWT, Integer.parseInt(gUserID), 5, nameInput, "toimplement");

                        final int[] runid = new int[1];

                        createRun.enqueue(new Callback<runsData>() {
                            @Override
                            public void onResponse(Call<runsData> call, Response<runsData> response) {
                                runid[0] = response.body().getRunID();


                                listener.applySend("2," + nameInput + "," + 5 + "," + runid[0]);
                                Retrofit retrofit = RetrofitBuilder.getInstance();

                                Call<runsData> createFilters = mySensorAPI.createFilters("Bearer " + JWT, runid[0], "", "", "", "", "", "", "");
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
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });


        builder.setView(v);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (OnMyDialogResult) context;
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
}