package com.pluralsight.accountingledger;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // I crate the scanner class to read the menu
        Scanner scanner = new Scanner(System.in);
        // as long as running is true, the menu keep appearing
        boolean running = true;{

            System.out.println("\n<<<<==========Acounting Leadger==========>>>>");
            System.out.println("===HOME SCREEN===");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.println("Choose an option: ");

            //I am using switch/case and pass the choice to make the menu dispay everytime
            String choice = scanner.nextLine(); // to read the next line as string
            switch (choice) {
                case "D":
                    System.out.println("Deposit selected");
                    break;
                    case "P":
                        System.out.println("Payment selected");
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
                  scanner.close(); // close the scanner
        }



    }
}
