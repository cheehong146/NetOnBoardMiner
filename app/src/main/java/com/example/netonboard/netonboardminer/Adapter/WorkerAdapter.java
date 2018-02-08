package com.example.netonboard.netonboardminer.Adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.netonboard.netonboardminer.Interface.RecyclerViewOnClickListener;
import com.example.netonboard.netonboardminer.Object.Worker;
import com.example.netonboard.netonboardminer.R;

import java.util.ArrayList;

/**
 * Created by Netonboard on 6/2/2018.
 */

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.ViewHolder> {
    private ArrayList<Worker> alWorker;
    RecyclerViewOnClickListener listener;

    public WorkerAdapter(ArrayList<Worker> alWorker, RecyclerViewOnClickListener listener) {
        this.alWorker = alWorker;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.worker_single_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, viewHolder.getPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WorkerAdapter.ViewHolder holder, int position) {
        Worker worker = alWorker.get(position);
        //TODO LOGIC HERE

        holder.tv_worker_id.setText(worker.getDisplayWorkerID());
        holder.tv_worker_time_elapsed.setText(worker.getTimeElapsed());
        holder.tv_worker_condition.setText(worker.getCondition());
        holder.tv_pool_status.setText(worker.getLinkStatus());

    }

    @Override
    public int getItemCount() {
        return alWorker.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_worker_id;
        public TextView tv_worker_condition;
        public TextView tv_worker_time_elapsed;
        public TextView tv_pool_status;

        public ViewHolder(final View itemView) {
            super(itemView);
            tv_worker_id = itemView.findViewById(R.id.tv_worker_id);
            tv_worker_condition = itemView.findViewById(R.id.tv_worker_condition);
            tv_worker_time_elapsed = itemView.findViewById(R.id.tv_worker_time_elapsed);
            tv_pool_status = itemView.findViewById(R.id.tv_pool_status);
        }
    }
}
