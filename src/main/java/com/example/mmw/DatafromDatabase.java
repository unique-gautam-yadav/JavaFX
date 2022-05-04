package com.example.mmw;

public class DatafromDatabase {
    private Integer sr;
    private  String title;
    private  String decs;
    private  String amount;

    public DatafromDatabase(int sr, String title, String decs, String amount) {
        this.sr = sr;
        this.title = title;
        this.decs = decs;
        this.amount = amount;
    }

    public int getSr() {
        return sr;
    }

    public void setSr(int sr) {
        this.sr = sr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDecs() {
        return decs;
    }

    public void setDecs(String decs) {
        this.decs = decs;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
