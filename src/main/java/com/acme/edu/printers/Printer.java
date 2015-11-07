package com.acme.edu.printers;

import com.acme.edu.exeptions.PrinterExeption;

/**
 * Print any messages
 * will have many realization
 *
 * @author Alena Sharko
 */
public interface Printer {

    /**
     * print string messages
     */
    void print(String message) throws PrinterExeption;


}
