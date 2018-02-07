package com.example.netonboard.netonboardminer.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.netonboard.netonboardminer.Activity.WorkerDetailActivity;
import com.example.netonboard.netonboardminer.Adapter.WorkerAdapter;
import com.example.netonboard.netonboardminer.Interface.RecyclerViewOnClickListener;
import com.example.netonboard.netonboardminer.Object.Account;
import com.example.netonboard.netonboardminer.Object.CrytoData;
import com.example.netonboard.netonboardminer.Object.Worker;
import com.example.netonboard.netonboardminer.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.securepreferences.SecurePreferences;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Netonboard on 2/2/2018.
 */

public class DashboardFragment extends Fragment {
    private static final String TAG = "DashboardFragment";

    private Context context;
    Spinner spinner_select;
    TextView tv_miner_amt, tv_miner_active, tv_miner_inactive, tv_live_hash, tv_miner_hash,tv_tot_earn_amt, tv_balance_amt;
    TextView tv_account_conversion_rate, tv_account_earn_usd, tv_account_balance_amt_usd;
    TextView tv_estimated_earning_day, tv_estimated_earning_week, tv_estimated_earning_month, tv_estimated_earning_year;
    TextView tv_estimated_earning_day_desc, tv_estimated_earning_week_desc, tv_estimated_earning_month_desc, tv_estimated_earning_year_desc;


    ArrayAdapter<String> adapter;
    ArrayList<Account> alAccount;
    RecyclerView rvWorker;

    SharedPreferences sharedPreferences;

    public DashboardFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        sharedPreferences = new SecurePreferences(getContext(), "netbtcbth", "loginInfo.xml");

        spinner_select = (Spinner) view.findViewById(R.id.spinner_select);
        tv_miner_amt = (TextView) view.findViewById(R.id.tv_miner_amt);
        tv_miner_active = (TextView) view.findViewById(R.id.tv_miner_active);
        tv_miner_inactive = (TextView) view.findViewById(R.id.tv_miner_inactive);
        tv_miner_hash = (TextView) view.findViewById(R.id.tv_miner_hash);
        tv_live_hash = (TextView) view.findViewById(R.id.tv_live_hash);
        tv_tot_earn_amt = (TextView) view.findViewById(R.id.tv_tot_earn_amt);
        tv_account_conversion_rate = (TextView) view.findViewById(R.id.tv_account_conversion_rate);
        tv_account_earn_usd = (TextView) view.findViewById(R.id.tv_account_earn_usd);
        tv_balance_amt = (TextView) view.findViewById(R.id.tv_balance_amt);
        tv_account_balance_amt_usd = (TextView) view.findViewById(R.id.tv_account_balance_amt_usd);
        tv_estimated_earning_day_desc = (TextView) view.findViewById(R.id.tv_estimated_earning_day_desc);
        tv_estimated_earning_week_desc = (TextView) view.findViewById(R.id.tv_estimated_earning_week_desc);
        tv_estimated_earning_month_desc = (TextView) view.findViewById(R.id.tv_estimated_earning_month_desc);
        tv_estimated_earning_year_desc = (TextView) view.findViewById(R.id.tv_estimated_earning_year_desc);
        tv_estimated_earning_day = (TextView)view.findViewById(R.id.tv_estimated_earning_day);
        tv_estimated_earning_week = (TextView)view.findViewById(R.id.tv_estimated_earning_week);
        tv_estimated_earning_month = (TextView)view.findViewById(R.id.tv_estimated_earning_month);
        tv_estimated_earning_year = (TextView)view.findViewById(R.id.tv_estimated_earning_year);
        rvWorker = (RecyclerView) view.findViewById(R.id.rv_worker);

        alAccount = new ArrayList<>();
        refreshFragment();

        return view;
    }

    public void loadAccount() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://cloudsub04.trio-mobile.com/curl/mobile/bitcoin/coin_type.php", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String body = new String(responseBody);
                try {
                    if (alAccount.size() >= 0)
                        alAccount.clear();

                    JSONArray jsonArray = new JSONArray(body);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        alAccount.add(new Account(obj.getInt("wallet_type_id"), obj.getString("s_wallet_type_desc"), obj.getString("s_wallet_type_short_desc")));
                    }

                    if (alAccount.size() >= 0) {
                        String[] list = new String[alAccount.size()];
                        for (int i = 0; i < alAccount.size(); i++) {
                            list[i] = alAccount.get(i).getWalletType();
                        }
                        adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, list);
                        spinner_select.setAdapter(adapter);
                    }
                    Log.i(TAG, " data loaded");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void refreshFragment() {
        loadAccount();

        spinner_select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                changeAccount(alAccount.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void changeAccount(final Account account) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();

        int userID = sharedPreferences.getInt("userID", -1);
        String time = dateFormat.format(System.currentTimeMillis());
//        String bitsystem = "netCrypto";
//        String hash = String.valueOf(userID) + time + bitsystem;
//        byte[] hashResult;
//        try {
//            MessageDigest digest = MessageDigest.getInstance("MD5");
//            hashResult = digest.digest(hash.getBytes());
//            requestParams.put("hash_result", hashResult.toString());
//        }catch (NoSuchAlgorithmException e){
//            e.printStackTrace();
//        }

        requestParams.put("uid", sharedPreferences.getInt("userID", -1));
        requestParams.put("t", account.getWalletId());
//        requestParams.put("current_time", time);

        client.get("http://cloudsub01.trio-mobile.com:81/bitcoin/api/mobile/api_home.php?uid=" + userID + "&c=" + account.getWalletDescription() + " &cid=" + account.getWalletId(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String body = new String(responseBody);
                try{
                    JSONObject jsonObject = new JSONObject(body);
                    JSONObject estimatedEarning = jsonObject.getJSONObject("estimate_earning");
                    JSONObject summary = jsonObject.getJSONObject("summary");
                    JSONArray worker = jsonObject.getJSONArray("worker_list");
                    account.setEstimatedEarningDay(estimatedEarning.getString("day"));
                    account.setEstimatedEarningWeek(estimatedEarning.getString("week"));
                    account.setEstimatedEarningMonth(estimatedEarning.getString("month"));
                    account.setEstimatedEarningYear(estimatedEarning.getString("year"));
                    CrytoData crytoData = new CrytoData(summary.getDouble("f_usd_rate"), summary.getInt("f_active_miner"), summary.getInt("f_inactive_miner"), summary.getInt("f_total_miner")
                            , summary.getDouble("f_balance"), summary.getDouble("f_balance_usd"), summary.getDouble("f_total_earn"), summary.getDouble("f_total_earn_usd")
                    , summary.getString("s_live_hashrate"), summary.getString("s_miner_hashrate"));

                    ArrayList<Worker> alWorker = new ArrayList<>();
                    for (int i = 0; i < worker.length(); i++) {
                        JSONObject workerObj = worker.getJSONObject(i);
                        alWorker.add(new Worker(workerObj.getString("s_link_status"), workerObj.getString("f_elapsed"), workerObj.getInt("worker_id"), workerObj.getString("s_worker_id"), workerObj.getString("s_condition")));
                    }


                    updateUI(account, crytoData, alWorker);
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void updateUI(Account account, CrytoData obj, final ArrayList<Worker> alWorker) {

        String currencyType = account.getWalletDescription().toUpperCase();
        tv_miner_amt.setText(obj.getTotMiner() + " Worker");
        tv_miner_active.setText("Active: " + obj.getActiveMiner());
        tv_miner_inactive.setText("Inactive: " + obj.getInactiveMiner());
        tv_balance_amt.setText(String.format("%.8f", obj.getBalance()) + " " + currencyType);
        tv_tot_earn_amt.setText(String.format("%.8f", obj.getTotEarn()) + " " + currencyType);
        tv_live_hash.setText(obj.getLiveHashRate());
        tv_miner_hash.setText(obj.getMinerHashRate());
        tv_account_conversion_rate.setText(String.format("%.4f", obj.getUsdRate()));
        tv_account_earn_usd.setText(String.format("%.8f", obj.getTotEarnUsd()));
        tv_account_balance_amt_usd.setText(String.format("%.8f", obj.getBalanceUsd()));

        tv_estimated_earning_day_desc.setText("Day (" + currencyType + ")");
        tv_estimated_earning_week_desc.setText("Week (" + currencyType + ")");
        tv_estimated_earning_month_desc.setText("Month (" + currencyType + ")");
        tv_estimated_earning_year_desc.setText("Year (" + currencyType + ")");
        tv_estimated_earning_day.setText(account.getEstimatedEarningDay());
        tv_estimated_earning_week.setText(account.getEstimatedEarningWeek());
        tv_estimated_earning_month.setText(account.getEstimatedEarningMonth());
        tv_estimated_earning_year.setText(account.getEstimatedEarningYear());

        WorkerAdapter workerAdapter = new WorkerAdapter(alWorker, new RecyclerViewOnClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                System.out.println(alWorker.get(position).toString());
                Intent intent = new Intent(getContext(), WorkerDetailActivity.class);
                intent.putExtra("workerID", alWorker.get(position).getWorkerID());
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvWorker.setLayoutManager(layoutManager);
        rvWorker.setAdapter(workerAdapter);
        rvWorker.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).build());
    }
}
