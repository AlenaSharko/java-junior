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
    private int lastSiringNum;

    private Printer printer;

    /**
     * constructor
     */
    public StateString(Printer printer) {
        this.printer = printer;
    }
    /**
     * change state
     *
     * @return StateString if before was StateInt
     */
    @Override
    public State swichStateToIntState() {
        return this;
    }

    /**
     * change state
     *
     * @return StateInt if before was StateString
     */
    @Override
    public State swichStateToStringState() {
        if (bufflag) {
            flush();
        }
        return new StateInt(printer);
    }

    /**
     *
     * @param mes this paramert will be loged
     */
    @Override
    public void log(String mes) {
        if (bufflag) {
            if (findStringNum(mes) == lastSiringNum) {
                countString++;
            } else {
                flush();
            }
        }

        lastSiringNum = findStringNum(mes);
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


    private static int findStringNum(String message) {
        String[] arr = message.split(" ");
        return Integer.parseInt(arr[arr.length - 1]);
    }


}
