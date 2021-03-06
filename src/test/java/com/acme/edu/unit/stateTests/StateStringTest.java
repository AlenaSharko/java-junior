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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Testing state string values
 */
public class StateStringTest {
    StateString state;
    Printer printer;

    @Before
    public void setUp() {
        printer = mock(ConsolPrinter.class);
        state = new StateString(printer);
    }


    @Test
    public void shouldCallPrinterOneTimeWhenStringCome() throws PrinterExeption{

        state.log("string 1");
        state.flush();
        verify(printer, times(1)).print("string: string 1");
    }

    @Test
    public void shouldCallPrinterTwoTimesWhenTwoDifferentStringsCome()throws PrinterExeption {

        state.log("string 1");
        state.log("string 2");
        state.flush();
        verify(printer, times(1)).print("string: string 1");
        verify(printer, times(1)).print("string: string 2");
    }

    @Test
    public void shouldCallPrinterOneTimesWhenTwoSameStringsCome() throws PrinterExeption{

        state.log("string 1");
        state.log("string 1");
        state.flush();
        verify(printer, times(1)).print("string: string 1 (x2)");

    }



    @Test
    public void shouldntChengeStatw() throws PrinterExeption {

        Assert.assertEquals(state, state.swichToNewState(state));

    }

    @Test
    public void shouldSwichToIntState() throws PrinterExeption {
        State state = mock(StateInt.class);
        when(state.swichToNewState(any())).thenReturn(state);
        assertEquals(state, this.state.swichToNewState(state));
    }









}
