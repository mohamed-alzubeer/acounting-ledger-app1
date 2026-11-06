package com.pluralsight.accountingledger;

public class Transaction {

    private String date;
    private String time;
    private String description;
    private String vendor;
    private Double amount;

    //crate a constructor had the same name of class

    public Transaction(String date, String time, String description, String vendor, Double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;




    }

    // I am using getters/stters because I can not access directly because they are in private. and getters allows other classes to read private data


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format(
                "%-12s %-10s %-20s %-15s $%,12.2f",
                date,
                time,
                description,
                vendor,
                amount
        );
    }

    public void saveTransaction(Transaction transaction) {
    }
}



