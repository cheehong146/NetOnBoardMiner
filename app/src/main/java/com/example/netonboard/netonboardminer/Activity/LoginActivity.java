package com.example.netonboard.netonboardminer.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.netonboard.netonboardminer.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.securepreferences.SecurePreferences;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    EditText tf_email, tf_password;
    TextView tv_error;
    Button btn_login;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tf_email = (EditText) findViewById(R.id.input_email);
        tf_password = (EditText) findViewById(R.id.input_password);
        tv_error = (TextView) findViewById(R.id.tv_login_error);
        btn_login = (Button) findViewById(R.id.btn_login);
        sharedPreferences = new SecurePreferences(this, "netbtcbth", "loginInfo.xml");

        if (sharedPreferences.getBoolean("loginBefore", false)) {
            startActivity(new Intent(LoginActivity.this, PinActivity.class));
            finish();
        }

        tv_error.setVisibility(View.INVISIBLE);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputUsername = tf_email.getText().toString();
                String inputPassword = tf_password.getText().toString();

                if (inputUsername.equals("") && inputPassword.equals("")) {
                    tv_error.setVisibility(View.VISIBLE);
                    tv_error.setText("Email and Password cannot be empty");
                } else if (inputUsername.equals("")) {
                    tv_error.setVisibility(View.VISIBLE);
                    tv_error.setText("Email cannot be empty");
                } else if (inputPassword.equals("")) {
                    tv_error.setVisibility(View.VISIBLE);
                    tv_error.setText("Password cannot be empty");
                } else {
                    validateUser();
                }
            }
        });
    }

    private void validateUser() {
        String email = tf_email.getText().toString();
        String password = tf_password.getText().toString();

        boolean isAuthenticated = false;
        final RequestParams requestParams = new RequestParams();
        requestParams.put("s_email", email);
        requestParams.put("s_password", password);

        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://cloudsub04.trio-mobile.com/curl/mobile/bitcoin/login.php"
                , requestParams, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String response = new String(responseBody);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("status").equals("success")) {
                                Log.i(TAG, "User authenticated");
                                tv_error.setVisibility(View.GONE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putInt("userID", jsonObject.getInt("user_id"));
                                editor.putBoolean("loginBefore", true);
                                if (editor.commit())
                                    Log.i(TAG, "committed");
                                else
                                    Log.e(TAG, "not commited");
                                startActivity(new Intent(LoginActivity.this, PinActivity.class));
                                finish();
                            } else {
                                Log.i(TAG, "User failed authentication");
                                tv_error.setVisibility(View.VISIBLE);
                                tv_error.setText("Wrong email or password");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.e(TAG, "Failed to authenticate with server");
                    }
                });
    }
}
