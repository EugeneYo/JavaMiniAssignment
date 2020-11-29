package tcc236.sep2020.assignment.assign3.YeohKokXuan;

/************************************************************************************************
 * Course Code   : TCC236/05
 * Course Title  : Data Structures and Algorithms
 * Student ID    : 141190136, 141190025, 141190043
 * Author        : Yeoh Kok Xuan
 * Date          : 01/12/2020
 * Honour Code   : I pledge that this is our own program code.
 *                 We received assistance from no one in understanding and debugging our program.
 ************************************************************************************************/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Transactions {
    private int ID;
    private String customerName;
    private Items[] items;
    private int itemIndex;
    private double total = 0;
    private double payment = 0;
    private String date;
    private String time;

    String dash = "\t\t\t\t------------------------------------------------------------------";
    String asterisks = "\t\t\t\t******************************************************************";
    String tab = "\t\t\t\t\t\t";

    public Transactions(int number, int id) {
        items = new Items[number];
        this.ID = id;
        itemIndex = 0;
    }

    // Adding item to the transaction
    public void addItems(String str) {
        items[itemIndex] = new Items(str, Seats.getPrice());
        itemIndex++;
    }

    // Print the transaction to show customer the details
    public void printTransaction() {
        System.out.println(asterisks);
        System.out.format( tab + "%28s : %s%d%s\n", "TRANSACTION ID", "#", ID, "#");
        System.out.println(dash);

        System.out.format( tab + "Customer : %s%n", customerName);
        System.out.print( tab + "Date : " + date);
        System.out.format("%18s : %s\n", "Time", time);
        System.out.println( tab + "Tickets bought : " + itemIndex);
        System.out.format("\n" + tab + "%-33s%-5s%8s\n", "SEAT", "  ", "PRICE");
        for (int i = 0; i < itemIndex; i++) {
            System.out.format( tab + "%-33s%-5s%8.2f\n", items[i].getName(), "RM", items[i].getPrice());
            total += items[i].getPrice();
        }

        System.out.println(dash);
        System.out.format( tab + "%-33s%-5s%8.2f\n", "TOTAL PRICE", "RM", total);
        System.out.println(asterisks);

    }

    // Completed transaction will be printed
    public void printInvoice() {
        System.out.format("\n"+ tab + "%22s %s%d%s\n", "TRANSACTION ", "#", ID, " COMPLETED");
        System.out.println(asterisks);
        System.out.format( tab + "%25s : %s%d%s\n", "INVOICE ID", "#", ID, "#");
        System.out.println(dash);

        System.out.format( tab + "Customer : %s%n", customerName);
        System.out.print( tab + "Date : " + date);
        System.out.format("%18s : %s\n", "Time", time);
        System.out.println( tab + "Tickets bought : " + itemIndex);
        System.out.format("\n"+ tab + "%-33s%-5s%8s\n", "SEAT", "  ", "PRICE");
        for (int i = 0; i < itemIndex; i++) {
            System.out.format( tab + "%-33s%-5s%8.2f\n", items[i].getName(), "RM", items[i].getPrice());
        }

        System.out.println(dash);
        System.out.format( tab + "%-33s%-5s%8.2f\n", "TOTAL PRICE", "RM", total);
        System.out.format( tab + "%-33s%-5s%8.2f\n", "AMOUNT PAID", "RM", payment);
        System.out.format( tab + "%-33s%-5s%8.2f\n", "CHANGE", "RM", payment - total);
        System.out.println(asterisks);
        System.out.format( tab + "%35s\n", "**NON-REFUNDABLE**");

    }

    // Store the completed transaction or invoice into Invoices.txt
    public void storeInvoice() throws IOException {
        File file = new File("Invoices.txt");
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);

        br.write(asterisks + "\n");
        br.write(String.format( tab + "%25s : %s%d%s\n", "INVOICE ID", "#", ID, "#"));
        br.write(dash + "\n");

        br.write(String.format( tab + "Customer : %s%n", customerName));
        br.write( tab + "Date : " + date);
        br.write(String.format("%18s : %s\n", "Time", time));
        br.write( tab + "Tickets bought : " + itemIndex + "\n");
        br.write(String.format("\n"+ tab + "%-33s%-5s%8s\n", "SEAT", "  ", "PRICE"));
        for (int i = 0; i < itemIndex; i++) {
            br.write(String.format( tab + "%-33s%-5s%8.2f\n", items[i].getName(), "RM", items[i].getPrice()));
        }

        br.write(dash + "\n");
        br.write(String.format( tab + "%-33s%-5s%8.2f\n", "TOTAL PRICE", "RM", total));
        br.write(String.format( tab + "%-33s%-5s%8.2f\n", "AMOUNT PAID", "RM", payment));
        br.write(String.format( tab + "%-33s%-5s%8.2f\n", "CHANGE", "RM", payment - total));
        br.write(asterisks + "\n");
        br.close();
        fr.close();
    }


    // Setters
    public void setCustomerName(String name) { this.customerName = name; }
    public void setTotal(double total) { this.total = total; }
    public void setPayment(double payment) { this.payment = payment; }
    public void setTime(String time) { this.time = time; }
    public void setDate(String date) { this.date = date; }

    // Getters
    public double getTotal() { return total; }
    public int getID() { return ID; }
    public String getCustomerName() { return customerName; }
    public int getItemIndex() { return itemIndex; }

}