package com.pluralsight.accountingledger;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Accounting Ledger Application");

        //create a transaction object
        Transaction t = new Transaction("2023 ", "10:13:25 ", "keyboard ", "Amazon ", -89.50);
        System.out.println("Transaction created successfully!");
        System.out.println(t);
        System.out.println();

        //create ArrayList Transactions to access to file manger
        ArrayList<Transaction> transactions = FileManger.loadTransactions();
        // I use for loop, means for each transaction in transactions give me transaction at time
        for (Transaction transaction : transactions){
            System.out.println(transaction);
        }
    }
}
