package com.example.netonboard.netonboardminer.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.netonboard.netonboardminer.Object.WorkerPool;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;

/**
 * Created by Netonboard on 7/2/2018.
 */

public class WorkerPoolTableAdapter extends TableDataAdapter<WorkerPool> {

    public WorkerPoolTableAdapter(Context context, List<WorkerPool> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        WorkerPool workerPool = getRowData(rowIndex);
        View view = null;
        switch (columnIndex){
            case 0:
                view = renderString(String.valueOf(workerPool.getPoolNo()));
                break;
            case 1:
                view = renderString(workerPool.getStatus());
                break;
        }
        return view;
    }

    public View renderString(String text){
        TextView textView = new TextView(getContext());
        textView.setText(text);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(14);
        return textView;
    }
}
