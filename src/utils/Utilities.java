package utils;

public class Utilities {
    /**
     * return number to 2 decimal places
     * @param number
     * @return
     */
    public static double toTwoDecimalPlaces(double number){
        return (int) (number * 100 ) / 100.0;
    }

    /**
     * toBoolean
     * @param charToConvert
     * @return
     */
    public static boolean YNtoBoolean(char charToConvert){
        return ((charToConvert == 'y') || (charToConvert == 'Y'));
    }

    /**
     * This method returns Y if the booleanToConvert value is true. Returns N otherwise.
     *
     * @param booleanToConvert The boolean value that will be used to determine Y/N
     * @return Returns Y if the booleanToConvert value is true. Returns N otherwise.
     */
    public static char booleanToYN(boolean booleanToConvert){
        return booleanToConvert ? 'Y' : 'N';
    }

    /**
     * Truncate String
     * @param stringToTruncate
     * @param length
     * @return
     */
    public static String truncateString(String stringToTruncate, int length){
        if (stringToTruncate.length() <= length) {
            return stringToTruncate;
        }
        else{
            return stringToTruncate.substring(0, length);
        }
    }

    /**
     * Valid Range - true if between min and max
     * @param numberToCheck The number being checked
     * @param min minimum set value
     * @param max max set
     * @return boolean
     */
    public static boolean validRange(int numberToCheck, int min, int max) {
        return ((numberToCheck >= min) && (numberToCheck <= max));
    }

    /**
     * Validate String Length
     * @param strToCheck
     * @param maxLength
     * @return
     */
    public static boolean validateStringLength(String strToCheck, int maxLength){
        return strToCheck.length() <= maxLength;
    }
}
