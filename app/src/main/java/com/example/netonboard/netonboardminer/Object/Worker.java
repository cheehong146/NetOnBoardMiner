package com.example.netonboard.netonboardminer.Object;

import java.util.ArrayList;

/**
 * Created by Netonboard on 6/2/2018.
 */

public class Worker {
    private String linkStatus;
    private String timeElapsed;
    private int workerID;
    private String displayWorkerID;
    private String condition;

    public Worker(String linkStatus, String timeElapsed, int workerID, String displayWorkerID, String condition) {
        this.linkStatus = linkStatus;
        this.timeElapsed = timeElapsed;
        this.workerID = workerID;
        this.displayWorkerID = displayWorkerID;
        this.condition = condition;
    }

    public String getLinkStatus() {
        return linkStatus;
    }

    public void setLinkStatus(String linkStatus) {
        this.linkStatus = linkStatus;
    }

    public String getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(String timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public int getWorkerID() {
        return workerID;
    }

    public void setWorkerID(int workerID) {
        this.workerID = workerID;
    }

    public String getDisplayWorkerID() {
        return displayWorkerID;
    }

    public void setDisplayWorkerID(String displayWorkerID) {
        this.displayWorkerID = displayWorkerID;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }


    @Override
    public String toString() {
        return "Worker{" +
                "linkStatus='" + linkStatus + '\'' +
                ", timeElapsed='" + timeElapsed + '\'' +
                ", workerID=" + workerID +
                ", displayWorkerID='" + displayWorkerID + '\'' +
                '}';
    }
}
