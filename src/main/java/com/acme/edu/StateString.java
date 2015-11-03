package com.acme.edu;

/**
 * log string values
 *
 * @author Alena Sharko
 */
public class StateString extends State {


    private static String stringBuf;
    private static boolean bufflag;
    private static int countString = 1;
    private static int lastChar;

    /**
     * change state
     *
     * @return StateString if before was StateInt
     */
    @Override
    public State swichStateToIntState() {
        return new StateString();
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
        return new StateInt();
    }

    /**
     *
     * @param mes this paramert will be loged
     */
    @Override
    public void log(String mes) {
        if (bufflag) {
            if (findStringNum(mes) == lastChar) {
                countString++;
            } else {
                flush();
            }
        }

        lastChar = findStringNum(mes);
        stringBuf = mes;
        bufflag = true;

    }

    /**
     * drop prepared stirng to print
     */
    @Override
    public void flush() {

        if (countString == 1) {
            print(Logger.STR + stringBuf);
        } else {
            print(Logger.STR + stringBuf + " " + Logger.X + countString + ")");
        }
        countString = 1;
        bufflag = false;

    }


    private static int findStringNum(String message) {
        String[] arr = message.split(" ");
        return Integer.parseInt(arr[arr.length - 1]);
    }


}
