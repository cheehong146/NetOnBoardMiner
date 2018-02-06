package com.example.netonboard.netonboardminer.Object;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Netonboard on 1/2/2018.
 */

public class Account {
    private int walletId;
    private String walletType;
    private String walletDescription;
    private String estimatedEarningDay;
    private String estimatedEarningWeek;
    private String estimatedEarningMonth;
    private String estimatedEarningYear;

    public Account(int walletId, String walletType, String walletDescription) {
        this.walletId = walletId;
        this.walletType = walletType;
        this.walletDescription = walletDescription;
    }

    public Account(int walletId, String walletType, String walletDescription, String estimatedEarningDay, String estimatedEarningWeek, String estimatedEarningMonth, String estimatedEarningYear) {
        this.walletId = walletId;
        this.walletType = walletType;
        this.walletDescription = walletDescription;
        this.estimatedEarningDay = estimatedEarningDay;
        this.estimatedEarningWeek = estimatedEarningWeek;
        this.estimatedEarningMonth = estimatedEarningMonth;
        this.estimatedEarningYear = estimatedEarningYear;
    }

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public void setWalletDescription(String walletDescription) {
        this.walletDescription = walletDescription;
    }

    public String getWalletType() {
        return walletType;
    }

    public void setWalletType(String walletType) {
        this.walletType = walletType;
    }

    public String getWalletDescription() {
        return walletDescription;
    }


    public String getEstimatedEarningDay() {
        return estimatedEarningDay;
    }

    public void setEstimatedEarningDay(String estimatedEarningDay) {
        this.estimatedEarningDay = estimatedEarningDay;
    }

    public String getEstimatedEarningWeek() {
        return estimatedEarningWeek;
    }

    public void setEstimatedEarningWeek(String estimatedEarningWeek) {
        this.estimatedEarningWeek = estimatedEarningWeek;
    }

    public String getEstimatedEarningMonth() {
        return estimatedEarningMonth;
    }

    public void setEstimatedEarningMonth(String estimatedEarningMonth) {
        this.estimatedEarningMonth = estimatedEarningMonth;
    }

    public String getEstimatedEarningYear() {
        return estimatedEarningYear;
    }

    public void setEstimatedEarningYear(String estimatedEarningYear) {
        this.estimatedEarningYear = estimatedEarningYear;
    }
}
