package com.pluralsight.accountingledger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // I crate the scanner class to read the menu
        Scanner scanner = new Scanner(System.in);
        // as long as running is true, the menu keep appearing
        boolean running = true;
        while (running) {

            System.out.println("\n<<<<==========Acounting Leadger==========>>>>");
            System.out.println("===HOME SCREEN===");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.println("Choose an option: ");


            //I am using switch/case and pass the choice to make the menu dispay everytime
            String choice = scanner.nextLine().toUpperCase(); // to read the next line as string
            switch (choice) {
                case "D":
                    System.out.println("Enter description: ");
                    String description = scanner.nextLine();

                    System.out.println("Enter vendor: ");
                    String vendor = scanner.nextLine();

                    System.out.println("Enter amount: ");
                    double amount = Double.parseDouble(scanner.nextLine());

                    Transaction deposit = new Transaction(
                            LocalDate.now().toString(),
                            LocalTime.now().withNano(0).toString(),
                            description,
                            vendor,
                            amount
                    );

                    //save the transaction

                    FileManger.saveTransaction(deposit);

                System.out.println("Deposit saved!");

                    break;

                case "P":

                    System.out.println("Enter description: ");
                    String paymentDescription = scanner.nextLine();

                    System.out.println("Enter vendor: ");
                    String paymentVendor = scanner.nextLine();

                    System.out.println("Enter amount: ");
                    double paymentAmount = Double.parseDouble(scanner.nextLine());

                    paymentAmount = -Math.abs(paymentAmount); // this will make all payments are negative as required


                    Transaction payment = new Transaction(
                            LocalDate.now().toString(),
                            LocalTime.now().withNano(0).toString(),
                            paymentDescription,
                            paymentVendor,
                            paymentAmount
                    );

                    FileManger.saveTransaction(payment);

                    System.out.println("Payment saved!");


                    break;
                case "L":
                    System.out.println("Ledger selected");
                    break;
                case "X":
                    running = false; // when running false , exit the program
                    System.out.println("GodBay!");
                    break;
                default:
                    System.out.println("Invalid option");
            }


        }
        scanner.close(); // close the scanner




    }



    }

