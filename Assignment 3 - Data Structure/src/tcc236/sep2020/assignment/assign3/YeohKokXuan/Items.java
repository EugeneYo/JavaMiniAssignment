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

public class Items {
    private String seat;
    private double price;

    public Items(String name, double price) {
        this.seat = name;
        this.price = price;
    }

    public String getName() { return seat; }
    public double getPrice() { return price; }

}
