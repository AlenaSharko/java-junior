package com.acme.edu.states;

import com.acme.edu.exeptions.PrinterExeption;
import com.acme.edu.logger.Logger;
import com.acme.edu.printers.Printer;

/**
 * log string values
 *
 * @author Alena Sharko
 */
public class StateString extends State {


    private String stringBuf;
    private boolean bufflag;
    private int countString = 1;
    private String lastSiring;

    private Printer[] printers;

    /**
     * constructor
     */
    public StateString(Printer... printers) {
        this.printers = printers;
    }

    /**
     * @param mes this paramert will be loged
     */
    @Override
    public void log(String mes) throws PrinterExeption {
        if (bufflag) {
            if (mes.equals(lastSiring)) {
                countString++;
            } else {
                flush();
            }
        }

        lastSiring = mes;
        stringBuf = mes;
        bufflag = true;

    }

    /**
     * drop prepared stirng to print
     */
    @Override
    public void flush() throws PrinterExeption {
        for (Printer curPrinter : printers) {
            if (countString == 1) {
                curPrinter.print(Logger.STR + stringBuf);
            } else {
                curPrinter.print(Logger.STR + stringBuf + " " + Logger.X + countString + ")");
            }
        }


        countString = 1;
        bufflag = false;

    }

}
