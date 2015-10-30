package com.acme.edu;

/**
 * print different values in console
 *
 * @author Alena Sharko
 */

public class Logger {
    /**
     * the sum of the integers
     */
    public static int sum = 0;

    /**
     * show string or number
     */
    public static boolean chek;

    /**
     * print sum integer numbers in the console
     * with decoration - "primitive: "
     *
     * @param message integer number
     */
    public static void log(int message) {
        sum += message;
        chek = true;
    }

    /**
     * print integer or charecter numbers in the console
     * with decoration - "char: "
     *
     * @param message char value
     */
    public static void log(char message) {
        printStr("char: " + message);
    }

    /**
     * print boolean value in the console
     * with decoration - "primitive: "
     *
     * @param message boolean value
     */
    public static void log(boolean message) {
        printStr("primitive: " + message);
    }

    /**
     * print string value in console
     *
     * @param message String value
     */
    public static void log(String message) {
        if (sum != 0) {
            printStr("primitive: " + sum);
            sum = 0;
        }
        printStr("string: " + message);
        chek = false;
    }

    /**
     * print any objects in console
     *
     * @param message any value
     */
    public static void log(Object message) {
        printStr("reference: " + message + "@");
    }

    /**
     * print final sum
     */
    public static void close() {
        if (chek) printStr("primitive: " + sum);
    }

    private static void printStr(String s) {
        System.out.println(s);
    }


}
