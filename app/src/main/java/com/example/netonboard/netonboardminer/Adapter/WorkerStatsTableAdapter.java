package com.example.netonboard.netonboardminer.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.netonboard.netonboardminer.Object.WorkerStats;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;

/**
 * Created by Netonboard on 7/2/2018.
 */

public class WorkerStatsTableAdapter extends TableDataAdapter<WorkerStats> {
    public WorkerStatsTableAdapter(Context context, List<WorkerStats> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        View view = null;
        WorkerStats workerStats = getRowData(rowIndex);
        switch (columnIndex) {
            case 0:
                view = renderString(String.valueOf(workerStats.getChainNo()));
                break;
            case 1:
                view = renderString(String.valueOf(workerStats.getTemperature()));
                break;
            case 2:
                view = renderString(workerStats.getAsic());
                break;
            case 3:
                view = renderString(String.valueOf(workerStats.getHw()));
                break;

        }
        return view;
    }

    public View renderString(String text){
        TextView textView = new TextView(getContext());
        textView.setText(text);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(14);
        return  textView;
    }
}
