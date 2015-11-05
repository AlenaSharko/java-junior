package com.acme.edu;

/**
 * log different values
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

    //endregion

    private State state;

    /**
     * Constructor
     */
    public Logger(State state) {
        this.state = state;

    }


    /**
     * log integer values
     * with decoration - "primitive: "
     */
    public void log(int message) {
        state = state.swichStateToStringState();
        state.log(message + "");
    }

    /**
     * log string values
     * with decoration "string: "
     */
    public void log(String message) {
        state = state.swichStateToIntState();
        state.log(message);
    }



    /**
     * log charecter numbers
     * with decoration - "char: "
     */
    public void log(char message) {
        state.log(CHAR + message);

    }

    /**
     * print boolean value
     * with decoration - "primitive: "
     */
    public void log(boolean message) {
        state.log(PRIMITIVE + message);
    }


    /**
     * log any objects
     * with decoration "reference: " and "@"
     */
    public void log(Object message) {
        state.log(REFERENCE + AT + message.toString());
    }

    /**
     * log matrix size 2x2 in the console
     * with decoration "primitives matrix:"
     */
    public void log(int[][] matrix) {
        StringBuilder array = new StringBuilder(" {\n");
        for (int[] xi : matrix) {
            array.append(makeOneString(xi)).append("\n");
        }
        array.append("}");
        state.log(PRIMITIVES_MATRIX + array);
    }

    /**
     * log any string values in the console
     * with decoration "primitives array: "
     */
    public void log(String... strings) {
        StringBuilder array = new StringBuilder();
        for (String string : strings) {
            array.append(string).append("\n");
        }
        state.log(PRIMITIVE_ARRAY + array);
    }

    /**
     * log integer array in the console
     * with decoration "primitives array: "
     */
    public void log(int... nums) {
        StringBuilder array = new StringBuilder("");
        array.append(makeOneString(nums));
        state.log(PRIMITIVE_ARRAY + array);
    }

    /**
     * log multimatrix - array of cubes in the console
     * with decoration "primitives multimatrix: "
     */
    public void log(int[][][][] array) {
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
        state.log(PRIMITIVES_MULTI_MATRIX + multiMatr);
    }

    /**
     * log integer sum of last string value
     */
    public void close() {
        state.flush();
    }

    private static String makeOneString(int[] arr) {
        StringBuilder oneString = new StringBuilder("{");
        for (int i = 0; i < arr.length - 1; i++) {
            oneString.append(arr[i]).append(", ");
        }
        oneString.append(arr[arr.length - 1]).append("}");
        return oneString.toString();
    }

}
