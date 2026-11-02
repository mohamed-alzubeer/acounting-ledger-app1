package com.pluralsight.accountingledger;

public class Main {
    public static void main(String[] args) {
        System.out.println("Accounting Ledger Application");

        //create a transaction object
        Transaction t = new Transaction("2023 ", "10:13:25 ", "keyboard ", "Amazon ", -89.50);
        System.out.println("Transaction created successfully!");
        System.out.println(t);
    }
}
