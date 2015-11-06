package com.acme.edu.unit;

import com.acme.edu.Printers.ConsolPrinter;
import com.acme.edu.Printers.Printer;
import com.acme.edu.States.StateInt;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Testing state int values
 */
public class StateIntTest {
    StateInt state;
    Printer printer;


    @Before
    public void setUp() {
        printer = mock(ConsolPrinter.class);
        state = new StateInt(printer);
    }

    @Test
    public void shouldCallPrinterOneTimeWhenOneIntegerValueCome() {

        state.log("1");
        state.flush();

        verify(printer, times(1)).print("primitive: 1");

    }

    @Test
    public void shouldCallPrinterOneTimeForSumWhenSomeIntegerValuesCome() {

        state.log("1");
        state.log("1");
        state.log("1");
        state.flush();

        verify(printer).print("primitive: 3");
    }

    @Test
    public void shouldCallPrinterForPrintMaxValueAndCurrentValueWhenMaxValueCome() {


        state.log("1");
        state.log("" + Integer.MAX_VALUE);
        state.flush();

        verify(printer).print("primitive: 1");
        verify(printer).print("primitive: " + Integer.MAX_VALUE);

    }

    @Test
    public void shouldCallPrinterForPrintMinValueAndCurrentValueWhenMinValueCome() {
        state.log("1");
        state.log("" + Integer.MIN_VALUE);
        state.flush();

        verify(printer).print("primitive: 1");
        verify(printer).print("primitive: " + Integer.MIN_VALUE);
    }

    @Test
    public void shouldCallPrinterForPrintMinValueAndSumWhenMinValueCome() {
        state.log("1");
        state.log("1");
        state.log("" + Integer.MIN_VALUE);
        state.flush();

        verify(printer).print("primitive: 2");
        verify(printer).print("primitive: " + Integer.MIN_VALUE);
    }

    @Test
    public void shouldCallPrinterForPrintMaxValueAndSumWhenMaxValueCome() {
        state.log("1");
        state.log("1");
        state.log("" + Integer.MAX_VALUE);
        state.flush();

        verify(printer).print("primitive: 2");
        verify(printer).print("primitive: " + Integer.MAX_VALUE);
    }

    @Test
    public void shouldCallPrinterForPrintCurrentValueWhenIntegerOverFlow() {
        state.log("1");
        state.log("2");
        state.log("" + (Integer.MAX_VALUE - 2));
        state.flush();

        verify(printer).print("primitive: 3");
        verify(printer).print("primitive: " + (Integer.MAX_VALUE - 2));
    }

//    @Test
//    public void shouldSaveState() {
//        assertEquals(state, state.swichStateToStringState());
//    }


}
