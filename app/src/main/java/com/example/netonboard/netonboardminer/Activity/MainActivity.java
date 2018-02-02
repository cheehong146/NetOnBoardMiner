package com.example.netonboard.netonboardminer.Activity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.netonboard.netonboardminer.Adapter.PagerAdapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.netonboard.netonboardminer.Fragment.DashboardFragment;
import com.example.netonboard.netonboardminer.Fragment.SettingFragment;
import com.example.netonboard.netonboardminer.Object.Account;
import com.example.netonboard.netonboardminer.Object.CrytoData;
import com.example.netonboard.netonboardminer.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new DashboardFragment());
        pagerAdapter.addFragment(new SettingFragment());
        viewPager.setAdapter(pagerAdapter);

        final AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_nav);
        AHBottomNavigationItem dashboardNav = new AHBottomNavigationItem("Dashboard", R.drawable.ic_grid_on_black_24dp, R.color.colorBlack);
        AHBottomNavigationItem settingNav = new AHBottomNavigationItem("Setting", R.drawable.ic_settings_black_24dp, R.color.colorBlack);
        bottomNavigation.addItem(dashboardNav);
        bottomNavigation.addItem(settingNav);
        bottomNavigation.setDefaultBackgroundColor(Color.WHITE);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case 0:
                        viewPager.setCurrentItem(0);
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        break;
                }
                return true;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigation.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
