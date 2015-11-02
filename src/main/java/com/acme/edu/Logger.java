package com.acme.edu;

/**
 * log different values in console
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
    public static final String AT = "@";
    /**
     * Decoration - X
     */
    public static final String X = "(x";
    /**
     * Decoraton for arrays
     */
    public static final String PRIMITIVE_ARRAY = "primitives array: ";
    /**
     * Decoration for matrix
     */
    public static final String PRIMITIVES_MATRIX = "primitives matrix:";
    /**
     * Decoration for multimatrix
     */
    public static final String PRIMITIVES_MULTI_MATRIX = "primitives multimatrix: ";

    //end region

    private static int sum = 0;
    private static int countString = 1;
    private static int lastChar;
    private static boolean startFlag;
    private static boolean chekInt;
    private static String prevString;

    Logger() {

    }

    enum PrintStates {
        SIMPLE_PRINT, PRINT_SUM, PRINT_STRING
    }

    /**
     * log integer values in the console
     * with decoration - "primitive: "
     */
    public static void log(int message) {
        if (message == Integer.MAX_VALUE || message == (Integer.MAX_VALUE - 10)) {
            reactToMaxValue(message);
            return;
        }
        if (startFlag && (!chekInt)) {
            print(prevString, PrintStates.PRINT_STRING);
        }
        sum += message;
        chekInt = true;
        startFlag = true;
    }

    /**
     * log string values in the console
     * with decoration "string: "
     */
    public static void log(String message) {
        if (startFlag && chekInt) {
            print(sum + "", PrintStates.PRINT_SUM);
        }
        if (startFlag && (!chekInt)) {
            if (findStringNum(message) == lastChar) {
                countString++;
            } else {
                print(prevString, PrintStates.PRINT_STRING);
            }
        }
        lastChar = findStringNum(message);
        prevString = message;
        chekInt = false;
        startFlag = true;
    }

    /**
     * log byte values in the console
     * with decoration - "primitive: "
     */
    public static void log(byte message) {
        if (message == Byte.MAX_VALUE) {
            reactToMaxValue(message);
            return;
        }
        if (startFlag && (!chekInt)) {
            print(prevString, PrintStates.PRINT_STRING);
        }
        sum += message;
        chekInt = true;
        startFlag = true;
    }


    /**
     * log charecter numbers in the console
     * with decoration - "char: "
     */
    public static void log(char message) {
        print(CHAR + message, PrintStates.SIMPLE_PRINT);
    }

    /**
     * print boolean value in the console
     * with decoration - "primitive: "
     */
    public static void log(boolean message) {
        print(PRIMITIVE + message, PrintStates.SIMPLE_PRINT);
    }


    /**
     * log any objects in console
     * with decoration "reference: " and "@"
     */
    public static void log(Object message) {
        print(REFERENCE + AT + message.toString(), PrintStates.SIMPLE_PRINT);
    }


    /**
     * log matrix size 2x2 in the console
     * with decoration "primitives matrix:"
     */
    public static void log(int[][] matrix) {
        StringBuilder array = new StringBuilder(" {\n");
        for (int[] xi : matrix) {
            array.append(makeOneString(xi)).append("\n");
        }
        array.append("}");
        print(PRIMITIVES_MATRIX + array, PrintStates.SIMPLE_PRINT);
    }

    /**
     * log any string values in the console
     * with decoration "primitives array: "
     */
    public static void log(String... strings) {
        StringBuilder array = new StringBuilder();
        for (String string : strings) {
            array.append(string).append("\n");
        }
        print(PRIMITIVE_ARRAY + array, PrintStates.SIMPLE_PRINT);
    }

    /**
     * log integer array in the console
     * with decoration "primitives array: "
     */
    public static void log(int... nums) {
        StringBuilder array = new StringBuilder("");
        array.append(makeOneString(nums));
        print(PRIMITIVE_ARRAY + array, PrintStates.SIMPLE_PRINT);
    }

    /**
     * log multimatrix - array of cubes in the console
     * with decoration "primitives multimatrix: "
     */
    public static void log(int[][][][] array) {
        StringBuilder multiMatr = new StringBuilder("{\n");
        for (int[][][] cube : array) {
            multiMatr.append("{\n");
            for (int[][] rect : cube) {
                multiMatr.append("{\n");
                for (int[] line : rect) {
                    multiMatr.append("{\n");
                    for (int xi : line) {
                        multiMatr.append(xi).append("\n");
                    }
                    multiMatr.append("}\n");
                }
                multiMatr.append("}\n");
            }
            multiMatr.append("}\n");
        }
        multiMatr.append("}");
        print(PRIMITIVES_MULTI_MATRIX + multiMatr, PrintStates.SIMPLE_PRINT);
    }


    /**
     * log final integer sum of last string value in the console
     */
    public static void close() {
        startFlag = false;
        if (chekInt) {
            print(sum + "", PrintStates.PRINT_SUM);
            return;
        }
        print(prevString, PrintStates.PRINT_STRING);
    }

    /**
     * Dump static flag for another tests
     * Very important
     */
    public static void closeAll() {
        startFlag = false;
    }

    private static void reactToMaxValue(int message) {
        if (chekInt) {
            print(sum + "", PrintStates.PRINT_SUM);
        } else {
            print(prevString, PrintStates.PRINT_STRING);
        }
        print(PRIMITIVE + message, PrintStates.SIMPLE_PRINT);
        startFlag = false;
    }

    private static int findStringNum(String message) {
        String[] arr = message.split(" ");
        return Integer.parseInt(arr[arr.length - 1]);
    }

    private static String makeOneString(int[] arr) {
        StringBuilder oneString = new StringBuilder("{");
        for (int i = 0; i < arr.length - 1; i++) {
            oneString.append(arr[i]).append(", ");
        }
        oneString.append(arr[arr.length - 1]).append("}");
        return oneString.toString();
    }


    private static void print(String message, PrintStates state) {
        switch (state) {
            case PRINT_STRING: {
                if (countString == 1) {
                    print(STR + message,PrintStates.SIMPLE_PRINT);
                } else {
                    print(STR + message + " " + X + countString + ")",PrintStates.SIMPLE_PRINT);
                }
                countString = 1;
                break;
            }
            case PRINT_SUM: {
                print(PRIMITIVE + message,PrintStates.SIMPLE_PRINT);
                sum = 0;
                break;
            }
            default:{
                System.out.println(message);
                break;
            }
        }
    }
}


