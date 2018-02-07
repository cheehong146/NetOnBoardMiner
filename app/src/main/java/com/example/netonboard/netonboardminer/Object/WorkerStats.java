package com.example.netonboard.netonboardminer.Object;

/**
 * Created by Netonboard on 7/2/2018.
 */

public class WorkerStats {
    private int chainNo;
    private int temperature;
    private int hw;
    private String asic;

    public WorkerStats(int chainNo, int temperature, int hw, String asic) {
        this.chainNo = chainNo;
        this.temperature = temperature;
        this.hw = hw;
        this.asic = asic;
    }

    public int getChainNo() {
        return chainNo;
    }

    public void setChainNo(int chainNo) {
        this.chainNo = chainNo;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHw() {
        return hw;
    }

    public void setHw(int hw) {
        this.hw = hw;
    }

    public String getAsic() {
        return asic;
    }

    public void setAsic(String asic) {
        this.asic = asic;
    }
}
