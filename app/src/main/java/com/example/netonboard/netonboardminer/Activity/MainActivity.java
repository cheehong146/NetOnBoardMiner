package com.example.netonboard.netonboardminer.Activity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.netonboard.netonboardminer.Adapter.PagerAdapter;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.netonboard.netonboardminer.Fragment.DashboardFragment;
import com.example.netonboard.netonboardminer.Fragment.SettingFragment;
import com.example.netonboard.netonboardminer.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate()");

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        final ViewPager viewPager = findViewById(R.id.main_viewpager);
        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new DashboardFragment());
        pagerAdapter.addFragment(new SettingFragment());
        viewPager.setAdapter(pagerAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshViewPager(pagerAdapter);
            }
        });


        final AHBottomNavigation bottomNavigation = findViewById(R.id.bottom_nav);
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

    public void refreshViewPager(PagerAdapter pagerAdapter) {
        DashboardFragment dashboard = (DashboardFragment)pagerAdapter.getItem(0);
        dashboard.refreshFragment();
        pagerAdapter.notifyDataSetChanged();
        Log.i(TAG, "REFRESHING");
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }
}
