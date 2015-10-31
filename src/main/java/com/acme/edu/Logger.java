package com.acme.edu;

/**
 * print different values in console
 *
 * @author Alena Sharko
 */

public class Logger {
    //region Decoration constasts
    /**
     * Decoration for integer and byte values
     */
    public static final String PRIMITIVE = "primitive: ";
    /**
     * Decoration for character values
     */
    public static final String CHAR = "char: ";
    /**
     * Decoration for objects
     */
    public static final String REFERENCE = "reference: ";
    /**
     * Decoration for string values
     */
    public static final String STR = "string: ";
    /**
     * Decoration - @
     */
    public static final String AD = "@";
    /**
     * Decoration - X
     */
    public static final String X = "(x";
    /**
     * Decoraton for arrays
     */
    public static final String PRIMITIVEAR = "primitives array: ";
    /**
     * Decoration for matrix
     */
    public static final String PRIMITIVEMATR = "primitives matrix:";
    //end region

    private static int sum = 0;
    private static int countString = 1;
    private static int lastChar;
    private static boolean startFlag = false;
    private static boolean chekInt = false;
    private static String prevString;

    /**
     * print previous string values if its not MAX Integer Value in the console
     * with decoration - "primitive: "
     *
     * @param message integer number
     */
    public static void log(int message) {
        if (message == Integer.MAX_VALUE) {
            caseMax(message);
            return;
        }
        if (startFlag && (!chekInt)) {
            printStr(prevString);
        }
        sum += message;
        chekInt = true;
        startFlag = true;
    }

    /**
     * print previous integer or byte values in console
     *
     * @param message String value
     */
    public static void log(String message) {
        if (startFlag && chekInt) {
            printSum(sum);
        }
        if (startFlag && (!chekInt)) {
            if (findStringNum(message) == lastChar) {
                countString++;
            } else {
                printStr(prevString);
            }
        }
        lastChar = findStringNum(message);
        prevString = message;
        chekInt = false;
        startFlag = true;
    }

    /**
     * print previous string values if its not MAX Byte Value in the console
     * with decoration - "primitive: "
     *
     * @param message integer number
     */
    public static void log(byte message) {
        if (message == Byte.MAX_VALUE) {
            caseMax(message);
            return;
        }
        if (startFlag && (!chekInt)) {
            printStr(prevString);
        }
        sum += message;
        chekInt = true;
        startFlag = true;
    }


    /**
     * print charecter numbers in the console
     * with decoration - "char: "
     *
     * @param message char value
     */
    public static void log(char message) {
        print(CHAR + message);
    }

    /**
     * print boolean value in the console
     * with decoration - "primitive: "
     *
     * @param message boolean value
     */
    public static void log(boolean message) {
        print(PRIMITIVE + message);
    }


    /**
     * print any objects in console
     *
     * @param message any value
     */
    public static void log(Object message) {
        print(REFERENCE + AD + message.toString());
    }

    public static void log(int[] arr) {
        printarr(arr);
    }

    public static void log(int[][] arr) {
        printduoarr(arr);
    }


    /**
     * print final integer sum of last string value
     */
    public static void close() {
        startFlag = false;
        if (chekInt) {
            printSum(sum);
            return;
        }
        printStr(prevString);
    }

    /**
     * Dump static flag for another tests
     * Very important
     */
    public static void closeAll() {
        startFlag = false;
    }

    private static void print(String s) {
        System.out.print(s + "\n");
    }

    private static void caseMax(int message) {
        if (chekInt) {
            printSum(sum);
        }
        print(PRIMITIVE + message);
        startFlag = false;
    }

    private static void printStr(String message) {
        if (countString == 1) {
            print(STR + message);
        } else {
            print(STR + message + " " + X + countString + ")");
        }
        countString = 1;
    }

    private static int findStringNum(String message) {
        String[] arr = message.split(" ");
        return Integer.parseInt(arr[arr.length - 1]);
    }

    private static void printSum(int summa) {
        print(PRIMITIVE + summa);
        sum = 0;
    }

    private static void printarr(int[] arr) {
        System.out.print(PRIMITIVEAR + "{");
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        print(arr[arr.length - 1] + "}");
    }

    private static void printduoarr(int[][] arr) {
        System.out.println(PRIMITIVEMATR + " {");
        for (int i = 0; i < arr.length; i++) {
            System.out.print("{");
            for (int j = 0; j < arr[i].length - 1; j++) {
                System.out.print(arr[i][j] + ", ");
            }
            print(arr[i][arr.length - 1] + "}");
        }
        System.out.println("}");
    }


}
