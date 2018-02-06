package com.example.netonboard.netonboardminer.Object;

/**
 * Created by Netonboard on 1/2/2018.
 */

public class CrytoData {
    private double usdRate;
    private int totMiner;
    private int activeMiner;
    private int inactiveMiner;
    private double balance;
    private double balanceUsd;
    private double totEarn;
    private double totEarnUsd;
    private String liveHashRate;
    private String minerHashRate;

    public CrytoData(double usdRate, int totMiner, int activeMiner, int inactiveMiner, double balance, double balanceUsd, double totEarn, double totEarnUsd, String liveHashRate, String minerHashRate) {
        this.usdRate = usdRate;
        this.totMiner = totMiner;
        this.activeMiner = activeMiner;
        this.inactiveMiner = inactiveMiner;
        this.balance = balance;
        this.balanceUsd = balanceUsd;
        this.totEarn = totEarn;
        this.totEarnUsd = totEarnUsd;
        this.liveHashRate = liveHashRate;
        this.minerHashRate = minerHashRate;
    }

    public double getUsdRate() {
        return usdRate;
    }

    public void setUsdRate(double usdRate) {
        this.usdRate = usdRate;
    }

    public int getTotMiner() {
        return totMiner;
    }

    public void setTotMiner(int totMiner) {
        this.totMiner = totMiner;
    }

    public int getActiveMiner() {
        return activeMiner;
    }

    public void setActiveMiner(int activeMiner) {
        this.activeMiner = activeMiner;
    }

    public int getInactiveMiner() {
        return inactiveMiner;
    }

    public void setInactiveMiner(int inactiveMiner) {
        this.inactiveMiner = inactiveMiner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalanceUsd() {
        return balanceUsd;
    }

    public void setBalanceUsd(double balanceUsd) {
        this.balanceUsd = balanceUsd;
    }

    public double getTotEarn() {
        return totEarn;
    }

    public void setTotEarn(double totEarn) {
        this.totEarn = totEarn;
    }

    public double getTotEarnUsd() {
        return totEarnUsd;
    }

    public void setTotEarnUsd(double totEarnUsd) {
        this.totEarnUsd = totEarnUsd;
    }

    public String getLiveHashRate() {
        return liveHashRate;
    }

    public void setLiveHashRate(String liveHashRate) {
        this.liveHashRate = liveHashRate;
    }

    public String getMinerHashRate() {
        return minerHashRate;
    }

    public void setMinerHashRate(String minerHashRate) {
        this.minerHashRate = minerHashRate;
    }
}
