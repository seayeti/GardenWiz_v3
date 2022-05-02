package com.example.gardenwiz_v3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gardenwiz_v3.MainActivity;
import com.example.gardenwiz_v3.R;

import API.RetrofitBuilder;
import API.login;
import API.userApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegistrationPage extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView alertText;
    private Button createAccount;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        Name = (EditText) findViewById(R.id.regName);
        Password = (EditText) findViewById(R.id.regPassword);
        alertText = (TextView) findViewById(R.id.alertbox);
        createAccount = (Button) findViewById(R.id.btnCreate);

        //Automatic set text to easily bypass login
//        Name.setText("a2@gmail.com");
//        Password.setText("555");


        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println();
                Retrofit retrofit = RetrofitBuilder.getInstance();
                userApi myuserAPI = retrofit.create(userApi.class);
                Call<login> emailCheck = myuserAPI.emailCheck(Name.getText().toString());
                emailCheck.enqueue(new Callback<login>() {
                    @Override
                    public void onResponse(Call<login> call, Response<login> response) {
                        if(response.body().getMessage().equals("No Users Found")){
                            Retrofit retrofit = RetrofitBuilder.getInstance();
                            userApi myuserAPI = retrofit.create(userApi.class);
                            Call<login> register = myuserAPI.register(Name.getText().toString(),Password.getText().toString());
                            register.enqueue(new Callback<login>() {
                                @Override
                                public void onResponse(Call<login> call, Response<login> response) {
                                    validate(response.body().getMessage());
                                }

                                @Override
                                public void onFailure(Call<login> call, Throwable t) {

                                }
                            });
                        }else{

                            alertText.setText("Username already exists, choose another");
                        }
                    }

                    @Override
                    public void onFailure(Call<login> call, Throwable t) {

                    }
                });





            }
        });

    }

    private void validate(String Loginmessage) {
        if(Loginmessage.equals("User Created")){
            Intent intent = new Intent(RegistrationPage.this, LoginPage.class);
            startActivity(intent);
        }else{

        }
    }


}
