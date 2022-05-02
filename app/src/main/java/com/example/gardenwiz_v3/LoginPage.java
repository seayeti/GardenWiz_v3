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

public class LoginPage extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private Button Register;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPassword);
        Info = (TextView) findViewById(R.id.tvInfo);
        Login = (Button) findViewById(R.id.btnLogin);
        Register = (Button) findViewById(R.id.btnReg);
        //Automatic set text to easily bypass login
        Name.setText("a2@gmail.com");
        Password.setText("555");

        Info.setText("No of attempts remaining: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println();
                Retrofit retrofit = RetrofitBuilder.getInstance();
                userApi myuserAPI = retrofit.create(userApi.class);
                Call<login> call = myuserAPI.login(Name.getText().toString(),Password.getText().toString());
                call.enqueue(new Callback<login>() {
                    @Override
                    public void onResponse(Call<login> call, Response<login> response) {
                        if(response.body() != null) {
                            validate(response.body().getMessage(),response.body().getJwt());
                        }else{
                            counter--;

                            Info.setText("No of attempts remaining: " + String.valueOf(counter));

                            if(counter == 0){
                                Login.setEnabled(false);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<login> call, Throwable t) {

                    }
                });


            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, RegistrationPage.class);
                startActivity(intent);
            }
        });

    }

    private void validate(String Loginmessage, String JWT) {
        if(Loginmessage.equals("Successful login.")){
            Bundle bundle = new Bundle();
            System.out.println("Login " + JWT);
            bundle.putString("JWT", JWT);
            Intent intent = new Intent(LoginPage.this, MainActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }else{

        }
    }

}

//Without Retrofit
//package com.example.gardenwiz_v3;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class LoginPage extends AppCompatActivity {
//
//    private EditText Name;
//    private EditText Password;
//    private TextView Info;
//    private Button Login;
//    private Button Register;
//    private int counter = 5;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login_page);
//
//        Name = (EditText) findViewById(R.id.etName);
//        Password = (EditText) findViewById(R.id.etPassword);
//        Info = (TextView) findViewById(R.id.tvInfo);
//        Login = (Button) findViewById(R.id.btnLogin);
//        Register = (Button) findViewById(R.id.btnReg);
//
//
//        Info.setText("No of attempts remaining: 5");
//        Name.setText("Admin");
//        Password.setText("1234");
//        Login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                validate(Name.getText().toString(), Password.getText().toString());
//            }
//        });
//
//        Register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(LoginPage.this, RegistrationPage.class);
//                startActivity(intent);
//            }
//        });
//
//    }
//
//    private void validate(String userName, String userPassword) {
//        if((userName.equals("Admin")) && (userPassword.equals("1234"))){
//            Intent intent = new Intent(LoginPage.this, MainActivity.class);
//            startActivity(intent);
//        }else{
//            counter--;
//
//            Info.setText("No of attempts remaining: " + String.valueOf(counter));
//
//            if(counter == 0){
//                Login.setEnabled(false);
//            }
//        }
//    }
//
//}
