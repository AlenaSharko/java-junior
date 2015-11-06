package com.acme.edu.Printers;

/**
 * Print message in the console
 *
 * @author Alena Sharko
 */
class ConsolPrinter implements Printer {

    @Override
    public void print(String message) {
        System.out.println(message);
    }
}