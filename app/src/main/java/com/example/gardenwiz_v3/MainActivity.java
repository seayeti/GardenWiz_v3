package com.example.gardenwiz_v3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button runPi, search, history;
    private  String JWT = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        JWT = bundle.getString("JWT");
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
        Bundle bundle = new Bundle();
        bundle.putString("JWT", JWT);
        Intent intent = new Intent(this, RunWiz.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void openSearchPlants(){
        Bundle bundle = new Bundle();
        bundle.putString("JWT", JWT);
        Intent intent = new Intent(this,SearchPlants.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void openHistory(){
        Bundle bundle = new Bundle();
        bundle.putString("JWT", JWT);
        Intent intent = new Intent(this,History.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}