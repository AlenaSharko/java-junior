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
    abstract void log(String mes);

    /**
     * drop prepared stirng to print
     */
    abstract void flush();

    /**
     * change state
     *
     * @return StateString if before was StateInt
     */
    abstract State swichStateToIntState();

    /**
     * changestate
     *
     * @return StateInt if before was StateString
     */
    abstract State swichStateToStringState();

}
