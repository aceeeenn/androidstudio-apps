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
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.apps.booking.MainBooking;
import com.example.apps.functions.Fungsi;
import com.example.apps.functions.VolleyObjectResult;
import com.example.apps.functions.VolleyObjectService;

import org.json.JSONObject;

import java.util.HashMap;

public class Login extends AppCompatActivity {
    RelativeLayout rellay;
    EditText _emailText, _passwordText;
    Button _loginButton;

    VolleyObjectResult volleyObjectResult, vor = null;
    VolleyObjectService volleyObjectService, vos;
    Fungsi fungsi = new Fungsi();
    String url = "/dologin";
    JSONObject data = null;

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

//        _loginButton.setEnabled(false);

        new android.os.Handler().postDelayed(
            new Runnable() {
                @Override
                public void run() {
//                    String hasemail = "aku@kamu.com";
//                    String haspassword = "140456";
                    final String email = _emailText.getText().toString();
                    final String password = _passwordText.getText().toString();

                    HashMap<String, String> dt = new HashMap<String, String>();
                    dt.put("email", email);
                    dt.put("password", password);

                    final JSONObject data = new JSONObject(dt);
                    vor = new VolleyObjectResult() {

                      @Override
                      public void resSuccess(String requestType, JSONObject response) {
                         try{
                       // String error = response.getString("error");
                            if (email.equals(email) && password.equals(password)) {
                                onLoginSuccess();
                                SharedPreferences prefs = PreferenceManager
                                    .getDefaultSharedPreferences(Login.this);
                                SharedPreferences.Editor lds = prefs.edit();
                                lds.putString("email", email);
                                lds.putString("password", password);
                                lds.commit();
//email.equals(email) && password.equals(password)
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                            }else{
                                onLoginFailed();

                            }
                              //  mail.equals(email) && password.equals(password)
//                                String message = response.getString("message");
//                                Intent intent = new Intent(Login.this, MainActivity.class);
//                                startActivity(intent);
//                                Toast.makeText(Login.this,message, Toast.LENGTH_LONG).show();
                        }catch (Exception e){
                             onLoginFailed();
                             e.printStackTrace();
                       // Toast.makeText(Login.this, "Terjadi kesalahan !!", Toast.LENGTH_LONG).show();
                    }
                    }

                    @Override
                    public void resError(String requestType, VolleyError error) {

                    }
                    };

                    vos = new VolleyObjectService(vor, Login.this);
                    vos.postJsonObject("POSTCALL", fungsi.url() + url, data);
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

    public void onLoginSuccess(){
        _loginButton.setEnabled(true);
        finish();
    }

}
