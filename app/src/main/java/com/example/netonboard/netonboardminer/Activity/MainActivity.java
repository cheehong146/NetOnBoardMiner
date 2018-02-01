package com.example.netonboard.netonboardminer.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

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

    Spinner spinner_select;
    TextView tv_btc_balance_amt, tv_time_left, tv_miner_amt, tv_miner_active, tv_miner_inactive, tv_btc_earn_tot_amt, tv_btc_estimated_amt, tv_usd_exchange_tot;
    TextView tv_account_balance_amt, tv_account_tot_amt_paid;
    TextView tv_btc_interval_10m, tv_btc_interval_30m, tv_btc_interval_1hr, tv_btc_interval_1d;

    ArrayAdapter<String> adapter;
    ArrayList<Account> alAccount;
    boolean loadSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner_select = (Spinner) findViewById(R.id.spinner_select);
        tv_btc_balance_amt = (TextView) findViewById(R.id.tv_btc_balance_amt);
        tv_time_left = (TextView) findViewById(R.id.tv_time_left);
        tv_miner_amt = (TextView) findViewById(R.id.tv_miner_amt);
        tv_miner_active = (TextView) findViewById(R.id.tv_miner_active);
        tv_miner_inactive = (TextView) findViewById(R.id.tv_miner_inactive);
        tv_btc_earn_tot_amt = (TextView) findViewById(R.id.tv_btc_earn_tot_amt);
        tv_btc_estimated_amt = (TextView) findViewById(R.id.tv_btc_estimated_amt);
        tv_usd_exchange_tot = (TextView) findViewById(R.id.tv_usd_exchange_tot);
        tv_account_balance_amt = (TextView) findViewById(R.id.tv_account_balance_amt);
        tv_account_tot_amt_paid = (TextView) findViewById(R.id.tv_account_tot_amt_paid);
        tv_btc_interval_10m = (TextView) findViewById(R.id.tv_btc_interval_10m);
        tv_btc_interval_30m = (TextView) findViewById(R.id.tv_btc_interval_30m);
        tv_btc_interval_1hr = (TextView) findViewById(R.id.tv_btc_interval_1hr);
        tv_btc_interval_1d = (TextView) findViewById(R.id.tv_btc_interval_1d);

        alAccount = new ArrayList<>();
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

    public void loadAccount() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://cloudsub04.trio-mobile.com/curl/mobile/bitcoin/coin_type.php", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String body = new String(responseBody);
                try {
                    JSONArray jsonArray = new JSONArray(body);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        alAccount.add(new Account(obj.getString("s_wallet_type_desc"), obj.getString("s_wallet_type_short_desc")));
                    }

                    if (alAccount.size() >= 0) {
                        String[] list = new String[alAccount.size()];
                        for (int i = 0; i < alAccount.size(); i++) {
                            list[i] = alAccount.get(i).getWalletType();
                        }
                            adapter = new ArrayAdapter<String>(getBaseContext(), R.layout.spinner_item, list);
                        spinner_select.setAdapter(adapter);
                        loadSuccess = true;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void changeAccount(final Account account){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://cloudsub04.trio-mobile.com/curl/mobile/bitcoin/bitcoin_info.php?t=" + account.getWalletDescription(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String body = new String (responseBody);
                try{
                    JSONObject jsonObject = new JSONObject(body);
                    CrytoData obj = new CrytoData(jsonObject.getDouble("earn_24h"), jsonObject.getDouble("earn_total"), jsonObject.getDouble("paid_out")
                    , jsonObject.getDouble("balance"), jsonObject.getDouble("earntotal_usd"), jsonObject.getString("hashrate_last_10m"), jsonObject.getString("hashrate_last_30m")
                    , jsonObject.getString("hashrate_last_1h"), jsonObject.getString("hashrate_last_1d"), jsonObject.getInt("tot_workers"), jsonObject.getInt("active_workers")
                            , jsonObject.getInt("inactive_workers"), jsonObject.getString("time_left"));
                    initUI(obj);
                }catch (JSONException e){
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void initUI(CrytoData obj){
        tv_miner_amt.setText(obj.getTotWorker() + " Miner");
        tv_miner_active.setText("Active: " + obj.getActiveWorker());
        tv_miner_inactive.setText("Inactive: " + obj.getInactiveWorker());
        tv_btc_balance_amt.setText(String.format("%.8f", obj.getBalance()) + " BTC");
        tv_btc_estimated_amt.setText(String.format("%.8f", obj.getEarnLast24hr()) + " BTC");
        tv_btc_earn_tot_amt.setText(String.format("%.8f", obj.getEarnTot()) + " BTC");
        tv_usd_exchange_tot.setText(String.format("%.8f", obj.getEarnTotUSD()) + " USD");
        tv_account_tot_amt_paid.setText(String.format("%.8f", obj.getTotAmountPaid()));
        tv_account_balance_amt.setText(String.format("%.8f", obj.getBalance()) + " BTC");
        tv_btc_interval_10m.setText(obj.getHashRateLast10m());
        tv_btc_interval_30m.setText(obj.getHashRateLast30m());
        tv_btc_interval_1hr.setText(obj.getHashRateLast1hr());
        tv_btc_interval_1d.setText(obj.getHashRateLast1d());
        tv_time_left.setText(obj.getTimeLeft());

    }
}
