package com.example.netonboard.netonboardminer.Object;

/**
 * Created by Netonboard on 1/2/2018.
 */

public class CrytoData {
    private double earnLast24hr;
    private double earnTot;
    private double totAmountPaid;
    private double balance;
    private double earnTotUSD;
    private String hashRateLast10m;
    private String hashRateLast30m;
    private String hashRateLast1hr;
    private String hashRateLast1d;
    private int totWorker;
    private int activeWorker;
    private int inactiveWorker;
    private String timeLeft;

    public CrytoData(double earnLast24hr, double earnTot, double totAmountPaid, double balance, double earnTotUSD, String hashRateLast10m, String hashRateLast30m, String hashRateLast1hr, String hashRateLast1d, int totWorker, int activeWorker, int inactiveWorker, String timeLeft) {
        this.earnLast24hr = earnLast24hr;
        this.earnTot = earnTot;
        this.totAmountPaid = totAmountPaid;
        this.balance = balance;
        this.earnTotUSD = earnTotUSD;
        this.hashRateLast10m = hashRateLast10m;
        this.hashRateLast30m = hashRateLast30m;
        this.hashRateLast1hr = hashRateLast1hr;
        this.hashRateLast1d = hashRateLast1d;
        this.totWorker = totWorker;
        this.activeWorker = activeWorker;
        this.inactiveWorker = inactiveWorker;
        this.timeLeft = timeLeft;
    }

    public double getEarnLast24hr() {
        return earnLast24hr;
    }

    public void setEarnLast24hr(double earnLast24hr) {
        this.earnLast24hr = earnLast24hr;
    }

    public double getEarnTot() {
        return earnTot;
    }

    public void setEarnTot(double earnTot) {
        this.earnTot = earnTot;
    }

    public double getTotAmountPaid() {
        return totAmountPaid;
    }

    public void setTotAmountPaid(double totAmountPaid) {
        this.totAmountPaid = totAmountPaid;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getEarnTotUSD() {
        return earnTotUSD;
    }

    public void setEarnTotUSD(double earnTotUSD) {
        this.earnTotUSD = earnTotUSD;
    }

    public String getHashRateLast10m() {
        return hashRateLast10m;
    }

    public void setHashRateLast10m(String hashRateLast10m) {
        this.hashRateLast10m = hashRateLast10m;
    }

    public String getHashRateLast30m() {
        return hashRateLast30m;
    }

    public void setHashRateLast30m(String hashRateLast30m) {
        this.hashRateLast30m = hashRateLast30m;
    }

    public String getHashRateLast1hr() {
        return hashRateLast1hr;
    }

    public int getTotWorker() {
        return totWorker;
    }

    public void setTotWorker(int totWorker) {
        this.totWorker = totWorker;
    }

    public int getActiveWorker() {
        return activeWorker;
    }

    public void setActiveWorker(int activeWorker) {
        this.activeWorker = activeWorker;
    }

    public int getInactiveWorker() {
        return inactiveWorker;
    }

    public void setInactiveWorker(int inactiveWorker) {
        this.inactiveWorker = inactiveWorker;
    }

    public void setHashRateLast1hr(String hashRateLast1hr) {
        this.hashRateLast1hr = hashRateLast1hr;
    }

    public String getHashRateLast1d() {
        return hashRateLast1d;
    }

    public void setHashRateLast1d(String hashRateLast1d) {
        this.hashRateLast1d = hashRateLast1d;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }
}
