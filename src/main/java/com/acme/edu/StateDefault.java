package com.acme.edu;

/**
 * Default state befoe any challenges
 *
 * @author Alena Sharko
 */
public class StateDefault extends State {

    @Override
    public State swichStateToIntState() {
        return new StateString();
    }

    @Override
    public State swichStateToStringState() {
        return new StateInt();
    }

    StateDefault(){

    }

    @Override
    public void log(String mes) {
        print(mes);
    }

    @Override
    public void flush() {

    }



}
