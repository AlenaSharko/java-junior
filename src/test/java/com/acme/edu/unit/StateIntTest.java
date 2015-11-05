package com.acme.edu.unit;

import com.acme.edu.ConsolPrinter;
import com.acme.edu.Printer;
import com.acme.edu.StateInt;
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

        state.log("10");
        state.flush();

        verify(printer, times(1)).print("primitive: 10");

    }

    @Test
    public void shouldCallPrinterOneTimeForSumWhenSomeIntegerValuesCome() {

        state.log("1");
        state.log("3");
        state.log("-5");
        state.flush();

        verify(printer).print("primitive: -1");
    }

    @Test
    public void shouldCallPrinterForPrintMaxValueAndCurrentValueWhenMaxValueCome() {


        state.log("30");
        state.log("" + Integer.MAX_VALUE);
        state.flush();

        verify(printer).print("primitive: 30");
        verify(printer).print("primitive: " + Integer.MAX_VALUE);

    }

    @Test
    public void shouldCallPrinterForPrintMinValueAndCurrentValueWhenMinValueCome() {
        state.log("5");
        state.log("" + Integer.MIN_VALUE);
        state.flush();

        verify(printer).print("primitive: 5");
        verify(printer).print("primitive: " + Integer.MIN_VALUE);
    }

    @Test
    public void shouldCallPrinterForPrintMinValueAndSumWhenMinValueCome() {
        state.log("5");
        state.log("3");
        state.log("" + Integer.MIN_VALUE);
        state.flush();

        verify(printer).print("primitive: 8");
        verify(printer).print("primitive: " + Integer.MIN_VALUE);
    }

    @Test
    public void shouldCallPrinterForPrintMaxValueAndSumWhenMaxValueCome() {
        state.log("5");
        state.log("3");
        state.log("" + Integer.MAX_VALUE);
        state.flush();

        verify(printer).print("primitive: 8");
        verify(printer).print("primitive: " + Integer.MAX_VALUE);
    }

    @Test
    public void shouldCallPrinterForPrintCurrentValueWhenIntegerOverFlow() {
        state.log("5");
        state.log("3");
        state.log("" + (Integer.MAX_VALUE - 2));
        state.flush();

        verify(printer).print("primitive: 8");
        verify(printer).print("primitive: " + (Integer.MAX_VALUE - 2));
    }

    @Test
    public void shouldSaveState() {
        assertEquals(state, state.swichStateToStringState());
    }


}
