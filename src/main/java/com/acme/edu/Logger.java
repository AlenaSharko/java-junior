package com.acme.edu;

/**
 * print different values in console
 *
 * @author Alena Sharko
 */

public class Logger {

    static int sum = 0;
    static boolean chek;
    public static final String PRIMITIVE = "primitive: ";
    public static final String CHAR = "char: ";
    public static final String REFERENCE = "reference: ";
    public static final String STR = "string: ";
    public static final String AD = "@";


    /**
     * print sum integer numbers if its not MAX Value in the console
     * with decoration - "primitive: "
     *
     * @param message integer number
     */
    public static void log(int message) {
        if (message == Integer.MAX_VALUE) {
            if (chek) {
                printStr(PRIMITIVE + sum);
                sum = 0;
            }
            printStr(PRIMITIVE + message);
        } else {
            sum += message;
        }
        chek = true;
    }

    /**
     * print integer or charecter numbers in the console
     * with decoration - "char: "
     *
     * @param message char value
     */
    public static void log(char message) {
        printStr(CHAR + message);
    }

    /**
     * print boolean value in the console
     * with decoration - "primitive: "
     *
     * @param message boolean value
     */
    public static void log(boolean message) {
        printStr(PRIMITIVE + message);
    }

    /**
     * print string value in console
     *
     * @param message String value
     */
    public static void log(String message) {
        if (sum != 0) {
            printStr(PRIMITIVE + sum);
            sum = 0;
        }
        printStr(STR + message);
        chek = false;
    }

    /**
     * print any objects in console
     *
     * @param message any value
     */
    public static void log(Object message) {
        printStr(REFERENCE + message + AD);
    }

    /**
     * print final sum
     */
    public static void close() {
        if (chek) printStr(PRIMITIVE + sum);
    }

    private static void printStr(String s) {
        System.out.println(s);
    }


}
