package com.acme.edu;

/**
 * Default state befoe any challenges
 *
 * @author Alena Sharko
 */
public class StateDefault extends State {
    /**
     * change state
     *
     * @return StateString if before was StateInt
     */
    @Override
    public State swichStateToIntState() {
        return new StateString();
    }

    /**
     * change state
     *
     * @return StateInt if before was StateString
     */
    @Override
    public State swichStateToStringState() {
        return new StateInt();
    }

    public StateDefault(){

    }

    /**
     *
     * @param mes this paramert will be loged
     */
    @Override
    public void log(String mes) {
        print(mes);
    }

    /**
     * dummy
     */
    @Override
    public void flush() {

    }



}
