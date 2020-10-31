package tcc236.sep2020.assignment.assign2.YeohKokXuan;

import java.io.*;
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

public class MyFileManager {
    SortIF s = new Sort();

    // Read File to collect the data from the file and return the collected data in an array form
    private static double[] readFile(File fileName, int size) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            String strLine;
            double[] collection = new double[size];
            int idx = 0;

            // Collecting the data into the array
            while ((strLine = br.readLine()) != null) {
                collection[idx++] = Double.parseDouble(strLine);
            }
            br.close();
            return collection;
        } catch (Exception e) {
            System.out.println("File does not exist in the same directory as the source code.");
            System.out.println("Please move the data file to the same directory path as the source code before proceed.");
            System.out.println("Thank you");
        }
        return null;
    }

    // Run the all the files withing the given range with a given number of trials
    public void runFile(String[] filePrefix, int[] size, int totalTrials) {

        double[] averageTime = new double[size.length];

        System.out.println("n \t\t: Number of Data need in the file");
        System.out.println("T(ms) \t: Average Time Taken in milliseconds");
        for (int k = 0; k < filePrefix.length; k++) {
            System.out.println("===============================================");
            System.out.println("Running test for case " + filePrefix[k] + "... ");

            // call the sort function for 5 trials and collect the time taken for each trial
            for (int trial = 0; trial < totalTrials; trial++) {
                for (int i = 0; i < size.length; i++) {

                    File fileName = new File(filePrefix[k] + size[i] + ".csv");
                    double[] input = readFile(fileName, size[i]);

                    long startTime = System.currentTimeMillis();
                    assert input != null;
                    s.sort(input); // calling the sort method
                    long endTime = System.currentTimeMillis();

                    long timeTaken = endTime - startTime;  // in milliseconds
                    averageTime[i] += timeTaken; //add the time taken for each trial
                }
            }

            // calculate the average time taken for each n value and start printing the collected data
            System.out.format("%-15s%-15s\n", "n", "T(ms)");
            for (int i = 0; i < size.length; i++) {
                double average = averageTime[i] / (totalTrials * 1.0);
                averageTime[i] = Math.round(average * 1000d) / 1000d;
                System.out.format("%-15s%-15.1f\n", size[i], averageTime[i]);
            }
            System.out.print("\n\n");
        }
    }

    // Create new sorted files. If existed, will overwrite the existing file.
    public void writeFile(String[] filePrefix, int[] size) throws IOException {
        for (int i = 0; i < filePrefix.length; i++) {
            for (int j = 0; j < size.length; j++) {

                File fileName = new File(filePrefix[i] + size[j] + ".csv");
                double[] input = readFile(fileName, size[j]);
                assert input != null;
                s.sort(input);

                String newFileName = filePrefix[i]+"SortedResult"+size[j]+".csv";
                File file = new File(newFileName);
                // Check if the file exist or not
                if(file.exists()){
                    System.out.println("Overwriting an existing file for "+ newFileName);
                }else{
                    System.out.println("Creating new file for "+newFileName);
                }

                // Write all the sorted data into the new file
                FileWriter writeFile = new FileWriter(newFileName);
                for (int k = 0; k < size[j]; k++) {
                    String data = String.valueOf(input[k]);
                    data = data + System.lineSeparator();
                    writeFile.write(data);
                }
                writeFile.close();
            }
        }
    }

    // Display a single file just for fun
    public void displayFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String input = sc.next();
                System.out.println(input);
            }
            System.out.println("\nTHE END OF THE FILE\n");
            sc.close();
        } catch (Exception e) {
            System.out.println("File can not be opened. The data file might not be in the same directory path as the source code or the file name is invalid.\n");
        }
    }


}

