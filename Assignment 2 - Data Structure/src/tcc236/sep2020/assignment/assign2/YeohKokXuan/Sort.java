package tcc236.sep2020.assignment.assign2.YeohKokXuan;

/************************************************************************************************
 * Course Code   : TCC236/05
 * Course Title  : Data Structures and Algorithms
 * Student ID    : 141190136, 141190025, 141190043
 * Author        : Yeoh Kok Xuan, Ho Shien Chun, Teoh Boon Teong
 * Date          : 01/11/2020
 * Honour Code   : I pledge that this is our own program code.
 *                 We received assistance from no one in understanding and debugging our program.
 ************************************************************************************************/

public class Sort implements SortIF{

    // Method of using merge sort
    public void sort (double[] input){
        mergeSort(input, 0, input.length);
    }

    public void mergeSort(double[] input, int start, int end) {
        if (end - start < 2) {
            return;
        }

        int mid = (start + end) / 2 ;   // setting mid for the partition

        mergeSort(input, start, mid);   // left partition
        mergeSort(input, mid, end);     // right partition
        merge(input, start, mid, end);  // merge left partition and right partition together
    }

    public void merge(double[] input, int start, int mid, int end) {
        // Do NOTHING if the LAST element (mid -i) in the left partition is smaller than
        //               the FIRST element (mid) in the right partition
        if (input[mid - 1] <= input[mid]) {
            return;
        }

        int i = start;
        int j = mid;
        int tempIndex = 0;

        // Create a temporary array to store the left and right partition
        double[] temp = new double[end - start];

        // Traversing the left array (i < mid), Traversing the right array (j < end)
        // While Loop break as soon as either one of the two finished traversing
        while (i < mid && j < end) {
            // While traversing, the smaller element will be stored in the temporary array
            // Comparing the left partition element with the right partition element
            temp[tempIndex++] = input[input[i] <= input[j] ? i++ : j++];
            // For case 1   : { 32, 34 }, { 33, 36}
            //                 {32, 33, 34,[]}   since the left partition is completely traversed. The while loop is stopped.
            //                 What about the remaining right partition ? Since position of 36 remained unchanged ( 4th to 4th after compared and assigned)
            //                 All the remaining right partition is always greater than the left sorted element
            //                 Thus, we don't need to do anything in this case
            // For case 2   : { 32, 36 }, { 33, 34}
            //                 { 32, 33, 34,  since the right partition is completely traversed. The while loop is stopped.
            //                  In this case, we will need to do something on the remaining left partition since the position of 36 is required
            //                  to be changed from the 2nd to the 4th.
        }

        //if there is some remaining element is the left partition array,
        /*
         *   input               : the source array
         *   i                   : first index of the left partition element that still haven't handled
         *   input               : destination array
         *   start + tempIndex   : tempIndex is the number of element that have been handled
         *   mid - i             : number of element that didn't copied into the temp array from the left partition.
         *                         If we traversed the left partition completely, then mid - i = 0 , So no need to do the copy
         * */
        // this is for the case where the left partition is not completely traversed
        System.arraycopy(input, i, input, start + tempIndex, mid-i);

        // Final step
        // Copy all the temp array to the input array
        /*
         *   temp        : temp array that stored the handled element
         *   0           : starting position
         *   input       : destination array (input array)
         *   start       : starting point in the input array
         *   tempIndex   : number of element that has been handled
         * */
        System.arraycopy(temp, 0, input, start, tempIndex);
    }
}
