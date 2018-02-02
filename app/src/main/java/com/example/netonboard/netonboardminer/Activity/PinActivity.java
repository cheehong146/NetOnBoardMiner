package com.example.netonboard.netonboardminer.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.example.netonboard.netonboardminer.R;

import org.w3c.dom.Text;

public class PinActivity extends AppCompatActivity {
    private static final String TAG = "PinActivity";

    private TextView tv_pinInfo;
    private PinLockView pinLockView;
    private PinLockListener pinLockListener;
    private IndicatorDots indicatorDots;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String firstPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        tv_pinInfo = (TextView) findViewById(R.id.tv_pin_header);

        sharedPreferences = this.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        final boolean pinExist = sharedPreferences.getBoolean("pinExist", false);
        final String storedPinCode = sharedPreferences.getString("pinCode", null);


        pinLockView = (PinLockView) findViewById(R.id.pinlock_view);
        indicatorDots = (IndicatorDots) findViewById(R.id.indicator_dots);
        pinLockView.attachIndicatorDots(indicatorDots);

        pinLockListener = new PinLockListener() {
            @Override
            public void onComplete(String pin) {
                Log.i(TAG, pin);

                if (pinExist) {
                    if (pin.equals(storedPinCode)) {
                        startActivity(new Intent(PinActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Log.e(TAG, "Wrong pinCode");
                        Toast toast = Toast.makeText(PinActivity.this, "Wrong Pin", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {
                    Log.i(TAG, "REGISTER PASS");
                    tv_pinInfo.setText("Confirm PIN");
                    if (firstPin == null) {
                        firstPin = pin;
                        pinLockView.resetPinLockView();
                    } else if (firstPin.equals(pin)) {
                        tv_pinInfo.setText("PIN Match");
                        tv_pinInfo.setTextColor(Color.GREEN);
                        editor.putBoolean("pinExist", true);
                        editor.putString("pinCode", pin);
                        if (editor.commit())
                            startActivity(new Intent(PinActivity.this, MainActivity.class));

                    } else {
                        Vibrator vibrate = (Vibrator) PinActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                        vibrate.vibrate(500);
                        tv_pinInfo.setText("Register PIN again");
                        pinLockView.resetPinLockView();
                        firstPin = null;
                    }
                }
            }

            @Override
            public void onEmpty() {
                Log.i(TAG, "EMPTY");
            }

            @Override
            public void onPinChange(int pinLength, String intermediatePin) {
            }
        };
        pinLockView.setPinLockListener(pinLockListener);


    }
}
