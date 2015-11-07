package com.acme.edu.printers;

/**
 * Print message in the console
 *
 * @author Alena Sharko
 */
public class ConsolPrinter implements Printer {

    @Override
    public void print(String message) {
        System.out.println(message);
    }
}