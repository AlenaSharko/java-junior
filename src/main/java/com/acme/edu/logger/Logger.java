package com.acme.edu.logger;

import com.acme.edu.exeptions.LoggerExeption;
import com.acme.edu.exeptions.PrinterExeption;
import com.acme.edu.printers.ConsolPrinter;
import com.acme.edu.printers.FilePrinter;
import com.acme.edu.printers.Printer;
import com.acme.edu.states.State;
import com.acme.edu.states.StateInt;
import com.acme.edu.states.StateString;
import com.acme.edu.states.StateUnBuffered;

/**
 * log different values
 *
 * @author Alena Sharko
 */
public class Logger {

    private State state;
    private Printer[] printers = {new ConsolPrinter(), new FilePrinter()};

    // region Constants state
    /**
     * Integer state constant for comparation to swich state
     */
    public final State INT_STATE = new StateInt(printers);

    /**
     * String state constant for comparation to swich state
     */
    public final State STRING_STATE = new StateString(printers);

    /**
     * Default state constant for comparation to swich state
     */
    public final State UNBUFFERED_STATE = new StateUnBuffered(printers);
    //endregion

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
    public void log(int message) throws LoggerExeption {
        try {
            state = state.swichToNewState(INT_STATE);
            state.log(message + "");
        } catch (PrinterExeption ex) {
            new LoggerExeption(ex);
        }

    }

    /**
     * log string values
     * with decoration "string: "
     */
    public void log(String message) throws LoggerExeption {
        try {
            state = state.swichToNewState(STRING_STATE);
            state.log(message);
        } catch (PrinterExeption ex) {
            new LoggerExeption(ex);
        }

    }

    /**
     * log charecter numbers
     * with decoration - "char: "
     */
    public void log(char message) throws LoggerExeption {
        try {
            state = state.swichToNewState(UNBUFFERED_STATE);
            state.log(CHAR + message);
        } catch (PrinterExeption ex) {
            new LoggerExeption(ex);
        }

    }

    /**
     * print boolean value
     * with decoration - "primitive: "
     */
    public void log(boolean message) throws LoggerExeption {
        try {
            state = state.swichToNewState(UNBUFFERED_STATE);
            state.log(PRIMITIVE + message);
        } catch (PrinterExeption ex) {
            new LoggerExeption(ex);
        }

    }


    /**
     * log any objects
     * with decoration "reference: " and "@"
     */
    public void log(Object message) throws LoggerExeption {
        try {
            state = state.swichToNewState(UNBUFFERED_STATE);
            state.log(REFERENCE + AT + message.toString());
        } catch (PrinterExeption ex) {
            new LoggerExeption(ex);
        }

    }

    /**
     * log matrix size 2x2 in the console
     * with decoration "primitives matrix:"
     */
    public void log(int[][] matrix) throws LoggerExeption {
        StringBuilder array = new StringBuilder(" {\n");
        for (int[] xi : matrix) {
            array.append(makeOneString(xi)).append("\n");
        }
        array.append("}");

        try {
            state = state.swichToNewState(UNBUFFERED_STATE);
            state.log(PRIMITIVES_MATRIX + array);
        } catch (PrinterExeption ex) {
            new LoggerExeption(ex);
        }

    }

    /**
     * log any string values in the console
     * with decoration "primitives array: "
     */
    public void log(String... strings) throws LoggerExeption {
        StringBuilder array = new StringBuilder();
        for (String string : strings) {
            array.append(string).append("\n");
        }
        try {
            state = state.swichToNewState(UNBUFFERED_STATE);
            state.log(PRIMITIVE_ARRAY + array);
        } catch (PrinterExeption ex) {
            new LoggerExeption(ex);
        }

    }

    /**
     * log integer array in the console
     * with decoration "primitives array: "
     */
    public void log(int... nums) throws LoggerExeption {
        StringBuilder array = new StringBuilder("");
        array.append(makeOneString(nums));
        try {
            state = state.swichToNewState(UNBUFFERED_STATE);
            state.log(PRIMITIVE_ARRAY + array);
        } catch (PrinterExeption ex) {
            new LoggerExeption(ex);
        }

    }

    /**
     * log multimatrix - array of cubes in the console
     * with decoration "primitives multimatrix: "
     */
    public void log(int[][][][] array) throws LoggerExeption {
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
        try {
            state = state.swichToNewState(UNBUFFERED_STATE);
            state.log(PRIMITIVES_MULTI_MATRIX + multiMatr);
        } catch (PrinterExeption ex) {
            new LoggerExeption(ex);
        }

    }

    /**
     * log integer sum of last string value
     */
    public void close() throws LoggerExeption {
        try {
            state.flush();
        } catch (PrinterExeption ex) {
            new LoggerExeption(ex);
        }

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
