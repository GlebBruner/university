package Util;

public class FirstLetterCapitalizer {

    public static String capitalizeFirstLetter(String s) {
        return s.charAt(0) + s.substring(1).toLowerCase();
    }

}
