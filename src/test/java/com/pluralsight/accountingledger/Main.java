package com.pluralsight.accountingledger;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // I crate the scanner class to read the menu
        Scanner sc = new Scanner(System.in);
        // as long as running is true, the menu keep appearing
        boolean running = true;{

            System.out.println("\n<<<<==========Acounting Leadger==========>>>>");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.println("Choose an option: ");

        }


        //create ArrayList Transactions to access to file manger
        ArrayList<Transaction> transactions = FileManger.loadTransactions();
        // I use for loop, means for each transaction in transactions give me transaction at time
        for (Transaction transaction : transactions){
            System.out.println(transaction);
        }
    }
}
