package com.acme.edu;

import java.util.Objects;

public class Logger {
    /**
     * print integer numbers in the console 
     * with decoration - "primitive: "
     * @author Alena Sharko
     * @param message integer number
     */
    public static void log(int message) {
        printStr("primitive: " + message);
    }

    /**
     * print integer or charecter numbers in the console 
     * with decoration - "char: "
     * @author Alena Sharko
     * @param message
     */
    public static void log(char message) {
        printStr("char: " + message);
    }

    /**
     * print boolean value in the console 
     * with decoration - "primitive: "
     * @author Alena Sharko
     * @param message
     */
    public static void log(boolean message) {
        printStr("primitive: " + message);
    }

    /**
     * print string value in console
     * @author Alena Sharko
     * @param message
     */
    public static void log(String message) {
        printStr("string: " + message);
    }


    private static void printStr(String s) {
        System.out.println(s);
    }
}
