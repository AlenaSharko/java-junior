package com.acme.edu.logger;

import com.acme.edu.exeptions.*;
import com.acme.edu.printers.*;
import com.acme.edu.states.*;
import java.nio.charset.Charset;

/**
 * log different values
 *
 * @author Alena Sharko
 */
public class Logger {

    private State state;
    private Printer[] printers = {new ConsolPrinter(),
            new FilePrinter("ClientFile", Charset.defaultCharset()),
            new NetPrinter()};

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
    /**
     * Decoration - another line
     */
    public static final String SEP = System.lineSeparator();

    //endregion

    // region Explain constants
    /**
     * Constant explain that object which we want to log = null
     */
    public static final String NULL = "Null value";
    /**
     * Constant explain that message cant be print
     */
    public static final String UMPOSIPLE_PRIT = "Cant Print log";
    // endregion

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
            checkingForNull(message);
        }catch (NullValueExeption ex) {
            throw new LoggerExeption(NULL , ex);
        }
        try {
            state = state.swichToNewState(INT_STATE);
            state.log(message + "");
        } catch (PrinterExeption ex) {
            throw new LoggerExeption(UMPOSIPLE_PRIT , ex);
        }
    }

    /**
     * log string values
     * with decoration "string: "
     */
    public void log(String message) throws LoggerExeption {
        try {
            checkingForNull(message);
        }catch (NullValueExeption ex) {
            throw new LoggerExeption(NULL, ex);
        }
        try {
            state = state.swichToNewState(STRING_STATE);
            state.log(message);
        } catch (PrinterExeption ex) {
            throw new LoggerExeption(UMPOSIPLE_PRIT ,ex);
        }

    }

    /**
     * log charecter numbers
     * with decoration - "char: "
     */
    public void log(char message) throws LoggerExeption {
        try {
            checkingForNull(message);
        }catch (NullValueExeption ex) {
            throw new LoggerExeption(NULL , ex);
        }
        try {
            state = state.swichToNewState(UNBUFFERED_STATE);
            state.log(CHAR + message);
        } catch (PrinterExeption ex) {
            throw new LoggerExeption(UMPOSIPLE_PRIT,ex);
        }

    }

    /**
     * print boolean value
     * with decoration - "primitive: "
     */
    public void log(boolean message) throws LoggerExeption {
        try {
            checkingForNull(message);
        }catch (NullValueExeption ex) {
            throw new LoggerExeption(NULL , ex);
        }
        try {
            state = state.swichToNewState(UNBUFFERED_STATE);
            state.log(PRIMITIVE + message);
        } catch (PrinterExeption ex) {
            throw new LoggerExeption(UMPOSIPLE_PRIT,ex);
        }

    }


    /**
     * log any objects
     * with decoration "reference: " and "@"
     */
    public void log(Object message) throws LoggerExeption {
        try {
            checkingForNull(message);
        }catch (NullValueExeption ex) {
            throw new LoggerExeption(NULL , ex);
        }
        try {
            state = state.swichToNewState(UNBUFFERED_STATE);
            state.log(REFERENCE + AT + message.toString());
        } catch (PrinterExeption ex) {
            throw new LoggerExeption(UMPOSIPLE_PRIT,ex);
        }

    }

    /**
     * log matrix size 2x2 in the console
     * with decoration "primitives matrix:"
     */
    public void log(int[][] matrix) throws LoggerExeption {
        try {
            checkingForNull(matrix);
        }catch (NullValueExeption ex) {
            throw new LoggerExeption(NULL , ex);
        }
        StringBuilder array = new StringBuilder(" {" + SEP);
        for (int[] xi : matrix) {
            array.append(makeOneString(xi)).append(SEP);
        }
        array.append("}");

        try {
            state = state.swichToNewState(UNBUFFERED_STATE);
            state.log(PRIMITIVES_MATRIX + array);
        } catch (PrinterExeption ex) {
           throw  new LoggerExeption(UMPOSIPLE_PRIT,ex);
        }
    }

    /**
     * log any string values in the console
     * with decoration "primitives array: "
     */
    public void log(String... strings) throws LoggerExeption {
        try {
            checkingForNull(strings);
        }catch (NullValueExeption ex) {
           throw  new LoggerExeption(NULL , ex);
        }
        StringBuilder array = new StringBuilder();
        for (String string : strings) {
            array.append(string).append("\n");
        }
        try {
            state = state.swichToNewState(UNBUFFERED_STATE);
            state.log(PRIMITIVE_ARRAY + array);
        } catch (PrinterExeption ex) {
            throw new LoggerExeption(UMPOSIPLE_PRIT,ex);
        }
    }

    /**
     * log integer array in the console
     * with decoration "primitives array: "
     */
    public void log(int... nums) throws LoggerExeption {
        try {
            checkingForNull(nums);
        }catch (NullValueExeption ex) {
            throw new LoggerExeption(NULL , ex);
        }
        StringBuilder array = new StringBuilder("");
        array.append(makeOneString(nums));
        try {
            state = state.swichToNewState(UNBUFFERED_STATE);
            state.log(PRIMITIVE_ARRAY + array);
        } catch (PrinterExeption ex) {
            throw new LoggerExeption(UMPOSIPLE_PRIT,ex);
        }

    }

    /**
     * log multimatrix - array of cubes in the console
     * with decoration "primitives multimatrix: "
     */
    public void log(int[][][][] array) throws LoggerExeption {
        try {
            checkingForNull(array);
        }catch (NullValueExeption ex) {
            throw new LoggerExeption(NULL , ex);
        }
        StringBuilder multiMatr = new StringBuilder("{" + SEP);
        for (int[][][] cube : array) {
            multiMatr.append("{").append(SEP);
            for (int[][] rect : cube) {
                multiMatr.append("{").append(SEP);
                for (int[] line : rect) {
                    multiMatr.append("{").append(SEP);
                    for (int xi : line) {
                        multiMatr.append(xi).append(SEP);
                    }
                    multiMatr.append("}").append(SEP);
                }
                multiMatr.append("}").append(SEP);
            }
            multiMatr.append("}").append(SEP);
        }
        multiMatr.append("}");
        try {
            state = state.swichToNewState(UNBUFFERED_STATE);
            state.log(PRIMITIVES_MULTI_MATRIX + multiMatr);
        } catch (PrinterExeption ex) {
            throw new LoggerExeption(UMPOSIPLE_PRIT,ex);
        }

    }

    /**
     * log integer sum of last string value
     */
    public void close() throws LoggerExeption {
        try {
            state.flush();
        } catch (PrinterExeption ex) {
            throw new LoggerExeption(UMPOSIPLE_PRIT,ex);
        }

    }

    private String makeOneString(int[] arr) {
        StringBuilder oneString = new StringBuilder("{");
        for (int i = 0; i < arr.length - 1; i++) {
            oneString.append(arr[i]).append(", ");
        }
        oneString.append(arr[arr.length - 1]).append("}");
        return oneString.toString();
    }

    private void checkingForNull(Object message) throws NullValueExeption{
        if(message == null) {
            throw new NullValueExeption(NULL);
        }
    }
}
