package com.example.gardenwiz_v3;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
//for progress bar
import android.app.ProgressDialog;
import com.google.android.material.tabs.TabLayout;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import API.RetrofitBuilder;
import API.SensorApi;
import API.plantApi;
import API.plantData;
import API.resultsData;
import API.runsData;
import bluetooth.BluetoothChatService;
import bluetooth.Constants;
import bluetooth.Devices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


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
    //private TextView rainValT;
    private Button viewRes;
    private Button refresh;
    private ProgressDialog progressBar;

    private String mConnectedDeviceName = null;
    private StringBuffer mOutStringBuffer;
    private StringBuffer mInStringBuffer;

    private BluetoothAdapter mBluetoothAdapter = null;
    private BluetoothChatService mChatService = null;

    String address = null;
    String deviceName = null;
    //ImageButton bluetoothButton = (ImageButton) findViewById(R.id.BTButton);

    private int piStatus = 0;
    private ListView listView;
    private int RunID = 0;
    private boolean connected = false;
    private  String JWT = null;
    private  String gUserID = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pigo);
        Bundle bundle = getIntent().getExtras();
        JWT = bundle.getString("JWT");
        gUserID = bundle.getString("userID");
        //Progress Bar
        ProgressDialog progressBar = new ProgressDialog(this);

        //for listing plants
        listView = findViewById(R.id.plantslistView);
        //progress bar init
        progressBar.setProgress(0);
        // TextView vars w/ locations
        runNameT = (TextView) findViewById(R.id.name_of_run);
        durationValT = (TextView) findViewById(R.id.durationList);
        humidValT = (TextView) findViewById(R.id.humidity);
        moistureValT = (TextView) findViewById(R.id.moisture);
        lightValT = (TextView) findViewById(R.id.lumen);
        tempValT = (TextView) findViewById(R.id.temperature);
        phValT = (TextView) findViewById(R.id.ph);
        //rainValT = (TextView) findViewById(R.id.rain);
        ImageButton bluetoothButton = (ImageButton) findViewById(R.id.BTButton);
        //Getting the Bluetooth Device name and Mac Address.
        Intent newint = getIntent();
        deviceName = newint.getStringExtra(Devices.EXTRA_NAME);
        address = newint.getStringExtra(Devices.EXTRA_ADDRESS);

//
//
        if (address != null) {
//        System.out.println(address);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            int port_number = 1;
            // if the default port is not used, get the port
            if (!sharedPreferences.getBoolean("default_port", true)) {
                String port_value = sharedPreferences.getString("port", "0");
                port_number = Integer.parseInt(port_value);
            }

            // Get local Bluetooth adapter
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

            // If the adapter is null, then Bluetooth is not supported
            if (mBluetoothAdapter == null) {
                Toast.makeText(getApplicationContext(), "Bluetooth is not available", Toast.LENGTH_LONG).show();
                this.finish();
            }

            // Initialize the BluetoothChatService to perform bluetooth connections
            mChatService = new BluetoothChatService(this, mHandler);

            // Initialize the buffer for outgoing messages
            mOutStringBuffer = new StringBuffer("");
            // Initialize the buffer for incoming messages
            mInStringBuffer = new StringBuffer("");

            // Get the BluetoothDevice object
            BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
            // Attempt to connect to the device
            mChatService.connect(device, port_number, true);


        }


        // buttons
        Button quickRun = findViewById(R.id.quickRun);
        Button advRun = findViewById(R.id.advRun);
        Button viewRes = findViewById(R.id.allRes);
        viewRes.setEnabled(false);
        quickRun.setEnabled(false);
        advRun.setEnabled(false);
        quickRun.setOnClickListener(v -> showQuickDialog());
        advRun.setOnClickListener(v -> showAdvDialog());
        viewRes.setOnClickListener(v -> getResPage());

        bluetoothButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mChatService != null && mChatService.getState() == 3){
                    disconnect();
                }else {
                    startBluetooth();
                }
            }
        });





    }
    //helper methods

    //Dialogs
    //Quick Run Dialog
    public void showQuickDialog() {
        // Create an instance of the dialog fragment and show it

        DialogFragment dialog = new QuickQuery();
        Bundle bundle = new Bundle();
        bundle.putString("JWT", JWT);
        bundle.putString("userID", gUserID);
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "quickDialog");

    }

    //Adv Run Dialog
    public void showAdvDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new AdvQuery(this);
        Bundle bundle = new Bundle();
        bundle.putString("JWT", JWT);
        bundle.putString("userID", gUserID);
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "AdvQuery");
    }

    public void startBluetooth() {
        Bundle bundle = new Bundle();
        bundle.putString("JWT", JWT);
        bundle.putString("userID", gUserID);
        Intent intent = new Intent(this, Devices.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //Res page
    public void getResPage() {
        if (RunID != 0) {
            Bundle bundle = new Bundle();
            bundle.putString("JWT", JWT);
            bundle.putString("userID", gUserID);
            Intent intent = new Intent(context, Results.class);
            intent.putExtras(bundle);
            Results.runID = String.valueOf(RunID);
            context.startActivity(intent);

        }
    }


    @Override
    public void applyTexts(String[] plantNames, int run_id, String runName, String humidVal,
                           String moistureVal, String lightVal, String tempVal, String rainVal,
                           String phVal, int duration) {

        String[] nullArr = new String[]{"None", "None"};
        RunID = run_id;
        runNameT.setText("Run ID: " + runName);
        humidValT.setText("Humidity: " + humidVal + "%");
        moistureValT.setText("Moisture: " + moistureVal + "%");
        lightValT.setText("Light: " + lightVal + "%");
        tempValT.setText("Temperature: " + tempVal + "\u00B0F");
        //rainValT.setText("Rainfall: " + rainVal);
        phValT.setText("PH: " + phVal);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, plantNames);
        listView.setAdapter(adapter);

    }

    @Override
    public void applySend(String test) {
        send(test);
    }

    public void send(String message) {
        // Check that we're actually connected before trying anything
        if (mChatService.getState() != BluetoothChatService.STATE_CONNECTED) {
            Toast.makeText(this, "cant send message - not connected", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check that there's actually something to send
        if (message.length() > 0) {
            // Get the message bytes and tell the BluetoothChatService to write
            byte[] send = message.getBytes();
            mChatService.write(send);

            // Reset out string buffer to zero and clear the edit text field
            mOutStringBuffer.setLength(0);

        }
    }


    private void disconnect() {
        ImageButton bluetoothButton = (ImageButton) findViewById(R.id.BTButton);
        bluetoothButton.setImageResource(R.drawable.bt_off);
        if (mChatService != null) {
            mChatService.stop();
        }
        ;

        finish();
    }

    private void msg(String message) {
        //TextView statusView = (TextView)findViewById(R.id.status);
        //statusView.setText(message);
        //System.out.println(message);
    }

    private void parseData(String data) {
        //msg(data);
        System.out.println("parseData()");
        // add the message to the buffer

        mInStringBuffer.append(data);
//        mInStringBuffer.toString();
        System.out.println(mInStringBuffer.toString());
        // debug - log data and buffer
//        Log.d("data", data);
//        Log.d("mInStringBuffer", mInStringBuffer.toString());
//        msg(data.toString());
        // find any complete messages


        String[] messages = mInStringBuffer.toString().split(", ");
        int noOfMessages = messages.length;
        // does the last message end in a \n, if not its incomplete and should be ignored
        if (!mInStringBuffer.toString().endsWith("\n")) {
            noOfMessages = noOfMessages - 1;
        }

        // clean the data buffer of any processed messages
        if (mInStringBuffer.lastIndexOf("\n") > -1) {
            mInStringBuffer.delete(0, mInStringBuffer.lastIndexOf("\n") + 1);

        }
        System.out.println("ttttt" + messages[0]);
        if (messages[0].equals("1")) {//TODO: Add setProgress()
            System.out.println("Aaaaaaaaaaaa");
            runNameT.setText("Run name: " + messages[7]);
            humidValT.setText("Humidity: " + messages[2]);
            moistureValT.setText("Moisture: " + messages[5]);
            lightValT.setText("Light: " + messages[3]);
            tempValT.setText("Temperature: " + messages[1] + "\u00B0F");
            phValT.setText("PH: " + messages[6]);


//            String duraString = durationValT.getText().toString();
//            duraString.replace("Duration:", "");
//            int duraInteger = Integer.valueOf(duraString);
//            progressBar.setMax(duraInteger);
//            progressBar.incrementProgressBy(30);
//
        } else if (messages[0].equals("2")) {

            getPlants(messages);

        } else if (messages[0].equals("3")) {
            System.out.println("in 3");
            System.out.println(messages[2]);
            if(messages[2].equals("Done\n")){
                System.out.println("done");

                Retrofit retrofit = RetrofitBuilder.getInstance();
                SensorApi mySensorAPI = retrofit.create(SensorApi.class);
                Call<resultsData> resultExists = mySensorAPI.resultsexists("Bearer " + JWT,messages[1]);
                resultExists.enqueue(new Callback<resultsData>() {
                    @Override
                    public void onResponse(Call<resultsData> call, Response<resultsData> response) {
                        if(response.body().getMessage().equals("True")){

//                            System.out.println(response.body().getMessage());
                            System.out.println("already results");
                        }else {

//                            System.out.println(response.body().getMessage());
                            send("4, " + messages[1]);
                        }
                    }

                    @Override
                    public void onFailure(Call<resultsData> call, Throwable t) {

                    }
                });



            }else if(messages[2].equals("Active\n")){
                System.out.println("active");
                //send("1");

            }


        }

        // process messages
        for (int messageNo = 0; messageNo < noOfMessages; messageNo++) {
            //System.out.println(messages[messageNo]);
        }

    }

    private void isRunning() {
    }
    //todo
    //on simple list click go to plant page.


    private void getPlants(String messages[]) {
        int runID = Integer.parseInt(messages[1]);
        double temp = Double.parseDouble(messages[2]);
        String humid = messages[3];
        String light = messages[4];
        String moisture = messages[5];
        String ph = messages[6];
        System.out.println("getplants()");
        //gets the run informations
        RunID = runID;
        Retrofit retrofit = RetrofitBuilder.getInstance();
        SensorApi mySensorAPI = retrofit.create(SensorApi.class);
        Call<runsData> getFilters = mySensorAPI.getrunFilters("Bearer " + JWT, runID);
        getFilters.enqueue(new Callback<runsData>() {
            @Override
            public void onResponse(Call<runsData> call, Response<runsData> response) {
                System.out.println("plants call "+temp +" ,"+moisture+" ,"+light+" ,"+ph+" ,");
                runsData run = response.body();

                plantApi plantSearch = retrofit.create(plantApi.class);
                Call<List<plantData>> plantCall = plantSearch.getadvData("Bearer " + JWT, temp, moisture, light, ph, run.getType(), run.getSeason(), run.getBloom(), run.getState(), run.getComm(), run.getDrought(), run.getEdible());
                plantCall.enqueue(new Callback<List<plantData>>() {
                    @Override
                    public void onResponse(Call<List<plantData>> call, Response<List<plantData>> response) {
                        String[] plantNames = new String[response.body().size()];
                        String[] betyID = new String[response.body().size()];
                        for (int i = 0; i < response.body().size(); i++) {

                            plantNames[i] = response.body().get(i).getCommonName();
                            betyID[i] = String.valueOf(response.body().get(i).getBetydbspeciesid());
                            System.out.println(response.body().get(i).getCommonName());
                        }
//                        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, plantNames);
                        ArrayAdapter adapter = new ArrayAdapter<String>(RunWiz.this,android.R.layout.simple_list_item_1, plantNames);
                        
                        listView.setAdapter(adapter);
                        //call method to display a array of Strings (this is the Results)

                        //retrofit call to set the Results in the database to be called later
                        for (int i = 0; i < response.body().size(); i++) {
                            Retrofit retrofit = RetrofitBuilder.getInstance();
                            SensorApi resultCreate = retrofit.create(SensorApi.class);
                            //System.out.println(response2.body().getRunID());

                            Call<resultsData> resultCall = resultCreate.createResult("Bearer " + JWT, runID, betyID[i]);
                            resultCall.enqueue(new Callback<resultsData>() {
                                @Override
                                public void onResponse(Call<resultsData> call, Response<resultsData> response) {
                                    Button viewRes = findViewById(R.id.allRes);
                                    viewRes.setEnabled(true);
                                    System.out.println("Results");
                                }

                                @Override
                                public void onFailure(Call<resultsData> call, Throwable t) {
                                    System.out.println("failed results");
                                    Button viewRes = findViewById(R.id.allRes);
                                    viewRes.setEnabled(false);
                                }
                            });


                        }
                    }

                    @Override
                    public void onFailure(Call<List<plantData>> call, Throwable t) {
                        System.out.println("failed plants");
                        String[] plantNames = {"None"};

                        ArrayAdapter adapter = new ArrayAdapter<String>(RunWiz.this, android.R.layout.simple_list_item_1,plantNames );
                    }
                });
            }

            @Override
            public void onFailure(Call<runsData> call, Throwable t) {

            }
        });
    }


    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case Constants.MESSAGE_STATE_CHANGE:
                    switch (msg.arg1) {
                        case BluetoothChatService.STATE_CONNECTED:
                            //Log.d("status", "connected");
                            msg("Connected to " + deviceName);
                            ImageButton bluetoothButton = (ImageButton) findViewById(R.id.BTButton);
                            bluetoothButton.setImageResource(R.drawable.bt_on);
                            checkRunningState();
                            Button quickRun = findViewById(R.id.quickRun);
                            Button advRun = findViewById(R.id.advRun);
                            quickRun.setEnabled(true);
                            advRun.setEnabled(true);
                            // send the protocol version to the server
                            send("5," + Constants.PROTOCOL_VERSION + "," + Constants.CLIENT_NAME + "\n");
                            break;
                        case BluetoothChatService.STATE_CONNECTING:
                            //Log.d("status", "connecting");
                            msg("Connecting to " + deviceName);


                            break;
                        case BluetoothChatService.STATE_LISTEN:
                        case BluetoothChatService.STATE_NONE:
                            //Log.d("status", "not connected");
                            msg("Not connected");
                            disconnect();
                            break;
                    }
                    break;
                case Constants.MESSAGE_WRITE:
                    byte[] writeBuf = (byte[]) msg.obj;
                    // construct a string from the buffer
                    String writeMessage = new String(writeBuf);
                    break;
                case Constants.MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    // construct a string from the valid bytes in the buffer
                    String readData = new String(readBuf, 0, msg.arg1);
                    // message received
                    parseData(readData);
                    break;
                case Constants.MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    mConnectedDeviceName = msg.getData().getString(Constants.DEVICE_NAME);
                    if (null != this) {
                        Toast.makeText(getApplicationContext(), "Connected to "
                                + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case Constants.MESSAGE_TOAST:
                    if (null != this) {
                        Toast.makeText(getApplicationContext(), msg.getData().getString(Constants.TOAST),
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
            }

        }
    };

    private void checkRunningState() {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        SensorApi getrunID = retrofit.create(SensorApi.class);
        //System.out.println(response2.body().getRunID());

        Call<runsData> runsCall = getrunID.getRunID("Bearer " + JWT, Integer.parseInt(gUserID));
        runsCall.enqueue(new Callback<runsData>() {
            @Override
            public void onResponse(Call<runsData> call, Response<runsData> response) {
                send("3,"+response.body().getRunID());
            }

            @Override
            public void onFailure(Call<runsData> call, Throwable t) {

            }
        });


    }

    @Override
    public void onBackPressed () {
        disconnect();
    }

}


