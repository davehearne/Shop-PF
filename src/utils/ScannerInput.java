package utils;

import java.util.Scanner;

/**
 * This class provides methods for the robust handling of I/O using Scanner.
 * It creates a new Scanner object for each read from the user, thereby
 * eliminating the Scanner bug (where the buffers don't flush correctly after an int read).
 *
 * The methods also parse the numeric data entered to ensure it is correct. If it isn't correct,
 * the user is prompted to enter it again.
 *
 * @author Siobhan Drohan, Mairead Meagher
 * @version 1.0
 *
 */

public class ScannerInput {

    /**
     * Read an int from the user.  If the entered data isn't actually an int,
     * the user is prompted again to enter the int.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The number read from the user and verified as an int.
     */
    public static int readNextInt(String prompt) {
        do {
            var scanner = new Scanner(System.in);
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.next());
            }
            catch (NumberFormatException e) {
                System.err.println("\tEnter a number please.");
            }
        }  while (true);
    }

    /**
     * Read a double from the user.  If the entered data isn't actually a double,
     * the user is prompted again to enter the double.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The number read from the user and verified as a double.
     */
    public static double readNextDouble(String prompt) {
        do {
            var scanner = new Scanner(System.in);
            try{
                System.out.print(prompt);
                return Double.parseDouble(scanner.next());
            }
            catch (NumberFormatException e) {
                System.err.println("\tEnter a number please.");
            }
        }  while (true);
    }

    /**
     * Read a line of text from the user.  There is no validation done on the entered data.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The String read from the user.
     */
    public static String readNextLine(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        return input.nextLine();
    }

    /**
     * Read a single character of text from the user.  There is no validation done on the entered data.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The char read from the user.
     */
    public static char readNextChar(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        return input.next().charAt(0);
    }

}
