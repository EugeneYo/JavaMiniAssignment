package tcc236.sep2020.assignment.assign3.YeohKokXuan;

/************************************************************************************************
 * Course Code   : TCC236/05
 * Course Title  : Data Structures and Algorithms
 * Student ID    : 141190136
 * Author        : Yeoh Kok Xuan
 * Date          : 01/12/2020
 * Honour Code   : I pledge that this is my own program code.
 *                 I received assistance from no one in understanding and debugging my program.
 ************************************************************************************************/

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class MainDriver {

    // Binary Search for specific ID as an input
    public static int searchID(Transactions[] transactions, int index, int id) {
        int start = 0;
        int end = index;

        while (start < end) {
            int midpoint = (start + end) / 2;

            if (transactions[midpoint].getID() == id) {
                transactions[midpoint].printInvoice();
                return midpoint;
            }
            else if (transactions[midpoint].getID() < id) {
                start = midpoint + 1;
            }
            else {
                end = midpoint;
            }
        }

        return -1;
    }

    // Linear Search for customer's name as an input
    // This method will print out the invoices the customer previously made
    public static boolean searchCustomer(Transactions[] transactions, int index, String name) {
        boolean state = false;
        for (int i = 0; i < index; i++) {
            if (transactions[i].getCustomerName().equals(name)) {
                transactions[i].printInvoice();
                state = true;
            }
        }
        return state;
    }


    public static void main(String[] args) throws IOException {

        Hall hall = new Hall(100);
        Transactions[] transaction = new Transactions[50];

        // Retrieve the index from Invoices.txt
        int index = hall.getData(transaction, hall);
        int transactionID = 2123 + index;

        Scanner scan = new Scanner(System.in);
        String choice;
        String tab = "\t\t\t\t";

        do {
            System.out.println(tab + "Welcome to JJY mini Concert");
            System.out.println(tab + "1. Buy Tickets");
            System.out.println(tab + "2. Search invoices");
            System.out.println(tab + "3. Print all invoices");
            System.out.println(tab + "4. Exit");
            System.out.print(tab + "Option :\t");

            //Taking input from user
            choice = scan.nextLine();
            System.out.println();

            switch (choice) {
                case "1": // Purchasing tickets
                    // Initializing a new transaction
                    transaction[index] = new Transactions(5, transactionID);

                    hall.displaySeats();
                    System.out.println("\n\n\t\tWhich seat would you like to buy? Enter -1 to exit");
                    System.out.println("\t\tMaximum 5 tickets per purchase.");
                    int ticket = 1;

                    while (true) {
                        System.out.print("\t\tSeat :\t");
                        String seat = scan.nextLine();
                        // Break if the input is -1 or the ticket purchased has hit the limit of 5
                        if (seat.equals("-1") || ticket == 5) {
                            break;
                        }
                        // To check whether the seat has already been taken
                        boolean statement = hall.buyTicket(seat);
                        if (statement) {
                            transaction[index].addItems(seat);
                            ticket++;
                        }
                    }
                    // If there is no ticket purchase during this phase, then exit to menu
                    if (transaction[index].getItemIndex() == 0) {
                        break;
                    }

                    // Setting customer's name on the transaction
                    System.out.print("\t\tCustomer's name :\t");
                    String name = scan.nextLine();
                    transaction[index].setCustomerName(name);

                    // Setting date and time on the transaction
                    Date date = Calendar.getInstance().getTime();
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                    String strDate = formatter.format(date);
                    transaction[index].setDate(strDate);

                    formatter = new SimpleDateFormat("hh:mm:ss");
                    String strTime = formatter.format(date);
                    transaction[index].setTime(strTime);
                    transaction[index].printTransaction();

                    // Settling the payment
                    System.out.print("\t\t\t\tAmount paid :\t");
                    double payment = scan.nextDouble();
                    double totalPayment = payment;
                    double total = transaction[index].getTotal();
                    // Asking for balance if the previous payment is insufficient
                    while (payment < total) {
                        System.out.println("\t\t\t\tInsufficient payment for complete transaction.");
                        System.out.print("\t\t\t\tPlease pay the balance :\t");
                        total = total - payment;
                        payment = scan.nextDouble();
                        totalPayment = totalPayment + payment;
                    }

                    System.out.println();
                    scan.nextLine();

                    // Settling the transaction, printing invoice and store the invoice into Invoices.txt
                    transaction[index].setPayment(totalPayment);
                    transaction[index].printInvoice();
                    transaction[index].storeInvoice();
                    index++;
                    transactionID++;
                    break;

                case "2": // Search invoices details
                    boolean state = true;
                    int i;
                    do {
                        System.out.println(tab + "1. Search invoice using transaction ID");
                        System.out.println(tab + "2. Search invoice/s using Customer's Name");
                        System.out.println(tab + "3. Exit");
                        System.out.print(tab + "Option :\t");
                        String option = scan.nextLine();
                        switch (option) {
                            case "1":
                                System.out.print(tab + "Enter the transaction ID :\t");
                                String input = scan.nextLine();
                                int id = 0;
                                try {
                                    id = Integer.parseInt(input);
                                } catch (Exception e) {
                                    System.out.println(tab + "Please enter a valid ID" + "\n");
                                    break;
                                }
                                i = searchID(transaction, index, id);
                                if (i == -1) {
                                    System.out.println(tab + "The transaction ID does not exist in the system.\n");
                                }
                                break;

                            case "2":
                                System.out.print(tab + "Enter the Customer's Name :\t");
                                String customerName = scan.nextLine();
                                boolean exist = searchCustomer(transaction, index, customerName);
                                if (!exist) {
                                    System.out.println(tab + "The customer does not exist in the system.\n");
                                }
                                break;

                            case "3":
                                state = false;
                                System.out.println();
                                break;

                            default:
                                System.out.println(tab + "Please enter a valid option \n ");
                                break;
                        }
                    } while (state);
                    break;

                case "3": // Printing out all the invoices made
                    for (i = 0; i < index; i++) {
                        transaction[i].printInvoice();
                    }
                    break;

                case "4": // Exit
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println(tab + "Please enter a valid option \n ");
                    break;
            }

        } while (true);
    }

}
