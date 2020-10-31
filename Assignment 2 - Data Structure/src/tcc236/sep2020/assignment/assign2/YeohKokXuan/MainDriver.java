package tcc236.sep2020.assignment.assign2.YeohKokXuan;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/************************************************************************************************
 * Course Code   : TCC236/05
 * Course Title  : Data Structures and Algorithms
 * Student ID    : 141190136, 141190025, 141190043
 * Author        : Yeoh Kok Xuan, Ho Shien Chun, Teoh Boon Teong
 * Date          : 01/11/2020
 * Honour Code   : I pledge that this is our own program code.
 *                 We received assistance from no one in understanding and debugging our program.
 ************************************************************************************************/

public class MainDriver {
    public static void main(String[] args) throws IOException {
        MyFileManager fileManager = new MyFileManager();

        // size : file size
        // filePrefix : the prefix of the file
        // totalTrials : total number of trials for each file
        int[] size = {100, 1000, 5000, 10000, 50000, 100000, 150000, 200000, 250000, 300000};
        String[] filePrefix = {"data/sortAsc", "data/sortDesc", "data/rand"};
        int totalTrials = 5;


        Scanner scan = new Scanner(System.in);
        String choice = "0";

        do {
            System.out.println("Sorting Program");
            System.out.println("1. Statistic Data   : Show average time taken for each file running 5 trials");
            System.out.println("2. Write Files      : Write all the files in a sorted way");
            System.out.println("3. Display File     : Display a single file");
            System.out.println("4. Exit");

            //Taking input from user
            choice = scan.nextLine();
            System.out.println();

            switch (choice) {
                case "1":
                    fileManager.runFile(filePrefix, size, totalTrials);
                    System.out.println("Test completed.\n");
                    break;

                case "2":
                    fileManager.writeFile(filePrefix, size);
                    System.out.println("Data have been sorted and the sorted files are created in the data file.\n");
                    break;
                case "3":
                    try {
                        // Get the path to the data file
                        Path currentPath = Paths.get("");
                        String s = currentPath.toAbsolutePath().toString();
                        s += "/data/";

                        // List out all the files in the data file
                        File file = new File(s);
                        String [] allFiles = file.list();
                        for(String files : allFiles){
                            System.out.println(files);
                        }

                        // Asking input from user
                        System.out.println("Please enter the file as shown above that you wish to display. (eg. sortAsc100.csv)");
                        String fileName = scan.nextLine();
                        System.out.println();

                        s += fileName;
                        fileManager.displayFile(s);

                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case "4":
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Please select again \n ");
                    main(args);
                    break;
            }

        } while (true);
    }
    }

