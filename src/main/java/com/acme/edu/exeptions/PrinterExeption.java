package com.acme.edu.exeptions;

/**
 * Called when some problems with printers
 */
public class PrinterExeption extends Exception {

    private String clarification;
    private Throwable reason;

    /**
     * Constructor when we dont want tee reason
     *
     * @param clarification explains why was exeption
     */
    public PrinterExeption(String clarification) {
        this.clarification = clarification;
    }

    /**
     * Constructor when we know reason and want tell something about exeption
     *
     * @param clarification explains why was exeption
     * @param reason        extpains real caurse of exeption
     */
    public PrinterExeption(String clarification, Throwable reason) {
        this.clarification = clarification;
        this.reason = reason;
    }

    /**
     * Constructor when we know reason and dont want tell something about exeption
     *
     * @param reason
     */
    public PrinterExeption(Throwable reason) {
        this.reason = reason;
    }
}
