package com.acme.edu.exeptions;

/**
 * Called whrn we have null value
 */
public class NullValueExeption extends Exception {

    /**
     * Constructor when we dont want tee reason
     *
     * @param clarification explains why was exeption
     */
    public NullValueExeption(String clarification) {
        super(clarification);
    }

    /**
     * Constructor when we know reason and want tell something about exeption
     *
     * @param clarification explains why was exeption
     * @param reason        extpains real caurse of exeption
     */
    public NullValueExeption(String clarification, Throwable reason) {
        super(clarification, reason);
    }

    /**
     * Constructor when we know reason and dont want tell something about exeption
     *
     */
    public NullValueExeption(Throwable reason) {
        super(reason);
    }
}
