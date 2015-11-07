package com.acme.edu.states;

import com.acme.edu.exeptions.PrinterExeption;

/**
 * switch states depending on the input parameter
 *
 * @author Alena Sharko
 */
public abstract class State {
    /**
     * prepare message for print
     *
     * @param mes this paramert will be loged
     */
    public abstract void log(String mes) throws PrinterExeption;

    /**
     * drop prepared stirng to print
     */
    public abstract void flush() throws PrinterExeption;


    /**
     * Swich states
     *
     * @param state current state
     * @return current state if previous state was same and another state if not
     */
    public State swichToNewState(State state) throws PrinterExeption {
        if (this == state) {
            return this;
        } else {
            this.flush();
            return state;
        }
    }
}
