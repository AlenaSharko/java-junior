package com.acme.edu.exeptions;

/**
 * will contains all exeprion in logger
 */
public class LoggerExeption extends OverallExeption {
    
    /**
     * Constructor when we dont want tee reason
     *
     * @param clarification explains why was exeption
     */
    public LoggerExeption(String clarification) {
        super(clarification);
    }

    /**
     * Constructor when we know reason and want tell something about exeption
     *
     * @param clarification explains why was exeption
     * @param reason        extpains real caurse of exeption
     */
    public LoggerExeption(String clarification, Throwable reason) {
        super(clarification, reason);
    }

    /**
     * Constructor when we know reason and dont want tell something about exeption
     *
     */
    public LoggerExeption(Throwable reason) {
        super(reason);
    }
}
