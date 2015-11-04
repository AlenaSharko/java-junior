package com.acme.edu;

/**
 * Default state befoe any challenges
 *
 * @author Alena Sharko
 */
public class StateDefault extends State {

    private Printer printer;

    public StateDefault(Printer printer) {
        this.printer = printer;
    }
    /**
     * change state
     *
     * @return StateString if before was StateInt
     */
    @Override
    public State swichStateToIntState() {
        return new StateString(printer);
    }

    /**
     * change state
     *
     * @return StateInt if before was StateString
     */
    @Override
    public State swichStateToStringState() {
        return new StateInt(printer);
    }

    /**
     *
     * @param mes this paramert will be loged
     */
    @Override
    public void log(String mes) {
        printer.print(mes);
    }

    /**
     * dummy
     */
    @Override
    public void flush() {

    }



}
