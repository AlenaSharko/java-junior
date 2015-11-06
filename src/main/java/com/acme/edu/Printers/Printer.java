package com.acme.edu.Printers;

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
    void print(String message);

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
}
