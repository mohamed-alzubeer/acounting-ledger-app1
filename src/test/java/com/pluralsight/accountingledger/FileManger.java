package com.pluralsight.accountingledger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class FileManger {

    public static ArrayList<Transaction> loadTransactions(){

        //create ArrayList for Transaction to stoe all transaction
        ArrayList<Transaction> transactions = new ArrayList<>();
        //using BufferedReader to read the file
        //using try/catch to catch the exceptions
        try {

            BufferedReader reader = new BufferedReader(new FileReader("transaction.csv"));
            //using the string line to read the line , and use loop
            String line;
            //read line by line by using while loop
            while ((line = reader.readLine()) != null) {
                //split the line by using String[] p = line.split("\\|")
                String[] parts = line.split("\\|");

                String date = parts[0];
                String time = parts[1];
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);

                //create transaction object to access to data in class
                Transaction transaction = new Transaction(date, time, description, vendor, amount);
                transactions.add(transaction); // to add transaction to ArrayList
            }

            reader.close();   // to close the file after read

        }catch (Exception e){
            e.printStackTrace();
        }
            return transactions;

    }


    public static void saveTransaction(Transaction transaction) {

        try {

            // I want to write to file by using BufferedWriter
            BufferedWriter writer = new BufferedWriter(new FileWriter("transaction.csv", true));
            writer.write(
                    transaction.getDate() + "|" +
                            transaction.getTime() + "|" +
                            transaction.getDescription() + "|" +
                            transaction.getVendor() + "|" +
                            transaction.getAmount());

            writer.newLine(); // to read the new line
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
