package com.acme.edu.exeptions;

/**
 * Abstact class for all exeptions
 */
public class OverallExeption extends Exception {


    private String clarification;
    private Throwable reason;

    /**
     * Constructor when we dont want tee reason
     *
     * @param clarification explains why was exeption
     */
    public OverallExeption(String clarification) {
        this.clarification = clarification;
    }

    /**
     * Constructor when we know reason and want tell something about exeption
     *
     * @param clarification explains why was exeption
     * @param reason        extpains real caurse of exeption
     */
    public OverallExeption(String clarification, Throwable reason) {
        this.clarification = clarification;
        this.reason = reason;
    }

    /**
     * Constructor when we know reason and dont want tell something about exeption
     *
     */
    public OverallExeption(Throwable reason) {
        this.reason = reason;
    }

}
