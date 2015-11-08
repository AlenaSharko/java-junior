package com.acme.edu.unit.stateTests;

import com.acme.edu.exeptions.PrinterExeption;
import com.acme.edu.printers.ConsolPrinter;
import com.acme.edu.printers.Printer;
import com.acme.edu.states.State;
import com.acme.edu.states.StateInt;
import com.acme.edu.states.StateString;
import org.junit.Assert;
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
    public void shouldCallPrinterOneTimeWhenOneIntegerValueCome() throws PrinterExeption {

        state.log("1");
        state.flush();

        verify(printer, times(1)).print("primitive: 1");

    }

    @Test
    public void shouldCallPrinterOneTimeForSumWhenSomeIntegerValuesCome() throws PrinterExeption {

        state.log("1");
        state.log("1");
        state.log("1");
        state.flush();

        verify(printer).print("primitive: 3");
    }

    @Test
    public void shouldCallPrinterForPrintMaxValueAndCurrentValueWhenMaxValueCome() throws PrinterExeption {


        state.log("1");
        state.log("" + Integer.MAX_VALUE);
        state.flush();

        verify(printer).print("primitive: 1");
        verify(printer).print("primitive: " + Integer.MAX_VALUE);

    }

    @Test
    public void shouldCallPrinterForPrintMinValueAndCurrentValueWhenMinValueOverfow() throws PrinterExeption {

        state.log("" + Integer.MIN_VALUE);
        state.log("-10");
        state.flush();

        verify(printer).print("primitive: " + Integer.MIN_VALUE);
        verify(printer).print("primitive: -10");


    }

    @Test
    public void shouldCallPrinterForPrintMinValueAndCurrentValueWhenMinValueCome() throws PrinterExeption {
        state.log("1");
        state.log("" + Integer.MIN_VALUE);
        state.flush();

        verify(printer).print("primitive: 1");
        verify(printer).print("primitive: " + Integer.MIN_VALUE);
    }

    @Test
    public void shouldCallPrinterForPrintMinValueAndSumWhenMinValueCome() throws PrinterExeption {
        state.log("1");
        state.log("1");
        state.log("" + Integer.MIN_VALUE);
        state.flush();

        verify(printer).print("primitive: 2");
        verify(printer).print("primitive: " + Integer.MIN_VALUE);
    }

    @Test
    public void shouldCallPrinterForPrintMaxValueAndSumWhenMaxValueCome() throws PrinterExeption {
        state.log("1");
        state.log("1");
        state.log("" + Integer.MAX_VALUE);
        state.flush();

        verify(printer).print("primitive: 2");
        verify(printer).print("primitive: " + Integer.MAX_VALUE);
    }

    @Test
    public void shouldCallPrinterForPrintCurrentValueWhenIntegerOverFlow() throws PrinterExeption {
        state.log("1");
        state.log("2");
        state.log("" + (Integer.MAX_VALUE - 2));
        state.flush();

        verify(printer).print("primitive: 3");
        verify(printer).print("primitive: " + (Integer.MAX_VALUE - 2));
    }

    @Test
    public void shouldntChengeState() throws PrinterExeption {

        assertEquals(state, state.swichToNewState(state));

    }

    @Test
    public void shouldSwichToStringState() throws PrinterExeption {
        State state = mock(StateString.class);
        when(state.swichToNewState(any())).thenReturn(state);
        assertEquals(state, this.state.swichToNewState(state));
    }
}
