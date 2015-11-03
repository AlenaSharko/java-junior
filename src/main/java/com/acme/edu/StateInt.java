package com.acme.edu;

/**
 * log integer values
 *
 * @author Alena Sharko
 */
public class StateInt extends State {

    private static long intBuf = 0;
    private static boolean bufFlag;


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
        return new StateString();
    }

    /**
     * change state
     *
     * @return StateInt
     */
    @Override
    public State swichStateToStringState() {
        return new StateInt();
    }

    /**
     *
     * @param mes this paramert will be loged
     */
    @Override
    public void log(String mes) {
        int message = Integer.parseInt(mes);
        if (message == Integer.MAX_VALUE) {
            reactToMaxValue(message);
            return;
        }

        intBuf += message;

        if(intBuf > Integer.MAX_VALUE) {
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
        print(Logger.PRIMITIVE + intBuf);
        intBuf = 0;
        bufFlag = false;
    }

    private void reactToMaxValue(int message) {
        flush();
        print(Logger.PRIMITIVE + message);
    }


}


