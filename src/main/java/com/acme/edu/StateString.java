package com.acme.edu;

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

    private Printer printer;

    /**
     * constructor
     */
    public StateString(Printer printer) {
        this.printer = printer;
    }

    /**
     *
     * @param mes this paramert will be loged
     */
    @Override
    public void log(String mes) {
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
    public void flush() {

        if (countString == 1) {
            printer.print(Logger.STR + stringBuf);
        } else {
            printer.print(Logger.STR + stringBuf + " " + Logger.X + countString + ")");
        }
        countString = 1;
        bufflag = false;

    }

}
