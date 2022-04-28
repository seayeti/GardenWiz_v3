package com.example.gardenwiz_v3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button runPi, search, history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        runPi = (Button) findViewById(R.id.pi_go);
        search = (Button) findViewById(R.id.search);
        history = (Button) findViewById(R.id.history);

        runPi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openPiGo();
            }
        });
        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openSearchPlants();
            }
        });
        history.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openHistory();
            }
        });
    }

    public void openPiGo(){
        Intent intent = new Intent(this, RunWiz.class);
        startActivity(intent);
    }
    public void openSearchPlants(){
        Intent intent = new Intent(this,SearchPlants.class);
        startActivity(intent);
    }
    public void openHistory(){
        Intent intent = new Intent(this,History.class);
        startActivity(intent);
    }

}