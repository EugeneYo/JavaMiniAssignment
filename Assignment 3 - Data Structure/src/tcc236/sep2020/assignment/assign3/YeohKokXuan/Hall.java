package tcc236.sep2020.assignment.assign3.YeohKokXuan;


/************************************************************************************************
 * Course Code   : TCC236/05
 * Course Title  : Data Structures and Algorithms
 * Student ID    : 141190136
 * Author        : Yeoh Kok Xuan
 * Date          : 01/12/2020
 * Honour Code   : I pledge that this is our own program code.
 *                 We received assistance from no one in understanding and debugging our program.
 ************************************************************************************************/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Hall {
    private int size;
    private Seats[] seats;

    public Hall(int size) {
        this.size = size;
        seats = new Seats[size];
        int row = size / 10;
        int j = 0;

        // Creating seats while naming each seat with alphabets and number
        for (int i = 65; i < (65 + row); i++) {
            for (int k = 0; k < 10; k++) {
                char ch = (char) i;
                seats[j] = new Seats(ch, k + 1);
                j++;
            }
        }

    }

    // Linear Search for seats
    private int searchSeats(String str) {
        for (int i = 0; i < size; i++) {
            if (seats[i].getSeats().equals(str)) {
                return i;
            }
        }
        return -1;
    }

    // Buy tickets, using searchSeats to make sure no duplication of seats is purchased
    public boolean buyTicket(String str) {
        int i = searchSeats(str);

        if (i >= 0) {
            if (seats[i].isBuy()) {
                System.out.println("\t\tThe seat have been taken.");
                return false;
            } else {
                seats[i].setBuy(true);
                return true;
            }
        } else {
            System.out.println("\t\tThere is no such seat.");
            return false;
        }

    }

    // Display seats in certain arrangement
    public void displaySeats() {
        String tab = "\t\t\t\t\t\t\t\t";
        String dash = "------------------------------------------------------------------";
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = formatter.format(date);
        formatter = new SimpleDateFormat("hh:mm:ss");
        String strTime = formatter.format(date);


        System.out.format("%-120s%s\n","Hall",strDate);
        System.out.format("%-120s%s","", strTime);
        System.out.println();

        System.out.println( dash + dash);
        System.out.format("%-62s %5s %62s\n","[", "Stage", "]");
        System.out.println( dash + dash + "\n\n");
        int firstIndex = (size / 10);
        int secondIndex = 0;

        if (size > 60) {
            secondIndex = firstIndex / 4 * 3;
        }

        int j = 0;
        for (int i = 0; i < firstIndex; i++) {
            if (i > secondIndex) {
                System.out.format("\n%4s", "");
                for (int k = 0; k < 10; k++) {
                    System.out.format("%9s%4s",seats[j].isTaken(), "");
                    j++;
                }

                continue;
            }
            for (int k = 0; k < 10; k++) {
                System.out.format("%9s%4s",seats[j].isTaken(),"");
                if (k == 1 || k == 7) {
                    System.out.format("%4s","");
                }
                j++;
            }
            System.out.println();
        }
    }
    
    // Get all the data and details from Invoices.txt
    public int getData(Transactions[] transactions, Hall hall) throws IOException {
        Transactions[] data = new Transactions[50];
        File file = new File("Invoices.txt");
        String line = "";
        String str;

        int i = 0;
        int position = 0;
        int position2 = 0;
        if (file.exists()) {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                if (line.contains("#")) {
                    position = line.indexOf("#");
                    position2 = line.lastIndexOf("#");
                    str = line.substring(position + 1, position2);
                    int id = Integer.parseInt(str);
                    transactions[i] = new Transactions(6, id);

                } else if (line.contains("Customer :")) {
                    position = line.indexOf("Customer : ") + 11;
                    position2 = line.length();
                    str = line.substring(position, position2);
                    transactions[i].setCustomerName(str);

                } else if (line.contains("Date : ") && line.contains("Time : ")) {
                    position = line.indexOf("Date : ") + 7;
                    position2 = position + 10;
                    str = line.substring(position, position2);
                    transactions[i].setDate(str);

                    position = line.indexOf("Time : ") + 7;
                    position2 = line.length();
                    str = line.substring(position, position2);
                    transactions[i].setTime(str);

                } else if (line.contains("PRICE") && line.contains("SEAT")) {
                    while (true) {
                        line = br.readLine();
                        if (line.contains("--")) {
                            break;
                        }
                        str = line.trim().substring(0, 3);
                        str = str.trim();
                        transactions[i].addItems(str);
                        hall.buyTicket(str);
                    }

                } else if (line.contains("TOTAL PRICE")) {
                    position = line.indexOf("RM") + 2;
                    position2 = line.length();
                    str = line.substring(position, position2);
                    double num = Double.parseDouble(str);
                    transactions[i].setTotal(num);

                } else if (line.contains("AMOUNT PAID")) {
                    position = line.indexOf("RM") + 2;
                    position2 = line.length();
                    str = line.substring(position, position2);
                    double num = Double.parseDouble(str);
                    transactions[i].setPayment(num);
                    i++;
                }

            } // end while loop
            br.close();
            fr.close();
        }
        return i;
    }
}

