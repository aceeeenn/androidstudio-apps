package com.example.apps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import org.json.JSONObject;

public class Login extends AppCompatActivity {
    RelativeLayout rellay;
    EditText _emailText, _passwordText;
    Button _loginButton;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay.setVisibility(View.VISIBLE);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _loginButton = (Button) findViewById(R.id.button);
        _emailText = (EditText) findViewById(R.id.email);
        _passwordText = (EditText) findViewById(R.id.password);

        rellay = (RelativeLayout)findViewById(R.id.rellay1);

        handler.postDelayed(runnable, 1000);

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login(){
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (!validate()){
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        String hasemail = "aku@kamu.com";
                        String haspassword = "140456";
                        String email = _emailText.getText().toString();
                        String password = _passwordText.getText().toString();

                        try{
                            if(email.equals(hasemail) && password.equals(haspassword) ){
                                onLooginSuccess();


                                SharedPreferences prefs = PreferenceManager
                                        .getDefaultSharedPreferences(Login.this);
                                SharedPreferences.Editor lds = prefs.edit();
                                lds.putString("email", email);
                                lds.putString("password", password);
                                lds.commit();


                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                            }else{
                                onLoginFailed();
                            }
                        } catch (Exception e) {
                            onLoginFailed();
                            e.printStackTrace();
                        }
                    }
                }, 3000L);
    }

    public boolean validate(){
        boolean valid = true;
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            _emailText.setError("enter a valid email address");
        } else {
            _emailText.setError(null);
        }

        if(password.isEmpty() || password.length() < 4){
            _passwordText.setError("between 4 and 10 alphanumeric characters");
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    public void onLoginFailed(){
        _passwordText.setError("please check your password");
        _emailText.setError("please check your email address");
        _loginButton.setEnabled(true);
    }

    public void onLooginSuccess(){
        _loginButton.setEnabled(true);
        finish();
    }

}