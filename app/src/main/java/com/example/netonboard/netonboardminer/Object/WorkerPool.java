package com.example.netonboard.netonboardminer.Object;

/**
 * Created by Netonboard on 7/2/2018.
 */

public class WorkerPool {
    private int poolNo;
    private String status;

    public WorkerPool(int poolNo, String status) {
        this.poolNo = poolNo;
        this.status = status;
    }

    public int getPoolNo() {
        return poolNo;
    }

    public void setPoolNo(int poolNo) {
        this.poolNo = poolNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
