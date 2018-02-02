package com.example.netonboard.netonboardminer.Object;

/**
 * Created by Netonboard on 1/2/2018.
 */

public class Account {
    private int walletId;
    private String walletType;
    private String walletDescription;

    public Account(int walletId, String walletType, String walletDescription) {
        this.walletId = walletId;
        this.walletType = walletType;
        this.walletDescription = walletDescription;
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

    public void setWalleyDescription(String walletDescription) {
        this.walletDescription = walletDescription;
    }
}
