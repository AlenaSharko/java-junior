package com.acme.edu.printers;

import com.acme.edu.logger.Logger;

/**
 * Print message in the console
 *
 * @author Alena Sharko
 */
public class ConsolPrinter implements Printer {

    @Override
    public void print(String message) {
        System.out.print(message + Logger.SEP);
    }
}