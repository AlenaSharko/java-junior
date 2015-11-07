package com.acme.edu.states;

import com.acme.edu.exeptions.PrinterExeption;
import com.acme.edu.printers.Printer;

/**
 * Default state befoe any challenges
 *
 * @author Alena Sharko
 */
public class StateUnBuffered extends State {

    private Printer[] printers;

    /**
     * constructor
     */
    public StateUnBuffered(Printer... printers) {
        this.printers = printers;
    }

    /**
     * @param mes this paramert will be loged
     */
    @Override
    public void log(String mes) throws PrinterExeption {
        for (Printer curPrinter : printers) {
            curPrinter.print(mes);
        }
    }

    /**
     * dummy
     */
    @Override
    public void flush() {

    }


}
