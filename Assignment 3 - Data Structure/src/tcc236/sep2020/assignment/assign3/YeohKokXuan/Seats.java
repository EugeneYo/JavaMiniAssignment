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

public class Seats {
    private final char character;
    private final int number;
    private boolean buy;
    private final static double price = 192.50;
    private String name;


    public Seats(char character, int number) {
        this.character = character;
        this.number = number;
        buy = false;
    }

    // Check if the seat is taken
    public String isTaken() {
        if (buy) {
            return "[ taken ]";
        } else {
            return "[  " + getSeats() + "  ]";
        }
    }

    // Get the alphabet and number of the seat
    public String getSeats() {
        String str = Character.toString(character);
        str += Integer.toString(number);
        return str;
    }

    public void setBuy(boolean buy) { this.buy = buy; }
    public static double getPrice() { return price; }
    public boolean isBuy() { return buy; }

}
