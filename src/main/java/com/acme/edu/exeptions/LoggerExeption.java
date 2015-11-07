package com.acme.edu.exeptions;

/**
 * will contains all exeprion in logger
 */
public class LoggerExeption extends Exception {

    private String clarification;
    private Throwable reason;

    /**
     * Constructor when we dont want tee reason
     *
     * @param clarification explains why was exeption
     */
    public LoggerExeption(String clarification) {
        this.clarification = clarification;
    }

    /**
     * Constructor when we know reason and want tell something about exeption
     *
     * @param clarification explains why was exeption
     * @param reason        extpains real caurse of exeption
     */
    public LoggerExeption(String clarification, Throwable reason) {
        this.clarification = clarification;
        this.reason = reason;
    }

    /**
     * Constructor when we know reason and dont want tell something about exeption
     *
     */
    public LoggerExeption(Throwable reason) {
        this.reason = reason;
    }
}
