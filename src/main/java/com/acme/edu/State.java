package com.acme.edu;

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
    public abstract void log(String mes);

    /**
     * drop prepared stirng to print
     */
    public abstract void flush();



    public State swichToNewState(State state) {
        if(this == state) {
            return this;
        } else {
            this.flush();
            return state;
        }
    }
}
