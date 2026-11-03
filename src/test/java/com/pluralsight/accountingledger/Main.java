package com.pluralsight.accountingledger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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

                    //create the ledger ment

                case "L":

                    boolean ledgerRunning = true;

                    while (ledgerRunning) {

                        System.out.println("\n=== LEDGER ===");
                        System.out.println("A) All");
                        System.out.println("D) Deposits");
                        System.out.println("P) Payments");
                        System.out.println("R) Reports");
                        System.out.println("H) Home");
                        System.out.print("Choose an option: ");

                        String ledgerChoice = scanner.nextLine().toUpperCase();

                        switch (ledgerChoice) {

                            case "A":
                                   //loads all transactions from the CSV file
                                ArrayList<Transaction> transactions =
                                        FileManger.loadTransactions();
                                // loop through every transaction and print it and this loop will show my the newest transaction appears first

                                for (int i = transactions.size() - 1; i >= 0; i--) {
                                    System.out.println(transactions.get(i));
                                }

                                break;
                            case "D":

                                ArrayList<Transaction> deposits =
                                        FileManger.loadTransactions();

                                for (int i = deposits.size() - 1; i >= 0; i--) { // this loop will load all transactions

                                    if (deposits.get(i).getAmount() > 0) {
                                        System.out.println(deposits.get(i));
                                    }
                                }

                                break;

                            case "P":

                                ArrayList<Transaction> payments =
                                        FileManger.loadTransactions();

                                for (int i = payments.size() - 1; i >= 0; i--) {

                                    if (payments.get(i).getAmount() < 0) {
                                        System.out.println(payments.get(i));
                                    }
                                }

                                break;

                            case "R":

                                boolean reportsRunning = true; // this make the report running if is true and stop/ exit if false
                                while (reportsRunning) {
                                    System.out.println("\n=== REPORTS ===");
                                    System.out.println("1) Month To Date");
                                    System.out.println("2) Previous Month");
                                    System.out.println("3) Year To Date");
                                    System.out.println("4) Previous Year");
                                    System.out.println("5) Search by Vendor");
                                    System.out.println("0) Back");
                                    System.out.print("Choose an option: ");

                                    //read the next line
                                    String  reportChoice = scanner.nextLine();
                                    switch (reportChoice) {
                                        case "1":
                                            //I created a month to date report that loads all transaction from the CSV file and displays only the transactions that belong to the current month and current year.
                                            //I used LocalDate.parse to convert the transaction date stored as String  into localData object.
                                            // I used transactionData.getmonth to check the same month
                                            ArrayList<Transaction>monthTransactions = FileManger.loadTransactions();
                                            LocalDate today = LocalDate.now();
                                            for(int i = monthTransactions.size() - 1; i >= 0; i--) {
                                                LocalDate transactionDate = LocalDate.parse(monthTransactions.get(i).getDate());
                                                if(transactionDate.getMonth() == today.getMonth() && transactionDate.getYear() == today.getYear()) {
                                                    System.out.println(monthTransactions.get(i));
                                                }
                                            }
                                            break;
                                        case "2":
                                            //load alll transaction from transactions.csv file into an ArrayList so I can loop through them and apply filters
                                            ArrayList<Transaction> previousMonthTransactions =
                                                    FileManger.loadTransactions();
                                           //I used LocalData.now() to get the current date from the system
                                            LocalDate today1 = LocalDate.now();
                                            //I calculate previous Month and I use minusMonth(1) to get the previous month
                                            LocalDate previousMonth = today1.minusMonths(1);
                                           // I use a reverse loop because the project requires displaying the newest transactions first.
                                            for (int i = previousMonthTransactions.size() - 1; i >= 0; i--) {
                                              // I use this to convert string data to LocalDate
                                                LocalDate transactionDate =
                                                        LocalDate.parse(
                                                                previousMonthTransactions.get(i).getDate());
                                               //I checked the previous Month
                                                if (transactionDate.getMonth() == previousMonth.getMonth()
                                                        && transactionDate.getYear() == previousMonth.getYear()) {
                                              //Display only transactions from the previous month.
                                                    System.out.println(previousMonthTransactions.get(i));

                                                }
                                            }
                                            boolean foundPreviousMonth = false;
                                            System.out.println("No transactions found!");

                                            break;

                                            // get yeae to date
                                        case "3":
                                            //in case 3 , I used LocalDate.now() to get the current year and compared it with each transactions year using getYear().
                                            //If both years match, the transaction is included in the Year-To-Date report
                                            ArrayList<Transaction> yearTransactions = FileManger.loadTransactions();
                                            LocalDate currentData = LocalDate.now();
                                            for(int i = yearTransactions.size() - 1; i >= 0; i--) {
                                                LocalDate transactionDate = LocalDate.parse(yearTransactions.get(i).getDate());
                                                if(transactionDate.getYear() == currentData.getYear()) {
                                                    System.out.println(yearTransactions.get(i));
                                                }
                                            }
                                            break;

                                        case "4":
                                            //I use minsYears(1) to calculate the previous year dynamically,then compared each transactions year with that value.
                                            //and this allows the report to automatically work every year without needing code change
                                            // get previous  report

                                            ArrayList<Transaction> previousYearTransactions =  FileManger.loadTransactions();
                                            //get the previous year
                                            int perviousYear = LocalDate.now().minusYears(1).getYear();

                                            for(int i = previousYearTransactions.size() - 1; i >= 0; i--) {
                                                //convert the transactions date String into LocalData
                                                LocalDate transactionDate = LocalDate.parse(previousYearTransactions.get(i).getDate());
                                                //compare years
                                                if(transactionDate.getYear() == perviousYear) {
                                                    System.out.println(previousYearTransactions.get(i));
                                                }
                                            }
                                            boolean found = false;
                                            System.out.println("No transactions found!");
                                            break;
                                            // in case 5 - search by vendor
                                               case "5":
                                                System.out.println("Enter Vendor name: ");
                                                String searchVendor = scanner.nextLine();

                                                ArrayList<Transaction> vendorTransactions = FileManger.loadTransactions();

                                                for(int i = vendorTransactions.size() - 1; i >= 0; i--) {
                                                    if(vendorTransactions.get(i).getVendor().equals(searchVendor)) {
                                                        System.out.println(vendorTransactions.get(i));
                                                    }

                                                }
                                                break;

                                                case "0":
                                                    reportsRunning = false;  // the program exits the reports menu and returns to previous menu
                                        break;

                                    }
                                }
                            case "H":
                                ledgerRunning = false;
                                break;

                            default:
                                System.out.println("Invalid option");
                        }
                    }

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

