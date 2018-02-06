package com.example.netonboard.netonboardminer.Activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.netonboard.netonboardminer.R;
import com.securepreferences.SecurePreferences;

import org.w3c.dom.Text;

import java.sql.BatchUpdateException;

public class ChangePinActivity extends AppCompatActivity {

    Button btn_pin_change;
    SharedPreferences sharedPreferences;
    TextView tv_pin_error;
    EditText input_old_pin, input_new_pin, input_new_pin_confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Change PIN code");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tv_pin_error = (TextView) findViewById(R.id.tv_change_pin_error);
        tv_pin_error.setTextColor(Color.RED);
        input_old_pin = (EditText) findViewById(R.id.input_old_pin);
        input_new_pin = (EditText) findViewById(R.id.input_new_pin);
        input_new_pin_confirm = (EditText) findViewById(R.id.input_new_pin_confirm);

        sharedPreferences = new SecurePreferences(this, "netbthbtc", "loginInfo.xml");
        String currentPin = sharedPreferences.getString("pinCode", null);

        btn_pin_change = (Button) findViewById(R.id.btn_pin_change);

        btn_pin_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPin = input_old_pin.getText().toString();
                String newPin = input_new_pin.getText().toString();
                String newPinConfirm = input_new_pin_confirm.getText().toString();

                if(oldPin.equals("") || newPin.equals("") || newPinConfirm.equals("") ){
                    tv_pin_error.setText("All 3 Field must be filled in");
                }else{
                    if(oldPin.equals(sharedPreferences.getString("pinCode", null))){
                        if(newPin.equals(newPinConfirm)){
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("pinCode", null);
                            if(editor.commit()) {
                                tv_pin_error.setText("PIN Updated");
                                tv_pin_error.setTextColor(Color.GREEN);
                            }
                        }else{
                            tv_pin_error.setText("new Pin and ");
                        }
                    }else{
                        tv_pin_error.setText("Old PIN doesn't match");
                    }
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
