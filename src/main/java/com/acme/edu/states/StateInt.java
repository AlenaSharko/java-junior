package com.acme.edu.states;

import com.acme.edu.exeptions.PrinterExeption;
import com.acme.edu.logger.Logger;
import com.acme.edu.printers.Printer;

/**
 * log integer values
 *
 * @author Alena Sharko
 */
public class StateInt extends State {

    private long intBuf = 0;
    private Printer[] printers;

    /**
     * constructor
     */
    public StateInt(Printer... printers) {
        this.printers = printers;
    }

    /**
     * @param mes this paramert will be loged
     */
    @Override
    public void log(String mes) throws PrinterExeption {
        int message = Integer.parseInt(mes);
        if (message == Integer.MAX_VALUE || message == Integer.MIN_VALUE) {
            flush();
            intBuf = message;
            return;
        }

        intBuf += message;

        if (intBuf > Integer.MAX_VALUE || intBuf < Integer.MIN_VALUE) {
            intBuf -= message;
            flush();
            intBuf = message;

        }
    }

    /**
     * rop prepared stirng to print
     */
    @Override
    public void flush() throws PrinterExeption {
        for (Printer currentPrinter : printers) {
            currentPrinter.print(Logger.PRIMITIVE + intBuf);
        }
        intBuf = 0;
    }


}


