package com.example.netonboard.netonboardminer.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.netonboard.netonboardminer.Adapter.WorkerPoolTableAdapter;
import com.example.netonboard.netonboardminer.Adapter.WorkerStatsTableAdapter;
import com.example.netonboard.netonboardminer.Object.WorkerPool;
import com.example.netonboard.netonboardminer.Object.WorkerStats;
import com.example.netonboard.netonboardminer.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;

import cz.msebera.android.httpclient.Header;
import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class WorkerDetailActivity extends AppCompatActivity {
    private static final String TAG = "WorkerDetailActivity";

    private SortableTableView tableviewPoolstatus;
    private SortableTableView tableviewAntminer;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<WorkerPool> alWorkerPool;
    private ArrayList<WorkerStats> alWorkerStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_detail);
        Log.d(TAG, "onCreate()");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final int workerID = getIntent().getIntExtra("workerID", -1);
        getSupportActionBar().setTitle("Worker " + workerID);

        tableviewPoolstatus = (SortableTableView) findViewById(R.id.tableView_pool_status);
        tableviewAntminer = (SortableTableView) findViewById(R.id.tableView_antminer);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout_worker);
        alWorkerPool = new ArrayList<>();
        alWorkerStats = new ArrayList<>();

        TableColumnWeightModel poolStatusWeightModel = new TableColumnWeightModel(2);
        poolStatusWeightModel.setColumnWeight(1, 1);
        poolStatusWeightModel.setColumnWeight(2, 1);
        tableviewPoolstatus.setColumnModel(poolStatusWeightModel);
        TableColumnWeightModel antminerWeightModel = new TableColumnWeightModel(4);
        antminerWeightModel.setColumnWeight(1, 1);
        antminerWeightModel.setColumnWeight(2, 1);
        antminerWeightModel.setColumnWeight(3, 1);
        antminerWeightModel.setColumnWeight(4, 1);
        tableviewAntminer.setColumnModel(antminerWeightModel);

        tableviewPoolstatus.setHeaderAdapter(new SimpleTableHeaderAdapter(this, new String[]{"Pool No", "Status"}));
        tableviewAntminer.setHeaderAdapter(new SimpleTableHeaderAdapter(this, new String[]{"Chain", "Temp", "ASIC", "HW"}));
        tableviewPoolstatus.setColumnComparator(0, new WorkerPoolComparator());
        tableviewAntminer.setColumnComparator(0, new ChainComparator());
        tableviewAntminer.setColumnComparator(1, new TemperatureComparator());
        tableviewAntminer.setColumnComparator(2, new AsicComparator());
        tableviewAntminer.setColumnComparator(3, new HwComparator());

        loadWorkerData(workerID, this);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadWorkerData(workerID, WorkerDetailActivity.this);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }


    public void loadWorkerData(final int workerID, final Context context) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://cloudsub01.trio-mobile.com:81/bitcoin/api/mobile/api_worker.php?wid=" + workerID + "&en=xxxx&dt=201802071122", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String body = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(body);
                    JSONArray workerPool = jsonObject.getJSONArray("worker_pool");
                    JSONArray workerStats = jsonObject.getJSONArray("worker_stats");

                    if (alWorkerPool.size() != 0)
                        alWorkerPool.clear();
                    if (alWorkerStats.size() != 0)
                        alWorkerStats.clear();

                    for (int i = 0; i < workerPool.length(); i++) {
                        JSONObject obj = workerPool.getJSONObject(i);
                        alWorkerPool.add(new WorkerPool(obj.getInt("i_no"), obj.getString("s_status")));
                    }

                    for (int i = 0; i < workerStats.length(); i++) {
                        JSONObject obj = workerStats.getJSONObject(i);
                        alWorkerStats.add(new WorkerStats(obj.getInt("i_chain"), obj.getInt("i_temp"), obj.getInt("i_hw"), obj.getString("s_asic")));
                    }

                    tableviewPoolstatus.setDataAdapter(new WorkerPoolTableAdapter(context, alWorkerPool));
                    tableviewAntminer.setDataAdapter(new WorkerStatsTableAdapter(context, alWorkerStats));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return false;
        }
        return super.onOptionsItemSelected(item);
    }

    private static class WorkerPoolComparator implements Comparator<WorkerPool> {
        @Override
        public int compare(WorkerPool workerPool, WorkerPool t1) {
            return String.valueOf(workerPool.getPoolNo()).compareTo(String.valueOf(t1.getPoolNo()));
        }
    }

    private static class ChainComparator implements Comparator<WorkerStats> {
        @Override
        public int compare(WorkerStats workerStats, WorkerStats t1) {
            return String.valueOf(workerStats.getChainNo()).compareTo(String.valueOf(t1.getChainNo()));
        }
    }

    private static class TemperatureComparator implements Comparator<WorkerStats> {
        @Override
        public int compare(WorkerStats workerStats, WorkerStats t1) {
            return String.valueOf(workerStats.getTemperature()).compareTo(String.valueOf(t1.getTemperature()));
        }
    }

    private static class AsicComparator implements Comparator<WorkerStats> {
        @Override
        public int compare(WorkerStats workerStats, WorkerStats t1) {
            return workerStats.getAsic().compareTo(t1.getAsic());
        }
    }

    private static class HwComparator implements Comparator<WorkerStats> {
        @Override
        public int compare(WorkerStats workerStats, WorkerStats t1) {
            return String.valueOf(workerStats.getHw()).compareTo(String.valueOf(t1.getHw()));
        }
    }
}
