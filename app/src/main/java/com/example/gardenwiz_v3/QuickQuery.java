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
import API.plantApi;
import API.plantData;
import API.resultsData;
import API.runsData;
import API.sensorData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuickQuery extends DialogFragment {
    private EditText name_input;
    private OnMyDialogResult listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View v = (inflater.inflate(R.layout.quickdialog, null));
        name_input = v.findViewById(R.id.name_input);
        builder.setTitle("Enter Parameters: ")
                .setPositiveButton(R.string.go, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Confirm choices
                        String nameInput = name_input.getText().toString();
                        resultsData getPiData = new resultsData();
                        //Getting the runID
                        Retrofit retrofit = RetrofitBuilder.getInstance();
                        SensorApi mySensorAPI = retrofit.create(SensorApi.class);
                        Call<runsData> IDcall = mySensorAPI.getRunID(1);
                        IDcall.enqueue(new Callback<runsData>() {
                            @Override
                            public void onResponse(Call<runsData> IDcall, Response<runsData> IDresponse) {

                                //retrofit instance for updating a run
                                Retrofit retrofit = RetrofitBuilder.getInstance();
                                SensorApi mySensorAPI = retrofit.create(SensorApi.class);
                                Call<runsData> call = mySensorAPI.updateRun(nameInput, IDresponse.body().getRunID());
                                call.enqueue(new Callback<runsData>() {
                                    @Override
                                    public void onResponse(Call<runsData> call, Response<runsData> response) {
                                        System.out.println("RunID " + IDresponse.body().getRunID());

                                        if (IDresponse.body().getRunID() == 0) {
                                            //Error message saying that there are no sensors datas to test and to go run the pi.
                                        } else {
                                            //Retrofit instance for getting sensor data


                                            Retrofit retrofit = RetrofitBuilder.getInstance();
                                            SensorApi mySensorAPI = retrofit.create(SensorApi.class);
                                            Call<sensorData> call2 = mySensorAPI.getSensorData(IDresponse.body().getRunID());

                                            call2.enqueue(new Callback<sensorData>() {
                                                @Override
                                                public void onResponse(Call<sensorData> call2, Response<sensorData> response2) {
                                                    if (response2.code() != 200) {
                                                        System.out.println("check con");
                                                        //txt.setText("check connection");
                                                        return;
                                                    }
                                                    sensorData sensors = response2.body();
                                                    String moisture = String.valueOf(sensors.getMoisture());
                                                    String light = String.valueOf(sensors.getLight());
                                                    String ph = String.valueOf(sensors.getPh());
                                                    String humid = String.valueOf(sensors.getHumidity());
                                                    String temperature = String.valueOf(sensors.getTemp());
                                                    String rain = String.valueOf(sensors.getRain());

                                                    //Retrofit call to get plants that match the sensor data
                                                    Retrofit retrofit = RetrofitBuilder.getInstance();
                                                    plantApi plantSearch = retrofit.create(plantApi.class);
                                                    Call<List<plantData>> plantCall = plantSearch.getData(sensors.getTemp(), moisture, light, ph, "", "CA");

                                                    plantCall.enqueue(new Callback<List<plantData>>() {
                                                        @Override
                                                        public void onResponse(Call<List<plantData>> call, Response<List<plantData>> response3) {
                                                            //txt.setText(response.body().get(1).getCommonName());
                                                            String[] plantNames = new String[response3.body().size()];
                                                            String[] betyID = new String[response3.body().size()];
                                                            for (int i = 0; i < response3.body().size(); i++) {

                                                                plantNames[i] = response3.body().get(i).getCommonName();
                                                                betyID[i] = String.valueOf(response3.body().get(i).getBetydbspeciesid());
                                                                //System.out.println(response3.body().get(i).getCommonName());
                                                            }
                                                            //call method to display a array of Strings (this is the Results)

                                                            listener.applyTexts(plantNames, IDresponse.body().getRunID(), nameInput, humid, moisture, light, temperature, rain, ph, 5);

                                                            //retrofit call to set the Results in the database to be called later
                                                            for (int i = 0; i < response3.body().size(); i++) {
                                                                Retrofit retrofit = RetrofitBuilder.getInstance();
                                                                SensorApi restultCreate = retrofit.create(SensorApi.class);
                                                                Call<resultsData> resultCall = restultCreate.createResult(IDresponse.body().getRunID(), betyID[i]);
                                                                resultCall.enqueue(new Callback<resultsData>() {
                                                                    @Override
                                                                    public void onResponse(Call<resultsData> call, Response<resultsData> response) {
                                                                        System.out.println("Results");
                                                                    }

                                                                    @Override
                                                                    public void onFailure(Call<resultsData> call, Throwable t) {
                                                                    }
                                                                });
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<List<plantData>> call, Throwable t) {
                                                        }
                                                    });
                                                }

                                                @Override
                                                public void onFailure(Call<sensorData> call, Throwable t) {
                                                }

                                            });
                                        }
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
    }
}
