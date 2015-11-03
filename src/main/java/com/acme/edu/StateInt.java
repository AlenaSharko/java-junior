package com.acme.edu;

/**
 * log integer values
 *
 * @author Alena Sharko
 */
public class StateInt extends State {

    private static long intBuf = 0;
    private static boolean bufFlag;
    //private static int predBuf = 0;
    //private static boolean predBufFlush;


    @Override
    public State swichStateToIntState() {
        if (bufFlag) {
            flush();
        }
        return new StateString();
    }

    @Override
    public State swichStateToStringState() {
        return new StateInt();
    }

    @Override
    public void log(String mes) {
        int message = Integer.parseInt(mes);
        if (message == Integer.MAX_VALUE) {
            reactToMaxValue(message);
            return;
        }
        //predBuf = intBuf;
        intBuf += message;

        if(intBuf > Integer.MAX_VALUE) {
            intBuf -= message;
            flush();
            intBuf = message;
            bufFlag = true;

        }
        bufFlag = true;
    }

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


