package com.acme.edu.States;

import com.acme.edu.Printers.Printer;
import com.acme.edu.States.State;

/**
 * Default state befoe any challenges
 *
 * @author Alena Sharko
 */
public class StateUnBuffered extends State {

    private Printer printer;

    /**
     * constructor
     */
    public StateUnBuffered(Printer printer) {
        this.printer = printer;
    }

    /**
     *
     * @param mes this paramert will be loged
     */
    @Override
    public void log(String mes) {
        printer.print(mes);
    }

    /**
     * dummy
     */
    @Override
    public void flush() {

    }



}
