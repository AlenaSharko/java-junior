package com.acme.edu;

/**
 * log integer values
 *
 * @author Alena Sharko
 */
public class StateInt extends State {

    private long intBuf = 0;
    private boolean bufFlag;
    private Printer printer;

    /**
     * constructor
     */
    public StateInt(Printer printer) {
        this.printer = printer;
    }


    /**
     * change state
     *
     * @return StateString
     */
    @Override
    public State swichStateToIntState() {
        if (bufFlag) {
            flush();
        }
        return new StateString(printer);
    }

    /**
     * change state
     *
     * @return StateInt
     */
    @Override
    public State swichStateToStringState() {
        return this;
    }

    /**
     * @param mes this paramert will be loged
     */
    @Override
    public void log(String mes) {
        int message = Integer.parseInt(mes);
        if (message == Integer.MAX_VALUE || message == Integer.MIN_VALUE) {
            flush();
            intBuf = message;
            bufFlag = true;
            return;
        }

        intBuf += message;

        if (intBuf > Integer.MAX_VALUE) {
            intBuf -= message;
            flush();
            intBuf = message;
            bufFlag = true;

        }
        bufFlag = true;
    }

    /**
     * rop prepared stirng to print
     */
    @Override
    public void flush() {
        printer.print(Logger.PRIMITIVE + intBuf);
        intBuf = 0;
        bufFlag = false;
    }



}


