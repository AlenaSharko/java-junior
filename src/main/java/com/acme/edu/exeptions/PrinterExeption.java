package com.acme.edu.exeptions;

/**
 * Called when some problems with printers
 */
public class PrinterExeption extends OverallExeption {

    /**
     * Constructor withoutparametrs
     */
    public PrinterExeption() {

    }

    /**
     * Constructor when we dont want tee reason
     *
     * @param clarification explains why was exeption
     */
    public PrinterExeption(String clarification) {
        super(clarification);
    }

    /**
     * Constructor when we know reason and want tell something about exeption
     *
     * @param clarification explains why was exeption
     * @param reason        extpains real caurse of exeption
     */
    public PrinterExeption(String clarification, Throwable reason) {
        super(clarification,reason);
    }

    /**
     * Constructor when we know reason and dont want tell something about exeption
     *
     */
    public PrinterExeption(Throwable reason) {
        super(reason);
    }
}
